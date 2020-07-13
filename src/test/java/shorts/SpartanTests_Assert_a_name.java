package shorts;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SpartanTests_Assert_a_name {


    String spartanBaseUrl = "http://3.81.99.109:8000";
    /*
        When user send GET requset to /api/spartans end point
        Then status code must be 200
        And body should contain ChallengeState
    */
    @Test
    public void assertSpartanTest2(){
        Response response = RestAssured.get(spartanBaseUrl + "/api/spartans");

        //verify the status code is 200
        Assert.assertEquals(response.statusCode(), 200);
        System.out.println("response.statusCode() = " + response.statusCode());

        //verify body contains Allen
        //System.out.println("response.body().asString() = " + response.body().asString());

        Assert.assertTrue(response.body().asString().contains("Allen"));

    }

}
