package ru.asemenov;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.asemenov.models.Street;

import java.util.List;

public class AddressStorage {
    private final SessionFactory factory = HibernateFactory.getFactory();

    private static final AddressStorage instance = new AddressStorage();

    public static AddressStorage getInstance() {
        return instance;
    }

    private AddressStorage() {
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
