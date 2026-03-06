mysql> select pname, color from parts where city = 'london';
+-------+-------+
| pname | color |
+-------+-------+
| Nut   | Red   |
| Screw | Red   |
| Dog   | Red   |
+-------+-------+
3 rows in set (0.06 sec)

mysql> select * from supplier where city = 'London';
+-----+--------+--------+--------+
| Sno | Sname  | status | city   |
+-----+--------+--------+--------+
| S1  | Smith  |     20 | London |
| S4  | Clarke |     20 | London |
+-----+--------+--------+--------+
2 rows in set (0.01 sec)

mysql> select * from supplier where city in('Paris', 'Athens');
+-----+-------+--------+--------+
| Sno | Sname | status | city   |
+-----+-------+--------+--------+
| S2  | Jones |     10 | Paris  |
| S3  | Blake |     30 | Paris  |
| S5  | Adams |     30 | Athens |
+-----+-------+--------+--------+
3 rows in set (0.00 sec)

mysql> select city from jobs where city = 'Athens';
+--------+
| city   |
+--------+
| Athens |
| Athens |
+--------+
2 rows in set (0.00 sec)

-- OR

mysql> select * from jobs where city in ('Athens');
+-----+---------+--------+
| Jno | Jname   | city   |
+-----+---------+--------+
| J3  | Reader  | Athens |
| J4  | Console | Athens |
+-----+---------+--------+
2 rows in set (0.00 sec)

mysql> select pname, weight from parts where weight in (12, 14);
+-------+--------+
| pname | weight |
+-------+--------+
| Nut   |     12 |
| Screw |     14 |
| Cam   |     12 |
+-------+--------+
3 rows in set (0.00 sec)

mysql> select * from supplier where status >= 20;
+-----+--------+--------+--------+
| Sno | Sname  | status | city   |
+-----+--------+--------+--------+
| S1  | Smith  |     20 | London |
| S3  | Blake  |     30 | Paris  |
| S4  | Clarke |     20 | London |
| S5  | Adams  |     30 | Athens |
+-----+--------+--------+--------+
4 rows in set (0.00 sec)

mysql> select * from supplier where city != 'london';
+-----+-------+--------+--------+
| Sno | Sname | status | city   |
+-----+-------+--------+--------+
| S2  | Jones |     10 | Paris  |
| S3  | Blake |     30 | Paris  |
| S5  | Adams |     30 | Athens |
+-----+-------+--------+--------+
3 rows in set (0.00 sec)

mysql> select city from supplier;
+--------+
| city   |
+--------+
| London |
| Paris  |
| Paris  |
| London |
| Athens |
+--------+
5 rows in set (0.00 sec)

mysql> select sno,sname,city from supplier ORDER BY city DESC;
+-----+--------+--------+
| sno | sname  | city   |
+-----+--------+--------+
| S2  | Jones  | Paris  |
| S3  | Blake  | Paris  |
| S1  | Smith  | London |
| S4  | Clarke | London |
| S5  | Adams  | Athens |
+-----+--------+--------+
5 rows in set (0.01 sec)

mysql> select * from parts order by city asc, pname asc;
+-----+-------+-------+--------+--------+
| Pno | Pname | color | weight | city   |
+-----+-------+-------+--------+--------+
| P6  | Dog   | Red   |     19 | London |
| P1  | Nut   | Red   |     12 | London |
| P4  | Screw | Red   |     14 | London |
| P2  | Bolt  | Green |     17 | Paris  |
| P5  | Cam   | Blue  |     12 | Paris  |
| P3  | Screw | Blue  |     17 | Rome   |
+-----+-------+-------+--------+--------+
6 rows in set (0.00 sec)

mysql> select * from supplier where status between 10 and 20;
+-----+--------+--------+--------+
| Sno | Sname  | status | city   |
+-----+--------+--------+--------+
| S1  | Smith  |     20 | London |
| S2  | Jones  |     10 | Paris  |
| S4  | Clarke |     20 | London |
+-----+--------+--------+--------+
3 rows in set (0.00 sec)

mysql> select pname, weight
    -> from parts
    -> where weight not between 10 and 20;
Empty set (0.00 sec)