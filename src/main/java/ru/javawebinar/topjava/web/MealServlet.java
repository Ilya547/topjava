package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.storage.MealsStorage;
import ru.javawebinar.topjava.storage.Storage;
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

    private Storage storage;

    @Override
    public void init(ServletConfig config) {
        storage = new MealsStorage();
        for (Meal meal : MealsUtil.fill()) {
            storage.create(meal.getDateTime(), meal.getDescription(), meal.getCalories());
        }
    }

    private static final Logger log = getLogger(MealServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to meals");
        String id = request.getParameter("id");
        String action = request.getParameter("action");
        if (action == null) {
            request.setAttribute("meals", MealsUtil.modifyMealInMealTo(storage.getStorageAsList()));
            request.getRequestDispatcher("meals.jsp").forward(request, response);
            return;
        }
        Meal m = null;
        switch (action) {
            case "delete":
                storage.delete(Integer.valueOf(id));
                response.sendRedirect("meals");
                return;
            case "add":
                m = MealsUtil.EMPTY;
                break;
            case "update":
                m = storage.get(Integer.valueOf(id));
                break;
            default:
                response.sendRedirect("meals");
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

        final boolean isCreate = (Integer.parseInt(id) == 0);
        if (isCreate) {
            storage.create(dateTime, description, calories);
        } else {
            storage.update(Integer.parseInt(id), dateTime, description, calories);
        }
        response.sendRedirect("meals");
    }
}
