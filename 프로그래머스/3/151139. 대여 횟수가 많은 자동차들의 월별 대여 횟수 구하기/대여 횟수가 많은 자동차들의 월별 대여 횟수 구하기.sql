/* Pass - 너무 어려움*/
# SELECT extract(month from start_date) as month, car_id, count(*) as records
# from car_rental_company_rental_history
# group by extract(month from start_date), car_id
# having month >= 8
#     and month <= 10
#     and count(*) >= 5
# order by month asc, car_id desc;

WITH MonthlyRentals AS (
    SELECT 
        MONTH(START_DATE) AS RENT_MONTH,
        CAR_ID,
        COUNT(HISTORY_ID) AS RECORDS
    FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
    WHERE START_DATE BETWEEN '2022-08-01' AND '2022-10-31'
    GROUP BY RENT_MONTH, CAR_ID
),
QualifiedCars AS (
    SELECT 
        CAR_ID
    FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
    WHERE START_DATE BETWEEN '2022-08-01' AND '2022-10-31'
    GROUP BY CAR_ID
    HAVING COUNT(HISTORY_ID) >= 5
)
SELECT 
    MR.RENT_MONTH,
    MR.CAR_ID,
    MR.RECORDS
FROM MonthlyRentals MR
JOIN QualifiedCars QC ON MR.CAR_ID = QC.CAR_ID
ORDER BY MR.RENT_MONTH ASC, MR.CAR_ID DESC;