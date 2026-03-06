select orders.onum, customers.cname
from orders, customers
where orders.cnum = customers.cnum;
+------+----------+
| onum | cname    |
+------+----------+
| 3001 | Cisneros |
| 3003 | Hoffman  |
| 3002 | Pereira  |
| 3005 | Liu      |
| 3006 | Cisneros |
| 3009 | Giovanni |
| 3007 | Grass    |
| 3008 | Clemens  |
| 3010 | Grass    |
| 3011 | Clemens  |
+------+----------+
10 rows in set (0.00 sec)

mysql> SELECT o.onum, c.cname, s.sname
    -> FROM orders o
    -> JOIN customers c ON o.cnum = c.cnum
    -> JOIN salespeople s ON o.snum = s.snum;
+------+----------+---------+
| onum | cname    | sname   |
+------+----------+---------+
| 3003 | Hoffman  | Peel    |
| 3009 | Giovanni | Axelrod |
| 3005 | Liu      | Serres  |
| 3010 | Grass    | Serres  |
| 3007 | Grass    | Serres  |
| 3011 | Clemens  | Peel    |
| 3008 | Clemens  | Peel    |
| 3006 | Cisneros | Rifkin  |
| 3001 | Cisneros | Rifkin  |
| 3002 | Pereira  | Motika  |
+------+----------+---------+
10 rows in set (0.01 sec)

mysql> SELECT c.cname, s.sname, s.comm
    -> FROM customers c
    -> JOIN salespeople s ON c.snum = s.snum
    -> WHERE s.comm > 0.12;
+----------+--------+------+
| cname    | sname  | comm |
+----------+--------+------+
| Liu      | Serres | 0.13 |
| Grass    | Serres | 0.13 |
| Cisneros | Rifkin | 0.15 |
+----------+--------+------+
3 rows in set (0.00 sec)

mysql> SELECT s.sname, SUM(o.amt * s.comm) AS commission_earned
    -> FROM orders o
    -> JOIN customers c ON o.cnum = c.cnum
    -> JOIN salespeople s ON o.snum = s.snum
    -> WHERE c.rating > 100
    -> GROUP BY s.sname;
+---------+-------------------+
| sname   | commission_earned |
+---------+-------------------+
| Serres  |          720.9995 |
| Rifkin  |          167.5275 |
| Axelrod |          171.3230 |
+---------+-------------------+


mysql> SELECT s1.sname AS salesperson1, s2.sname AS salesperson2, s1.city
    -> FROM salespeople s1
    -> JOIN salespeople s2 ON s1.city = s2.city AND s1.snum < s2.snum;
+--------------+--------------+--------+
| salesperson1 | salesperson2 | city   |
+--------------+--------------+--------+
| Peel         | Motika       | London |
+--------------+--------------+--------+
1 row in set (0.01 sec)

-- Optional Questions 
mysql> SELECT d.department_name, e.first_name
    -> FROM departments d
    -> JOIN employees e ON d.manager_id = e.employee_id;
Empty set (0.05 sec)

mysql> SELECT d.department_name, e.first_name
    -> FROM departments d
    -> JOIN employees e ON d.manager_id = e.employee_id;
Empty set (0.00 sec)

mysql> SELECT c.country_name, l.city, d.department_name
    -> FROM countries c
    -> JOIN locations l ON c.country_id = l.country_id
    -> JOIN departments d ON l.location_id = d.location_id;
Empty set (0.00 sec)


mysql> SELECT j.job_title, d.department_name, e.last_name, jh.start_date
    -> FROM job_history jh
    -> JOIN jobs j ON jh.job_id = j.job_id
    -> JOIN employees e ON jh.employee_id = e.employee_id
    -> JOIN departments d ON jh.department_id = d.department_id
    -> WHERE jh.start_date BETWEEN '2011-01-01' AND '2015-12-31';
Empty set (0.00 sec)


mysql> SELECT j.job_title, AVG(e.salary) AS avg_salary
    -> FROM employees e
    -> JOIN jobs j ON e.job_id = j.job_id
    -> GROUP BY j.job_title;
Empty set, 107 warnings (0.01 sec)

mysql> SELECT j.job_title, e.first_name, (j.max_salary - e.salary) AS diff
    -> FROM employees e
    -> JOIN jobs j ON e.job_id = j.job_id;
Empty set, 107 warnings (0.00 sec)

mysql>
mysql> SELECT e.last_name, j.job_title
    -> FROM employees e
    -> JOIN jobs j ON e.job_id = j.job_id
    -> WHERE e.commission_pct IS NOT NULL AND e.department_id IS NULL;
Empty set (0.00 sec)