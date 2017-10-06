package ru.asemenov.servlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.asemenov.models.Street;
import ru.asemenov.storage.StreetsStorage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Street Servlet.
 */
public class StreetServlet extends HttpServlet {
    /**
     * Переопределенный метод doGet.
     * @param req HttpServletRequest
     * @param resp HttpServletResponse
     * @throws ServletException исключение.
     * @throws IOException исключение.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new GsonBuilder().create();
        List<Street> streets = StreetsStorage.getInstance().getAllStreets();
        String json = gson.toJson(streets);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(json);
    }

    /**
     * Переопределение метода doPost.
     * @param req HttpServletRequest
     * @param resp HttpServletResponse
     * @throws ServletException исключение.
     * @throws IOException исключение.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Street street = new Street();
        street.setName(req.getParameter("name"));
        StreetsStorage.getInstance().addStreet(street);
    }
}
