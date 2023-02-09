package ru.javawebinar.topjava.storage;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface StorageForMeals {

    Meal create(Meal m);

    Meal update(Meal m);

    Meal get(int id);

    void delete(int id);

    List<Meal> getAll();
}