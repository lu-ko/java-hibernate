package sk.elko.trainings.hibernate.bookshop.dao;

import java.util.List;

import sk.elko.trainings.hibernate.bookshop.bo.Publisher;

public interface PublisherDAO {

    public Long create(Publisher publisher);

    public Publisher get(Long id);

    public List<Publisher> find(String code);

    public List<Publisher> getAll();

}
