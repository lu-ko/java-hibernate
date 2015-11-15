package sk.elko.trainings.hibernate.bookshop.bo;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Chapter {

    @Column(name = "ORDER_NUMBER", nullable = false)
    private Integer orderNumber;

    @Column(name = "TITLE", nullable = true, length = 30)
    private String title;

    @Column(name = "NUM_OF_PAGES", nullable = true)
    private Integer numOfPages;

    // getters and setters

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getNumOfPages() {
        return numOfPages;
    }

    public void setNumOfPages(Integer numOfPages) {
        this.numOfPages = numOfPages;
    }

}
