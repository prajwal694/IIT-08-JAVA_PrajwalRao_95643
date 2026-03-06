
mysql> SELECT *
    -> FROM orders
    -> WHERE cnum = (
    ->     SELECT cnum
    ->     FROM customers
    ->     WHERE cname = 'Cisneros'
    -> );
+------+---------+------------+------+------+
| onum | amt     | odate      | cnum | snum |
+------+---------+------------+------+------+
| 3001 |   18.69 | 1990-10-03 | 2008 | 1007 |
| 3006 | 1098.16 | 1990-10-03 | 2008 | 1007 |
+------+---------+------------+------+------+
2 rows in set (0.01 sec)

mysql> SELECT *
    -> FROM salespeople s
    -> WHERE s.snum NOT IN (
    ->     SELECT c.snum
    ->     FROM customers c
    ->     WHERE c.city = s.city
    -> );
+------+---------+-----------+------+
| snum | sname   | city      | comm |
+------+---------+-----------+------+
| 1004 | Motika  | London    | 0.11 |
| 1007 | Rifkin  | Barcelona | 0.15 |
| 1003 | Axelrod | New York  | 0.10 |
+------+---------+-----------+------+
3 rows in set (0.00 sec)

mysql> SELECT *
    -> FROM orders
    -> WHERE cnum = (
    ->     SELECT cnum
    ->     FROM customers
    ->     WHERE cname = 'Motika'
    -> );
Empty set (0.00 sec)

mysql> SELECT *
    -> FROM orders
    -> WHERE cnum IN (
    ->     SELECT cnum
    ->     FROM customers
    ->     WHERE city = 'London'
    -> );
+------+---------+------------+------+------+
| onum | amt     | odate      | cnum | snum |
+------+---------+------------+------+------+
| 3003 |  767.19 | 1990-10-03 | 2001 | 1001 |
| 3008 | 4723.00 | 1990-10-04 | 2006 | 1001 |
| 3011 | 9891.88 | 1990-10-04 | 2006 | 1001 |
+------+---------+------------+------+------+
3 rows in set (0.00 sec)

mysql> SELECT s.sname, s.snum
    -> FROM salespeople s
    -> WHERE s.snum IN (
    ->     SELECT c.snum
    ->     FROM customers c
    ->     GROUP BY c.snum
    ->     HAVING COUNT(*) > 1
    -> );
+--------+------+
| sname  | snum |
+--------+------+
| Peel   | 1001 |
| Serres | 1002 |
+--------+------+
2 rows in set (0.01 sec)

mysql> SELECT *
    -> FROM customers
    -> WHERE rating > ANY (
    ->     SELECT rating
    ->     FROM customers
    ->     WHERE city = 'Rome'
    -> );
+------+----------+----------+--------+------+
| cnum | cname    | city     | rating | snum |
+------+----------+----------+--------+------+
| 2002 | Giovanni | Rome     |    200 | 1003 |
| 2003 | Liu      | San Jose |    200 | 1002 |
| 2004 | Grass    | Berlin   |    300 | 1002 |
| 2008 | Cisneros | San Jose |    300 | 1007 |
+------+----------+----------+--------+------+
4 rows in set (0.01 sec)

mysql> SELECT *
    -> FROM orders
    -> WHERE amt > ANY (
    ->     SELECT amt
    ->     FROM orders
    ->     WHERE odate = '1990-10-04'
    -> );
+------+---------+------------+------+------+
| onum | amt     | odate      | cnum | snum |
+------+---------+------------+------+------+
| 3003 |  767.19 | 1990-10-03 | 2001 | 1001 |
| 3002 | 1900.10 | 1990-10-03 | 2007 | 1004 |
| 3005 | 5160.45 | 1990-10-03 | 2003 | 1002 |
| 3006 | 1098.16 | 1990-10-03 | 2008 | 1007 |
| 3009 | 1713.23 | 1990-10-04 | 2002 | 1003 |
| 3008 | 4723.00 | 1990-10-04 | 2006 | 1001 |
| 3010 |  309.95 | 1990-10-04 | 2004 | 1002 |
| 3011 | 9891.88 | 1990-10-04 | 2006 | 1001 |
+------+---------+------------+------+------+
8 rows in set (0.00 sec)

mysql> SELECT *
    -> FROM customers
    -> WHERE rating > ALL (
    ->     SELECT rating
    ->     FROM customers
    ->     WHERE city = 'London'
    -> );
+------+----------+----------+--------+------+
| cnum | cname    | city     | rating | snum |
+------+----------+----------+--------+------+
| 2002 | Giovanni | Rome     |    200 | 1003 |
| 2003 | Liu      | San Jose |    200 | 1002 |
| 2004 | Grass    | Berlin   |    300 | 1002 |
| 2008 | Cisneros | San Jose |    300 | 1007 |
+------+----------+----------+--------+------+
4 rows in set (0.00 sec)
