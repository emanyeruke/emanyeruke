#claims
select claim_status,
       count(case
                 when TIMESTAMPDIFF(MINUTE, created_date, last_modified_date) < 30 then claim_status
                 else null end) as first_tier,
       count(case
                 when TIMESTAMPDIFF(MINUTE, created_date, last_modified_date) > 30 and
                      TIMESTAMPDIFF(MINUTE, created_date, last_modified_date) < 60 then claim_status
                 else null end) as second_tier,
       count(case
                 when TIMESTAMPDIFF(MINUTE, created_date, last_modified_date) > 60 and
                      TIMESTAMPDIFF(MINUTE, created_date, last_modified_date) < 240 then claim_status
                 else null end) as third_tier,
       count(case
                 when TIMESTAMPDIFF(MINUTE, created_date, last_modified_date) > 240 then claim_status
                 else null end) as fourth_tier

from claim_accident
where claim_status = 'PAID'
group by 1;

select month(created_date) as month,
       year(created_date) as year,
       count(case
                 when TIMESTAMPDIFF(MINUTE, created_date, last_modified_date) < 30 then claim_status
                 else null end) as first_tier,
       count(case
                 when TIMESTAMPDIFF(MINUTE, created_date, last_modified_date) > 30 and
                      TIMESTAMPDIFF(MINUTE, created_date, last_modified_date) < 60 then claim_status
                 else null end) as second_tier,
       count(case
                 when TIMESTAMPDIFF(MINUTE, created_date, last_modified_date) > 60 and
                      TIMESTAMPDIFF(MINUTE, created_date, last_modified_date) < 240 then claim_status
                 else null end) as third_tier,
       count(case
                 when TIMESTAMPDIFF(MINUTE, created_date, last_modified_date) > 240 then claim_status
                 else null end) as fourth_tier

from claim_accident
where claim_status = 'PAID'
group by month, year;