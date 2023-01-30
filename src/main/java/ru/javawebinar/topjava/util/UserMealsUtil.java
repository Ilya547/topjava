package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExcess;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;

import static ru.javawebinar.topjava.util.TimeUtil.isBetweenHalfOpen;

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> meals = Arrays.asList(
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410)
        );

        List<UserMealWithExcess> mealsTo = filteredByCycles(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
        mealsTo.forEach(System.out::println);

//        System.out.println(filteredByStreams(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000));
    }

    public static List<UserMealWithExcess> filteredByCycles(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        meals.sort((Comparator.comparingInt(o -> o.getDateTime().getDayOfMonth())));

        Map<Integer, Object> map = new TreeMap<>();
        int day = 0;
        int calories = 0;
        boolean excess = false;
        for (UserMeal meal : meals) {
            if (day != meal.getDateTime().getDayOfMonth()) {
                day = meal.getDateTime().getDayOfMonth();
                calories = 0;
            }
            calories += meal.getCalories();
            excess = calories > caloriesPerDay;
            map.put(day, excess);
        }

        for (UserMeal meal : meals) {
            if (isBetweenHalfOpen(meal.getDateTime().toLocalTime(), startTime, endTime)) {
                map.merge(meal.getDateTime().getDayOfMonth(), excess, (a, b) ->
                        new UserMealWithExcess(meal.getDateTime(), meal.getDescription(), meal.getCalories(), (Boolean) b));
            }
        }

        List<Object> objects = new ArrayList<>(map.values());
        List<UserMealWithExcess> filteredUserMealsWE = new ArrayList<>(objects.size());
        for (Object object : objects) {
            filteredUserMealsWE.add((UserMealWithExcess) object);
        }
        return filteredUserMealsWE;
    }

    public static List<UserMealWithExcess> filteredByStreams(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        // TODO Implement by streams
        return null;
    }
}
