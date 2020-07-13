package API_HWs.HW3_HarryPotter;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.Arrays;
import java.util.List;

public class TestCase01VerifySortingHat {
    String pagekey = "$2a$10$1aEwwK7/5SoSNyHEkOCfY.jZ/nNK/ohfoqH1Bkl95inAHJMOiTava";

    /**
     * Verify sorting hat
     * 1. Send a get request to /sortingHat. Request includes :
     * 2. Verify status code 200, content type application/json; charset=utf-8
     * 3. Verify that response body contains one of the following houses:
     * "Gryffindor", "Ravenclaw", "Slytherin", "Hufflepuff"
     */

    @Test
    public void verifySortingHat(){
        Response response = given().contentType(ContentType.JSON)
                .and().auth().basic("letmetest1822@gmail.com", "1!HarryPotter1!")
                .when().get("https://www.potterapi.com/v1/sortingHat");

        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json; charset=utf-8");


        List<String> houses = Arrays.asList("\"Gryffindor\"", "\"Ravenclaw\"", "\"Slytherin\"", "\"Hufflepuff\"");
        response.prettyPrint();

       assertTrue(houses.contains(response.body().asString()));

    }

    @Test
    public void test1(){

        Response response = given().accept(ContentType.JSON)
                .when().get("https://www.potterapi.com/v1/sortingHat");

        assertEquals(response.statusCode(), 200);
        assertEquals(response.contentType(), "application/json; charset=utf-8");

        System.out.println(response.prettyPrint());
        assertTrue(response.body().asString().contains("Gryffindor")
        || response.body().asString().contains("Ravenclaw")
        || response.body().asString().contains("Slytherin")
        || response.body().asString().contains("Hufflepuff"));
    }


}
