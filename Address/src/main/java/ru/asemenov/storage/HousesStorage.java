package ru.asemenov.storage;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.asemenov.HibernateFactory;
import ru.asemenov.models.House;

import java.util.List;

public class HousesStorage {
    private final SessionFactory factory = HibernateFactory.getFactory();

    private static final HousesStorage instance = new HousesStorage();

    public static HousesStorage getInstance() {
        return instance;
    }

    public HousesStorage() {
    }

    public List<House> getAllHouses() {
        List<House> houses = null;
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            houses = session.createQuery("from House").list();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return houses;
    }
}
