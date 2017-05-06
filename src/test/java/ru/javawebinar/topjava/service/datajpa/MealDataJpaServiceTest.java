package ru.javawebinar.topjava.service.datajpa;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.service.MealServiceTest;

/**
 * Created by tomas on 30.04.17.
 */
@ActiveProfiles({Profiles.HSQL_DB, Profiles.DATAJPA})
public class MealDataJpaServiceTest extends MealServiceTest {
}
