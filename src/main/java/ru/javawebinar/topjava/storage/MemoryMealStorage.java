package ru.javawebinar.topjava.storage;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

public class MemoryMealStorage implements MealStorage {
    protected List<Meal> meals = Arrays.asList(
            new Meal(1, LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
            new Meal(2, LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
            new Meal(3, LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
            new Meal(4, LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
            new Meal(5, LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
            new Meal(6, LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
            new Meal(7, LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410)
    );

    @Override///
    public Meal save(Meal meal) {
        meals.add(meal);
        return meal;
    }

    @Override
    public Meal update(Meal meal) {
        save(meal);
        return meal;
    }

    @Override///
    public Meal get(Integer id) {
        return meals.stream().filter(meal -> meal.getId().equals(id)).findFirst().orElse(null);
    }

    @Override///
    public List<Meal> getAll() {
        return meals;
    }

    @Override///
    public void delete(Integer id) {
        meals.removeIf(meal -> meal.getId().equals(id));
    }
}
