package sk.elko.trainings.hibernate.bookshop.dao;

import java.util.List;

import sk.elko.trainings.hibernate.bookshop.bo.Book;

public interface BookDAO {

    public static final String BOOK_GET_ALL = "Book.getAll";
    public static final String BOOK_FIND_BY_ISBN = "Book.findByIsbn";

    public Long create(Book book);

    public Book get(Long id);

    public List<Book> find(String isbn);

    public List<Book> getAll();

}
