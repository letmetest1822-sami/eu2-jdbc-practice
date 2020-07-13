package API_HWs.HW3_HarryPotter;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.Arrays;
import java.util.List;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.hasItems;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class TC10VerifyHouseWithMostMembers {

    String pagekey = ConfigurationReader.getProperty("key");

    @BeforeClass
    public void beforeClass() {

        RestAssured.baseURI = ConfigurationReader.getProperty("hpapi_url");
    }

/**
 * Verify house with most members
 * 1. Send a get request to /houses. Request includes :
 *    • Header Accept with value application/json
 *    • Query param key with value {{apiKey}}
 * 2. Verify status code 200, content type application/json; charset=utf-8
 * 3. Verify that Gryffindor house has the most members
 */

@Test
public void houseWithMostMembers() {
    given().accept(ContentType.JSON)
            .and().queryParam("key", pagekey)
            .when().get("/houses")

    .then()
            .assertThat()
                .statusCode(200)
                .contentType("application/json; charset=utf-8");





}

}
