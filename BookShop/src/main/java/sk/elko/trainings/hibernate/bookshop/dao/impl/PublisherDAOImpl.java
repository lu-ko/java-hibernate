package sk.elko.trainings.hibernate.bookshop.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import sk.elko.trainings.hibernate.bookshop.bo.Publisher;
import sk.elko.trainings.hibernate.bookshop.dao.PublisherDAO;

@Repository
@Transactional
public class PublisherDAOImpl implements PublisherDAO {

    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Override
    public Publisher get(Long id) {
        return sessionFactory.getCurrentSession().get(Publisher.class, id);
    }

    @Override
    public List<Publisher> find(String code) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Publisher.class);
        criteria.add(Restrictions.eq("code", code));

        List<Publisher> list = new ArrayList<Publisher>();
        for (Object publisher : criteria.list()) {
            list.add((Publisher) publisher);
        }
        return list;
    }

    @Override
    public List<Publisher> getAll() {
        return hibernateTemplate.loadAll(Publisher.class);
    }

    @Override
    public Long create(Publisher publisher) {
        sessionFactory.getCurrentSession().saveOrUpdate(publisher);
        return publisher.getId();
    }

}
