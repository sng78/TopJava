package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.web.meal.MealRestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class MealServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(MealServlet.class);
    private ConfigurableApplicationContext appCtx;
    private MealRestController mealController;

    @Override
    public void init() {
        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml")) {
            this.appCtx = appCtx;
            mealController = appCtx.getBean(MealRestController.class);
        }
    }

    @Override
    public void destroy() {
        appCtx.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        String filter = request.getParameter("filter");
        switch (filter == null ? "all" : filter) {
            case "all":
                String id = request.getParameter("id");
                Meal meal = new Meal(id.isEmpty() ? null : Integer.valueOf(id),
                        LocalDateTime.parse(request.getParameter("dateTime")),
                        request.getParameter("description"),
                        Integer.parseInt(request.getParameter("calories")));
                if (meal.isNew()) {
                    log.info("Create {}", meal);
                    mealController.create(meal);
                } else {
                    log.info("Update {}", meal);
                    mealController.update(meal, meal.getId());
                }
            case "filtered":
                HttpSession session = request.getSession();
                session.setAttribute("filter", filter);
                session.setAttribute("startDate", request.getParameter("startDate"));
                session.setAttribute("endDate", request.getParameter("endDate"));
                session.setAttribute("startTime", request.getParameter("startTime"));
                session.setAttribute("endTime", request.getParameter("endTime"));
        }
        response.sendRedirect("meals");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action == null ? "all" : action) {
            case "delete":
                int id = getId(request);
                log.info("Delete id={}", id);
                mealController.delete(id);
                response.sendRedirect("meals");
                break;
            case "create":
            case "update":
                final Meal meal = "create".equals(action) ?
                        new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", 1000) :
                        mealController.get(getId(request));
                request.setAttribute("meal", meal);
                request.getRequestDispatcher("/mealForm.jsp").forward(request, response);
                break;
            case "all":
            default:
                log.info("getAll");
                HttpSession session = request.getSession();
                if (session.getAttribute("filter") == null) {
                    request.setAttribute("meals", mealController.getAll());
                } else {
                    String startDate = (String) session.getAttribute("startDate");
                    String startTime = (String) session.getAttribute("startTime");
                    String endDate = (String) session.getAttribute("endDate");
                    String endTime = (String) session.getAttribute("endTime");
                    request.setAttribute("meals", mealController.getFiltered(
                            startDate.isEmpty() ? null : LocalDate.parse(startDate),
                            startTime.isEmpty() ? null : LocalTime.parse(startTime),
                            endDate.isEmpty() ? null : LocalDate.parse(endDate),
                            endTime.isEmpty() ? null : LocalTime.parse(endTime)));
                }
                request.getRequestDispatcher("/meals.jsp").forward(request, response);
                break;
        }
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.parseInt(paramId);
    }
}
