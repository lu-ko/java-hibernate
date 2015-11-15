package sk.elko.trainings.hibernate.bookshop.dao;

import java.util.List;

import sk.elko.trainings.hibernate.bookshop.bo.Customer;

public interface CustomerDAO {

    public Long create(Customer customer);

    public Customer get(Long id);

    public List<Customer> find(String email);

    public List<Customer> getAll();

}
