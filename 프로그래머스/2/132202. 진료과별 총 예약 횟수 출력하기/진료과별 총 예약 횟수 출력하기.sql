# /**/
# select mcdp_cd as "진료과코드", count(*) as "5월예약건수"
# from appointment
# where extract(year from apnt_ymd) = 2022
#     and extract(month from apnt_ymd) = 5
# group by mcdp_cd
# order by count(*) asc, mcdp_cd asc;

/*order by "한글컬럼" X*/


select mcdp_cd as '진료과코드', count(apnt_no) as '5월예약건수'
from appointment a
where extract(year from a.apnt_ymd) = 2022
    and extract(month from a.apnt_ymd) = 5
group by mcdp_cd
order by count(apnt_no), mcdp_cd









