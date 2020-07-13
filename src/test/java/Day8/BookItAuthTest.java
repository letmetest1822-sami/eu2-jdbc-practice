package Day8;

import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class BookItAuthTest {
    String accessToken = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxOTkxIiwiYXVkIjoic3R1ZGVudC10ZWFtLWxlYWRlciJ9.PejlP7F8p8bA9BGqol3jfWESaPRP5lBkIy7q2huN-4s";

    @BeforeClass
    public void before() {
        baseURI = "https://cybertek-reservation-api-qa.herokuapp.com";
    }

    @Test
    public void getAllCampuses(){

        Response response = given().header("Authorization", accessToken)
                .when().get("/api/campuses");

        response.prettyPrint();



    }


}
