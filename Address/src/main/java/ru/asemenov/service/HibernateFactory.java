package ru.asemenov.service;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * HibernateFactory.
 */
public class HibernateFactory {
    /**
     * Создание SessionFactory.
     */
    private static final SessionFactory FACTORY = new Configuration()
            .configure()
            .buildSessionFactory();

    /**
     * Конструктор.
     */
    public HibernateFactory() {
    }

    /**
     * Получить созданный SessionFactory.
     * @return SessionFactory.
     */
    public static SessionFactory getFactory() {
        return FACTORY;
    }
}
