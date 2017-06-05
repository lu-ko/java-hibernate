package sk.elko.trainings.hibernate.bookshop;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import sk.elko.trainings.hibernate.bookshop.bo.Address;
import sk.elko.trainings.hibernate.bookshop.bo.Book;
import sk.elko.trainings.hibernate.bookshop.bo.Chapter;
import sk.elko.trainings.hibernate.bookshop.bo.Customer;
import sk.elko.trainings.hibernate.bookshop.bo.Publisher;
import sk.elko.trainings.hibernate.bookshop.config.AppConfig;
import sk.elko.trainings.hibernate.bookshop.dao.BookDAO;
import sk.elko.trainings.hibernate.bookshop.dao.CustomerDAO;
import sk.elko.trainings.hibernate.bookshop.dao.PublisherDAO;

@ContextConfiguration(classes = {AppConfig.class})
public class BasicOperationsAfterDeploymentTest extends AbstractTestNGSpringContextTests {
    private static final Log log = LogFactory.getLog(BasicOperationsAfterDeploymentTest.class);

    @Autowired
    private BookDAO bookDAO;

    @Autowired
    private CustomerDAO customerDAO;

    @Autowired
    private PublisherDAO publisherDAO;

    @Test
    public void test01a_createPublishers() {
        List<Publisher> publishers = publisherDAO.getAll();
        log.info("test01a_createPublishers - Existing publishers: " + publishers.size());

        Date date = new Date();
        Publisher newPublisher = new Publisher();
        newPublisher.setCode("ABC-" + date.getTime());
        newPublisher.setName("ABC Publishing inc.");
        newPublisher.setAddress(new Address());
        newPublisher.getAddress().setStreet("Main Street");
        newPublisher.getAddress().setStreetNr("1234/6");
        newPublisher.getAddress().setCity("Prague");
        newPublisher.getAddress().setZip("111 00");
        newPublisher.getAddress().setCountry("Czech Republic");

        Long publisherId = publisherDAO.create(newPublisher);
        log.info("test01a_createPublishers - Publisher '" + newPublisher.getCode() + "' created with ID = " + publisherId);

        Publisher publisher = publisherDAO.get(publisherId);
        assertNotNull(publisher);
        assertEquals(publisher.getId(), publisherId);
        assertEquals(publisher.getCode(), newPublisher.getCode());
        assertEquals(publisher.getName(), newPublisher.getName());
        assertNotNull(publisher.getAddress());
        assertEquals(publisher.getAddress().getStreet(), newPublisher.getAddress().getStreet());
        assertEquals(publisher.getAddress().getStreetNr(), newPublisher.getAddress().getStreetNr());
        assertEquals(publisher.getAddress().getCity(), newPublisher.getAddress().getCity());
        assertEquals(publisher.getAddress().getZip(), newPublisher.getAddress().getZip());
        assertEquals(publisher.getAddress().getCountry(), newPublisher.getAddress().getCountry());
    }

    @Test(dependsOnMethods = "test01a_createPublishers")
    public void test01b_findPublishers() {
        List<Publisher> publishersAll = publisherDAO.getAll();
        log.info("test01b_findPublishers - Existing all publishers: " + publishersAll.size());
        assertEquals(publishersAll.size(), 1);

        List<Publisher> publishersInvalid = publisherDAO.find("invalid-code");
        log.info("test01b_findPublishers - Invalid code publishers: " + publishersInvalid.size());
        assertEquals(publishersInvalid.size(), 0);

        String expectedCode = publishersAll.get(0).getCode();
        List<Publisher> publishersValid = publisherDAO.find(expectedCode);
        log.info("test01b_findPublishers - Valid code(" + expectedCode + ") publishers: " + publishersValid.size());
        assertEquals(publishersValid.size(), 1);
        // TODO maybe print
    }

    @Test(dependsOnMethods = "test01b_findPublishers")
    public void test02a_createBooks() {
        log.info("test02a_createBooks - Existing books: " + bookDAO.getAll().size());

        Long publisherId = publisherDAO.getAll().get(0).getId();

        Date date = new Date();
        int random = new Random().nextInt(1000);
        Book newBook = new Book();
        newBook.setIsbn("" + date.getTime());
        newBook.setName("My book " + date.hashCode());
        newBook.setPrice(random);
        newBook.setPublishDate(date);
        newBook.setPublisher(new Publisher());
        newBook.getPublisher().setId(publisherId);
        newBook.getPublisher().setName("new publisher name, test " + random); // update test

        Chapter chapter1 = new Chapter();
        chapter1.setOrderNumber(1);
        chapter1.setTitle("Prolog");
        chapter1.setNumOfPages(5);
        newBook.getChapters().add(chapter1);
        Chapter chapter2 = new Chapter();
        chapter2.setOrderNumber(2);
        chapter2.setTitle("First Chapter");
        newBook.getChapters().add(chapter2);

        Long bookId = bookDAO.create(newBook);
        log.info("test02a_createBooks - Book '" + newBook.getIsbn() + "' created with ID = " + bookId);

        Book book = bookDAO.get(bookId);
        assertNotNull(book);
        assertEquals(book.getId(), bookId);
        assertEquals(book.getIsbn(), newBook.getIsbn());
        assertEquals(book.getName(), newBook.getName());
        assertEquals(book.getPrice(), newBook.getPrice());
        assertEquals(book.getPublishDate(), newBook.getPublishDate());
        assertNotNull(book.getPublisher());
        assertEquals(book.getPublisher().getId(), publisherId);
        assertEquals(book.getPublisher().getName(), "ABC Publishing inc."); // update test
        assertNotNull(book.getChapters());
        assertEquals(book.getChapters().size(), newBook.getChapters().size());
    }

