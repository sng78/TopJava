package ru.javawebinar.topjava.web.meal;

import ru.javawebinar.topjava.service.MealService;

public class MealRestController extends AbstractMealController{
    public MealRestController(MealService service) {
        super(service);
    }
}
