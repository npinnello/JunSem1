SELECT D.name, D.level, COUNT(M.snum) AS num_students
FROM degrees AS D
INNER JOIN major AS M ON D.name = M.name AND D.level = M.level
GROUP BY D.name, D.level
ORDER BY num_students DESC, D.name;
