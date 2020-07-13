package API_HWs.HW3_HarryPotter;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import static io.restassured.RestAssured.given;

public class TC08VerifyHouseMembers {

    String pagekey = ConfigurationReader.getProperty("key");

    @BeforeClass
    public void beforeClass() {

        RestAssured.baseURI = ConfigurationReader.getProperty("hpapi_url");
    }


/**
 * Verify house members
 * 1. Send a get request to /houses. Request includes :
 * • Header Accept with value application/json
 * • Query param key with value {{apiKey}}
 * 2. Verify status code 200, content type application/json; charset=utf-8
 * 3. Capture the id of the Gryffindor house
 * 4. Capture the ids of the all members of the Gryffindor house
 * 5. Send a get request to /houses/:id. Request includes :
 * • Header Accept with value application/json
 * • Query param key with value {{apiKey}}
 * • Path param id with value from step 3
 * 6. Verify that response contains the same member ids as the step 4
 */

@Test
public void houseMembers() {
    given().accept(ContentType.JSON)
            .and().queryParam("key", pagekey)
            .when().get("/houses")
    .then()
            .assertThat()
                .statusCode(200)
                .contentType("application/json; charset=utf-8");




}

}
