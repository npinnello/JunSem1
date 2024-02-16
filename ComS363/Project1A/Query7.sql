select s.name, s.snum
from students s, register r, courses c, major m
where s.snum = r.snum and r.course_number = c.number and c.name = 'Database Design Principle' and s.snum=m.snum and m.level !='BS'
order by s.snum