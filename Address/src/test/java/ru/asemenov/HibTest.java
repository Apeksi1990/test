package ru.asemenov;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import ru.asemenov.models.Apartment;
import ru.asemenov.models.House;
import ru.asemenov.models.Street;
import ru.asemenov.service.HibernateFactory;

import java.util.List;

public class HibTest {

    @Test
    public void streets() {
        final SessionFactory factory = HibernateFactory.getFactory();
        List streets = null;
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            streets = session.createQuery("from Street").list();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (Object street: streets) {
            System.out.println(street.toString());
        }
    }

    @Test
    public void houses() {
        final SessionFactory factory = HibernateFactory.getFactory();
        List<House> streets = null;
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            streets = session.createQuery("from House").list();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (House street: streets) {
            System.out.println(street.toString());
        }
    }

    @Test
    public void apartaments() {
        final SessionFactory factory = HibernateFactory.getFactory();
        List<Apartment> streets = null;
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            streets = session.createQuery("from Apartment").list();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (Apartment street: streets) {
            System.out.println(street.toString());
        }
    }
}
