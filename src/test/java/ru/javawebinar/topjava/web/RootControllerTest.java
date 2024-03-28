package ru.javawebinar.topjava.web;

import org.assertj.core.matcher.AssertionMatcher;
import org.junit.jupiter.api.Test;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.to.MealTo;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.*;

class RootControllerTest extends AbstractControllerTest {

    @Test
    void getUsers() throws Exception {
        perform(get("/users"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("users"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/users.jsp"))
                .andExpect(model().attribute("users", new AssertionMatcher<List<User>>() {
                            @Override
                            public void assertion(List<User> actual) throws AssertionError {
                                USER_MATCHER.assertMatch(actual, admin, guest, user);
                            }
                        }
                ));
    }

    @Test
    void getMeals() throws Exception {
        perform(get("/meals"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("meals"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/meals.jsp"))
                .andExpect(model().attribute("meals", List.of(
                        new MealTo(meal7.getId(), meal7.getDateTime(), meal7.getDescription(), meal7.getCalories(), true),
                        new MealTo(meal6.getId(), meal6.getDateTime(), meal6.getDescription(), meal6.getCalories(), true),
                        new MealTo(meal5.getId(), meal5.getDateTime(), meal5.getDescription(), meal5.getCalories(), true),
                        new MealTo(meal4.getId(), meal4.getDateTime(), meal4.getDescription(), meal4.getCalories(), true),
                        new MealTo(meal3.getId(), meal3.getDateTime(), meal3.getDescription(), meal3.getCalories(), false),
                        new MealTo(meal2.getId(), meal2.getDateTime(), meal2.getDescription(), meal2.getCalories(), false),
                        new MealTo(meal1.getId(), meal1.getDateTime(), meal1.getDescription(), meal1.getCalories(), false))));
    }
}
