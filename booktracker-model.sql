CREATE TABLE author(
	authorId SERIAL,
	name Varchar(100),
	PRIMARY KEY (authorId)
);
	
CREATE TABLE book (
	bookId SERIAL,
	title Varchar(100) not null,
	series Varchar(100),
	year Int,
	genre Varchar(50),
	french Boolean,
	library Boolean,
	pages Int,
	read Boolean,
	started Date,
	ended Date,
	rating Int,
	notes Varchar(1000),
	PRIMARY KEY (bookId)
);

CREATE TABLE book_author (
	bookId Int not null,
	authorId Int not null,
	PRIMARY KEY (bookId, authorId),
	FOREIGN KEY (bookId) REFERENCES book(bookId),
	FOREIGN KEY (authorId) REFERENCES author(authorId)
);