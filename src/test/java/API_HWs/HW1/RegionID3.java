package API_HWs.HW1;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import utilities.ConfigurationReader;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import java.util.List;

public class RegionID3 {

    @BeforeClass
    public void beforeClass() {
        RestAssured.baseURI = ConfigurationReader.getProperty("hrapi_url");
    }

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

//- Given accept type is Json
        Response response = given().accept(ContentType.JSON)
// - Query param value q= region_id 3
                .and().queryParam("q", "{\"region_id\": 3}")
// - When users sends request to /countries
                .when().get("/countries");
//- Then status code is 200
        assertEquals(response.statusCode(), 200);
// - And all regions_id is 3

    List<Integer> regionIDs = response.path("region_ID");
        System.out.println("regionIDs = " + regionIDs);

    }
}
