package integration;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;
import io.restassured.http.ContentType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
                .post("/author/add")
                .then()
                .statusCode(200)
                .header("Content-Type", "application/json")
                .body("authorId", equalTo(1))
                .body("name", equalTo("Robin Hobb"));

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
        String json =
                given()
                        .contentType(ContentType.JSON)
                        .body(bodyBook)
                        .when()
                        .post("/book/add")
                        .then()
                        .statusCode(200)
                        .header("Content-Type", "application/json")
                        .body("bookId", notNullValue())
                        .body("title", equalTo("Assassin's Apprentice"))
                        .body("series", equalTo("Farseer trilogy"))
                        .body("year", equalTo(1995))
                        .body("genre", equalTo("FANTASY"))
                        .body("pages", equalTo(400))
                        .body("read", equalTo(true))
                        .body("rating", equalTo("WONDERFUL"))
                        .toString();

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
                    "readed" : true,
                    "rating" : "WONDERFUL",
                }
                """;
        String json =
                given()
                        .contentType(ContentType.JSON)
                        .body(body)
                        .when()
                        .post("/book/add")
                        .then()
                        .statusCode(400)
                        .toString();

    }




//    public void testGetBook() {
//        String json = given()
//                .pathParam("id", 13)
//                .when()
//                .get("/book/{id}")
//                .then()
//                .statusCode(200)
//                .extract()
//                .asString();
//
//        ReadContext jsonPath = JsonPath.parse(json);
//
//        assertEquals("17", jsonPath.read("$.bookId").toString());
//    }
}
