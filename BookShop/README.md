# Book Shop

## About:

Simple Java Maven project to test some features in Hibernate.

**Created:** Jan 2013

**Last update:** Nov 2015

## Keywords:

* [Hibernate](http://hibernate.org/orm/) - 4.1.0.Final

* [HyperSQL Database Engine (HSQLDB)](http://hsqldb.org/)

## Setup:

Next steps are relevant for [Eclipse IDE](http://www.eclipse.org/downloads/packages/) with [Maven plugin](http://www.eclipse.org/m2e/) and [TestNG plugin](http://testng.org/doc/eclipse.html).

1. Import this Maven project to Eclipse IDE

2. Install & configure [HyperSQL Database Engine (HSQLDB)](http://hsqldb.org/)

3. Open [startHsqldb.bat] file and set absolute path to your HSQLDB instance

4. (Optional) Run [Test] as TestNG Test

## Notes:

* A lot of functionality is missing now because project is not finished.

* There is no UI yet.

* You can generate current DB schema with ```mvn hibernate3:hbm2ddl```. Output file: ```target/sql/hibernate3/createSchema.sql```

