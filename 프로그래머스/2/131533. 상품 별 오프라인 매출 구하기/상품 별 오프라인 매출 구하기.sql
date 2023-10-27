-- 코드를 입력하세요
# SELECT *
# from product;

# PRODUCT_ID, PRODUCT_CODE, PRICE는 각각 상품 ID, 상품코드, 판매가
# OFFLINE_SALE_ID, PRODUCT_ID, SALES_AMOUNT, SALES_DATE는 각각 오프라인 상품 판매 ID, 상품 ID, 판매량, 판매일
select p.product_code as PRPDUCT_CODE, sum(p.price * o.sales_amount) as SALES
from product p join offline_sale o
on p.product_id = o.product_id
group by p.product_code
order by SALES desc, PRODUCT_CODE asc;