DELETE FROM user_role;
DELETE FROM meals;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin'),
       ('Guest', 'guest@gmail.com', 'guest');

INSERT INTO user_role (role, user_id)
VALUES ('USER', 100000),
       ('ADMIN', 100001);

INSERT INTO meals (user_id, date_time, description, calories)
VALUES (100000, '2023-02-20 02:00', 'Завт', 200),
       (100000, '2023-02-20 06:00', 'Обед', 300),
       (100000, '2023-02-20 08:00', 'Ужин', 400),
       (100000, '2023-02-21 03:00', 'Еда на граничное значение', 500),
       (100000, '2023-02-21 04:00', 'Завт', 600),
       (100000, '2023-02-21 04:50', 'Обед', 600),
       (100000, '2023-02-21 04:55', 'Ужин', 600),
       (100001, '2023-02-21 04:00', 'Ужин админа', 900);


