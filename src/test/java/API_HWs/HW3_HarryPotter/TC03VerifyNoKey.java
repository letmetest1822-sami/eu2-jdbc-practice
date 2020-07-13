package API_HWs.HW3_HarryPotter;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class TC03VerifyNoKey {

    @BeforeClass
    public void beforeClass() {

        RestAssured.baseURI = ConfigurationReader.getProperty("hpapi_url");
    }

    /**
     * Verify no key
     * 1. Send a get request to /characters. Request includes :
     * • Header Accept with value application/json
     * 2. Verify status code 409, content type application/json; charset=utf-8
     * 3. Verify response status line include message Conflict
     * 4. Verify that response body says "error": "Must pass API key for request"
     * Verify
     */

    @Test
    public void noKey() {
        Response response = given().accept(ContentType.JSON)
                .when().get("/characters");

        assertEquals(response.statusCode(), 409);

        assertEquals(response.contentType(),"application/json; charset=utf-8");

        String errorMessage = response.statusLine();
        assertTrue(errorMessage.contains("Conflict"));

        assertEquals(response.path("error"),"Must pass API key for request" );
    }

    @Test
    public void noKeyWithHamcrestMatchers() {
        given().accept(ContentType.JSON)
                .when().get("/characters")

                .then()
                .assertThat().statusCode(409)
                .contentType("application/json; charset=utf-8")
                //.statusLine(contains("Conflict"))
                .body("error", equalTo("Must pass API key for request"));

    }
}
