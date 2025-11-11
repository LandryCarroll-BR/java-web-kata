package com.landrycarroll.javawebkata.config;

import com.landrycarroll.javawebkata.entities.Todo;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;


public class Hibernate {

    private static SessionFactory sessionFactory;

    static {
        setUp();
    }

    protected static void setUp() {
        // A SessionFactory is set up once for an application
        final StandardServiceRegistry registry =
                new StandardServiceRegistryBuilder()
                        // Optionally load from hibernate.cfg.xml if present
                        // .configure()
                        .build();

        try {
            sessionFactory = new MetadataSources(registry)
                    .addAnnotatedClass(Todo.class)
                    .buildMetadata()
                    .buildSessionFactory();
        } catch (Exception e) {
            System.err.println("SessionFactory creation failed: " + e);
            // The registry would be destroyed by the SessionFactory, but since
            // we had trouble building it, we must destroy it manually.
            StandardServiceRegistryBuilder.destroy(registry);
            throw new ExceptionInInitializerError(e);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}
