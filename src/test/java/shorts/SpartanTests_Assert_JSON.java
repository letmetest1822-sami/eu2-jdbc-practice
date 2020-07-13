package shorts;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.basic;

public class SpartanTests_Assert_JSON {


    String spartanBaseUrl = "http://3.81.99.109:8000";
    /*
        Given accept type is Json
        When user send GET requset to /api/spartans end point
        Then status code must be 200
        And response body should Json Format
    */
    @Test
    public void assertJSON_SpartanTest3(){

        RestAssured.authentication = basic("admin","admin");

        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get(spartanBaseUrl + "/api/spartans");

        //verify the status code is 200
        Assert.assertEquals(response.statusCode(),200);

        System.out.println("response.statusCode() = " + response.statusCode());

        //verify response body is JSON
       Assert.assertEquals(response.contentType(), "application/json;charset=UTF-8");
    }
}
