# select u.user_id, u.nickname, sum(b.price) as total_sales
# from used_goods_board b
# join used_goods_user u on b.writer_id = u.user_id
# where b.status = 'DONE'
# group by u.user_id
# having total_sales >= 700000
# order by total_sales asc




# select u.user_id, u.nickname, sum(price) as total_sales
# from used_goods_board b
# join used_goods_user u on b.writer_id = u.user_id
# where b.status = 'done'
# group by b.writer_id
# having total_sales >= 700000
# order by total_sales asc;


select u.user_id, u.nickname, sum(b.price) as total_sales
from used_goods_board b
join used_goods_user u on b.writer_id = u.user_id
where b.status = "DONE"
group by b.writer_id
having sum(b.price) >= 700000
order by sum(b.price)








