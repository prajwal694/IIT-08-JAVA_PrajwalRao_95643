Question 1 =>
DELIMITER $$

CREATE PROCEDURE calc_rectangle()
BEGIN
    -- Define variables for length, width, perimeter, and area
    DECLARE L DECIMAL(10,2) DEFAULT 12.5;
    DECLARE W DECIMAL(10,2) DEFAULT 8.3;
    DECLARE perimeter DECIMAL(10,2);
    DECLARE area DECIMAL(10,2);

    -- Calculate perimeter and area
    SET perimeter = 2 * (L + W);
    SET area = L * W;

    -- Display results
    SELECT L AS Length,
           W AS Width,
           perimeter AS Perimeter,
           area AS Area;
END$$

DELIMITER ;

CALL calc_rectangle();


mysql> CALL calc_rectangle();
+--------+-------+-----------+--------+
| Length | Width | Perimeter | Area   |
+--------+-------+-----------+--------+
|  12.50 |  8.30 |     41.60 | 103.75 |
+--------+-------+-----------+--------+
1 row in set (0.00 sec)




Question 2 =>

CREATE TABLE temp (
 num INT,
 square INT,
`cube` INT
);

DELIMITER $$

CREATE PROCEDURE insert_num_cube()
BEGIN
DECLARE v_num INT DEFAULT 5;
DECLARE v_sq INT;
DECLARE v_cb INT;
SET v_sq = v_num * v_num;
SET v_cb = v_num * v_num * v_num;
INSERT INTO temp (num, square, `cube`)
VALUES (v_num, v_sq, v_cb);

SELECT * FROM temp;
END$$

DELIMETER ;

mysql> CALL insert_num_cube();
+------+--------+------+
| num  | square | cube |
+------+--------+------+
|    5 |     25 |  125 |
+------+--------+------+
1 row in set (0.03 sec)

Query OK, 0 rows affected (0.03 sec)



QUESTION 3 =>

DELIMITER $$

DROP PROCEDURE IF EXISTS fahrenheit_to_celsius$$
CREATE PROCEDURE fahrenheit_to_celsius(IN f_value DECIMAL(10,2), OUT c_value DECIMAL(10,2))
BEGIN
    SET c_value = (f_value - 32) * 5 / 9;
END$$


DROP PROCEDURE IF EXISTS celsius_to_fahrenheit$$
CREATE PROCEDURE celsius_to_fahrenheit(IN c_value DECIMAL(10,2), OUT f_value DECIMAL(10,2))
BEGIN
    SET f_value = (9/5) * c_value + 32;
END$$


DROP PROCEDURE IF EXISTS convert_temperature$$
CREATE PROCEDURE convert_temperature(
    IN temp_value DECIMAL(10,2),
    IN from_scale VARCHAR(1),
    OUT result DECIMAL(10,2)
)
BEGIN
    IF UPPER(from_scale) = 'F' THEN
    SET result = (temp_value - 32) * 5 / 9;
    ELSEIF UPPER(from_scale) = 'C' THEN
     SET result = (9/5) * temp_value + 32;
    ELSE
        SET result = NULL;
    END IF;
END$$

DELIMITER ;

mysql> CALL fahrenheit_to_celsius(100, @c);
Query OK, 0 rows affected, 1 warning (0.01 sec)

mysql> SELECT @c AS 'Temperature in Celsius';
+------------------------+
| Temperature in Celsius |
+------------------------+
|                  37.78 |
+------------------------+
1 row in set (0.00 sec)


mysql> CALL celsius_to_fahrenheit(25, @f);
Query OK, 0 rows affected (0.00 sec)

mysql> SELECT @f AS 'Temperature in Fahrenheit';
+---------------------------+
| Temperature in Fahrenheit |
+---------------------------+
|                     77.00 |
+---------------------------+
1 row in set (0.00 sec)



mysql> CALL convert_temperature(98.6, 'F', @result);
Query OK, 0 rows affected (0.00 sec)

mysql> SELECT @result AS 'Celsius Result';
+----------------+
| Celsius Result |
+----------------+
|          37.00 |
+----------------+
1 row in set (0.00 sec)



mysql> CALL convert_temperature(37, 'C', @result);
Query OK, 0 rows affected (0.00 sec)

mysql> SELECT @result AS 'Fahrenheit Result';
+-------------------+
| Fahrenheit Result |
+-------------------+
|             98.60 |
+-------------------+
1 row in set (0.00 sec)



QUESTION 4 =>

DELIMITER $$
DROP PROCEDURE IF EXISTS convert_inches$$
CREATE PROCEDURE convert_inches(IN total_inches INT)
BEGIN
    DECLARE v_yards INT;
    DECLARE v_feet INT;
    DECLARE v_remaining_inches INT;
    SET v_yards = FLOOR(total_inches / 36);
    SET v_remaining_inches = total_inches % 36;
    SET v_feet = FLOOR(v_remaining_inches / 12);
    SET v_remaining_inches = v_remaining_inches % 12;

    SELECT 
        total_inches AS 'Total Inches',
        v_yards AS 'Yards',
        v_feet AS 'Feet',
        v_remaining_inches AS 'Inches';

