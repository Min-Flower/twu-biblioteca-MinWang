-- 2. How many people have NOT checked OUT anything?
SELECT count(m.id) AS non_checkout_user_count
FROM member AS m
WHERE m.id NOT IN (
	SELECT member.id
	FROM checkout_item
);