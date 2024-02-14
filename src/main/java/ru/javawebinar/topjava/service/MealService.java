package ru.javawebinar.topjava.service;

import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static ru.javawebinar.topjava.util.DateTimeUtil.getEndDate;
import static ru.javawebinar.topjava.util.DateTimeUtil.getStartDate;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MealService {
    private final MealRepository mealRepository;

    public MealService(MealRepository mealRepository) {
        this.mealRepository = mealRepository;
    }

    public Meal get(int mealId, int userId) {
        return checkNotFoundWithId(mealRepository.get(mealId, userId), mealId);
    }

    public Meal create(Meal meal, int userId) {
        return mealRepository.save(meal, userId);
    }

    public void delete(int mealId, int userId) {
        checkNotFoundWithId(mealRepository.delete(mealId, userId), mealId);
    }

    public void update(Meal meal, int userId) {
        checkNotFoundWithId(mealRepository.save(meal, userId), meal.getId());
    }

    public List<MealTo> getAll(int userId, int calories) {
        return MealsUtil.getTos(mealRepository.getAll(userId), calories);
    }

    public List<MealTo> getFiltered(
            int userId, LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime, int calories) {
        List<Meal> filteredMeals = mealRepository.getFiltered(userId, getStartDate(startDate), getEndDate(endDate));
        return MealsUtil.getFilteredTos(filteredMeals, calories, startTime, endTime);
    }
}
