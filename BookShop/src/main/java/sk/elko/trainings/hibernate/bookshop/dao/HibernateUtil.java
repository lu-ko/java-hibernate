package sk.elko.trainings.hibernate.bookshop.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateUtil {
    private static final Log log = LogFactory.getLog(HibernateUtil.class);

    private static SessionFactory sessionFactory;

    private static synchronized void initSessionFactory() {
        log.info("initSessionFactory - Initializing SessionFactory...");
        try {
            Configuration config = new Configuration().configure("hibernate.cfg.xml");
            ServiceRegistryBuilder builder = new ServiceRegistryBuilder().applySettings(config.getProperties());
            sessionFactory = config.buildSessionFactory(builder.buildServiceRegistry());
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
