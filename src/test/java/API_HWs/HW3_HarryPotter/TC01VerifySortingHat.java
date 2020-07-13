package API_HWs.HW3_HarryPotter;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utilities.ConfigurationReader;
import java.util.Arrays;
import java.util.List;

public class TC01VerifySortingHat {

    @BeforeClass
    public void beforeClass() {

        RestAssured.baseURI = ConfigurationReader.getProperty("hpapi_url");
    }

    /**
     * Verify sorting hat
     * 1. Send a get request to /sortingHat. Request includes :
     * 2. Verify status code 200, content type application/json; charset=utf-8
     * 3. Verify that response body contains one of the following houses:
     * "Gryffindor", "Ravenclaw", "Slytherin", "Hufflepuff"
     */

    @Test
    public void verifySortingHat_withList(){
        Response response = given().contentType(ContentType.JSON)
                .when().get("/sortingHat");

        assertEquals(response.statusCode(),200);

        assertEquals(response.contentType(),"application/json; charset=utf-8");

        List<String> houses = Arrays.asList("\"Gryffindor\"", "\"Ravenclaw\"", "\"Slytherin\"", "\"Hufflepuff\"");

        response.prettyPrint();

        assertTrue(houses.contains(response.body().asString()));
    }


    @Test
    public void verifySortingHat_with_OR(){

        Response response = given().accept(ContentType.JSON)
                .when().get("/sortingHat");

        assertEquals(response.statusCode(), 200);

        assertEquals(response.contentType(), "application/json; charset=utf-8");

        System.out.println(response.prettyPrint());

        assertTrue(response.body().asString().contains("Gryffindor")
                        || response.body().asString().contains("Ravenclaw")
                        || response.body().asString().contains("Slytherin")
                        || response.body().asString().contains("Hufflepuff"));
    }

}
