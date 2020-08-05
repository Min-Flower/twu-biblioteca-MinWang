-- 5. Who has checked OUT more THAN 1 item?
-- Tip: Research the GROUP BY syntax.
SELECT m.`name`
FROM member AS m
WHERE m.id IN (
	SELECT count.member_id
	FROM (
		SELECT c.member_id, count(c.book_id) + count(m.movie_id) AS items
		FROM checkout_item AS c
		GROUP BY member_id
	) AS count
	WHERE c.items > 1);