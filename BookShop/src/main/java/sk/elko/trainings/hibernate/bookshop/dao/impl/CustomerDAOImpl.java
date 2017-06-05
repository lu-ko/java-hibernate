package sk.elko.trainings.hibernate.bookshop.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import sk.elko.trainings.hibernate.bookshop.bo.Customer;
import sk.elko.trainings.hibernate.bookshop.dao.CustomerDAO;

@Repository
@Transactional
public class CustomerDAOImpl implements CustomerDAO {

    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Override
    public Customer get(Long id) {
        return sessionFactory.getCurrentSession().get(Customer.class, id);
    }

    @Override
    public List<Customer> find(String email) {
        Customer example = new Customer();
        example.setEmail(email);
        return hibernateTemplate.findByExample(example);
    }

    @Override
    public List<Customer> getAll() {
        return hibernateTemplate.loadAll(Customer.class);
    }

    @Override
    public Long create(Customer customer) {
        sessionFactory.getCurrentSession().saveOrUpdate(customer);
        return customer.getId();
    }

}
