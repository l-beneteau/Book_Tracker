package integration;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.Data;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class AuthorTest extends BaseIntegrationTest {
    @Test
    public void testAddAuthor() {
        String body = """
                {
                "name" : "Robin Hobb"
                }
                """;
                given()
                    .contentType(ContentType.JSON)
                    .body(body)
                .when()
                    .post("/author/add")
                .then()
                    .statusCode(200)
                    .header("Content-Type", "application/json")
                    .body("authorId", equalTo(1))
                    .body("name", equalTo("Robin Hobb"));

    }

    @Test
    public void testGetAuthorByID() {
        String body1 = """
                {
                "name" : "Robin Hobb"
                }
                """;
        given()
                .contentType(ContentType.JSON)
                .body(body1)
                .when()
                .post("/author/add");
        String body2 = """
                {
                "name" : "Patrick K. Dewdney"
                }
                """;
        given()
                .contentType(ContentType.JSON)
                .body(body2)
                .when()
                .post("/author/add");

        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/author/1")
                .then()
                .statusCode(200)
                .header("Content-Type", "application/json")
                .body("authorId", equalTo(1))
                .body("name", equalTo("Robin Hobb"));

    }

    @Test
    public void testGetAuthorByIdAuthorNotFoundException(){
        String body1 = """
                {
                "name" : "Robin Hobb"
                }
                """;
        given()
                .contentType(ContentType.JSON)
                .body(body1)
                .when()
                .post("/author/add");
        String body2 = """
                {
                "name" : "Patrick K. Dewdney"
                }
                """;
        given()
                .contentType(ContentType.JSON)
                .body(body2)
                .when()
                .post("/author/add");
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/author/8728")
                .then()
                .statusCode(404)
                .header("Content-Type", "application/json");

    }


    @Test
    public void testGetAuthorByName() {
        String body1 = """
                {
                "name" : "Robin Hobb"
                }
                """;
        given()
                .contentType(ContentType.JSON)
                .body(body1)
                .when()
                .post("/author/add");
        String body2 = """
                {
                "name" : "Patrick K. Dewdney"
                }
                """;
        given()
                .contentType(ContentType.JSON)
                .body(body2)
                .when()
                .post("/author/add");

                given()
                    .contentType(ContentType.JSON)
                .when()
                    .get("/author?name=Robin Hobb")
                .then()
                    .statusCode(200)
                    .header("Content-Type", "application/json")
                    .body("authorId[0]", equalTo(1))
                    .body("authorId", hasSize(1))
                    .body("name[0]", equalTo("Robin Hobb"));

    }

    @Test
    public void testGetAuthorByNameAuthorNotFoundException(){
        String body1 = """
                {
                "name" : "Robin Hobb"
                }
                """;
        given()
                .contentType(ContentType.JSON)
                .body(body1)
                .when()
                .post("/author/add");
        String body2 = """
                {
                "name" : "Patrick K. Dewdney"
                }
                """;
        given()
                .contentType(ContentType.JSON)
                .body(body2)
                .when()
                .post("/author/add");
            given()
            .contentType(ContentType.JSON)
            .when()
            .get("/author?name=George R. R. Martin")
            .then()
            .statusCode(404)
            .header("Content-Type", "application/json");

    }
}
