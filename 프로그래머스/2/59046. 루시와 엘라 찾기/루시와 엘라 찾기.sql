-- 문제
-- 동물 보호소에 들어온 동물 중 이름이 Lucy, Ella, Pickle, Rogan, Sabrina, Mitty인 동물의 (where)
-- 아이디와 이름, 성별 및 중성화 여부를 조회 (select)
-- 하는 SQL 문을 작성해주세요
-- 이때 결과는 아이디 순으로 조회해주세요.

-- 코드를 입력하세요
select ANIMAL_ID, NAME, SEX_UPON_INTAKE
from ANIMAL_INS
where name in ('Lucy', 'Ella', 'Pickle', 'Rogan', 'Sabrina', 'Mitty')
order by ANIMAL_ID;
