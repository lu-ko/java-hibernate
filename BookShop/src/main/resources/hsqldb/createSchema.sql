
    create table ADDRESS (
        ID bigint not null,
        CITY varchar(30) not null,
        COUNTRY varchar(20) not null,
        STREET varchar(30) not null,
        STREET_NR varchar(10) not null,
        ZIP varchar(10) not null,
        primary key (ID)
    );

    create table BOOK (
        ISBN varchar(20) not null,
        PUBLISH_DATE timestamp,
        ID bigint not null,
        PUBLISHER_ID bigint not null,
        primary key (ID),
        unique (ISBN)
    );

    create table CHAPTER (
        Book_ID bigint not null,
        NUM_OF_PAGES integer,
        ORDER_NUMBER integer not null,
        TITLE varchar(30),
        INDEX integer not null,
        primary key (Book_ID, INDEX)
    );

    create table CUSTOMER (
        ID bigint not null,
        EMAIL varchar(50) not null,
        FIRSTNAME varchar(50) not null,
        SURNAME varchar(50) not null,
        TITLES varchar(30),
        ADDRESS_ID bigint not null,
        primary key (ID),
        unique (EMAIL)
    );

    create table PRODUCT (
        ID bigint not null,
        NAME varchar(50) not null,
        PRICE integer,
        primary key (ID)
    );

    create table PUBLISHER (
        ID bigint not null,
        CODE varchar(20) not null,
        NAME varchar(50) not null,
        ADDRESS_ID bigint not null,
        primary key (ID),
        unique (CODE)
    );

    create table RESERVATION (
        ID bigint not null,
        CREATED_WHEN timestamp,
        QUANTITY integer not null,
        CUSTOMER_ID bigint not null,
        PRODUCT_ID bigint not null,
        primary key (ID)
    );

    alter table BOOK 
        add constraint FK1F32E94EB9FE7A 
        foreign key (ID) 
        references PRODUCT;

    alter table BOOK 
        add constraint FK1F32E9AE3928AA 
        foreign key (PUBLISHER_ID) 
        references PUBLISHER;

    alter table CHAPTER 
        add constraint FK56D8082DCFB4CEAA 
        foreign key (Book_ID) 
        references BOOK;

    alter table CUSTOMER 
        add constraint FK52C76FDE6CB2A1AA 
        foreign key (ADDRESS_ID) 
        references ADDRESS;

    alter table PUBLISHER 
        add constraint FKFC5DB1DC6CB2A1AA 
        foreign key (ADDRESS_ID) 
        references ADDRESS;

    alter table RESERVATION 
        add constraint FK2328D7ACB736BBCA 
        foreign key (PRODUCT_ID) 
        references PRODUCT;

    alter table RESERVATION 
        add constraint FK2328D7AC7EDA668A 
        foreign key (CUSTOMER_ID) 
        references CUSTOMER;

    create sequence hibernate_sequence;
    