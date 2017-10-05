package ru.asemenov.servlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.asemenov.models.House;
import ru.asemenov.models.Street;
import ru.asemenov.storage.HousesStorage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * House Servlet.
 */
public class HouseServlet extends HttpServlet {
    /**
     * Переопределенный метод doGet.
     * @param req HttpServletRequest
     * @param resp HttpServletResponse
     * @throws ServletException исключение.
     * @throws IOException исключение.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GsonBuilder b = new GsonBuilder();
        b.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY);
        Gson gson = b.create();
        int street = Integer.parseInt(req.getParameter("street"));
        List<House> houses = HousesStorage.getInstance().getAllHouses(street);
        String json = gson.toJson(houses);
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
        House house = new House();
        house.setStreet(new Street(Integer.valueOf(req.getParameter("street"))));
        if (req.getParameter("house") != null) {
            house.setId(Integer.parseInt(req.getParameter("house")));
            HousesStorage.getInstance().editHouse(house);
        } else {
            house.setName(req.getParameter("name"));
            HousesStorage.getInstance().addHouse(house);
        }
    }
}
