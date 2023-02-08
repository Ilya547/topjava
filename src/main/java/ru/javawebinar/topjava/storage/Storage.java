package ru.javawebinar.topjava.storage;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;

public interface Storage {

    Meal create(Integer key, LocalDateTime dateTime, String description, int calories);

    Meal update(Integer key, LocalDateTime dateTime, String description, int calories);

    Meal get(Integer key);

    void delete(Integer key);
}