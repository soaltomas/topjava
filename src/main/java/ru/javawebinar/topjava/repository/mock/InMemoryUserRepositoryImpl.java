package ru.javawebinar.topjava.repository.mock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class InMemoryUserRepositoryImpl implements UserRepository {
    private static final Logger LOG = LoggerFactory.getLogger(InMemoryUserRepositoryImpl.class);

    private Map<Integer, User> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        save(new User(counter.getAndIncrement(), "User", "user@topjava.com", "user_password", Role.ROLE_USER));
        save(new User(counter.getAndIncrement(), "Admin", "admin@topjava.com", "admin_password", Role.ROLE_USER, Role.ROLE_ADMIN));
    }

    @Override
    public boolean delete(int id) {
        LOG.info("delete " + id);
        return repository.remove(id) == null ? false : true;
    }

    @Override
    public User save(User user) {
        LOG.info("save " + user);
        if(user.isNew()) {
            user.setId(counter.getAndIncrement());
        }
            repository.put(user.getId(), user);
        return user;
    }

    @Override
    public User get(int id) {
        LOG.info("get " + id);
        return repository.get(id);
    }

    @Override
    public Collection<User> getAll() {
        LOG.info("getAll");
        repository.values();
        return repository.values();
    }

    @Override
    public User getByEmail(String email) {
        LOG.info("getByEmail " + email);
        return repository.values().stream().filter(u -> u.getEmail().equals(email)).findFirst().orElse(null);
    }
}
