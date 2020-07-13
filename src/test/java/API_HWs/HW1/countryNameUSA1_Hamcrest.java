package API_HWs.HW1;

import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;

public class countryNameUSA1_Hamcrest {

    /*
   Q1:
- Given accept type is Json
- Path param value- US
- When users sends request to /countries
- Then status code is 200
- And Content - Type is Json
- And country_id is US
- And Country_name is United States of America
- And Region_id is
*/
    @Test
    public void CountryName() {

    given().accept(ContentType.JSON)
            .and().queryParam("q","{\"country_id\":\"US\"}")
            .when().get("http://3.81.99.109:1000/ords/hr/countries")
            .then()
            .assertThat().statusCode(200)
            .contentType("application/json")
            .body("items.country_id[0]", equalTo("US"),
                    "items.country_name[0]", equalTo("United States of America"),
                    "items.region_id[0]", equalTo(2));

    }
}