# #문법 실수 - join할대 =대신 and
# #추출 문법 - extract(year from 컬럼)

# # select a.author_id, a.author_name, b.category, sum(bs.sales * b.price) as total_sales
# # from book b
# # join author a on b.author_id = a.author_id
# # join book_sales bs on b.book_id = bs.book_id
# # where extract(year from bs.sales_date) = 2022
# #     and extract(month from bs.sales_date) = 1
# # group by a.author_id, b.category
# # order by a.author_id asc, b.category desc


# # select a.author_id, a.author_name, b.category, sum(b.price * bs.sales) as total_sales
# # from book b
# # join author a on b.author_id = a.author_id
# # join book_sales bs on b.book_id = bs.book_id
# # where extract(year from bs.sales_date) = 2022
# #     and extract(month from bs.sales_date) = 1
# # group by a.author_id, b.category
# # order by a.author_id, b.category desc

# select a.author_id, a.author_name, b.category, (sales * price) as total_sales
# from book b
# join author a on b.author_id = a.author_id
# join book_sales bs on b.book_id = bs.book_id 
# where extract (year from bs.sales_date)=2022
#     and extract (month from bs.sales_date)=1
# group by a.author_id, b.category
# order by a.author_id, b.category desc

select a.author_id, a.author_name, b.category, sum(b.price*bs.sales) as total_sales
from book b
join author a on b.author_id = a.author_id
join book_sales bs on b.book_id = bs.book_id
where extract (year from bs.sales_date) = 2022 
    and extract(month from bs.sales_date) = 1
group by a.author_id, b.category
order by a.author_id, b.category desc










