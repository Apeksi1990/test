package ru.asemenov.storage;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.asemenov.HibernateFactory;
import ru.asemenov.models.Apartment;

import java.util.List;

public class ApartmentsStorage {
    private final SessionFactory factory = HibernateFactory.getFactory();

    private static final ApartmentsStorage instance = new ApartmentsStorage();

    public static ApartmentsStorage getInstance() {
        return instance;
    }

    private ApartmentsStorage() {
    }

    public List<Apartment> getAllApartments() {
        List<Apartment> apartments = null;
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            apartments = session.createQuery("from Apartment").list();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return apartments;
    }
}
