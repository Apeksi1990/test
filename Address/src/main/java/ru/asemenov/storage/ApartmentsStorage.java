package ru.asemenov.storage;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.asemenov.service.HibernateFactory;
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

    public List<Apartment> getAllApartments(int house) {
        List<Apartment> apartments = null;
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            Query query = session.createQuery("from Apartment where house.id=:house");
            query.setParameter("house", house);
            apartments = query.list();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return apartments;
    }
}
