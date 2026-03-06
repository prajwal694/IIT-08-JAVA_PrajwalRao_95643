mysql> select lower(first_name), upper(last_name) from employees;
+-------------------+------------------+
| lower(first_name) | upper(last_name) |
+-------------------+------------------+
| ellen             | ABEL             |
| sundar            | ANDE             |
| mozhe             | ATKINSON         |
| shelli            | BAIDA            |
| amit              | BANDA            |
| elizabeth         | BATES            |
| sarah             | BELL             |
| david             | BERNSTEIN        |
| laura             | BISSOT           |
| harrison          | BLOOM            |
| hermann           | BROWN            |
| alexis            | BULL             |
| anthony           | CABRIO           |
| gerald            | CAMBRAULT        |
| nanette           | CAMBRAULT        |
| john              | CHEN             |
| kelly             | CHUNG            |
| karen             | COLMENARES       |
| curtis            | DAVIES           |
| pat               | DAVIS            |
| julia             | DELLINGER        |
| jennifer          | DILLY            |
| louise            | DORAN            |
| alberto           | ERRAZURIZ        |
| britney           | EVERETT          |
| daniel            | FAVIET           |
| kevin             | FEENEY           |
| jean              | FLEAUR           |
| tayler            | FOX              |
| adam              | FRIPP            |
| lex               | GARCIA           |
| ki                | GEE              |
| girard            | GEONI            |
| william           | GIETZ            |
| douglas           | GRANT            |
| kimberely         | GRANT            |
| danielle          | GREENE           |
| nancy             | GRUENBERG        |
| peter             | HALL             |
| shelley           | HIGGINS          |
| guy               | HIMURO           |
| alyssa            | HUTTON           |
| valli             | JACKSON          |
| susan             | JACOBS           |
| alexander         | JAMES            |
| charles           | JOHNSON          |
| vance             | JONES            |
| payam             | KAUFLING         |
| alexander         | KHOO             |
| janette           | KING             |
| steven            | KING             |
| sundita           | KUMAR            |
| renske            | LADWIG           |
| james             | LANDRY           |
| david             | LEE              |
| den               | LI               |
| jack              | LIVINGSTON       |
| jason             | MALLIN           |
| steven            | MARKLE           |
| james             | MARLOW           |
| michael           | MARTINEZ         |
| mattea            | MARVINS          |
| randall           | MATOS            |
| allan             | MCEWEN           |
| samuel            | MCLEOD           |
| irene             | MIKKILINENI      |
| bruce             | MILLER           |
| kevin             | MOURGOS          |
| julia             | NAYER            |
| diana             | NGUYEN           |
| donald            | OCONNELL         |
| christopher       | OLSEN            |
| tj                | OLSON            |
| lisa              | OZER             |
| karen             | PARTNERS         |
| joshua            | PATEL            |
| randall           | PERKINS          |
| hazel             | PHILTANKER       |
| luis              | POPP             |
| trenna            | RAJS             |
| michael           | ROGERS           |
| nandita           | SARCHAND         |
| ismael            | SCIARRA          |
| john              | SEO              |
| sarath            | SEWALL           |
| john              | SINGH            |
| lindsey           | SMITH            |
| william           | SMITH            |
| stephen           | STILES           |
| martha            | SULLIVAN         |
| patrick           | SULLY            |
| jonathon          | TAYLOR           |
| winston           | TAYLOR           |
| sigal             | TOBIAS           |
| sean              | TUCKER           |
| oliver            | TUVAULT          |
| jose manuel       | URMAN            |
| peter             | VARGAS           |
| timothy           | VENZL            |
| clara             | VISHNEY          |
| shanta            | VOLLMAN          |
| alana             | WALSH            |
| matthew           | WEISS            |
| jennifer          | WHALEN           |
| david             | WILLIAMS         |
| neena             | YANG             |
| eleni             | ZLOTKEY          |
+-------------------+------------------+
107 rows in set (0.01 sec)

mysql> select substring_index('job title', '_' , 1);
+---------------------------------------+
| substring_index('job title', '_' , 1) |
+---------------------------------------+
| job title                             |
+---------------------------------------+
1 row in set (0.00 sec)

