# -- 코드를 입력하세요
# SELECT category,
#     sum(sales) as total_sales
# from book b
# join book_sales bs on b.book_id = bs.book_id
# where extract(year from sales_date) = 2022
#     and extract(month from sales_date) = 1
# group by category 
# order by category asc;












select b.category, sum(bs.sales) as total_sales
from book b
join book_sales bs on b.book_id = bs.book_id
where extract(year from bs.sales_date) = 2022
    and extract(month from bs.sales_date) = 1
group by b.category
order by b.category