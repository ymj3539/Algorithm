-- 코드를 작성해주세요
select sum(price) as TOTAL_PRICE
from item_info
group by rarity
having rarity = "LEGEND";
