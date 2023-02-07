import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.storage.Storage;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public abstract class AbstractStorageTest {
    private Storage storage;

    Meal test = new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "test", 7);


    protected AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        List<Meal> meals = Arrays.asList(
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410)
        );
        for (Meal meal : meals) {
            storage.save(meal);
        }
    }

    @Test
    public void size() throws Exception {
        assertSize(7);
    }

    @Test
    public void clear() throws Exception {
        storage.clear();
        assertSize(0);
    }

    @Test
    public void getAll() throws Exception {
        List<Meal> list = storage.getAll();
        assertEquals(7, list.size());
    }

    @Test
    public void save() throws Exception {
        storage.save(test);
        assertSize(8);
        assertGet(test);
    }

    @Test(expected = Exception.class)
    public void deleteNotExist() throws Exception {
        storage.delete(22222222);
    }


    @Test(expected = Exception.class)
    public void getNotExist() throws Exception {
        storage.get(22222222);
    }

    private void assertGet(Meal m) {
        assertEquals(m, storage.get(m.getId()));
    }

    private void assertSize(int size) {
        assertEquals(size, storage.size());
    }
}
