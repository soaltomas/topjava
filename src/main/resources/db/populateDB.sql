DELETE FROM user_roles;
DELETE FROM users;
DELETE FROM meals;

ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password');

INSERT INTO users (name, email, password)
VALUES ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);

INSERT INTO meals (user_id, date_time, description, calories)
VALUES (100000, '2015-07-10 10:00:00', 'Завтрак', 500);
INSERT INTO meals (user_id, date_time, description, calories)
VALUES (100000, '2015-07-10 12:00:00', 'Обед', 500);
INSERT INTO meals (user_id, date_time, description, calories)
VALUES (100000, '2015-07-10 18:00:00', 'Ужин', 510);

INSERT INTO meals (user_id, date_time, description, calories)
VALUES (100001, '2015-08-10 10:00:00', 'Завтрак админа', 345);
INSERT INTO meals (user_id, date_time, description, calories)
VALUES (100001, '2015-08-10 12:00:00', 'Обед админа', 522);
INSERT INTO meals (user_id, date_time, description, calories)
VALUES (100001, '2015-08-10 18:00:00', 'Ужин админа', 800);

CREATE INDEX date_time_index ON meals (date_time);
CREATE INDEX description_index ON meals (description);