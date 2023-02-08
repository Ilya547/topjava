package ru.javawebinar.topjava.storage;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.concurrent.ConcurrentHashMap;

public class MealsStorage implements Storage {

    private final ConcurrentHashMap<Integer, Meal> meals = new ConcurrentHashMap<>();

    protected int countMeal = 1;

    @Override
    public Meal create(Integer key, LocalDateTime dateTime, String description, int calories) {
        Meal m = new Meal(countMeal, dateTime, description, calories);
        meals.put(key, m);
        countMeal++;
        return meals.get(key);
    }

    @Override
    public Meal update(Integer key, LocalDateTime dateTime, String description, int calories) {
        Meal m = new Meal(dateTime, description, calories);
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
        countMeal--;
    }
}
