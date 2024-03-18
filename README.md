# Book Tracker

This API provides functionality to store and manage books in a database. It allows users to perform CRUD (Create, Read, Update, Delete) operations on books.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

What things you need to install the software and how to install them

```
Give examples
```

### Installing

A step by step series of examples that tell you how to get a development env running

Say what the step will be

```
Give the example
```

And repeat

```
until finished
```

End with an example of getting some data out of the system or using it for a little demo

## Endpoints

### Get all authors

```
GET /authors
```
Returns a list of all authors stored in the database.

Request
No request parameters required.

Response
```
[
    {
        "authorId": 1,
        "name": "Author Name 1",
        "books": [
            {
                "bookId": 1,
                "title": "Book Title 1",
                "series": "Series 1",
                "year": 0,
                "genre": "FANTASY",
                "pages": 0,
                "read": true,
                "rating": "WONDERFUL",
                "notes": "Lorem Ipsum"
            }
            {
                "bookId": 2,
                "title": "Book Title 2",
                "series": "Series 2",
                "year": 0,
                "genre": "FANTASY",
                "pages": 0,
                "read": true,
                "rating": "WONDERFUL",
                "notes": "Lorem Ipsum"
            }
        ]
    }
]
```


## Built With

* [Dropwizard](http://www.dropwizard.io/1.0.2/docs/) - The web framework used
* [Maven](https://maven.apache.org/) - Dependency Management
* [ROME](https://rometools.github.io/rome/) - Used to generate RSS Feeds

## Contributing

Please read [CONTRIBUTING.md](https://gist.github.com/PurpleBooth/b24679402957c63ec426) for details on our code of conduct, and the process for submitting pull requests to us.

## Versioning

We use [SemVer](http://semver.org/) for versioning. For the versions available, see the [tags on this repository](https://github.com/your/project/tags). 

## Authors

* **Billie Thompson** - *Initial work* - [PurpleBooth](https://github.com/PurpleBooth)

See also the list of [contributors](https://github.com/your/project/contributors) who participated in this project.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgments

* Hat tip to anyone whose code was used
* Inspiration
* etc
