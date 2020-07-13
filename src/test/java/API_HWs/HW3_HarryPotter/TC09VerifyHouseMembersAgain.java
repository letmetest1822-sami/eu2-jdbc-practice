package API_HWs.HW3_HarryPotter;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class TC09VerifyHouseMembersAgain {

    String pagekey = ConfigurationReader.getProperty("key");

    @BeforeClass
    public void beforeClass() {

        RestAssured.baseURI = ConfigurationReader.getProperty("hpapi_url");
    }




/**
 * Verify house members again
 * 1. Send a get request to /houses/:id. Request includes :
 *    • Header Accept with value application/json
 *    • Query param key with value {{apiKey}}
 *    • Path param id with value 5a05e2b252f721a3cf2ea33f
 * 2. Capture the ids of all members
 * 3. Send a get request to /characters. Request includes :
 *    • Header Accept with value application/json
 *    • Query param key with value {{apiKey}}
 *    • Query param house with value Gryffindor
 * 4. Verify that response contains the same member ids from step 2
 */

@Test
public void houseMembersAgain() {
    Response response = given().accept(ContentType.JSON)
            .and().queryParam("key", pagekey)
            .and().queryParam("id", "5a05e2b252f721a3cf2ea33f")
            .when().get("/characters");

    assertEquals(response.statusCode(),200);
    assertEquals(response.contentType(),"application/json; charset=utf-8");


    List<String> membersListByID = response.path("members");

    Response response2 = given().accept(ContentType.JSON)
            .and().queryParam("key", pagekey)
            .and().queryParam("house", "Gryffindor")
            .when().get("/characters");

    List<String> membersListByHouse = response.path("members");

    System.out.println("membersListByHouse.size() = " + membersListByHouse.size());
    System.out.println("membersListByID.size() = " + membersListByID.size());

    for (int i=0; i<membersListByHouse.size(); i++){
        assertEquals(membersListByHouse.get(i),membersListByID.get(i));
    }
}

}