mysql> select first_name,
    -> last_name,
    -> length(first_name) as first_name_length
    -> from employees
    -> where substring(lower(last_name), 4) like '%b%';
+------------+-----------+-------------------+
| first_name | last_name | first_name_length |
+------------+-----------+-------------------+
| Gerald     | Cambrault |                 6 |
| Nanette    | Cambrault |                 7 |
| Nancy      | Gruenberg |                 5 |
| Susan      | Jacobs    |                 5 |
+------------+-----------+-------------------+
4 rows in set (0.00 sec)

mysql> select first_name, last_name from employees
    -> where first_name like '% %'
    -> or last_name like '% %';
+-------------+-----------+
| first_name  | last_name |
+-------------+-----------+
| Jose Manuel | Urman     |
+-------------+-----------+
1 row in set (0.00 sec)

mysql>  select first_name,
    ->  last_name, round(salary, -3) as salary_in_thousand from employees;
+-------------+-------------+--------------------+
| first_name  | last_name   | salary_in_thousand |
+-------------+-------------+--------------------+
| Steven      | King        |              24000 |
| Neena       | Yang        |              17000 |
| Lex         | Garcia      |              17000 |
| Alexander   | James       |               9000 |
| Bruce       | Miller      |               6000 |
| David       | Williams    |               5000 |
| Valli       | Jackson     |               5000 |
| Diana       | Nguyen      |               4000 |
| Nancy       | Gruenberg   |              12000 |
| Daniel      | Faviet      |               9000 |
| John        | Chen        |               8000 |
| Ismael      | Sciarra     |               8000 |
| Jose Manuel | Urman       |               8000 |
| Luis        | Popp        |               7000 |
| Den         | Li          |              11000 |
| Alexander   | Khoo        |               3000 |
| Shelli      | Baida       |               3000 |
| Sigal       | Tobias      |               3000 |
| Guy         | Himuro      |               3000 |
| Karen       | Colmenares  |               3000 |
| Matthew     | Weiss       |               8000 |
| Adam        | Fripp       |               8000 |
| Payam       | Kaufling    |               8000 |
| Shanta      | Vollman     |               7000 |
| Kevin       | Mourgos     |               6000 |
| Julia       | Nayer       |               3000 |
| Irene       | Mikkilineni |               3000 |
| James       | Landry      |               2000 |
| Steven      | Markle      |               2000 |
| Laura       | Bissot      |               3000 |
| Mozhe       | Atkinson    |               3000 |
| James       | Marlow      |               3000 |
| TJ          | Olson       |               2000 |
| Jason       | Mallin      |               3000 |
| Michael     | Rogers      |               3000 |
| Ki          | Gee         |               2000 |
| Hazel       | Philtanker  |               2000 |
| Renske      | Ladwig      |               4000 |
| Stephen     | Stiles      |               3000 |
| John        | Seo         |               3000 |
| Joshua      | Patel       |               3000 |
| Trenna      | Rajs        |               4000 |
| Curtis      | Davies      |               3000 |
| Randall     | Matos       |               3000 |
| Peter       | Vargas      |               3000 |
| John        | Singh       |              14000 |
| Karen       | Partners    |              14000 |
| Alberto     | Errazuriz   |              12000 |
| Gerald      | Cambrault   |              11000 |
| Eleni       | Zlotkey     |              11000 |
| Sean        | Tucker      |              10000 |
| David       | Bernstein   |              10000 |
| Peter       | Hall        |               9000 |
| Christopher | Olsen       |               8000 |
| Nanette     | Cambrault   |               8000 |
| Oliver      | Tuvault     |               7000 |
| Janette     | King        |              10000 |
| Patrick     | Sully       |              10000 |
| Allan       | McEwen      |               9000 |
| Lindsey     | Smith       |               8000 |
| Louise      | Doran       |               8000 |
| Sarath      | Sewall      |               7000 |
| Clara       | Vishney     |              11000 |
| Danielle    | Greene      |              10000 |
| Mattea      | Marvins     |               7000 |
| David       | Lee         |               7000 |
| Sundar      | Ande        |               6000 |
| Amit        | Banda       |               6000 |
| Lisa        | Ozer        |              12000 |
| Harrison    | Bloom       |              10000 |
| Tayler      | Fox         |              10000 |
| William     | Smith       |               7000 |
| Elizabeth   | Bates       |               7000 |
| Sundita     | Kumar       |               6000 |
| Ellen       | Abel        |              11000 |
| Alyssa      | Hutton      |               9000 |
| Jonathon    | Taylor      |               9000 |
| Jack        | Livingston  |               8000 |
| Kimberely   | Grant       |               7000 |
| Charles     | Johnson     |               6000 |
| Winston     | Taylor      |               3000 |
| Jean        | Fleaur      |               3000 |
| Martha      | Sullivan    |               3000 |
| Girard      | Geoni       |               3000 |
| Nandita     | Sarchand    |               4000 |
| Alexis      | Bull        |               4000 |
| Julia       | Dellinger   |               3000 |
| Anthony     | Cabrio      |               3000 |
| Kelly       | Chung       |               4000 |
| Jennifer    | Dilly       |               4000 |
| Timothy     | Venzl       |               3000 |
| Randall     | Perkins     |               3000 |
| Sarah       | Bell        |               4000 |
| Britney     | Everett     |               4000 |
| Samuel      | McLeod      |               3000 |
| Vance       | Jones       |               3000 |
| Alana       | Walsh       |               3000 |
| Kevin       | Feeney      |               3000 |
| Donald      | OConnell    |               3000 |
| Douglas     | Grant       |               3000 |
| Jennifer    | Whalen      |               4000 |
| Michael     | Martinez    |              13000 |
| Pat         | Davis       |               6000 |
| Susan       | Jacobs      |               7000 |
| Hermann     | Brown       |              10000 |
| Shelley     | Higgins     |              12000 |
| William     | Gietz       |               8000 |
+-------------+-------------+--------------------+
107 rows in set (0.00 sec)

