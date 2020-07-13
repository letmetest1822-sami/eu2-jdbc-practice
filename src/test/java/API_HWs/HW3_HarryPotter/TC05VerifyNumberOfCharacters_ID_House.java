package API_HWs.HW3_HarryPotter;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class TC05VerifyNumberOfCharacters_ID_House {

    String pagekey = ConfigurationReader.getProperty("key");

    @BeforeClass
    public void beforeClass() {

        RestAssured.baseURI = ConfigurationReader.getProperty("hpapi_url");
    }

    /**
     * Verify number of character id and house
     * 1. Send a get request to /characters. Request includes :
     * • Header Accept with value application/json
     * • Query param key with value {{apiKey}}
     * 2. Verify status code 200, content type application/json; charset=utf-8
     * 3. Verify all characters in the response have id field which is not empty
     * 4. Verify that value type of the field dumbledoresArmy is a boolean in all characters in the response
     * 5. Verify value of the house in all characters in the response is one of the following:
     * "Gryffindor", "Ravenclaw", "Slytherin", "Hufflepuff"
     */

    @Test
    public void numberOfCharIDandHouse() {

        given().accept(ContentType.JSON)
                .and().queryParam("key", pagekey)
                .when().get("/characters")

        .then()
                .assertThat()
                    .statusCode(200)
                    .contentType("application/json; charset=utf-8")

                .and().body("_id", hasItem(notNullValue()))

                            //path içerisinde verilen yığın içerisinde boş olan var mı?         --Tek parametre aranırsa item
                .and().body("dumbledoresArmy", hasItems(true, false))

                            //path içerisinde verilen yığın içerisinde bu ikisi var mı?         --Birden çok parametre aranırsa items
                .and().body("house", hasItems("Gryffindor", "Ravenclaw", "Slytherin", "Hufflepuff"));

    }

}