SELECT CONCAT(
        total_inches, ' inches = ',
        v_yards, ' yard(s), ',
        v_feet, ' foot/feet, and ',
        v_remaining_inches, ' inch(es)'
    ) AS 'Conversion Result';
END$$
DELIMITER ;

1) mysql> CALL convert_inches(124);
+--------------+-------+------+--------+
| Total Inches | Yards | Feet | Inches |
+--------------+-------+------+--------+
|          124 |     3 |    1 |      4 |
+--------------+-------+------+--------+
1 row in set (0.00 sec)

+-----------------------------------------------------+
| Conversion Result                                   |
+-----------------------------------------------------+
| 124 inches = 3 yard(s), 1 foot/feet, and 4 inch(es) |
+-----------------------------------------------------+
1 row in set (0.01 sec)

Query OK, 0 rows affected (0.01 sec)


2) mysql> CALL convert_inches(100);
+--------------+-------+------+--------+
| Total Inches | Yards | Feet | Inches |
+--------------+-------+------+--------+
|          100 |     2 |    2 |      4 |
+--------------+-------+------+--------+
1 row in set (0.00 sec)

+-----------------------------------------------------+
| Conversion Result                                   |
+-----------------------------------------------------+
| 100 inches = 2 yard(s), 2 foot/feet, and 4 inch(es) |
+-----------------------------------------------------+
1 row in set (0.01 sec)

Query OK, 0 rows affected (0.01 sec)


3) mysql>  CALL convert_inches(72);
+--------------+-------+------+--------+
| Total Inches | Yards | Feet | Inches |
+--------------+-------+------+--------+
|           72 |     2 |    0 |      0 |
+--------------+-------+------+--------+
1 row in set (0.00 sec)

+----------------------------------------------------+
| Conversion Result                                  |
+----------------------------------------------------+
| 72 inches = 2 yard(s), 0 foot/feet, and 0 inch(es) |
+----------------------------------------------------+
1 row in set (0.01 sec)

Query OK, 0 rows affected (0.01 sec)


4) mysql> CALL convert_inches(15);
+--------------+-------+------+--------+
| Total Inches | Yards | Feet | Inches |
+--------------+-------+------+--------+
|           15 |     0 |    1 |      3 |
+--------------+-------+------+--------+
1 row in set (0.00 sec)

+----------------------------------------------------+
| Conversion Result                                  |
+----------------------------------------------------+
| 15 inches = 0 yard(s), 1 foot/feet, and 3 inch(es) |
+----------------------------------------------------+
1 row in set (0.01 sec)

Query OK, 0 rows affected (0.01 sec)



Question 5 =>

DELIMITER $$

DROP PROCEDURE IF EXISTS check_product$$

CREATE PROCEDURE check_product(IN num1 DECIMAL(10,2), IN num2 DECIMAL(10,2))
BEGIN
    DECLARE v_product DECIMAL(10,2);
    DECLARE v_message VARCHAR(100);
    SET v_product = num1 * num2;
    IF v_product >= 100 THEN
        SET v_message = CONCAT('Product (', v_product, ') is GREATER THAN or EQUAL to 100');
    ELSE
        SET v_message = CONCAT('Product (', v_product, ') is LESS THAN 100');
    END IF;
    SELECT 
        num1 AS 'Number 1',
        num2 AS 'Number 2',
        v_product AS 'Product',
        v_message AS 'Result';
END$$
DELIMITER ;



1) mysql> CALL check_product(10, 10);
+----------+----------+---------+--------------------------------------------------+
| Number 1 | Number 2 | Product | Result                                           |
+----------+----------+---------+--------------------------------------------------+
|    10.00 |    10.00 |  100.00 | Product (100.00) is GREATER THAN or EQUAL to 100 |
+----------+----------+---------+--------------------------------------------------+
1 row in set (0.01 sec)


2) mysql> CALL check_product(12, 9);
+----------+----------+---------+--------------------------------------------------+
| Number 1 | Number 2 | Product | Result                                           |
+----------+----------+---------+--------------------------------------------------+
|    12.00 |     9.00 |  108.00 | Product (108.00) is GREATER THAN or EQUAL to 100 |
+----------+----------+---------+--------------------------------------------------+
1 row in set (0.00 sec)

Query OK, 0 rows affected (0.01 sec)


3) mysql> CALL check_product(5, 10);
+----------+----------+---------+----------------------------------+
| Number 1 | Number 2 | Product | Result                           |
+----------+----------+---------+----------------------------------+
|     5.00 |    10.00 |   50.00 | Product (50.00) is LESS THAN 100 |
+----------+----------+---------+----------------------------------+
1 row in set (0.00 sec)

Query OK, 0 rows affected (0.01 sec)



4) mysql> CALL check_product(7.5, 15);
+----------+----------+---------+--------------------------------------------------+
| Number 1 | Number 2 | Product | Result                                           |
+----------+----------+---------+--------------------------------------------------+
|     7.50 |    15.00 |  112.50 | Product (112.50) is GREATER THAN or EQUAL to 100 |
+----------+----------+---------+--------------------------------------------------+
1 row in set (0.00 sec)

Query OK, 0 rows affected (0.01 sec)














