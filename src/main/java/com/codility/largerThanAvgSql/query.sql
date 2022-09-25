select m.entry_date , m.entry_time , m.value , b.av FROM measurements m
join
(SELECT m1.entry_date ,AVG(m1.value) as av from measurements m1 group by m1.entry_date) b
ON m.entry_date = b.entry_date
WHERE m.value > b.av