    @Test(dependsOnMethods = "test02a_createBooks")
    public void test02b_findBooks() {
        List<Book> booksAll = bookDAO.getAll();
        log.info("test02b_findBooks - All books: " + booksAll.size());
        assertEquals(booksAll.size(), 1);
        printBook(booksAll);

        List<Book> booksInvalid = bookDAO.find("invalid-isbn");
        log.info("test02b_findBooks - Invalid ISBN books: " + booksInvalid.size());
        assertEquals(booksInvalid.size(), 0);

        String expectedIsbn = booksAll.get(0).getIsbn();
        List<Book> booksValid = bookDAO.find(expectedIsbn);
        log.info("test02b_findBooks - Valid ISBN(" + expectedIsbn + ") books: " + booksValid.size());
        assertEquals(booksValid.size(), 1);
        printBook(booksValid);
    }

    private static void printBook(Book book) {
        if (book == null) {
            log.info("Book: NULL");
        } else {
            log.info("Book: ISBN=" + book.getIsbn() + //
                    ", NAME=" + book.getName() + //
                    ", DATE=" + book.getPublishDate() + //
                    ", PUBLISHER=" + book.getPublisher().getName() + //
                    ", CHAPTERS=" + book.getChapters().size());
        }
    }

    private static void printBook(List<Book> books) {
        log.info("=Books=");
        for (Book book : books) {
            printBook(book);
        }
    }

    @Test
    public void test03a_createCustomers() {
        List<Customer> customers = customerDAO.getAll();
        log.info("test03a_createCustomers - Existing customers: " + customers.size());

        Date date = new Date();
        Customer newCustomer = new Customer();
        newCustomer.setEmail("customer_" + date.getTime() + "@example.sk");
        newCustomer.setFirstname("First name");
        newCustomer.setSurname("Surname");
        newCustomer.setAddress(new Address());
        newCustomer.getAddress().setStreet("Customer Street");
        newCustomer.getAddress().setStreetNr("154/A");
        newCustomer.getAddress().setCity("Kosice");
        newCustomer.getAddress().setZip("123 45");
        newCustomer.getAddress().setCountry("Slovakia");

        Long customerId = customerDAO.create(newCustomer);
        log.info("test03a_createCustomers - Customer '" + newCustomer.getEmail() + "' created with ID = " + customerId);

        Customer customer = customerDAO.get(customerId);
        assertNotNull(customer);
        assertEquals(customer.getId(), customerId);
        assertEquals(customer.getEmail(), newCustomer.getEmail());
        assertEquals(customer.getFirstname(), newCustomer.getFirstname());
        assertEquals(customer.getSurname(), newCustomer.getSurname());
        assertEquals(customer.getTitles(), newCustomer.getTitles());
        assertNotNull(customer.getAddress());
        assertEquals(customer.getAddress().getStreet(), newCustomer.getAddress().getStreet());
        assertEquals(customer.getAddress().getStreetNr(), newCustomer.getAddress().getStreetNr());
        assertEquals(customer.getAddress().getCity(), newCustomer.getAddress().getCity());
        assertEquals(customer.getAddress().getZip(), newCustomer.getAddress().getZip());
        assertEquals(customer.getAddress().getCountry(), newCustomer.getAddress().getCountry());
    }

    @Test(dependsOnMethods = "test03a_createCustomers")
    public void test03b_findCustomers() {
        List<Customer> customersAll = customerDAO.getAll();
        log.info("test03b_findCustomers - Existing all customers: " + customersAll.size());
        assertEquals(customersAll.size(), 1);

        List<Customer> customersInvalid = customerDAO.find("invalid-email");
        log.info("test03b_findCustomers - Invalid email customers: " + customersInvalid.size());
        assertEquals(customersInvalid.size(), 0);

        String expectedEmail = customersAll.get(0).getEmail();
        List<Customer> customersValid = customerDAO.find(expectedEmail);
        log.info("test03b_findCustomers - Valid email(" + expectedEmail + ") customers: " + customersValid.size());
        assertEquals(customersValid.size(), 1);
        // TODO maybe print
    }

}
