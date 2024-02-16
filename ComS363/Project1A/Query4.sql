SELECT S.name
FROM students AS S
INNER JOIN register AS R ON S.snum = R.snum
WHERE R.regtime = 'Fall2020';
