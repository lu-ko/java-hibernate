package sk.elko.trainings.hibernate.bookshop;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import sk.elko.trainings.hibernate.bookshop.config.AppConfig;
import sk.elko.trainings.hibernate.bookshop.dao.BookDAO;
import sk.elko.trainings.hibernate.bookshop.dao.CustomerDAO;
import sk.elko.trainings.hibernate.bookshop.dao.PublisherDAO;

public class MainClass {

    public static void main(String[] args) {
        System.err.println("Application is starting ...");
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        System.err.println("Application is running ...");

        BookDAO bookDAO = ctx.getBean("bookDAOImpl", BookDAO.class);
        CustomerDAO customerDAO = ctx.getBean("customerDAOImpl", CustomerDAO.class);
        PublisherDAO publisherDAO = ctx.getBean("publisherDAOImpl", PublisherDAO.class);

        System.err.println("Application is stopping ...");
    }

}
