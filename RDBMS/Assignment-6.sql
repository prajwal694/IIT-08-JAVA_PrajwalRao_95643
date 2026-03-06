hr > department
classwork_db > employee
 
mysql>  select distinct salespeople.snum from salespeople
        join orders on salespeople.snum = orders.snum;
+------+
| snum |
+------+
| 1007 |
| 1001 |
| 1004 |
| 1002 |
| 1003 |
+------+
5 rows in set (0.00 sec)

mysql> select cname, rating, city from customers order by rating desc LIMIT 1,1;

+----------+--------+----------+
| cname    | rating | city     |
+----------+--------+----------+
| Cisneros |    300 | San Jose |
+----------+--------+----------+
1 row in set (0.00 sec)


mysql> SELECT CONCAT('For the city ', city, ' the highest rating is ', MAX(rating)) AS customer_data
    from customers
    order by rating desc group by city ;

mysql> select onum, sum(onum)
    -> from orders
    -> group by onum
    -> Having sum(onum) > (select avg(onum) from orders);
+------+-----------+
| onum | sum(onum) |
+------+-----------+
| 3009 |      3009 |
| 3007 |      3007 |
| 3008 |      3008 |
| 3010 |      3010 |
| 3011 |      3011 |
+------+-----------+
5 rows in set (0.01 sec)    

mysql> select salespeople.snum
    -> from salespeople
    -> inner join orders on salespeople.snum = orders.snum
    -> where orders.amt > 3000;
+------+
| snum |
+------+
| 1001 |
| 1001 |
| 1002 |
+------+
3 rows in set (0.00 sec)

mysql> SELECT *
    -> FROM orders
    -> INNER JOIN customers ON orders.snum = customers.snum
    -> WHERE orders.amt = (SELECT MIN(amt) FROM orders);
+------+-------+------------+------+------+------+----------+----------+--------+------+
| onum | amt   | odate      | cnum | snum | cnum | cname    | city     | rating | snum |
+------+-------+------------+------+------+------+----------+----------+--------+------+
| 3001 | 18.69 | 1990-10-03 | 2008 | 1007 | 2008 | Cisneros | San Jose |    300 | 1007 |
+------+-------+------------+------+------+------+----------+----------+--------+------+
1 row in set (0.01 sec)



mysql> select orders.onum, customers.cname
    -> from orders, customers
    -> where orders.cnum = customers.cnum;
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