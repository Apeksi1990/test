package ru.asemenov.storage;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.asemenov.service.HibernateFactory;
import ru.asemenov.models.Street;

import java.util.List;

public class StreetsStorage {
    private final SessionFactory factory = HibernateFactory.getFactory();

    private static final StreetsStorage instance = new StreetsStorage();

    public static StreetsStorage getInstance() {
        return instance;
    }

    private StreetsStorage() {
    }

    public List<Street> getAllStreets() {
        List<Street> streets = null;
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            streets = session.createQuery("from Street").list();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return streets;
    }
}
