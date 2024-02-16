SELECT COUNT(DISTINCT S.snum) AS num_female_students
FROM students AS S
JOIN (SELECT snum FROM major WHERE name = 'Software Engineering'
      UNION
      SELECT snum FROM minor WHERE name = 'Software Engineering') AS SE
ON S.snum = SE.snum
WHERE S.gender = 'F';