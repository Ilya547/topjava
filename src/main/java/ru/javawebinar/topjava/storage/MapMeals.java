package ru.javawebinar.topjava.storage;

import ru.javawebinar.topjava.model.Meal;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MapMeals implements StorageForMeals {

    private final Map<Integer, Meal> meals = new ConcurrentHashMap<>();
    private final AtomicInteger atomicInteger = new AtomicInteger();

    private int counter = 0;

    @Override
    public synchronized Meal create(Meal m) {
        counter = atomicInteger.incrementAndGet();
        Meal mPlusId = new Meal(counter, m.getDateTime(), m.getDescription(), m.getCalories());
        meals.put(counter, mPlusId);
        return meals.get(counter);
    }

    @Override
    public synchronized Meal update(Meal m) {
        meals.put(m.getId(), m);
        return meals.get(m.getId());
    }

    @Override
    public synchronized Meal get(int id) {
        return meals.get(id);
    }

    @Override
    public synchronized void delete(int id) {
        meals.remove(id);
    }

    @Override
    public List<Meal> getAll() {
        return new ArrayList<>(meals.values());
    }
}
