select ii.ingredient_type, sum(fh.total_order) as total_order
from first_half fh
join icecream_info ii on fh.flavor = ii.flavor
group by ii.ingredient_type
order by total_order;












select i.ingredient_type, sum(total_order) as total_order
from first_half f
join icecream_info i on f.flavor = i.flavor
group by ingredient_type












