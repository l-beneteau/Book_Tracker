# Book Tracker

This API provides functionality to store and manage books in a database. It allows users to perform CRUD (Create, Read, Update, Delete) operations on books.

## Getting Started

To run this project, you'll require the PostgreSQL relational database management system. The database schema is defined in the file named "booktracker-model.sql".

Prior to initiating the project, generate a file named "application.properties" following the provided template located at "app/src/main/resources/application.properties".

To run the project, use the following command:

```
./gradlew bootRun
```
If you encounter a "permission denied" error during the compilation process, attempt the following command:
```
chmod +x gradlew
```

## Endpoints

### Add an author
```
POST /author
```
Add an author to the database.

Request body
```
{
    "name": "Author Name"
}
```

Request details

* `name` (String): Name of the author.

### Get author by ID
```
GET /author/{id}
```
Returns the details of an author specified by its ID.

Request details

* `id` (integer): ID of the author to retrieve.

Response
```
[
    {
        "authorId": 1,
        "name": "Robin Hobb",
        "books": [
            {
                "bookId": 2,
                "title": "Royal Assassin",
                "series": "Farseer trilogy",
                "year": 1996,
                "genre": "FANTASY",
                "pages": 580,
                "read": true,
                "started": "2023-04-20",
                "ended": "2023-06-01",
                "rating": "GOOD",
                "notes": null
            },
            {
                "bookId": 1,
                "title": "Assassin's Apprentice",
                "series": "Farseer trilogy",
                "year": 1995,
                "genre": "FANTASY",
                "pages": 400,
                "read": true,
                "started": "2023-02-20",
                "ended": "2023-04-01",
                "rating": "WONDERFUL",
                "notes": null
            }
        ]
    }
]
```
### Get authors

```
GET /author?name={name}
```

Return the details of all the authors with specified name, if the parameter name is not entered, return all the authors in the database. 

Request details

* `name` (String): name of the authors to retrieve.

Response
```
[
    {
        "authorId": 1,
        "name": "Robin Hobb",
        "books": [
            {
                "bookId": 2,
                "title": "Royal Assassin",
                "series": "Farseer trilogy",
                "year": 1996,
                "genre": "FANTASY",
                "pages": 580,
                "read": true,
                "started": "2023-04-20",
                "ended": "2023-06-01",
                "rating": "GOOD",
                "notes": null
            },
            {
                "bookId": 1,
                "title": "Assassin's Apprentice",
                "series": "Farseer trilogy",
                "year": 1995,
                "genre": "FANTASY",
                "pages": 400,
                "read": true,
                "started": "2023-02-20",
                "ended": "2023-04-01",
                "rating": "WONDERFUL",
                "notes": null
            }
        ]
    }
]
```


### Add book

```
POST /book
```
Add a book to the database.

Request body
```
{
    "title": "Book title",
    "authors": [Author 1 ID, Author 2 ID, ...],
    "series": "series",
    "year": year,
    "genre": genre,
    "pages": pages nb,
    "read": read,
    "started":started,
    "ended":ended
    "rating": "rating",
    "notes": "Lorem ipsum"
}
```

Request details

* `title` (String): title of the book.
* `authors` (String): tList of the book's authors.
* `series` (String): seriesof the book.
* `year` (integer): year the book was published.
* `genre` (String): genre of the book (FANTASY, SCIENCE_FICTION, DOCUMENTARY, POPULARIZATION, COMIC_BOOK, FANTASTIC or DETECTIVE_NOVEL)
* `read` (Boolean): true if the book is read, false otherwise
* `started` (Date): date the book was started (fotmat : "yyyy-mm-dd")
* `ended` (Date): date the book was finished (fotmat : "yyyy-mm-dd")
* `rating` (String): rating of the book (UNFINISHED, MEH, GOOD or WONDERFUL).
* `notes` (String): notes about the book.


### Get book by ID
```
GET /book/{id}
```
Returns the details of a book specified by its ID.

Request details

* `id` (integer): ID of the book to retrieve.

