SELECT d.name, d.level, COUNT(*) as 'Number of Students' FROM students s
JOIN major m ON s.snum = m.snum
JOIN minor mi ON s.snum = mi.snum
JOIN degrees d ON (m.name = d.name AND m.level = d.level) OR (mi.name = d.name AND mi.level = d.level)
GROUP BY d.name, d.level
ORDER BY COUNT(*) DESC, d.name
;