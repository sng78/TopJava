package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.storage.MealStorage;
import ru.javawebinar.topjava.storage.MemoryMealStorage;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import static org.slf4j.LoggerFactory.getLogger;
import static ru.javawebinar.topjava.util.MealsUtil.CALORIES_PER_DAY;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);
    private MealStorage storage;

    public void init() {
        log.debug("init");

        storage = new MemoryMealStorage();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        String id;
        switch (action == null ? "" : action) {
            case "delete":
                id = request.getParameter("id");
                storage.delete(Integer.parseInt(id));
                response.sendRedirect("meals");
                log.debug("meal deleted");
                break;
            case "update":
            case "add":
                id = request.getParameter("id");
                Meal meal = "add".equals(action) ? new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES),"", 0) :
                        storage.get(Integer.parseInt(id));
                request.setAttribute("meal", meal);
                request.getRequestDispatcher("/meal.jsp").forward(request, response);
                log.debug("meal edited");
                break;
            default:
                request.setAttribute("meals",
                        MealsUtil.filteredByStreams(storage.getAll(), LocalTime.MIN, LocalTime.MAX, CALORIES_PER_DAY));
                request.getRequestDispatcher("meals.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        LocalDateTime dateTime = LocalDateTime.parse(request.getParameter("dateTime"));
        String description = request.getParameter("description");
        int calories = Integer.parseInt(request.getParameter("calories"));
        String id = (request.getParameter("id"));
        Meal meal = new Meal(id == null || id.isEmpty() ? null : Integer.parseInt(id), dateTime, description, calories);
        log.debug("doPost meal");
        storage.save(meal);
        response.sendRedirect("meals");
    }
}
