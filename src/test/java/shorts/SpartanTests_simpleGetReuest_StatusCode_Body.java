package shorts;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class SpartanTests_simpleGetReuest_StatusCode_Body {


    String spartanBaseUrl = "http://3.81.99.109:8000";
    String hrurl ="http://3.81.99.109:1000/ords/hr/regions";
    
    @Test
    public void viewSpartanTest1(){
        Response response = RestAssured.get(spartanBaseUrl + "/api/spartans");
        //Response response1 = RestAssured.get(hrurl);

        //print the status code
        System.out.println("response.statusCode() = " + response.statusCode());

        //print body
        System.out.println("response.body().asString() = " + response.body().prettyPrint());


    }

}
