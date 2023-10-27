-- 코드를 입력하세요
SELECT substring(product_code, 1, 2) as CATEGORY, count(product_code) as PRODUCTS
from product
group by CATEGORY
order by CATEGORY asc;