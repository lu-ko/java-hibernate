package sk.elko.trainings.hibernate.bookshop.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "Address")
@Table(name = "ADDRESS")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID")
    private Long id;

    @Column(name = "STREET", nullable = false, length = 30)
    private String street;

    @Column(name = "STREET_NR", nullable = false, length = 10)
    private String streetNr;

    @Column(name = "CITY", nullable = false, length = 30)
    private String city;

    @Column(name = "ZIP", nullable = false, length = 10)
    private String zip;

    @Column(name = "COUNTRY", nullable = false, length = 20)
    private String country;

    // getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreetNr() {
        return streetNr;
    }

    public void setStreetNr(String streetNr) {
        this.streetNr = streetNr;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
