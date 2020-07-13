package API_HWs.HW3_HarryPotter;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class TC04VerifyNumberOfCaharcters {

    String pagekey = ConfigurationReader.getProperty("key");

    @BeforeClass
    public void beforeClass() {

        RestAssured.baseURI = ConfigurationReader.getProperty("hpapi_url");

    }

    /**
     * Verify number of characters
     * 1. Send a get request to /characters. Request includes :
     *   • Header Accept with value application/json
     *   • Query param key with value {{apiKey}}
     * 2. Verify status code 200, content type application/json; charset=utf-8
     * 3. Verify response contains 194 characters
     */

    @Test
    public void numberOfCharactersWithMatchers() {
        given().accept(ContentType.JSON)
                .and().queryParam("key", pagekey)
                .when().get("/characters")
        .then()
                .assertThat()
                    .statusCode(200)
                    .contentType("application/json; charset=utf-8")
                    .body(hasSize(195));

    }

}
