-- 코드를 입력하세요
# SELECT *
# from food_product p 
# join(
#     select product_id, max(price) as mp
#     from food_product
#     group by category
# )pm
# on p.product_id = pm.product_id
#     and p.price = pm.mp
# group by category
# order by p.price desc;

# SELECT p.category, p.price, p.product_name
# from food_product p
# join (
#     select category, max(price) as mp
#     from food_product
#     group by category
# ) cmp
# on p.category = cmp.category
#     and p.price = cmp.mp
# where p.category = '과자'
#     or p.category = '국'
#     or p.category = '김치'
#     or p.category = '식용유'
# order by p.price desc;

select fp.category, fp.price as max_price, fp.product_name
from food_product fp
join
    (select category, max(price) as price
    from food_product
    group by category) fm
on fp.category = fm.category and fp.price = fm.price
where fp.category in ('과자', '국', '김치', '식용유')
order by fp.price desc









