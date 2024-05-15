-- 코드를 작성해주세요
select f.id, n.fish_name, f.length
from fish_info f
join fish_name_info n
on f.fish_type = n.fish_type
where (f.fish_type, f.length) in (
    SELECT FISH_TYPE, max(length)
    FROM FISH_INFO
    GROUP BY FISH_TYPE
)
order by f.id;



# SELECT ID, FISH_NAME, FISH_INFO.LENGTH AS LENGTH
# FROM FISH_INFO JOIN FISH_NAME_INFO ON FISH_INFO.FISH_TYPE = FISH_NAME_INFO.FISH_TYPE
# WHERE FISH_INFO.FISH_TYPE IN 
# (
#     SELECT FISH_TYPE
#     FROM FISH_INFO
#     GROUP BY FISH_TYPE
#     HAVING LENGTH = MAX(LENGTH)
# )
# ORDER BY ID;