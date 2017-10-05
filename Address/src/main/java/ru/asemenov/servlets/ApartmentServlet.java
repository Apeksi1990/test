package ru.asemenov.servlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.asemenov.models.Apartment;
import ru.asemenov.models.House;
import ru.asemenov.storage.ApartmentsStorage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Apartment Servlet.
 */
public class ApartmentServlet extends HttpServlet {
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
        int house = Integer.parseInt(req.getParameter("house"));
        List<Apartment> houses = ApartmentsStorage.getInstance().getAllApartments(house);
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
        Apartment apartment = new Apartment();
        apartment.setHouse(new House(Integer.parseInt(req.getParameter("house"))));
        if (req.getParameter("apartment") != null) {
            apartment.setId(Integer.parseInt(req.getParameter("apartment")));
            ApartmentsStorage.getInstance().editApartment(apartment);
        } else {
            apartment.setName(req.getParameter("name"));
            ApartmentsStorage.getInstance().addApartment(apartment);
        }
    }
}
