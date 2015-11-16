package sk.elko.trainings.hibernate.bookshop.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import sk.elko.trainings.hibernate.bookshop.bo.Customer;
import sk.elko.trainings.hibernate.bookshop.dao.CustomerDAO;
import sk.elko.trainings.hibernate.bookshop.dao.HibernateUtil;

public class CustomerDAOImpl implements CustomerDAO {

    @Override
    public Customer get(Long id) {
        Session session = HibernateUtil.openSession();
        try {
            return (Customer) session.get(Customer.class, id);
        } finally {
            session.close();
        }
    }

    @Override
    public List<Customer> find(String email) {
        Session session = HibernateUtil.openSession();
        try {
            Criteria criteria = session.createCriteria(Customer.class);
            criteria.add(Restrictions.eq("email", email));

            List<Customer> list = new ArrayList<Customer>();
            for (Object customer : criteria.list()) {
                list.add((Customer) customer);
            }
            return list;
        } finally {
            session.close();
        }
    }

    @Override
    public List<Customer> getAll() {
        Session session = HibernateUtil.openSession();
        try {
            Criteria criteria = session.createCriteria(Customer.class);

            List<Customer> list = new ArrayList<Customer>();
            for (Object customer : criteria.list()) {
                list.add((Customer) customer);
            }
            return list;
        } finally {
            session.close();
        }
    }

    @Override
    public Long create(Customer customer) {
        Long id = null;

        Session session = HibernateUtil.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            // insert / update
            session.saveOrUpdate(customer);
            id = customer.getId();
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
