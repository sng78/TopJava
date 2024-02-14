package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealTo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.assureIdConsistent;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNew;
import static ru.javawebinar.topjava.web.SecurityUtil.authUserCaloriesPerDay;
import static ru.javawebinar.topjava.web.SecurityUtil.authUserId;

@Controller
public class MealRestController {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final MealService service;

    public MealRestController(MealService service) {
        this.service = service;
    }

    public Meal get(int mealId) {
        int userId = authUserId();
        log.info("get meal id {} user id {}", mealId, userId);
        return service.get(mealId, userId);
    }

    public Meal create(Meal meal) {
        int userId = authUserId();
        log.info("New meal {} for user id {}", meal, userId);
        checkNew(meal);
        return service.create(meal, userId);
    }

    public void delete(int mealId) {
        int userId = authUserId();
        log.info("Delete meal id {} user id {}", mealId, userId);
        service.delete(mealId, userId);
    }

    public void update(Meal meal, int mealId) {
        int userId = authUserId();
        log.info("Update meal {} user id {}", meal, userId);
        assureIdConsistent(meal, mealId);
        service.update(meal, userId);
    }

    public List<MealTo> getAll() {
        int userId = authUserId();
        log.info("Get all meals user id {}", userId);
        return service.getAll(userId, authUserCaloriesPerDay());
    }

    public List<MealTo> getFiltered(LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime) {
        int userId = authUserId();
        log.info("Get filtered meals user id {}", userId);
        return service.getFiltered(userId, startDate, startTime, endDate, endTime, authUserCaloriesPerDay());
    }
}
