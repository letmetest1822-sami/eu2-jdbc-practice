package API_HWs.HW3_HarryPotter;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.List;
import java.util.Random;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;

public class TC06VerifyAllCharacterInfo {

    String pagekey = ConfigurationReader.getProperty("key");

    @BeforeClass
    public void beforeClass() {

        RestAssured.baseURI = ConfigurationReader.getProperty("hpapi_url");
    }

    /**
     * Verify all character information
     * 1. Send a get request to /characters. Request includes :
     *   • Header Accept with value application/json
     *   • Query param key with value {{apiKey}}
     * 2. Verify status code 200, content type application/json; charset=utf-8
     * 3. Select name of any random character
     * 4. Send a get request to /characters. Request includes :
     *   • Header Accept with value application/json
     *   • Query param key with value {{apiKey}}
     *   • Query param name with value from step 3
     * 5. Verify that response contains the same character information from step 3. Compare all fields.
     */

    @Test
    public void allCharInfo() {

        Response response = given().accept(ContentType.JSON)
                .and().queryParam("key", pagekey )
                .when().get("/characters");

        assertEquals(response.statusCode(), 200);
        assertEquals(response.contentType(), "application/json; charset=utf-8");

        Random rn = new Random();
        int chooseCharacter = rn.nextInt(195);

        List<String> names = response.path("name");
        // System.out.println(names.toString());

        String nameRandom = names.get(chooseCharacter);

        List<Character> characters = response.body().as(List.class);

        Response response2 = given().accept(ContentType.JSON)
                .and().queryParam("key", pagekey )
                .and().queryParam("name", nameRandom)
                .when().get("/characters");

        List<Character> characterRand = response2.body().as(List.class);

        assertEquals(characters.get(chooseCharacter), characterRand.get(0));

    }

}
