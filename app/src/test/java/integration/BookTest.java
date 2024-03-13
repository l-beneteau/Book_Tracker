package integration;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@EqualsAndHashCode(callSuper = true)
@Data
public class BookTest extends BaseIntegrationTest {
    @Test
    public void testTest(){
        assertTrue(true);
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
