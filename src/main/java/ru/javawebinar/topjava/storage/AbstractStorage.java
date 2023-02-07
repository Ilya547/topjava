package ru.javawebinar.topjava.storage;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public abstract class AbstractStorage<SK> implements Storage {

    public void update(Meal m) {
        SK searchKey = getExistedSearchKey(m.getId());
        doUpdate(m, searchKey);
    }

    public void save(Meal m) {
        SK searchKey = getNotExistedSearchKey(m.getId());
        doSave(m, searchKey);
    }

    public Meal get(int id) {
        SK searchKey = getExistedSearchKey(id);
        return doGet(searchKey);
    }

    public void delete(int id) {
        SK searchKey = getExistedSearchKey(id);
        doDelete(searchKey);
    }

    private SK getExistedSearchKey(int id) {
        SK searchKey = getSearchKey(id);
        if (!isExist(searchKey)) {
            try {
                throw new Exception(" is not exist.");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return searchKey;
    }

    private SK getNotExistedSearchKey(int id) {
        SK searchKey = getSearchKey(id);
        if (isExist(searchKey)) {
            try {
                throw new Exception(" already exist");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return searchKey;
    }

    @Override
    public List<Meal> getAll() {
        List<Meal> meals = doCopyAll();
        return meals;
    }

    protected abstract void doUpdate(Meal m, SK searchKey);

    protected abstract void doSave(Meal m, SK searchKey);

    protected abstract boolean isExist(SK searchKey);

    protected abstract Meal doGet(SK searchKey);


    protected abstract SK getSearchKey(int id);

    protected abstract void doDelete(SK searchKey);

    protected abstract List<Meal> doCopyAll();
}
