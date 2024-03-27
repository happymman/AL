/**/
# select ri.food_type, ri.rest_id, ri.rest_name, ri.favorites
# from rest_info ri
# join (
#     select food_type, max(favorites) as favorites
#     from rest_info
#     group by food_type) rm
# on ri.food_type = rm.food_type and ri.favorites = rm.favorites
# order by ri.food_type desc;

# select food_type, max(favorites)
#     from rest_info
#     group by food_type

# select food_type, rest_id, rest_name, max(favorites) favorites
# from rest_info
# group by food_type
# order by food_type desc

select ri.food_type, ri.rest_id, ri.rest_name, ri.favorites
from rest_info ri
join
    (select food_type, max(favorites) as favorites
    from rest_info
    group by food_type) rm
on ri.food_type = rm.food_type and ri.favorites = rm.favorites
order by food_type desc
    
    
    
    
    
    
    
    
    
    
    