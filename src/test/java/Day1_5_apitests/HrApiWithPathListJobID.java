package Day1_5_apitests;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import utilities.ConfigurationReader;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.List;

public class HrApiWithPathListJobID {


    @BeforeClass
    public void beforeClass(){
        RestAssured.baseURI = ConfigurationReader.getProperty("hrapi_url");
    }


    @Test
    public  void  test1(){
        Response response = given().accept(ContentType.JSON)
                .and().queryParam("q", "{\"region_id\":2}")
                .when().get("/countries");
/*
        assertEquals(response.statusCode(), 200);
        assertEquals(response.contentType(), "application/json");

        assertTrue(response.asString().contains("United States of America"));

        response.prettyPrint();
*/
        List<String> JobIDs = response.path("items.job_id");

        for (String jobID : JobIDs) {
            assertEquals(jobID, "IT_PROG");
            System.out.println("jobID = " + jobID);
        }
    }

}
