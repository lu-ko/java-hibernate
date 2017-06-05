package sk.elko.trainings.hibernate.bookshop.dao;

import java.util.List;

import sk.elko.trainings.hibernate.bookshop.bo.Book;

public interface BookDAO {

    public Long create(Book book);

    public Book get(Long id);

    public List<Book> find(String isbn);

    public List<Book> getAll();

}
