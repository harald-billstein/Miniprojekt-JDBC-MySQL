# Miniprojekt-JDBC-MySQL
av Harald & Christoffer

#######################
Database sql creation commands:

CREATE DATABASE the_firm;

CREATE TABLE department (department_id int NOT NULL AUTO_INCREMENT, name varchar(32) UNIQUE NOT NULL, rent int NOT NULL, phone_number varchar(32) UNIQUE NOT NULL, PRIMARY KEY (department_id));



CREATE TABLE employee (employee_id int NOT NULL AUTO_INCREMENT, fname varchar(32) NOT NULL, lname varchar(32) NOT NULL, salary int NOT NULL, hire_date date NOT NULL, department_id int NOT NULL, FOREIGN KEY(department_id) REFERENCES department(department_id), PRIMARY KEY (`employee_id`)
);


INSERT INTO department (name, rent, phone_number) VALUES ('Engineer',25000, '08-555 123 123'), ('Sales',15000,'0411-433 56'), ('Developers', 30000, '08-765 543'), ('HR',19000,'046-123 432');

INSERT INTO employee (fname,lname,salary,hire_date,department_id) VALUES   ('Harald', 'Billstein', 33000, '2009-10-11',1),   ('Cristoffer', 'Lönn', 32000, '2012-01-09',2),   ('Isak', 'Wertwein', 30000, '2017-03-19',3),   ('Anneli', 'Bertlid', 36000, '1999-08-12',4),   ('Stefan', 'Lönn', 35000, '2011-05-09',1), ('Marcus', 'Hallén', 33000, '2013-05-12',2),   ('Benjamin', 'Rosman', 23000, '2010-03-27',3);


TEST TABLE!!!!
MariaDB [the_firm]> select name, COUNT(employee_id) FROM department JOIN employee ON department.department_id = employee.department_id GROUP BY name//
+------------+--------------------+
| name       | COUNT(employee_id) |
+------------+--------------------+
| Developers |                  2 |
| Engineer   |                  2 |
| HR         |                  1 |
| Sales      |                  2 |
+------------+--------------------+
4 rows in set (0,00 sec)



WORK IN PROGRESS

