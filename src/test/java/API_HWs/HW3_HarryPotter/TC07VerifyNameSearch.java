package API_HWs.HW3_HarryPotter;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class TC07VerifyNameSearch {

    String pagekey = ConfigurationReader.getProperty("key");

    @BeforeClass
    public void beforeClass() {

        RestAssured.baseURI = ConfigurationReader.getProperty("hpapi_url");
    }

    /**
     * Verify name search
     * 1. Send a get request to /characters. Request includes :
     *    • Header Accept with value application/json
     *    • Query param key with value {{apiKey}}
     *    • Query param name with value Harry Potter
     * 2. Verify status code 200, content type application/json; charset=utf-8
     * 3. Verify name Harry Potter
     * 4. Send a get request to /characters. Request includes :
     *    • Header Accept with value application/json
     *    • Query param key with value {{apiKey}}
     *    • Query param name with value Marry Potter
     * 5. Verify status code 200, content type application/json; charset=utf-8
     * 6. Verify response body is empty
     */

    @Test
    public  void randomChar(){

        Response response = given().accept(ContentType.JSON)
                .and().queryParam("key", pagekey )
                .and().queryParam("name","Harry Potter")
                .when().get("/characters");

        assertEquals(response.statusCode(), 200);
        assertEquals(response.contentType(), "application/json; charset=utf-8");
        assertEquals(response.body().path("name[0]"), "Harry Potter");

        // * 4. Send a get request to /characters. Request includes :
        //     *    • Header Accept with value application/json
        //     *    • Query param key with value {{apiKey}}
        //     *    • Query param name with value Marry Potter

        Response response2 = given().contentType(ContentType.JSON)
                .and().queryParam("key", pagekey)
                .and().queryParam("name", "Marry Potter")
                .when().get("/characters");

        //     * 5. Verify status code 200, content type application/json; charset=utf-8
        assertEquals(response2.statusCode(),200);
        assertEquals(response2.contentType(), "application/json; charset=utf-8");

        //     * 6. Verify response body is empty
        System.out.println(response2.prettyPrint());
        assertTrue(response2.body().asString().isEmpty());

    }

}
