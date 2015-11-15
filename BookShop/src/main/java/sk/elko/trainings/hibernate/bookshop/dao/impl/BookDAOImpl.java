package sk.elko.trainings.hibernate.bookshop.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import sk.elko.trainings.hibernate.bookshop.bo.Book;
import sk.elko.trainings.hibernate.bookshop.dao.BookDAO;
import sk.elko.trainings.hibernate.bookshop.dao.HibernateUtil;

public class BookDAOImpl implements BookDAO {

    @Override
    public Book get(Long id) {
        Session session = HibernateUtil.openSession();
        try {
            return (Book) session.get(Book.class, id);
        } finally {
            session.close();
        }
    }

    @Override
    public List<Book> find(String isbn) {
        Session session = HibernateUtil.openSession();
        try {
            Query query = session.getNamedQuery(BOOK_FIND_BY_ISBN);
            query.setString("isbn", isbn);

            List<Book> list = new ArrayList<Book>();
            for (Object book : query.list()) {
                list.add((Book) book);
            }
            return list;
        } finally {
            session.close();
        }
    }

    @Override
    public List<Book> getAll() {
        Session session = HibernateUtil.openSession();
        try {
            Query query = session.getNamedQuery(BOOK_GET_ALL);

            List<Book> list = new ArrayList<Book>();
            for (Object book : query.list()) {
                list.add((Book) book);
            }
            return list;
        } finally {
            session.close();
        }
    }

    @Override
    public Long create(Book book) {
        Long id = null;

        Session session = HibernateUtil.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            // insert / update
            session.saveOrUpdate(book);

            // get id
            Query query = session.getNamedQuery(BOOK_FIND_BY_ISBN);
            query.setString("isbn", book.getIsbn());
            Book newBook = (Book) query.uniqueResult();
            if (newBook != null) {
                id = newBook.getId();
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
