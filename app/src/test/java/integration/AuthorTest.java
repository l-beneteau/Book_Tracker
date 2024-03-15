package integration;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;
import io.restassured.http.ContentType;
import lombok.Data;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
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
        String json =
                given()
                    .contentType(ContentType.JSON)
                    .body(body)
                .when()
                    .post("/author/add")
                .then()
                    .statusCode(200)
                    .header("Content-Type", "application/json")
                    .body("authorId", notNullValue())
                    .body("name", equalTo("Robin Hobb")).toString();

    }
    @Test
    public void testGetAuthorByName() {
        String json =
                given()
                    .contentType(ContentType.JSON)
                .when()
                    .get("/author?name=Robin Hobb")
                .then()
                    .statusCode(200)
                    .header("Content-Type", "application/json")
                    .body("name", hasItem("Robin Hobb")).toString();

    }
}
