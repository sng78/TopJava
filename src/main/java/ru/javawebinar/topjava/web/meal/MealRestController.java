package ru.javawebinar.topjava.web.meal;

import org.slf4j.LoggerFactory;

public class MealRestController extends AbstractMealController{
    public MealRestController() {
        super(LoggerFactory.getLogger(MealRestController.class));
    }
}
