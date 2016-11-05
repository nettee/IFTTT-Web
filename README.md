# Setup

## Install MySQL server

For Ubuntu distribution of Linux, you can follow the instructions [in this page](http://dev.mysql.com/doc/mysql-apt-repo-quick-guide/en/).

+ user: root
+ password: 123456

### Create database

```Shell
mysql -u root -p
```

```SQL
create database ifttt;
use ifttt
```

```Shell
mysql ifttt -u root -p
```

```Shell
mysql ifttt < src/main/resources/schema.sql -u root -p
```

## Run

```Shell
mvn jetty:run
```

## Deploy

```Shell
mvn package
```

Copy target/ifttt-web.war to Tomcat webapps/ directory.
