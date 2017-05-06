package ru.javawebinar.topjava.service.jpa;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.service.UserServiceTest;

/**
 * Created by tomas on 30.04.17.
 */
@ActiveProfiles({Profiles.ACTIVE_DB, Profiles.JPA})
public class UserJpaServiceTest extends UserServiceTest {
}
