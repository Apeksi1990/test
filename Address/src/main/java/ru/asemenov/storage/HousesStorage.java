package ru.asemenov.storage;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.asemenov.models.House;
import ru.asemenov.service.HibernateFactory;

import java.util.List;

/**
 * Houses Storage.
 */
public class HousesStorage {
    /**
     * SessionFactory.
     */
    private final SessionFactory factory = HibernateFactory.getFactory();
    /**
     * Singleton.
     */
    private static final HousesStorage INSTANCE = new HousesStorage();

    /**
     * Singleton.
     * @return HousesStorage.
     */
    public static HousesStorage getInstance() {
        return INSTANCE;
    }

    /**
     * Конструтор.
     */
    public HousesStorage() {
    }

    /**
     * Получить список всех домов на улице.
     * @param street улица на которой расположены дома.
     * @return List домов.
     */
    public List<House> getAllHouses(int street) {
        List<House> houses = null;
        try (Session session = factory.openSession()) {
            try {
                session.beginTransaction();
                Query query = session.createQuery("from House where street.id=:street");
                query.setParameter("street", street);
                houses = query.list();
                session.getTransaction().commit();
            } catch (Exception e) {
                session.getTransaction().rollback();
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return houses;
    }

    /**
     * Добавить новый дом.
     * @param house новый дом.
     */
    public void addHouse(House house) {
        try (Session session = factory.openSession()) {
            try {
                session.beginTransaction();
                session.save(house);
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
     * Изменить привязку дома.
     * @param house дом с новой привязкой.
     */
    public void editHouse(House house) {
        try (Session session = factory.openSession()) {
            try {
                session.beginTransaction();
                House edit = session.get(House.class, house.getId());
                edit.setStreet(house.getStreet());
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
