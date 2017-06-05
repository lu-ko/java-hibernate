package sk.elko.trainings.hibernate.bookshop.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import sk.elko.trainings.hibernate.bookshop.bo.Address;
import sk.elko.trainings.hibernate.bookshop.bo.Book;
import sk.elko.trainings.hibernate.bookshop.bo.Chapter;
import sk.elko.trainings.hibernate.bookshop.bo.Customer;
import sk.elko.trainings.hibernate.bookshop.bo.Product;
import sk.elko.trainings.hibernate.bookshop.bo.Publisher;
import sk.elko.trainings.hibernate.bookshop.bo.Reservation;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@PropertySource("classpath:db.properties")
@EnableTransactionManagement
@ComponentScans(value = {
        @ComponentScan("sk.elko.trainings.hibernate.bookshop.dao.impl"),
})
public class DatabaseConfig {

    @Autowired
    private Environment env;

    @Bean
    public DataSource getDataSource() {
        System.out.println("Initializing DataSource - Connecting to: " + env.getProperty("db.url"));

        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(env.getProperty("db.driver_class"));
        dataSource.setUrl(env.getProperty("db.url"));
        dataSource.setUsername(env.getProperty("db.username"));
        dataSource.setPassword(env.getProperty("db.password"));
        return dataSource;
    }

    @Bean
    public Properties getHibernateProperties() {
        Properties props = new Properties();
        props.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
        props.put("hibernate.connection.charSet", env.getProperty("hibernate.connection.charSet"));
        props.put("hibernate.connection.characterEncoding", env.getProperty("hibernate.connection.characterEncoding"));
        props.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
        props.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
        return props;
    }

    @Bean
    public LocalSessionFactoryBean getSessionFactory() {
        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
        factoryBean.setDataSource(getDataSource());
        factoryBean.setHibernateProperties(getHibernateProperties());
        factoryBean.setPackagesToScan("sk.elko.trainings.hibernate.bookshop.bo");
        // factoryBean.setAnnotatedClasses(Address.class, Book.class, Chapter.class, Customer.class, Product.class, Publisher.class, Reservation.class);
        return factoryBean;
    }

    @Bean
    public HibernateTransactionManager getTransactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(getSessionFactory().getObject());
        return transactionManager;
    }

    @Bean
    public HibernateTemplate getHibernateTemplate() {
        HibernateTemplate hibernateTemplate = new HibernateTemplate();
        hibernateTemplate.setSessionFactory(getSessionFactory().getObject());
        return hibernateTemplate;
    }

}
