-- 3. What books AND movies aren't checked OUT?
SELECT b.title
FROM book AS b
WHERE b.id NOT IN (
	SELECT c.book_id
	FROM checkout_item AS c
	WHERE c.book_id IS NOT NULL
)
UNION
SELECT m.title
FROM movie AS m
WHERE m.id NOT IN (
	SELECT c.movie_id
	FROM checkout_item AS c
	WHERE c.movie_id IS NOT NULL
)