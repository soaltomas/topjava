package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.repository.InMemoryMealRepository;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by tomas on 30.03.17.
 */
public class MealServlet extends HttpServlet {
    private static final Logger LOG = getLogger(UserServlet.class);

    MealRepository repository = new InMemoryMealRepository();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if ("delete".equals(request.getParameter("action"))) {
            LOG.debug("delete meal");
            repository.delete(Integer.parseInt(request.getParameter("mealId")));
            LOG.debug("forward to meals");
            List<MealWithExceed> mealWithExceeds = MealsUtil.getFilteredWithExceeded(repository.getAll(), LocalTime.MIN, LocalTime.MAX, 2000);
            request.setAttribute("mealList", mealWithExceeds);
            request.getRequestDispatcher("/meals.jsp").forward(request, response);
        } else {
            if ("add".equals(request.getParameter("action"))) {
                LOG.debug("add meal");
                response.sendRedirect("update.jsp");
            } else {
                if ("update".equals(request.getParameter("action"))) {
                    LOG.debug("update meal");
                    Meal meal = new Meal(
                            repository.get(Integer.parseInt(request.getParameter("mealId"))).getDateTime(),
                            repository.get(Integer.parseInt(request.getParameter("mealId"))).getDescription(),
                            repository.get(Integer.parseInt(request.getParameter("mealId"))).getCalories()
                    );
                    meal.setId(Integer.parseInt(request.getParameter("mealId")));
                    request.setAttribute("meal", meal);
                    request.getRequestDispatcher("/update.jsp").forward(request, response);
                } else {
                    LOG.debug("forward to meals");
                    List<MealWithExceed> mealWithExceeds = MealsUtil.getFilteredWithExceeded(repository.getAll(), LocalTime.MIN, LocalTime.MAX, 2000);
                    request.setAttribute("mealList", mealWithExceeds);
                    request.getRequestDispatcher("/meals.jsp").forward(request, response);
                }
            }
        }

    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Meal meal = new Meal(
                LocalDateTime.parse(request.getParameter("dateTime")),
                request.getParameter("description"),
                Integer.parseInt(request.getParameter("calories"))
        );
        if(!request.getParameter("id").isEmpty()) {
            meal.setId(Integer.parseInt(request.getParameter("id")));
        }
        repository.update(meal);
        LOG.debug("forward to meals");
        List<MealWithExceed> mealWithExceeds = MealsUtil.getFilteredWithExceeded(repository.getAll(), LocalTime.MIN, LocalTime.MAX, 2000);
        request.setAttribute("mealList", mealWithExceeds);
        request.getRequestDispatcher("/meals.jsp").forward(request, response);
    }
}
