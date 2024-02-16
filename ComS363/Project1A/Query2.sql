SELECT M.name, M.level
FROM major AS M
INNER JOIN students AS S ON M.snum = S.snum
WHERE S.name = 'Kevin';
