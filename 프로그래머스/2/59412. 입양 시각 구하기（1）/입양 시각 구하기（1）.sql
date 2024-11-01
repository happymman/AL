-- 코드를 입력하세요
SELECT extract(hour from datetime) as hour, count(*) as count
from animal_outs
group by extract(hour from datetime)
having 9<= hour and hour <= 19
order by hour;




select extract(hour from datetime) hour, count(animal_id) as count
from animal_outs
where extract(hour from datetime) >= 9
    and extract(hour from datetime) <= 19
group by extract(hour from datetime)
order by extract(hour from datetime)