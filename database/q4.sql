-- 4. ADD the book 'The Pragmatic Programmer', AND ADD yourself AS a member. CHECK OUT 'The Pragmatic Programmer'. USE your QUERY FROM question 1 TO verify that you have checked it out. Also, provide the SQL used TO UPDATE the database.
INSERT INTO book (id, title)
VALUES (0, 'The Pragmatic Programmer');

INSERT INTO member (id, `name`)
VALUES (0, 'Min');

SELECT m.name
FROM member AS m
WHERE m.id = (
	 SELECT c.member_id
	 FROM checkout_item c
	 WHERE c.book_id = (
	 	SELECT id
	 	FROM book
	 	WHERE title = 'The Pragmatic Programmer'
	 )
);