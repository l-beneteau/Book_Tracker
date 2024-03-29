CREATE TABLE author(
	author_id SERIAL,
	name Varchar(100),
	PRIMARY KEY (author_id)
);
	
CREATE TABLE book (
	book_id SERIAL,
	title Varchar(100) not null,
	series Varchar(100),
	year Int,
	genre Varchar(50),
	pages Int,
	read Boolean,
	started Date,
	ended Date,
	rating Varchar(20),
	notes Varchar(1000),
	PRIMARY KEY (book_id)
);

CREATE TABLE book_author (
	book_id Int not null,
	author_id Int not null,
	PRIMARY KEY (book_id, author_id),
	FOREIGN KEY (book_id) REFERENCES book(book_id) ON DELETE CASCADE,
	FOREIGN KEY (author_id) REFERENCES author(author_id) ON DELETE CASCADE
);