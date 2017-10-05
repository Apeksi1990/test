package ru.asemenov.storage;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.asemenov.service.HibernateFactory;
import ru.asemenov.models.Street;

import java.util.List;

/**
 * Streets Storage.
 */
public class StreetsStorage {
    /**
     * SessionFactory.
     */
    private final SessionFactory factory = HibernateFactory.getFactory();
    /**
     * Singleton.
     */
    private static final StreetsStorage INSTANCE = new StreetsStorage();

    /**
     * Singleton.
     * @return StreetsStorage.
     */
    public static StreetsStorage getInstance() {
        return INSTANCE;
    }

    /**
     * Конструктор.
     */
    private StreetsStorage() {
    }

    /**
     * Получить все улицы.
     * @return List улиц.
     */
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

    /**
     * Добавить новую улицу.
     * @param street новая улица.
     */
    public void addStreet(Street street) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.save(street);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
