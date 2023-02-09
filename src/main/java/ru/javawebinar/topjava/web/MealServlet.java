package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.storage.MapMeals;
import ru.javawebinar.topjava.storage.StorageForMeals;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {

    private static final Logger log = getLogger(MealServlet.class);

    private StorageForMeals storageForMeals;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        storageForMeals = new MapMeals();
        for (Meal meal : MealsUtil.getTestMeals()) {
            storageForMeals.create(meal);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to meals");
        String id = request.getParameter("id");
        String action = request.getParameter("action");
        if (action == null) {
            request.setAttribute("meals", MealsUtil.modifyMealInMealTo(storageForMeals.getAll()));
            request.getRequestDispatcher("meals.jsp").forward(request, response);
            return;
        }
        Meal m;
        switch (action) {
            case "delete":
                storageForMeals.delete(Integer.valueOf(id));
                response.sendRedirect("meals");
                return;
            case "add":
                m = MealsUtil.EMPTY;
                break;
            case "update":
                m = storageForMeals.get(Integer.valueOf(id));
                break;
            default:
                response.sendRedirect("meals");
                return;
        }
        request.setAttribute("meal", m);
        request.getRequestDispatcher("mealsUpdate.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");
        LocalDateTime dateTime = LocalDateTime.parse(request.getParameter("dateTime"));
        String description = request.getParameter("description");
        int calories = Integer.parseInt(request.getParameter("calories"));

        final boolean isCreate = (id.length() == 0);
        Meal m;
        if (isCreate) {
            m = new Meal(dateTime,description,calories);
            storageForMeals.create(m);
        } else {
            m = new Meal(Integer.valueOf(id), dateTime,description,calories);
            storageForMeals.update(m);
        }
        response.sendRedirect("meals");
    }
}
