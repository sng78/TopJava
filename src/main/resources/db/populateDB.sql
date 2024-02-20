DELETE FROM meals;
DELETE FROM user_role;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin'),
       ('Guest', 'guest@gmail.com', 'guest');

INSERT INTO user_role (role, user_id)
VALUES ('USER', 100000),
       ('ADMIN', 100001);

INSERT INTO meals (date_time, description, calories, user_id)
VALUES ('2023-08-5 10:00', 'Завтрак', 1000, 100000),
       ('2023-08-5 14:00', 'Обед', 500, 100000),
       ('2023-08-5 19:00', 'Ужин', 450, 100000),
       ('2023-09-5 10:00', 'Завтрак', 1000, 100000),
       ('2023-09-5 14:00', 'Обед', 600, 100000),
       ('2023-09-5 19:00', 'Ужин', 401, 100000),
       ('2023-08-5 08:00', 'Завтрак', 1000, 100001),
       ('2023-08-5 20:00', 'Ужин', 1000, 100001);
