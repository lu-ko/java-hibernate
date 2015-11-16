package sk.elko.trainings.hibernate.bookshop.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

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
            Criteria criteria = session.createCriteria(Publisher.class);
            criteria.add(Restrictions.eq("code", code));

            List<Publisher> list = new ArrayList<Publisher>();
            for (Object publisher : criteria.list()) {
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
            Criteria criteria = session.createCriteria(Publisher.class);

            List<Publisher> list = new ArrayList<Publisher>();
            for (Object publisher : criteria.list()) {
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
            id = publisher.getId();
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
