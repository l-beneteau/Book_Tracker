package integration;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;

public class StatTest extends BaseIntegrationTest{
    @Test
    public void testGetStatNoParameter(){
        databaseSetup();
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/stat")
                .then()
                .statusCode(200)
                .body("stat[0].nbBook", equalTo(4))
                .body("stat[0].nbPage", equalTo(1765));

    }

    @Test
    public void testGetStatWithParameter(){
        databaseSetup();
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/stat?rating=WONDERFUL")
                .then()
                .statusCode(200)
                .body("stat[0].nbBook", equalTo(3))
                .body("stat[0].nbPage", equalTo(1185));

    }

    @Test
    public void testGetStatWithParameterAndGroup(){
        databaseSetup();
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/stat?rating=WONDERFUL&groupType=GENRE")
                .then()
                .statusCode(200)
                .body("stat[0].nbBook", equalTo(2))
                .body("stat[0].nbPage", equalTo(848))
                .body("stat[1].nbBook", equalTo(0))
                .body("stat[1].nbPage", equalTo(0))
                .body("stat[2].nbBook", equalTo(0))
                .body("stat[2].nbPage", equalTo(0))
                .body("stat[3].nbBook", equalTo(1))
                .body("stat[3].nbPage", equalTo(337))
                .body("stat[4].nbBook", equalTo(0))
                .body("stat[4].nbPage", equalTo(0))
                .body("stat[5].nbBook", equalTo(0))
                .body("stat[5].nbPage", equalTo(0))
                .body("stat[6].nbBook", equalTo(0))
                .body("stat[6].nbPage", equalTo(0));


    }

    public void databaseSetup() {
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

        String bodyAuthor4 = """
                {
                "name" : "Claire North"
                }
                """;
        given()
                .contentType(ContentType.JSON)
                .body(bodyAuthor4)
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

        String bodyBook5 = """
                {
                    "title" : "The Gameshouse",
                    "authors":[4],
                    "year" : 2015,
                    "genre" : "FANTASY",
                    "pages" : 448,
                    "read" : true,
                    "started" : "2023-03-01",
                    "ended" : "2023-04-01",
                    "rating" : "WONDERFUL"
                }""";
        given()
                .contentType(ContentType.JSON)
                .body(bodyBook5)
                .when()
                .post("/book/add")
                .then();

    }
}
