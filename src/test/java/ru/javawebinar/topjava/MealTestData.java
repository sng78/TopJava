package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
    public static final int MEAL1_ID = START_SEQ + 3;
    public static final int MEAL2_ID = START_SEQ + 4;
    public static final int MEAL3_ID = START_SEQ + 5;
    public static final int MEAL4_ID = START_SEQ + 6;
    public static final int MEAL5_ID = START_SEQ + 7;
    public static final int MEAL6_ID = START_SEQ + 8;
    public static final int MEAL7_ID = START_SEQ + 9;
    public static final int MEAL8_ID = START_SEQ + 10;
    public static final int NOT_FOUND = 10;

    public static final Meal mealUser1 = new Meal(MEAL1_ID, LocalDateTime.of(2023, Month.AUGUST, 5, 10, 0), "Завтрак", 1000);
    public static final Meal mealUser2 = new Meal(MEAL2_ID, LocalDateTime.of(2023, Month.AUGUST, 5, 14, 0), "Обед", 500);
    public static final Meal mealUser3 = new Meal(MEAL3_ID, LocalDateTime.of(2023, Month.AUGUST, 5, 19, 0), "Ужин", 450);
    public static final Meal mealUser4 = new Meal(MEAL4_ID, LocalDateTime.of(2023, Month.SEPTEMBER, 5, 10, 0), "Завтрак", 1000);
    public static final Meal mealUser5 = new Meal(MEAL5_ID, LocalDateTime.of(2023, Month.SEPTEMBER, 5, 14, 0), "Обед", 600);
    public static final Meal mealUser6 = new Meal(MEAL6_ID, LocalDateTime.of(2023, Month.SEPTEMBER, 5, 19, 0), "Ужин", 401);
    public static final Meal mealAdmin1 = new Meal(MEAL7_ID, LocalDateTime.of(2023, Month.AUGUST, 5, 8, 0), "Завтрак", 1000);
    public static final Meal mealAdmin2 = new Meal(MEAL8_ID, LocalDateTime.of(2023, Month.AUGUST, 5, 20, 0), "Ужин", 1000);

    public static Meal getNew() {
        return new Meal(null, LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500);
    }

    public static Meal getUpdated() {
        Meal updated = new Meal(mealUser1);
        updated.setDateTime(LocalDateTime.of(2023, Month.AUGUST, 5, 11, 0));
        updated.setDescription("Новая еда");
        updated.setCalories(1200);
        return updated;
    }

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }
}
