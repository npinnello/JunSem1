SELECT C.number, C.name, COUNT(R.snum) AS num_students
FROM courses AS C
LEFT JOIN register AS R ON C.number = R.course_number
GROUP BY C.number, C.name
ORDER BY C.number;
