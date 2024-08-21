package integration;

import io.restassured.http.ContentType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@EqualsAndHashCode(callSuper = true)
@Data
public class BookTest extends BaseIntegrationTest {
    @Test
    public void testAddBook() {
        String bodyAuthor = """
                {
                "name" : "Robin Hobb"
                }
                """;
        given()
                .contentType(ContentType.JSON)
                .body(bodyAuthor)
                .when()
                .post("/author/add");

        String bodyBook = """
                {
                    "title" : "Assassin's Apprentice",
                    "series" : "Farseer trilogy",
                    "authors":[1],
                    "year" : 1995,
                    "genre" : "FANTASY",
                    "pages" : 400,
                    "read" : true,
                    "rating" : "WONDERFUL"
                }
                """;
        given()
                .contentType(ContentType.JSON)
                .body(bodyBook)
                .when()
                .post("/book/add")
                .then()
                .statusCode(200)
                .header("Content-Type", "application/json")
                .body("bookId", equalTo(1))
                .body("title", equalTo("Assassin's Apprentice"))
                .body("series", equalTo("Farseer trilogy"))
                .body("authors[0].authorId", equalTo(1))
                .body("authors[0].name", equalTo("Robin Hobb"))
                .body("year", equalTo(1995))
                .body("genre", equalTo("FANTASY"))
                .body("pages", equalTo(400))
                .body("read", equalTo(true))
                .body("rating", equalTo("WONDERFUL"));

    }

    @Test
    public void testAddBookNoAuthorException() {

        String body = """
                {
                    "title" : "Assassin's Apprentice",
                    "series" : "Farseer trilogy",
                    "authors":[],
                    "year" : 1995,
                    "genre" : "FANTASY",
                    "pages" : 400,
                    "read" : true,
                    "rating" : "WONDERFUL"
                }
                """;
        given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post("/book/add")
                .then()
                .statusCode(400);

    }

    @Test
    public void testAddBookAuthorNotFoundException() {
        String body = """
                {
                    "title" : "Assassin's Apprentice",
                    "series" : "Farseer trilogy",
                    "authors":[8],
                    "year" : 1995,
                    "genre" : "FANTASY",
                    "pages" : 400,
                    "read" : true,
                    "rating" : "WONDERFUL"
                }
                """;
        given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post("/book/add")
                .then()
                .statusCode(404);

    }

    @Test
    public void testGetBookById() {
        booksSetup();
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/book/2")
                .then()
                .statusCode(200)
                .body("bookId", equalTo(2))
                .body("title", equalTo("Royal Assassin"))
                .body("series", equalTo("Farseer trilogy"))
                .body("authors[0].authorId", equalTo(1))
                .body("authors[0].name", equalTo("Robin Hobb"))
                .body("year", equalTo(1996))
                .body("genre", equalTo("FANTASY"))
                .body("pages", equalTo(580))
                .body("read", equalTo(true))
                .body("rating", equalTo("GOOD"));
    }

    @Test
    public void testGetBooksByTitle() {
        booksSetup();
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/book?title=Royal Assassin")
                .then()
                .statusCode(200)
                .body("bookId", hasSize(1))
                .body("bookId[0]", equalTo(2));
    }

    @Test
    public void testGetBooksBySeries() {
        booksSetup();
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/book?series=Farseer trilogy")
                .then()
                .statusCode(200)
                .body("bookId", hasSize(3))
                .body("bookId[0]", equalTo(1))
                .body("bookId[1]", equalTo(2))
                .body("bookId[2]", equalTo(4));
    }

    @Test
    public void testGetBooksByGenre() {
        booksSetup();
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/book?genre=FANTASY")
                .then()
                .statusCode(200)
                .body("bookId", hasSize(3))
                .body("bookId[0]", equalTo(1))
                .body("bookId[1]", equalTo(2))
                .body("bookId[2]", equalTo(4));
    }

    @Test
    public void testGetReadBooks() {
        booksSetup();
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/book?read=true")
                .then()
                .statusCode(200)
                .body("bookId", hasSize(3))
                .body("bookId[0]", equalTo(1))
                .body("bookId[1]", equalTo(2))
                .body("bookId[2]", equalTo(3));
    }

    @Test
    public void testGetBooksByRating() {
        booksSetup();
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/book?rating=WONDERFUL")
                .then()
                .statusCode(200)
                .body("bookId", hasSize(2))
                .body("bookId[0]", equalTo(1))
                .body("bookId[1]", equalTo(3));
    }

    @Test
    public void testGetBookByDate(){
        booksSetup();
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/book?started=2022-11-15&ended=2023-02-01")
                .then()
                .statusCode(200)
                .body("bookId", hasSize(1))
                .body("bookId[0]", equalTo(2));
    }

    public void booksSetup() {
        String bodyAuthor1 = """
                {
                "name" : "Robin Hobb"
                }
                """;
        given()
                .contentType(ContentType.JSON)
                .body(bodyAuthor1)
                .when()
                .post("/author/add");

        String bodyAuthor2 = """
                {
                "name" : "Christian Grataloup"
                }
                """;
        given()
                .contentType(ContentType.JSON)
                .body(bodyAuthor2)
                .when()
                .post("/author/add");
        String bodyAuthor3 = """
                {
                "name" : "Charlotte Becquart-Rousset"
                }
                """;
        given()
                .contentType(ContentType.JSON)
                .body(bodyAuthor3)
                .when()
                .post("/author/add");


        String bodyBook1 = """
                {
                    "title" : "Assassin's Apprentice",
                    "series" : "Farseer trilogy",
                    "authors":[1],
                    "year" : 1995,
                    "genre" : "FANTASY",
                    "pages" : 400,
                    "read" : true,
                    "started" : "2022-11-01",
                    "ended" : "2022-12-01",
                    "rating" : "WONDERFUL"
                }
                """;
        given()
                .contentType(ContentType.JSON)
                .body(bodyBook1)
                .when()
                .post("/book/add")
                .then();
        String bodyBook2 = """
                {
                    "title" : "Royal Assassin",
                    "series" : "Farseer trilogy",
                    "authors":[1],
                    "year" : 1996,
                    "genre" : "FANTASY",
                    "pages" : 580,
                    "read" : true,
                    "started" : "2022-12-01",
                    "ended" : "2023-01-15",
                    "rating" : "GOOD"
                }""";
        given()
                .contentType(ContentType.JSON)
                .body(bodyBook2)
                .when()
                .post("/book/add")
                .then();
        String bodyBook3 = """
                {
                    "title" : "Atlas historique de la terre",
                    "authors":[2,3],
                    "year" : 2022,
                    "genre" : "POPULARIZATION",
                    "pages" : 337,
                    "read" : true,
                    "started" : "2023-01-16",
                    "ended" : "2023-03-01",
                    "rating" : "WONDERFUL"
                }""";
        given()
                .contentType(ContentType.JSON)
                .body(bodyBook3)
                .when()
                .post("/book/add")
                .then();
        String bodyBook4 = """
                {
                    "title" : "Assassin's Quest",
                    "series" : "Farseer trilogy",
                    "authors":[1],
                    "year" : 1997,
                    "genre" : "FANTASY",
                    "pages" : 742,
                    "read" : false
                }""";
        given()
                .contentType(ContentType.JSON)
                .body(bodyBook4)
                .when()
                .post("/book/add")
                .then();

    }
}




