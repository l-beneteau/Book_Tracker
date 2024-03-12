package integration;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookTest extends BaseIntegrationTest {
    @Test
    public void testGetBook() {
        String json = given()
                .pathParam("id", 17)
                .when()
                .get("/book/{id}")
                .then()
                .statusCode(200)
                .extract()
                .asString();

        ReadContext jsonPath = JsonPath.parse(json);

        assertEquals("17", jsonPath.read("$.bookId").toString());
    }
}