mysql> SELECT EMPLOYEE_ID,
    -> DATE_FORMAT(HIRE_DATE, '%W-%M-%d-%Y')
    -> AS formatted_hire_date
    -> FROM employees;
+-------------+-----------------------------+
| EMPLOYEE_ID | formatted_hire_date         |
+-------------+-----------------------------+
|         100 | Monday-June-17-2013         |
|         101 | Monday-September-21-2015    |
|         102 | Thursday-January-13-2011    |
|         103 | Sunday-January-03-2016      |
|         104 | Sunday-May-21-2017          |
|         105 | Thursday-June-25-2015       |
|         106 | Friday-February-05-2016     |
|         107 | Tuesday-February-07-2017    |
|         108 | Friday-August-17-2012       |
|         109 | Thursday-August-16-2012     |
|         110 | Monday-September-28-2015    |
|         111 | Wednesday-September-30-2015 |
|         112 | Monday-March-07-2016        |
|         113 | Thursday-December-07-2017   |
|         114 | Friday-December-07-2012     |
|         115 | Saturday-May-18-2013        |
|         116 | Thursday-December-24-2015   |
|         117 | Friday-July-24-2015         |
|         118 | Tuesday-November-15-2016    |
|         119 | Thursday-August-10-2017     |
|         120 | Friday-July-18-2014         |
|         121 | Friday-April-10-2015        |
|         122 | Wednesday-May-01-2013       |
|         123 | Saturday-October-10-2015    |
|         124 | Thursday-November-16-2017   |
|         125 | Thursday-July-16-2015       |
|         126 | Wednesday-September-28-2016 |
|         127 | Saturday-January-14-2017    |
|         128 | Thursday-March-08-2018      |
|         129 | Thursday-August-20-2015     |
|         130 | Friday-October-30-2015      |
|         131 | Monday-February-16-2015     |
|         132 | Monday-April-10-2017        |
|         133 | Saturday-June-14-2014       |
|         134 | Friday-August-26-2016       |
|         135 | Tuesday-December-12-2017    |
|         136 | Tuesday-February-06-2018    |
|         137 | Sunday-July-14-2013         |
|         138 | Monday-October-26-2015      |
|         139 | Friday-February-12-2016     |
|         140 | Wednesday-April-06-2016     |
|         141 | Thursday-October-17-2013    |
|         142 | Thursday-January-29-2015    |
|         143 | Tuesday-March-15-2016       |
|         144 | Saturday-July-09-2016       |
|         145 | Wednesday-October-01-2014   |
|         146 | Monday-January-05-2015      |
|         147 | Tuesday-March-10-2015       |
|         148 | Sunday-October-15-2017      |
|         149 | Monday-January-29-2018      |
|         150 | Friday-January-30-2015      |
|         151 | Tuesday-March-24-2015       |
|         152 | Thursday-August-20-2015     |
|         153 | Wednesday-March-30-2016     |
|         154 | Friday-December-09-2016     |
|         155 | Thursday-November-23-2017   |
|         156 | Thursday-January-30-2014    |
|         157 | Tuesday-March-04-2014       |
|         158 | Friday-August-01-2014       |
|         159 | Tuesday-March-10-2015       |
|         160 | Tuesday-December-15-2015    |
|         161 | Thursday-November-03-2016   |
|         162 | Wednesday-November-11-2015  |
|         163 | Sunday-March-19-2017        |
|         164 | Wednesday-January-24-2018   |
|         165 | Friday-February-23-2018     |
|         166 | Saturday-March-24-2018      |
|         167 | Saturday-April-21-2018      |
|         168 | Wednesday-March-11-2015     |
|         169 | Wednesday-March-23-2016     |
|         170 | Sunday-January-24-2016      |
|         171 | Thursday-February-23-2017   |
|         172 | Friday-March-24-2017        |
|         173 | Saturday-April-21-2018      |
|         174 | Sunday-May-11-2014          |
|         175 | Thursday-March-19-2015      |
|         176 | Thursday-March-24-2016      |
|         177 | Saturday-April-23-2016      |
|         178 | Wednesday-May-24-2017       |
|         179 | Thursday-January-04-2018    |
|         180 | Sunday-January-24-2016      |
|         181 | Tuesday-February-23-2016    |
|         182 | Wednesday-June-21-2017      |
|         183 | Saturday-February-03-2018   |
|         184 | Monday-January-27-2014      |
|         185 | Friday-February-20-2015     |
|         186 | Friday-June-24-2016         |
|         187 | Tuesday-February-07-2017    |
|         188 | Sunday-June-14-2015         |
|         189 | Thursday-August-13-2015     |
|         190 | Monday-July-11-2016         |
|         191 | Tuesday-December-19-2017    |
|         192 | Tuesday-February-04-2014    |
|         193 | Tuesday-March-03-2015       |
|         194 | Friday-July-01-2016         |
|         195 | Friday-March-17-2017        |
|         196 | Sunday-April-24-2016        |
|         197 | Monday-May-23-2016          |
|         198 | Wednesday-June-21-2017      |
|         199 | Saturday-January-13-2018    |
|         200 | Tuesday-September-17-2013   |
|         201 | Monday-February-17-2014     |
|         202 | Monday-August-17-2015       |
|         203 | Thursday-June-07-2012       |
|         204 | Thursday-June-07-2012       |
|         205 | Thursday-June-07-2012       |
|         206 | Thursday-June-07-2012       |
+-------------+-----------------------------+
107 rows in set (0.00 sec)

 SELECT FIRST_NAME,
    -> DATE_FORMAT(DATE_SUB(HIRE_DATE, INTERVAL DAY(HIRE_DATE)-1 DAY), '%d-%M-%Y') AS first_salary_date
    -> FROM employees;
