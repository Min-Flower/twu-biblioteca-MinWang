-- 1. Who checked OUT the book 'The Hobbit’?
SELECT m.name
FROM member AS m
WHERE m.id = (
	 SELECT c.member_id
	 FROM checkout_item c
	 WHERE c.book_id = (
	 	SELECT id
	 	FROM book
	 	WHERE title = 'The Hobbit'
	 )
);