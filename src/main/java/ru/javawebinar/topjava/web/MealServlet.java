package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.Config;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.storage.Storage;

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
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        storage = Config.get().getStorage();
    }

    private static final Logger log = getLogger(UserServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to meals");
        String id = request.getParameter("id");
        String action = request.getParameter("action");
        if (action == null) {
            request.setAttribute("meals", Config.modifyMealInMealTo(storage.getAll()));
            request.getRequestDispatcher("meals.jsp").forward(request, response);
            return;
        }
        Meal m;
        switch (action) {
            case "delete":
                storage.delete(Integer.parseInt(id));
                //url
                response.sendRedirect("meals");
                return;
            case "add":
                m = Meal.EMPTY;
                break;
            case "update":
                m = storage.get(Integer.parseInt(id));
                break;
            default:
                throw new IllegalArgumentException("Action " + action + " is illegal");
        }
        request.setAttribute("meal", m);
        request.getRequestDispatcher("mealsUpdate.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");
        String dateTime = request.getParameter("dateTime");
        String description = request.getParameter("description");
        String calories = request.getParameter("calories");

        final boolean isCreate = (Integer.parseInt(id) > 90000);
        Meal m;
        if (isCreate) {
            m = new Meal(LocalDateTime.parse(dateTime), description, Integer.parseInt(calories));
            storage.save(m);
        } else {
            m = storage.get(Integer.parseInt(id));
            m.setDateTime(LocalDateTime.parse(dateTime));
            m.setDescription(description);
            m.setCalories(Integer.parseInt(calories));
            storage.update(m);
        }
        response.sendRedirect("meals");
    }
}
