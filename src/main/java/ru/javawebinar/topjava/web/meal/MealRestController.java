package ru.javawebinar.topjava.web.meal;

import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.Date;
import java.util.List;

@Controller
public class MealRestController {
    //like ProfileRestController by Pavlvov т.е. задогиненый юзер может изменять только свою еду
    private MealService service;

    public Meal get(int id) {
        try {
            return service.get(id);
        } catch (NotFoundException e) {
            throw new NotFoundException(e + "id is not exist");
        }
    }

    public void delete(int id) {
        service.delete(id);
    }

    public void update(Meal meal) {
        service.update(meal);
    }

    public List<MealTo> getAll() {

    }

    public List<MealTo> getSorted(Date startTime, Date endTime) {
        //paaram startDate, startTime, endDate, endTime
    }

}