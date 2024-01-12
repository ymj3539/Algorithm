-- 코드를 입력하세요
-- 7월 아이스크림 총 주문량과 상반기의 아이스크림 총 주문량을 더한 값이 큰 순서대로 상위 3개의 맛을 조회하는 SQL 문을 작성해주세요.

select f.flavor
from first_half f join (select flavor, sum(total_order) total_order from july group by flavor) as j
on f.flavor = j.flavor
order by (f.total_order + j.total_order) desc
limit 3
;