+-------------+-------------------+
| FIRST_NAME  | first_salary_date |
+-------------+-------------------+
| Steven      | 01-June-2013      |
| Neena       | 01-September-2015 |
| Lex         | 01-January-2011   |
| Alexander   | 01-January-2016   |
| Bruce       | 01-May-2017       |
| David       | 01-June-2015      |
| Valli       | 01-February-2016  |
| Diana       | 01-February-2017  |
| Nancy       | 01-August-2012    |
| Daniel      | 01-August-2012    |
| John        | 01-September-2015 |
| Ismael      | 01-September-2015 |
| Jose Manuel | 01-March-2016     |
| Luis        | 01-December-2017  |
| Den         | 01-December-2012  |
| Alexander   | 01-May-2013       |
| Shelli      | 01-December-2015  |
| Sigal       | 01-July-2015      |
| Guy         | 01-November-2016  |
| Karen       | 01-August-2017    |
| Matthew     | 01-July-2014      |
| Adam        | 01-April-2015     |
| Payam       | 01-May-2013       |
| Shanta      | 01-October-2015   |
| Kevin       | 01-November-2017  |
| Julia       | 01-July-2015      |
| Irene       | 01-September-2016 |
| James       | 01-January-2017   |
| Steven      | 01-March-2018     |
| Laura       | 01-August-2015    |
| Mozhe       | 01-October-2015   |
| James       | 01-February-2015  |
| TJ          | 01-April-2017     |
| Jason       | 01-June-2014      |
| Michael     | 01-August-2016    |
| Ki          | 01-December-2017  |
| Hazel       | 01-February-2018  |
| Renske      | 01-July-2013      |
| Stephen     | 01-October-2015   |
| John        | 01-February-2016  |
| Joshua      | 01-April-2016     |
| Trenna      | 01-October-2013   |
| Curtis      | 01-January-2015   |
| Randall     | 01-March-2016     |
| Peter       | 01-July-2016      |
| John        | 01-October-2014   |
| Karen       | 01-January-2015   |
| Alberto     | 01-March-2015     |
| Gerald      | 01-October-2017   |
| Eleni       | 01-January-2018   |
| Sean        | 01-January-2015   |
| David       | 01-March-2015     |
| Peter       | 01-August-2015    |
| Christopher | 01-March-2016     |
| Nanette     | 01-December-2016  |
| Oliver      | 01-November-2017  |
| Janette     | 01-January-2014   |
| Patrick     | 01-March-2014     |
| Allan       | 01-August-2014    |
| Lindsey     | 01-March-2015     |
| Louise      | 01-December-2015  |
| Sarath      | 01-November-2016  |
| Clara       | 01-November-2015  |
| Danielle    | 01-March-2017     |
| Mattea      | 01-January-2018   |
| David       | 01-February-2018  |
| Sundar      | 01-March-2018     |
| Amit        | 01-April-2018     |
| Lisa        | 01-March-2015     |
| Harrison    | 01-March-2016     |
| Tayler      | 01-January-2016   |
| William     | 01-February-2017  |
| Elizabeth   | 01-March-2017     |
| Sundita     | 01-April-2018     |
| Ellen       | 01-May-2014       |
| Alyssa      | 01-March-2015     |
| Jonathon    | 01-March-2016     |
| Jack        | 01-April-2016     |
| Kimberely   | 01-May-2017       |
| Charles     | 01-January-2018   |
| Winston     | 01-January-2016   |
| Jean        | 01-February-2016  |
| Martha      | 01-June-2017      |
| Girard      | 01-February-2018  |
| Nandita     | 01-January-2014   |
| Alexis      | 01-February-2015  |
| Julia       | 01-June-2016      |
| Anthony     | 01-February-2017  |
| Kelly       | 01-June-2015      |
| Jennifer    | 01-August-2015    |
| Timothy     | 01-July-2016      |
| Randall     | 01-December-2017  |
| Sarah       | 01-February-2014  |
| Britney     | 01-March-2015     |
| Samuel      | 01-July-2016      |
| Vance       | 01-March-2017     |
| Alana       | 01-April-2016     |
| Kevin       | 01-May-2016       |
| Donald      | 01-June-2017      |
| Douglas     | 01-January-2018   |
| Jennifer    | 01-September-2013 |
| Michael     | 01-February-2014  |
| Pat         | 01-August-2015    |
| Susan       | 01-June-2012      |
| Hermann     | 01-June-2012      |
| Shelley     | 01-June-2012      |
| William     | 01-June-2012      |
+-------------+-------------------+
107 rows in set (0.00 sec)

