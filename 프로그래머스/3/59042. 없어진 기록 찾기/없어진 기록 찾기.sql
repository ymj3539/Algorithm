-- 코드를 입력하세요
select o.animal_id, o.name
from animal_ins i
right join animal_outs o
on i.animal_id = o.animal_id
where i.animal_id is null
order by o.animal_id;












# select o.animal_id as ANIMAL_ID, o.name as NAME
# from animal_ins i right join animal_outs o
# on i.animal_id = o.animal_id
# where i.animal_id is null
# order by o.animal_id asc;