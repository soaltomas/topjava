package ru.javawebinar.topjava;

import ru.javawebinar.topjava.matcher.ModelMatcher;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.Objects;

import static ru.javawebinar.topjava.model.BaseEntity.START_SEQ;

public class MealTestData {
    public static final int MEAL_ID = START_SEQ + 2;


    public static final Meal[] ADMIN_MEALS = {new Meal(MEAL_ID + 5, LocalDateTime.of(2015, 8, 10, 18, 0, 0), "Ужин админа", 800),
            new Meal(MEAL_ID + 4, LocalDateTime.of(2015, 8, 10, 12, 0, 0), "Обед админа", 522),
            new Meal(MEAL_ID + 3, LocalDateTime.of(2015, 8, 10, 10, 0, 0), "Завтрак админа", 345)};

    public static final Meal[] MEALS = {new Meal(MEAL_ID + 2, LocalDateTime.of(2015, 7, 10, 18, 0, 0), "Ужин", 510),
            new Meal(MEAL_ID + 1, LocalDateTime.of(2015, 7, 10, 12, 0, 0), "Обед", 500),
            new Meal(MEAL_ID, LocalDateTime.of(2015, 7, 10, 10, 0, 0), "Завтрак", 500)};


    public static final ModelMatcher<Meal> MATCHER = new ModelMatcher<>(
            ((expected, actual) -> expected == actual ||
                    Objects.equals(expected.getId(), actual.getId())
                            && Objects.equals(expected.getDateTime(), actual.getDateTime())
                            && Objects.equals(expected.getDescription(), actual.getDescription())
                            && Objects.equals(expected.getCalories(), actual.getCalories()))
    );

}
