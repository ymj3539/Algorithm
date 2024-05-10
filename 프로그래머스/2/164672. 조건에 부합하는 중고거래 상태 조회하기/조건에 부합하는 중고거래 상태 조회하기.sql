select BOARD_ID, WRITER_ID, TITLE, PRICE, 
case 
    when status = "SALE" then "판매중" 
    when status = "RESERVED" then "예약중"
    when status = "DONE" then "거래완료"
end as STATUS
from USED_GOODS_BOARD
where date_format(created_date, "%Y%m%d") = "20221005"
order by board_id desc;