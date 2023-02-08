package ru.javawebinar.topjava.storage;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class MealsStorage implements Storage {

    private final ConcurrentHashMap<Integer, Meal> meals = new ConcurrentHashMap<>();

    protected int id = 0;

    @Override
    public Meal create(LocalDateTime dateTime, String description, int calories) {
        id++;
        Meal m = new Meal(id, dateTime, description, calories);
        meals.put(id, m);
        return meals.get(id);
    }

    @Override
    public Meal update(Integer key, LocalDateTime dateTime, String description, int calories) {
        Meal m = new Meal(key, dateTime, description, calories);
        meals.put(key, m);
        return meals.get(key);
    }

    @Override
    public Meal get(Integer key) {
        return meals.get(key);
    }

    @Override
    public void delete(Integer key) {
        meals.remove(key);
        id--;
    }

    @Override
    public List<Meal> getStorageAsList() {
        return new ArrayList<>(meals.values());
    }
}
