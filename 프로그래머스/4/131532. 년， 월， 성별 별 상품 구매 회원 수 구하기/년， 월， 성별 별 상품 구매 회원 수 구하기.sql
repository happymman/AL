/**/
# SELECT extract(year from sales_date) as year, extract(month from sales_date) as month, gender, count(distinct u.user_id) as users
# from user_info u
# join online_sale o on u.user_id = o.user_id
# where u.gender is not null
# group by extract(year from o.sales_date), extract(month from o.sales_date),u.gender
# order by year, month,u.gender;

/**/
/**/
# /**/
# /**/
# select extract(year from sales_date) year, extract(month from sales_date) month, u.gender, count(distinct u.user_id) as users
# from user_info u
# join online_sale s on u.user_id = s.user_id
# where u.gender is not null
# group by extract(year from sales_date), extract(month from sales_date), u.gender
# order by year, month, u.gender;


# select extract(year from o.sales_date) year, extract(month from o.sales_date) month, gender, count(distinct u.user_id) users
# from user_info u
# join online_sale o on u.user_id = o.user_id
# where u.gender is not null
# group by extract(year from o.sales_date)
#     ,extract(month from o.sales_date)
#     ,gender
# order by year, month, gender

# select extract(year from o.sales_date) as year,
#     extract(month from o.sales_date) as month,
#     u.gender as gender,
#     count(distinct u.user_id) as users 
# from user_info u
# join online_sale o on u.user_id = o.user_id
# where u.gender is not null
# group by extract(year from o.sales_date),
#     extract(month from o.sales_date),
#     u.gender
# order by extract(year from o.sales_date),
#     extract(month from o.sales_date),
#     u.gender


select extract(year from o.sales_date) as year,
    extract (month from o.sales_date) as month,
    u.gender,
    count(distinct(u.user_id)) as user
from user_info u
join online_sale o on u.user_id=o.user_id
where u.gender is not null
group by extract (year from o.sales_date),extract (month from o.sales_date),u.gender
order by extract (year from o.sales_date),extract (month from o.sales_date),u.gender

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    