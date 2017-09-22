# Miniprojekt-JDBC-MySQL
av Harald & Christoffer

#######################
Database sql creation commands:

CREATE DATABASE the_firm;

CREATE TABLE department (department_id int NOT NULL AUTO_INCREMENT, name varchar(32) UNIQUE NOT NULL, rent int NOT NULL, phone_number varchar(32) UNIQUE NOT NULL, PRIMARY KEY (department_id));



CREATE TABLE employee (employee_id int NOT NULL AUTO_INCREMENT, fname varchar(32) NOT NULL, lname varchar(32) NOT NULL, salary int NOT NULL, hire_date date, department_id int NOT NULL, FOREIGN KEY(department_id) REFERENCES department(department_id), PRIMARY KEY (`employee_id`)
);

INSERT INTO employee (fname,lname,salary,hire_date) VALUES   ('Harald', 'Billstein', 33000, '2009-10-11'),   ('Christoffer', 'Lönn', 32000, '2012-01-09'),   ('Isak', 'Wertwein', 30000, '2017-03-19'),   ('Anneli', 'Bertlid', 36000, '1999-08-12'),   ('Stefan', 'Lönn', 35000, '2011-05-09'), ('Marcus', 'Hallén', 33000, '2013-05-12'),   ('Benjamin', 'Rosman', 23000, '2010-03-27');

WORK IN PROGRESS
CREATE TABLE department (department_id int NOT NULL AUTO_INCREMENT, kitchen_duty tinyint(1) DEFAULT 1, PRIMARY KEY (department_id));
