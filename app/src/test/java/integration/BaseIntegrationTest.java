package integration;

import ch.qos.logback.classic.Level;
import io.restassured.RestAssured;
import io.restassured.filter.log.ErrorLoggingFilter;
import io.restassured.filter.log.RequestLoggingFilter;
import lombok.Data;
import org.booktracker.Application;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;
import java.sql.SQLException;

//@TestPropertySource(locations = "classpath:config/application-test.properties")
@TestPropertySource(locations = "classpath:application-test.properties")
@Data
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = Application.class)
public class BaseIntegrationTest {

    @Autowired
    private DataSource dataSource;


    @LocalServerPort
    int port;

    @BeforeAll
    public void setup() throws SQLException {
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

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        ScriptUtils.executeSqlScript(jdbcTemplate.getDataSource().getConnection(),
                new ClassPathResource("booktracker-test-truncate.sql"));
    }
}