mysql> SELECT first_name
    -> FROM employees
    -> WHERE MONTH(hire_date) = 8;
+------------+
| first_name |
+------------+
| Nancy      |
| Daniel     |
| Karen      |
| Laura      |
| Michael    |
| Peter      |
| Allan      |
| Jennifer   |
| Pat        |
+------------+
9 rows in set (0.00 sec)

mysql> select first_name, hire_date
    -> from employees
    -> where year(hire_date) < 2015;
+------------+------------+
| first_name | hire_date  |
+------------+------------+
| Steven     | 2013-06-17 |
| Lex        | 2011-01-13 |
| Nancy      | 2012-08-17 |
| Daniel     | 2012-08-16 |
| Den        | 2012-12-07 |
| Alexander  | 2013-05-18 |
| Matthew    | 2014-07-18 |
| Payam      | 2013-05-01 |
| Jason      | 2014-06-14 |
| Renske     | 2013-07-14 |
| Trenna     | 2013-10-17 |
| John       | 2014-10-01 |
| Janette    | 2014-01-30 |
| Patrick    | 2014-03-04 |
| Allan      | 2014-08-01 |
| Ellen      | 2014-05-11 |
| Nandita    | 2014-01-27 |
| Sarah      | 2014-02-04 |
| Jennifer   | 2013-09-17 |
| Michael    | 2014-02-17 |
| Susan      | 2012-06-07 |
| Hermann    | 2012-06-07 |
| Shelley    | 2012-06-07 |
| William    | 2012-06-07 |
+------------+------------+
24 rows in set (0.00 sec)

