package ru.javawebinar.topjava.service;

/**
 * Created by tomas on 19.04.17.
 */

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.DbPopulator;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.util.*;

import static ru.javawebinar.topjava.MealTestData.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class MealServiceTest {
    static {
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private MealService service;

    @Autowired
    private DbPopulator dbPopulator;

    @Before
    public void setUp() throws Exception {
        dbPopulator.execute();
    }

    @Test
    public void testSave() throws Exception {
        Meal newMeal = new Meal(null, LocalDateTime.now(), "Новая еда", 1000);
        Meal created = service.save(newMeal, AuthorizedUser.id());
        newMeal.setId(created.getId());
        List<Meal> listMeal = new ArrayList<>();
        listMeal.add(newMeal);
        listMeal.addAll(Arrays.asList(MEALS));
        MATCHER.assertCollectionEquals(listMeal, service.getAll(AuthorizedUser.id()));
    }

    @Test
    public void testDelete() throws Exception {
        service.delete(MEAL_ID, AuthorizedUser.id());
        MATCHER.assertCollectionEquals(Arrays.asList(MEALS[0], MEALS[1]), service.getAll(AuthorizedUser.id()));
    }


    @Test(expected = NotFoundException.class)
    public void testNotFoundDelete() throws Exception {
        service.delete(MEAL_ID + 4, AuthorizedUser.id());
    }

    @Test
    public void testGet() throws Exception {
        Meal meal = service.get(MEAL_ID, AuthorizedUser.id());
        MATCHER.assertEquals(MEALS[2], meal);
    }

    @Test(expected = NotFoundException.class)
    public void testGetNotFound() throws Exception {
        service.get(MEAL_ID + 4, AuthorizedUser.id());
    }

    @Test
    public void testGetAll() throws Exception {
        Collection<Meal> all = service.getAll(AuthorizedUser.id());
        MATCHER.assertCollectionEquals(Arrays.asList(MEALS), all);
    }

    @Test
    public void testUpdate() throws Exception {
        Meal updated = new Meal(MEALS[2]);
        updated.setDescription("UpdatedDescription");
        updated.setCalories(777);
        service.update(updated, AuthorizedUser.id());
        MATCHER.assertEquals(updated, service.get(MEAL_ID, AuthorizedUser.id()));
    }

    @Test(expected = NotFoundException.class)
    public void testUpdateNotFound() throws Exception {
        Meal updated = new Meal(MEALS[2]);
        updated.setDescription("UpdatedDescription");
        updated.setCalories(777);
        service.update(updated, AuthorizedUser.id());
        MATCHER.assertEquals(updated, service.get(MEAL_ID + 4, AuthorizedUser.id()));
    }
}
