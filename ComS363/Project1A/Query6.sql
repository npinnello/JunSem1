SELECT S.snum, S.name
FROM students AS S
INNER JOIN minor AS M ON S.snum = M.snum
ORDER BY S.snum;
