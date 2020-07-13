package API_HWs.HW1;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class countryNameUSA1_JsonPath {

    @BeforeClass
    public void beforeClass() {
        RestAssured.baseURI = ConfigurationReader.getProperty("hrapi_url");
    }

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

//- Given accept type is Json
    Response response = given().accept(ContentType.JSON)

//- Path param value- US
           .pathParam("country_id", "US")

//- When users sends request to /countries
           .when().get("/countries/{country_id}");

// - Then status code is 200
        assertEquals(response.statusCode(), 200);

//- And Content - Type is Json
        assertEquals(response.contentType(),"application/json");

        JsonPath jsonData = response.jsonPath();

//- And country_id is US
        String countryID = jsonData.getString("country_id");
        System.out.println("countryID = " + countryID);
        assertEquals(countryID,"US");

//- And Country_name is United States of America
        String countryName = jsonData.getString("country_name");
        System.out.println("countryName = " + countryName);
        assertEquals(countryName, "United States of America");

//- And Region_id is
        String regionID = jsonData.getString("region_id");
        System.out.println("regionID = " + regionID);
        assertEquals(regionID, "2");
    }
}