# Miniprojekt JDBC MySQL


#####av Harald & Christoffer



**SQL commands**


```
# code block
DELIMITER //
```

```
# code block
CREATE DATABASE the_firm;
```

```
# code block
USE the_firm
```



```
# code block
CREATE TABLE department (department_id int NOT NULL AUTO_INCREMENT, name varchar(32) 
UNIQUE NOT NULL, rent int NOT NULL, phone_number varchar(32) UNIQUE NOT NULL, PRIMARY 
KEY (department_id));
```

```
# code block
CREATE TABLE employee (employee_id int NOT NULL AUTO_INCREMENT, fname varchar(32) NOT NULL, lname varchar(32) NOT NULL, salary int NOT NULL, hire_date 
date NOT NULL, department_id int NOT NULL, FOREIGN KEY(department_id) REFERENCES department(department_id), PRIMARY KEY (employee_id));

```

```
# code block
INSERT INTO department (name, rent, phone_number) VALUES ('Engineer',25000, '08-555 123 123'), ('Sales',15000,'0411-433 56'), 
('Developers', 30000, '08-765 543'), ('HR',19000,'046-123 432');
```

```
# code block
INSERT INTO employee (fname,lname,salary,hire_date,department_id) VALUES   ('Harald', 'Billstein', 33000, '2009-10-11',1), 
  ('Cristoffer', 'Lönn', 32000, '2012-01-09',2),   ('Isak', 'Wertwein', 30000, '2017-03-19',3),   ('Anneli', 'Bertlid', 36000, 
'1999-08-12',4),   ('Stefan', 'Lönn', 35000, '2011-05-09',1), ('Marcus', 'Hallén', 33000, '2013-05-12',2),   ('Benjamin', 'Rosman', 
23000, '2010-03-27',3);
```


```
# code block
SELECT name, COUNT(employee_id) FROM department JOIN employee ON department.department_id = employee.department_id GROUP BY name;
```
<img width="257" alt="test_result" src="https://user-images.githubusercontent.com/5534092/30777031-537283a2-a0b2-11e7-9195-32173c98da51.png"> 


```
# code block
WORK IN PROGRESS employee_id foreign key problem!
CREATE TABLE company_car (reg_nr char(6) NOT NULL UNIQUE, brand varchar(32) NOT NULL, model varchar(32) NOT NULL, purchase_price int 
NOT NULL, purchase_date date NOT NULL, employee_id int UNIQUE, PRIMARY KEY(reg_nr), FOREIGN KEY(employee_id) REFERENCES employee(employee_id) ON DELETE SET NULL);
```


```
# code block
INSERT INTO company_car (reg_nr, brand, model, purchase_price, purchase_date, employee_id) VALUES('abc123','VOLVO','V70',
250000,'2017-01-12',1),('edf456','SAAB','9-5',200000,'2016-10-11',2),('hij789','OPEL','ASTRA',140000,'2015-03-09',3),
('klmo12','PEUGEOT','208',107000,'2009-06-19',4);
```


```
# code block
CREATE PROCEDURE add_employee(IN firstname varchar(32), IN lastname varchar(32), IN salary int, IN department_id int) BEGIN INSERT INTO
 employee (fname, lname, salary, department_id) VALUES (firstname, lastname, salary, department_id); END
 ```
 
 
 ```
# code block
CREATE TRIGGER set_created_date BEFORE INSERT ON employee FOR EACH ROW
BEGIN
    SET NEW.hire_date = NOW();
END
 ```
 
 ```
# code block
DELIMITER ;
```

