package sk.elko.trainings.hibernate.bookshop.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import sk.elko.trainings.hibernate.bookshop.bo.Publisher;
import sk.elko.trainings.hibernate.bookshop.dao.HibernateUtil;
import sk.elko.trainings.hibernate.bookshop.dao.PublisherDAO;

public class PublisherDAOImpl implements PublisherDAO {

    @Override
    public Publisher get(Long id) {
        Session session = HibernateUtil.openSession();
        try {
            return (Publisher) session.get(Publisher.class, id);
        } finally {
            session.close();
        }
    }

    @Override
    public List<Publisher> find(String code) {
        Session session = HibernateUtil.openSession();
        try {
            Query query = session.createQuery("from Publisher where code = ?");
            query.setString(0, code);

            List<Publisher> list = new ArrayList<Publisher>();
            for (Object publisher : query.list()) {
                list.add((Publisher) publisher);
            }
            return list;
        } finally {
            session.close();
        }
    }

    @Override
    public List<Publisher> getAll() {
        Session session = HibernateUtil.openSession();
        try {
            Query query = session.createQuery("from Publisher");

            List<Publisher> list = new ArrayList<Publisher>();
            for (Object publisher : query.list()) {
                list.add((Publisher) publisher);
            }
            return list;
        } finally {
            session.close();
        }
    }

    @Override
    public Long create(Publisher publisher) {
        Long id = null;

        Session session = HibernateUtil.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            // insert / update
            session.saveOrUpdate(publisher);

            // get id
            Query query = session.createQuery("from Publisher where code = ?");
            query.setString(0, publisher.getCode());
            Publisher newPublisher = (Publisher) query.uniqueResult();
            if (newPublisher != null) {
                id = newPublisher.getId();
            }

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        } finally {
            session.close();
        }

        return id;
    }

}
