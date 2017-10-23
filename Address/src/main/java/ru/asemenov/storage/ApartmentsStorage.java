package ru.asemenov.storage;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.asemenov.models.Apartment;
import ru.asemenov.service.HibernateFactory;

import java.util.List;

/**
 * Apartments Storage.
 */
public class ApartmentsStorage {
    /**
     * SessionFactory.
     */
    private final SessionFactory factory = HibernateFactory.getFactory();
    /**
     * Singleton.
     */
    private static final ApartmentsStorage INSTANCE = new ApartmentsStorage();

    /**
     * Singleton.
     * @return ApartmentsStorage.
     */
    public static ApartmentsStorage getInstance() {
        return INSTANCE;
    }

    /**
     * Конструктор.
     */
    private ApartmentsStorage() {
    }

    /**
     * Получить список квартир.
     * @param house дом в котором должны находиться квартиры.
     * @return List квартир.
     */
    public List<Apartment> getAllApartments(int house) {
        List<Apartment> apartments = null;
        try (Session session = factory.openSession()) {
            try {
                session.beginTransaction();
                Query query = session.createQuery("from Apartment where house.id=:house");
                query.setParameter("house", house);
                apartments = query.list();
                session.getTransaction().commit();
            } catch (Exception e) {
                session.getTransaction().rollback();
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return apartments;
    }

    /**
     * Добавить новую квартиру.
     * @param apartment новая квартира.
     */
    public void addApartment(Apartment apartment) {
        try (Session session = factory.openSession()) {
            try {
                session.beginTransaction();
                session.save(apartment);
                session.getTransaction().commit();
            } catch (Exception e) {
                session.getTransaction().rollback();
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Изменить привязку квартиры.
     * @param apartment кварттира с новым расположением.
     */
    public void editApartment(Apartment apartment) {
        try (Session session = factory.openSession()) {
            try {
                session.beginTransaction();
                Apartment edit = session.get(Apartment.class, apartment.getId());
                edit.setHouse(apartment.getHouse());
                session.update(edit);
                session.getTransaction().commit();
            } catch (Exception e) {
                session.getTransaction().rollback();
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
