# select emp_no, emp_name, g.grade, 
# from hr_department as d
# join  as e on d.dept_id=e.dept_id
# join hr_grade as g on e.emp_no=g.emp_no

select e.emp_no,
    e.emp_name,
    case when g.score >= 96 then 'S'
        when g.score >=90 then 'A'
        when g.score >=80 then 'B'
        else 'C'
    end as grade,
    case when g.score >= 96 then e.sal*20/100
        when g.score >=90 then e.sal*15/100
        when g.score >=80 then e.sal*10/100
        else 0
    end as bonus
from hr_employees as e
join (select emp_no, avg(score) as score
        from hr_grade
        group by emp_no)g on e.emp_no=g.emp_no