Response
```
{
    "bookId": 1,
    "title": "Assassin's Apprentice",
    "authors": [
        {
            "authorId": 1,
            "name": "Robin Hobb"
        }
    ],
    "series": "Farseer trilogy",
    "year": 1995,
    "genre": "FANTASY",
    "pages": 400,
    "read": true,
    "started": "2023-02-20",
    "ended": "2023-04-01",
    "rating": "WONDERFUL",
    "notes": null
}
```

### Get book by properties
```
GET /book?title={title}&series={series}&year={year}&genre={genre}&read={read}&rating={rating}
```
Returns the details of the books that matches all the entered properties, each property is optional.

Request details

* `title` (String): title of the books to retrieve.
* `series` (String): series of the books to retrieve.
* `year` (integer): year the books to retrieve was published.
* `genre` (String): genre of the books to retrieve (FANTASY, SCIENCE_FICTION, DOCUMENTARY, POPULARIZATION, COMIC_BOOK, FANTASTIC or DETECTIVE_NOVEL)
* `read` (Boolean): true if the books to retrieve are read, false otherwise
* `rating` (String): rating of the books to retrieve (UNFINISHED, MEH, GOOD or WONDERFUL).

Response
```
[
    {
        "bookId": 1,
        "title": "Assassin's Apprentice",
        "authors": [
            {
                "authorId": 1,
                "name": "Robin Hobb"
            }
        ],
        "series": "Farseer trilogy",
        "year": 1995,
        "genre": "FANTASY",
        "pages": 400,
        "read": true,
        "started": "2023-02-20",
        "ended": "2023-04-01",
        "rating": "WONDERFUL",
        "notes": null
    },
    {
        "bookId": 2,
        "title": "Royal Assassin",
        "authors": [
            {
                "authorId": 1,
                "name": "Robin Hobb"
            }
        ],
        "series": "Farseer trilogy",
        "year": 1996,
        "genre": "FANTASY",
        "pages": 580,
        "read": true,
        "started": "2023-04-20",
        "ended": "2023-06-01",
        "rating": "GOOD",
        "notes": null
    }
]
```

### Statistics on read books
```
GET /stats?series={series}&genre={genre}&rating={rating}&grouptype={grouptype}
```
This endpoint retrieves statistics about the read books based on various criteria. 
It returns aggregated data containing information about the number of books 
and total pages. Each parameter is optional.

Request details

* `series` (String): Filter books by series.
* `genre` (String): Filter books by genre (FANTASY, SCIENCE_FICTION, DOCUMENTARY, POPULARIZATION, COMIC_BOOK, FANTASTIC or DETECTIVE_NOVEL)
* `rating` (String): Filter books by rating (UNFINISHED, MEH, GOOD or WONDERFUL).
* `grouptype`(String):Specifies the grouping type for the statistics(GENRE or RATING).

Response

```
[
    {
        "groupType": "GENRE",
        "groupValue": "FANTASY",
        "stat": {
            "nbBook": 1,
            "nbPage": 400
        }
    },
    {
        "groupType": "GENRE",
        "groupValue": "SCIENCE_FICTION",
        "stat": {
            "nbBook": 0,
            "nbPage": 0
        }
    },
    {
        "groupType": "GENRE",
        "groupValue": "DOCUMENTARY",
        "stat": {
            "nbBook": 0,
            "nbPage": 0
        }
    },
    {
        "groupType": "GENRE",
        "groupValue": "POPULARIZATION",
        "stat": {
            "nbBook": 1,
            "nbPage": 337
        }
    },
    {
        "groupType": "GENRE",
        "groupValue": "COMIC_BOOK",
        "stat": {
            "nbBook": 0,
            "nbPage": 0
        }
    },
    {
        "groupType": "GENRE",
        "groupValue": "FANTASTIC",
        "stat": {
            "nbBook": 0,
            "nbPage": 0
        }
    },
    {
        "groupType": "GENRE",
        "groupValue": "DETECTIVE_NOVEL",
        "stat": {
            "nbBook": 0,
            "nbPage": 0
        }
    }
]
```