mysql> select first_name
    -> from employees
    -> where hire_date between
    -> '2011-01-01' and CURRENT_DATE();
+-------------+
| first_name  |
+-------------+
| Steven      |
| Neena       |
| Lex         |
| Alexander   |
| Bruce       |
| David       |
| Valli       |
| Diana       |
| Nancy       |
| Daniel      |
| John        |
| Ismael      |
| Jose Manuel |
| Luis        |
| Den         |
| Alexander   |
| Shelli      |
| Sigal       |
| Guy         |
| Karen       |
| Matthew     |
| Adam        |
| Payam       |
| Shanta      |
| Kevin       |
| Julia       |
| Irene       |
| James       |
| Steven      |
| Laura       |
| Mozhe       |
| James       |
| TJ          |
| Jason       |
| Michael     |
| Ki          |
| Hazel       |
| Renske      |
| Stephen     |
| John        |
| Joshua      |
| Trenna      |
| Curtis      |
| Randall     |
| Peter       |
| John        |
| Karen       |
| Alberto     |
| Gerald      |
| Eleni       |
| Sean        |
| David       |
| Peter       |
| Christopher |
| Nanette     |
| Oliver      |
| Janette     |
| Patrick     |
| Allan       |
| Lindsey     |
| Louise      |
| Sarath      |
| Clara       |
| Danielle    |
| Mattea      |
| David       |
| Sundar      |
| Amit        |
| Lisa        |
| Harrison    |
| Tayler      |
| William     |
| Elizabeth   |
| Sundita     |
| Ellen       |
| Alyssa      |
| Jonathon    |
| Jack        |
| Kimberely   |
| Charles     |
| Winston     |
| Jean        |
| Martha      |
| Girard      |
| Nandita     |
| Alexis      |
| Julia       |
| Anthony     |
| Kelly       |
| Jennifer    |
| Timothy     |
| Randall     |
| Sarah       |
| Britney     |
| Samuel      |
| Vance       |
| Alana       |
| Kevin       |
| Donald      |
| Douglas     |
| Jennifer    |
| Michael     |
| Pat         |
| Susan       |
| Hermann     |
| Shelley     |
| William     |
+-------------+
107 rows in set (0.00 sec)


mysql> select first_name, hire_date
    -> from employees
    -> where DAY(hire_date) > '15';
