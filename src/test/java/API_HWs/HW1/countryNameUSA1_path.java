package API_HWs.HW1;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class countryNameUSA1_path {

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
       // .pathParam("country_id", "US")
            .queryParam("q", "{\"country_id\":\"US\"}")

//- When users sends request to /countries
        .when().get("/countries");

// - Then status code is 200
        assertEquals(response.statusCode(), 200);

//- And Content - Type is Json
        assertEquals(response.contentType(),"application/json");

//- And country_id is US
        String country = response.body().path("items.country_id[0]");
        System.out.println("country = " + country);
        assertEquals(country,"US");

//- And Country_name is United States of America
        String country_name = response.path("items.country_name[0]");
        System.out.println("country_name = " + country_name);
        assertEquals(country_name, "United States of America");

//- And Region_id is
        int regionID = response.path("items.region_id[0]");
        System.out.println("regionID = " + regionID);
        assertEquals(regionID, 2);

        response.body().prettyPrint();
    }
}