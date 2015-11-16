package sk.elko.trainings.hibernate.bookshop.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {
    private static final Log log = LogFactory.getLog(HibernateUtil.class);

    private static SessionFactory sessionFactory;

    private static synchronized void initSessionFactory() {
        log.info("initSessionFactory - Initializing SessionFactory...");
        try {
            StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
            Metadata metadata = new MetadataSources(standardRegistry).getMetadataBuilder().build();
            sessionFactory = metadata.getSessionFactoryBuilder().build();
            log.info("initSessionFactory - SessionFactory initialized.");
        } catch (Throwable ex) {
            log.error("Initial SessionFactory creation failed. " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            initSessionFactory();
        }
        return sessionFactory;
    }

    public static Session openSession() {
        return getSessionFactory().openSession();
    }

}