+-------------+------------+
| first_name  | hire_date  |
+-------------+------------+
| Steven      | 2013-06-17 |
| Neena       | 2015-09-21 |
| Bruce       | 2017-05-21 |
| David       | 2015-06-25 |
| Nancy       | 2012-08-17 |
| Daniel      | 2012-08-16 |
| John        | 2015-09-28 |
| Ismael      | 2015-09-30 |
| Alexander   | 2013-05-18 |
| Shelli      | 2015-12-24 |
| Sigal       | 2015-07-24 |
| Matthew     | 2014-07-18 |
| Kevin       | 2017-11-16 |
| Julia       | 2015-07-16 |
| Irene       | 2016-09-28 |
| Laura       | 2015-08-20 |
| Mozhe       | 2015-10-30 |
| James       | 2015-02-16 |
| Michael     | 2016-08-26 |
| Stephen     | 2015-10-26 |
| Trenna      | 2013-10-17 |
| Curtis      | 2015-01-29 |
| Eleni       | 2018-01-29 |
| Sean        | 2015-01-30 |
| David       | 2015-03-24 |
| Peter       | 2015-08-20 |
| Christopher | 2016-03-30 |
| Oliver      | 2017-11-23 |
| Janette     | 2014-01-30 |
| Danielle    | 2017-03-19 |
| Mattea      | 2018-01-24 |
| David       | 2018-02-23 |
| Sundar      | 2018-03-24 |
| Amit        | 2018-04-21 |
| Harrison    | 2016-03-23 |
| Tayler      | 2016-01-24 |
| William     | 2017-02-23 |
| Elizabeth   | 2017-03-24 |
| Sundita     | 2018-04-21 |
| Alyssa      | 2015-03-19 |
| Jonathon    | 2016-03-24 |
| Jack        | 2016-04-23 |
| Kimberely   | 2017-05-24 |
| Winston     | 2016-01-24 |
| Jean        | 2016-02-23 |
| Martha      | 2017-06-21 |
| Nandita     | 2014-01-27 |
| Alexis      | 2015-02-20 |
| Julia       | 2016-06-24 |
| Randall     | 2017-12-19 |
| Vance       | 2017-03-17 |
| Alana       | 2016-04-24 |
| Kevin       | 2016-05-23 |
| Donald      | 2017-06-21 |
| Jennifer    | 2013-09-17 |
| Michael     | 2014-02-17 |
| Pat         | 2015-08-17 |
+-------------+------------+
57 rows in set (0.00 sec)

mysql> select salary from employees
    -> order by salary desc
    -> limit 1 offset 3;
+----------+
| salary   |
+----------+
| 14000.00 |
+----------+
1 row in set (0.00 sec)

mysql> select city from supplier where city like '%L%';
+--------+
| city   |
+--------+
| London |
| London |
+--------+
2 rows in set (0.03 sec)

select Jname from jobs where Jname like '%  n';
Empty set (0.00 sec)

mysql> select substring(upper(sname),1, 1) as cap from supplier;
+------+
| cap  |
+------+
| S    |
| J    |
| B    |
| C    |
| A    |
+------+
5 rows in set (0.00 sec)


mysql> select upper(sname) as cap from supplier;
+--------+
| cap    |
+--------+
| SMITH  |
| JONES  |
| BLAKE  |
| CLARKE |
| ADAMS  |
+--------+
5 rows in set (0.00 sec)

mysql> select lower(sname) as cap from supplier;
+--------+
| cap    |
+--------+
| smith  |
| jones  |
| blake  |
| clarke |
| adams  |
+--------+
5 rows in set (0.00 sec)

mysql> select length(sname) as cap from supplier;
+------+
| cap  |
+------+
|    5 |
|    5 |
|    5 |
|    6 |
|    5 |
+------+
5 rows in set (0.00 sec)

mysql> select * from supplier;
+-----+--------+--------+--------+
| Sno | Sname  | status | city   |
+-----+--------+--------+--------+
| S1  | Smith  |     20 | London |
| S2  | Jones  |     10 | Paris  |
| S3  | Blake  |     30 | Paris  |
| S4  | Clarke |     20 | London |
| S5  | Adams  |     30 | Athens |
+-----+--------+--------+--------+
5 rows in set (0.01 sec)

mysql> select min(status) from supplier;
+-------------+
| min(status) |
+-------------+
|          10 |
+-------------+

mysql> select max(weight) from parts;
+-------------+
| max(weight) |
+-------------+
|          19 |
+-------------+
1 row in set (0.07 sec)

mysql> select avg(weight) from parts;
+-------------+
| avg(weight) |
+-------------+
|     15.1667 |
+-------------+
1 row in set (0.00 sec)




