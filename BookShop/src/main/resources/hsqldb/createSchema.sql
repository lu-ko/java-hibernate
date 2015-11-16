
    alter table book
        drop constraint FK_19ss4s5ji828yqdpgm0otr93s;

    alter table book
        drop constraint FK_6kcjrhl9yf3g278kp065falx9;

    alter table chapter
        drop constraint FK_2e1fctd0c12tev2t15j4u3qsr;

    alter table customer
        drop constraint FK_r8whbd0mf9er6vwfr0sclsxkd;

    alter table publisher
        drop constraint FK_jxn55xet4bty6xv3qfbw58si;

    alter table reservation
        drop constraint FK_69tigr4wbkrwhfef2l1mp6vgu;

    alter table reservation
        drop constraint FK_j2uyenywxkekrc52wq08413fo;

    drop table address if exists;

    drop table book if exists;

    drop table chapter if exists;

    drop table customer if exists;

    drop table product if exists;

    drop table publisher if exists;

    drop table reservation if exists;

    drop sequence hibernate_sequence;

    create table address (
        id bigint not null,
        city varchar(30) not null,
        country varchar(20) not null,
        street varchar(30) not null,
        street_nr varchar(10) not null,
        zip varchar(10) not null,
        primary key (id)
    );

    create table book (
        isbn varchar(20) not null,
        publish_date timestamp,
        id bigint not null,
        publisher_id bigint not null,
        primary key (id)
    );

    create table chapter (
        book bigint not null,
        num_of_pages integer,
        order_number integer not null,
        title varchar(30),
        index integer not null,
        primary key (book, index)
    );

    create table customer (
        id bigint not null,
        email varchar(50) not null,
        firstname varchar(50) not null,
        surname varchar(50) not null,
        titles varchar(30),
        address_id bigint not null,
        primary key (id)
    );

    create table product (
        id bigint not null,
        name varchar(50) not null,
        price integer,
        primary key (id)
    );

    create table publisher (
        id bigint not null,
        code varchar(20) not null,
        name varchar(50) not null,
        address_id bigint not null,
        primary key (id)
    );

    create table reservation (
        id bigint not null,
        created_when timestamp,
        quantity integer not null,
        customer_id bigint not null,
        product_id bigint not null,
        primary key (id)
    );

    alter table book
        add constraint UK_ehpdfjpu1jm3hijhj4mm0hx9h unique (isbn);

    alter table customer
        add constraint UK_dwk6cx0afu8bs9o4t536v1j5v unique (email);

    alter table publisher
        add constraint UK_pphoxl6ndros8lnhqunddl02g unique (code);

    alter table book
        add constraint FK_19ss4s5ji828yqdpgm0otr93s
        foreign key (publisher_id)
        references publisher;

    alter table book
        add constraint FK_6kcjrhl9yf3g278kp065falx9
        foreign key (id)
        references product;

    alter table chapter
        add constraint FK_2e1fctd0c12tev2t15j4u3qsr
        foreign key (book)
        references book;

    alter table customer
        add constraint FK_r8whbd0mf9er6vwfr0sclsxkd
        foreign key (address_id)
        references address;

    alter table publisher
        add constraint FK_jxn55xet4bty6xv3qfbw58si
        foreign key (address_id)
        references address;

    alter table reservation
        add constraint FK_69tigr4wbkrwhfef2l1mp6vgu
        foreign key (customer_id)
        references customer;

    alter table reservation
        add constraint FK_j2uyenywxkekrc52wq08413fo
        foreign key (product_id)
        references product;

    create sequence hibernate_sequence;
