package API_HWs.HW3_HarryPotter;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class TC02VerifyBadKey {

    @BeforeClass
    public void beforeClass() {

        RestAssured.baseURI = ConfigurationReader.getProperty("hpapi_url");
    }

    /**
     * Verify bad key
     * 1. Send a get request to /characters. Request includes :
     * • Header Accept with value application/json
     * • Query param key with value invalid
     * 2. Verify status code 401, content type application/json; charset=utf-8
     * 3. Verify response status line include message Unauthorized
     * 4. Verify that response body says "error": "API Key Not Found"
     */

    @Test
    public void badKey() {
        Response response = given().accept(ContentType.JSON)
                .and().queryParam("key", "invalid")
                .when().get("/characters");

        assertEquals(response.statusCode(), 401);

        assertEquals(response.contentType(), "application/json; charset=utf-8");

        String statusMessage = response.statusLine();

        assertTrue(statusMessage.contains("Unauthorized"));

        assertEquals(response.body().path("error"), "API Key Not Found");
    }

}
