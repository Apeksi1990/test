package ru.asemenov.storage;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.asemenov.models.Street;
import ru.asemenov.service.HibernateFactory;
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

    public List<House> getAllHouses(int street) {
        List<House> houses = null;
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            Query query = session.createQuery("from House where street.id=:street");
            query.setParameter("street", street);
            houses = query.list();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return houses;
    }

    public void addHouse(House house) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.save(house);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void editHouse(House house) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            House edit = session.get(House.class, house.getId());
            edit.setStreet(house.getStreet());
            session.update(edit);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
