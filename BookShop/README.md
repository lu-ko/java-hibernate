# Book Shop

## About:

Simple Java Maven project to test some features in Hibernate.

**Created:** Jan 2013

**Last update:** Nov 2015

**Keywords:** [Hibernate 5.0.2.Final](http://hibernate.org/orm/), [HyperSQL Database Engine (HSQLDB)](http://hsqldb.org/), [Maven](https://maven.apache.org/), [TestNG](http://testng.org/), for more details please see [maven-dependencies.txt](https://github.com/lu-ko/java-hibernate/blob/master/BookShop/maven-dependencies.txt)

## Setup:

Next steps are relevant for [Eclipse IDE](http://www.eclipse.org/downloads/packages/) with [Maven plugin](http://www.eclipse.org/m2e/) and [TestNG plugin](http://testng.org/doc/eclipse.html).

1. Import this Maven project to Eclipse IDE

2. Install & configure [HyperSQL Database Engine (HSQLDB)](http://hsqldb.org/)

3. Open [startHsqldb.bat](https://github.com/lu-ko/java-hibernate/blob/master/BookShop/src/main/resources/hsqldb/startHsqldb.bat) file and set absolute path to your HSQLDB instance

4. Start DB with [startHsqldb.bat](https://github.com/lu-ko/java-hibernate/blob/master/BookShop/src/main/resources/hsqldb/startHsqldb.bat)

5. ```mvn clean install```

## Notes:

* A lot of functionality is missing now because project is not finished.

* There is no UI yet.

* Generated SQL script to drop and create schema: ```target/createSchema.sql```

