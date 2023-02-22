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

INSERT INTO meals (user_id, datetime, description, calories)
VALUES (100000, '2023-02-19 02:00', 'test1', 200),
       (100000, '2023-02-19 06:00', 'test2', 300),
       (100000, '2023-02-20 02:00', 'test3', 400),
       (100000, '2023-02-20 03:00', 'test4', 500),
       (100000, '2023-02-20 04:00', 'test5', 600),
       (100001, '2023-02-20 04:00', 'admtest5', 600),
       (100001, '2023-02-20 04:00', 'admtest4', 900);


