package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by tomas on 30.03.17.
 */
public class InMemoryMealRepository implements MealRepository {

    private static AtomicInteger count = new AtomicInteger(0);

    CopyOnWriteArrayList<Meal> meals = new CopyOnWriteArrayList<>(new Meal[]{
            new Meal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
            new Meal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
            new Meal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
            new Meal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
            new Meal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
            new Meal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
    });

    {
        meals.forEach(m -> m.setId(count.getAndIncrement()));
    }

    @Override
    public Meal update(Meal meal) {
        if (meal.getId() != null) {
            Meal m = get(meal.getId().get());
            if (m != null) {
                m.setDateTime(meal.getDateTime());
                m.setDescription(meal.getDescription());
                m.setCalories(meal.getCalories());
            }
            return m;
        } else {
            meal.setId(count.getAndIncrement());
            meals.add(meal);
            return meal;
        }
    }

    @Override
    public Meal get(int id) {
        for (Meal m : meals) {
            if (m.getId().get() == id) {
                return m;
            }
        }
        return null;
    }

    @Override
    public List<Meal> getAll() {
        return meals;
    }

    @Override
    public void delete(int id) {
        for (Meal m : meals) {
            if (m.getId().get() == id) {
                meals.remove(m);
            }
        }
    }

}
