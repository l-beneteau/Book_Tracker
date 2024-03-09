package integration;

import ch.qos.logback.classic.Level;
import io.restassured.RestAssured;
import io.restassured.filter.log.ErrorLoggingFilter;
import io.restassured.filter.log.RequestLoggingFilter;
import org.booktracker.Application;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = Application.class)
public class BaseIntegrationTest {

    @LocalServerPort
    int port;

    @BeforeAll
    public void setup() {
        // Ref https://github.com/rest-assured/rest-assured/wiki/Usage
        RestAssured.basePath = "";

        // Target
        // Local
        RestAssured.port = this.port;

        // RestAssured logging
        RestAssured.filters(
                new RequestLoggingFilter(), // log requests
//                new ResponseLoggingFilter(), // uncomment to log every test response
                new ErrorLoggingFilter()); // log errors

        // Logging (temp)
        ch.qos.logback.classic.Logger root = (ch.qos.logback.classic.Logger) org.slf4j.LoggerFactory.getLogger(
                ch.qos.logback.classic.Logger.ROOT_LOGGER_NAME);
        root.setLevel(Level.INFO);
    }
}
