package sk.elko.trainings.hibernate.bookshop.bo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name = "Reservation")
@Table(name = "RESERVATION")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "CUSTOMER_ID", insertable = false, updatable = false, nullable = false)
    private Customer customer;

    @Column(name = "CREATED_WHEN", nullable = true)
    private Date createdWhen;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID", insertable = false, updatable = false, nullable = false)
    private Product product;

    @Column(name = "QUANTITY", nullable = false)
    private Integer quantity;

    // getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Date getCreatedWhen() {
        return createdWhen;
    }

    public void setCreatedWhen(Date createdWhen) {
        this.createdWhen = createdWhen;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product book) {
        this.product = book;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

}
