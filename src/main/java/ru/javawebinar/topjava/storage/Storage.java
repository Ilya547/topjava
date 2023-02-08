package ru.javawebinar.topjava.storage;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.List;

public interface Storage {

    Meal create(LocalDateTime dateTime, String description, int calories);

    Meal update(Integer key, LocalDateTime dateTime, String description, int calories);

    Meal get(Integer key);

    void delete(Integer key);

    List<Meal> getStorageAsList();
}