-- 코드를 입력하세요
SELECT u.user_id as USER_ID, u.nickname as NICKNAME, sum(price) as TOTAL_SALES
from used_goods_board b join used_goods_user u
on b.writer_id = u.user_id
where b.status = 'DONE'
group by u.user_id
having TOTAL_SALES >= 700000
order by TOTAL_SALES;
