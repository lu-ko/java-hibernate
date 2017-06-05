package sk.elko.trainings.hibernate.bookshop.bo;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

@Entity(name = "Book")
@Table(name = "BOOK")
@NamedQueries({
        @NamedQuery(
                name = "findByIsbn",
                query = "from Book b where b.isbn = :isbn"
        )
})
public class Book extends Product {

    @Column(name = "ISBN", nullable = false, unique = true, length = 20)
    private String isbn;

    @Column(name = "PUBLISH_DATE", nullable = true)
    private Date publishDate;

    @ManyToOne
    @JoinColumn(name = "PUBLISHER_ID", insertable = true, updatable = true, nullable = false)
    private Publisher publisher;

    @ElementCollection(targetClass = Chapter.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "CHAPTER")
    @OrderColumn(name = "INDEX")
    private List<Chapter> chapters;

    // getters and setters

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public List<Chapter> getChapters() {
        if (chapters == null) {
            chapters = new ArrayList<Chapter>();
        }
        return chapters;
    }

    public void setChapters(List<Chapter> chapters) {
        this.chapters = chapters;
    }
}
