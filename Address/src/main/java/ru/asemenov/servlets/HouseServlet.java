package ru.asemenov.servlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.asemenov.models.House;
import ru.asemenov.models.Street;
import ru.asemenov.storage.HousesStorage;
import ru.asemenov.storage.StreetsStorage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class HouseServlet extends HttpServlet {
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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        House house = new House();
        house.setName(req.getParameter("name"));
        house.setStreet(new Street(Integer.valueOf(req.getParameter("street"))));
        HousesStorage.getInstance().addHouse(house);
    }
}
