package ru.javawebinar.topjava.storage;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface MealStorage {
    Meal save(Meal meal);

    Meal update(Meal meal);

    Meal get(Integer id);

    List<Meal> getAll();

    void delete(Integer id);
}
