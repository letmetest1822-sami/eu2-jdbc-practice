package API_HWs.HW1;


import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;

public class RegionID3_Hamcrest {


    /*
Q3:
- Given accept type is Json
- Query param value q= region_id 3
- When users sends request to /countries
- Then status code is 200
- And all regions_id is 3
- And count is 6
- And hasMore is false
- And Country_name are;
Australia,China,India,Japan,Malaysia,Singapore
     */

    @Test
    public void RegionID3() {

        given().accept(ContentType.JSON)
                .queryParam("q", "{\"region_id\":3}")
                .when().get("http://3.81.99.109:1000/ords/hr/countries")
                .then()
                .statusCode(200)
                .body("items.region_id",hasItems(equalTo(3)),
                        "count", equalTo(6),
                        "hasMore", equals("false"),
                        "items.country_name", hasItems("Australia","China","India","Japan","Malaysia","Singapore"));

    }
}
