package ru.javawebinar.topjava.storage;

import ru.javawebinar.topjava.model.Meal;

import java.util.ArrayList;
import java.util.List;

public class MealsListStorage extends AbstractStorage<Integer> {

    private final List<Meal> meals = new ArrayList<>();

    @Override
    protected void doUpdate(Meal m, Integer searchKey) {
        meals.set(searchKey, m);
    }

    @Override
    protected void doSave(Meal m, Integer searchKey) {
        meals.add(m);
    }

    @Override
    protected boolean isExist(Integer searchKey) {
        return searchKey != null;
    }

    @Override
    protected Meal doGet(Integer searchKey) {
        return meals.get(searchKey);
    }

    @Override
    protected Integer getSearchKey(int id) {
        for (int i = 0; i < meals.size(); i++) {
            if (meals.get(i).getId() == id) {
                return i;
            }
        }
        return null;
    }

    @Override
    protected void doDelete(Integer searchKey) {
        meals.remove(meals.get(searchKey));
    }

    @Override
    protected List<Meal> doCopyAll() {
        return new ArrayList<>(meals);
    }

    @Override
    public void clear() {
        meals.clear();
    }

    @Override
    public int size() {
        return meals.size();
    }
}
