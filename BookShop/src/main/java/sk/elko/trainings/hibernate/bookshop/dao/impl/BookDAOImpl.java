package sk.elko.trainings.hibernate.bookshop.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import sk.elko.trainings.hibernate.bookshop.bo.Book;
import sk.elko.trainings.hibernate.bookshop.dao.BookDAO;

@Repository
@Transactional
public class BookDAOImpl implements BookDAO {

    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Override
    public Book get(Long id) {
        return sessionFactory.getCurrentSession().get(Book.class, id);
    }

    @Override
    public List<Book> find(String isbn) {
        Query query = sessionFactory.getCurrentSession().getNamedQuery("findByIsbn");
        query.setParameter("isbn", isbn);

        List<Book> list = new ArrayList<Book>();
        for (Object book : query.list()) {
            list.add((Book) book);
        }
        return list;
    }

    @Override
    public List<Book> getAll() {
        return hibernateTemplate.loadAll(Book.class);
    }

    @Override
    public Long create(Book book) {
        sessionFactory.getCurrentSession().saveOrUpdate(book);
        return book.getId();
    }

}
