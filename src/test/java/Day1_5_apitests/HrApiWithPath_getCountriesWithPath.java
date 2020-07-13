package Day1_5_apitests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class HrApiWithPath_getCountriesWithPath {


    @BeforeClass
    public void beforeClass(){
        RestAssured.baseURI = ConfigurationReader.getProperty("hrapi_url");
    }

    @Test
    public void getCountriesWithPath(){
        Response response = given().queryParam("q", "{\"region_id\": 2}")
                .when().get("/countries");

        assertEquals(response.statusCode(),200);

        //print count value
        System.out.println("response.path(\"count\") = " + response.path("count"));
        //print hasMore
        System.out.println(response.path("hasMore").toString());



        /*items.country_id[0]   --> AR
          items.country_name[1] -->Brazil
          items.links[2].href[0] --> http://3.81.99.109:1000/ords/hr/countries/CA
          items.country_name -->get all countries name
         */

            String firstCountryId = response.path("items.country_id[0]");
            System.out.println("firstCountryId = " + firstCountryId);

            String secondCountryName = response.path("items.country_name[1]");
            System.out.println("secondCountryName = " + secondCountryName);

            String link2 = response.path("items.links[2].href[0]");
            System.out.println("link2 = " + link2);

            //get all countries
            List<String> allCountries = response.path("items.country_name");
            System.out.println("allCountries.size() = " + allCountries.size());

            for (String country : allCountries) {
                System.out.println(country);
            }

            //TASK
            //assert that all regions id's are equal to 2

        List<Integer> region_ids = response.path("items.region_id");
        for (int region_id : region_ids) {
            System.out.println("region_id = " + region_id);
            assertEquals(region_id,2);
        }



    }
}
