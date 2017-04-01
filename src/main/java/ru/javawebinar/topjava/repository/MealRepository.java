package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

/**
 * Created by tomas on 30.03.17.
 */
public interface MealRepository {

    Meal update(Meal meal);

    Meal get(int id);

    List<Meal> getAll();

    void delete(int id);
}
