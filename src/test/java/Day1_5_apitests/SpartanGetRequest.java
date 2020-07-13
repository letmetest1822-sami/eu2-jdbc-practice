package Day1_5_apitests;


import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;


public class SpartanGetRequest {

    String spartanUrl = "http://3.81.99.109:8000";

    @Test
    public void test1() {
        Response response = given().accept(ContentType.JSON)
                .and().auth().basic("admin", "admin")
                .when().get(spartanUrl + "/api/spartans");
        System.out.println("response.statusCode() = " + response.statusCode());

        response.prettyPrint();
    }

    /*
    When user sends a get request to /api/spartans/3
    Then status code should be 200
    And content type should be application/json;charset=UTF-8
    and json body should contain Fidole
     */
    @Test
    public void test2() {

        //When user sends a get request to /api/spartans/3
        Response response = given().auth().basic("admin", "admin")
                .when().get(spartanUrl + "/api/spartans/3");

        //verify the status code is 200
        assertEquals(response.statusCode(), 200);

        //And content type should be application/json;charset=UFT-8 (verify response body is JSON)
        assertEquals(response.contentType(), "application/json;charset=UTF-8");

        //And verify body should contain Fidole
        assertTrue(response.body().asString().contains("Fidole"));
    }

    /*
    Given no headers provided
    When Users sends GET request to /api/hello
    Then response status code should be 200
    And Content type header should be "text/plain;charset=UTF-8"
    And header should contain date
    And Content-Length should be 17
    And body should be "Hello from Sparta"
     */


    @Test
    public void helloTest() {

        //Given no headers provided
        //When Users sends GET request to /api/hello
        Response response = given()
                .when().get(spartanUrl + "/api/hello");

        //Then response status code should be 200
       assertEquals(response.statusCode(), 200);

       //And Content type header should be "text/plain;charset=UTF-8"
        assertEquals(response.contentType(), "text/plain;charset=UTF-8");

        //And header should contain date
        assert response.headers().hasHeaderWithName("Date");

        System.out.println("response.header(\"date\") = " + response.header("date"));
        System.out.println("response.getHeader(\"date\") = " + response.getHeader("date"));


        // And Content-Length should be 17
        assertEquals(response.getHeader("Content-Length"), "17");

        //And body should be "Hello from Sparta"
        assertTrue(response.body().asString().contains("Hello from Sparta"));



    }
}

