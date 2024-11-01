#고난이도 - select - case문

# SELECT 
#     case when price < 1000 then 0
#         else floor(price/10000)*10000
#     end as price_group,
#     count(*) as products
# from product
# group by price_group
# order by price_group asc;

select case when price<10000 then 0
        else floor(price/10000)*10000
        end as price_group
        , count(*) as products
from product
group by price_group
order by price_group

        









