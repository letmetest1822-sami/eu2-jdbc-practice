package API_HWs.HW2;

import static io.restassured.RestAssured.*;

import org.testng.annotations.BeforeClass;
import utilities.ConfigurationReader;
import org.testng.annotations.Test;

import static org.testng.Assert.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class LotharioMale755155 {

    @BeforeClass
    public void beforeClass() {
        baseURI = ConfigurationReader.getProperty("spartanapi_url");
    }

    //Q1:
    //Given accept type is json
    //And path param id is 20
    //When user sends a get request to "/spartans/{id}"
    //Then status code is 200
    //And content-type is "application/json;charset=UTF-8"
    //And response header contains Date
    //And Transfer-Encoding is chunked
    //And response payload values match the following:
    //id is 20,
    //name is "Lothario",
    //gender is "Male",
    //phone is 7551551687




    @Test
    public void NameGenderPhone() {

        Response response = given().accept(ContentType.JSON)
                .and().auth().basic("admin", "admin")
                .and().pathParam("id", 20)
                .when().get("/api/spartans/{id}");


        assertEquals(response.statusCode(), 200);
        //Given accept type is json
        assertEquals(response.contentType(),"application/json;charset=UTF-8");

        //And path param id is 20
        assertEquals(response.path("id").toString(),"20");

        //And response header contains Date


    }



    //Q2:
    //Given accept type is json
    //And query param gender = Female
    //And queary param nameContains = r
    //When user sends a get request to "/spartans/search"
    //Then status code is 200
    //And content-type is "application/json;char"
    //And all genders are Female
    //And all names contains r
    //And size is 20
    //And totalPages is 1
    //And sorted is false



    /*
    API_formulas
Extract Multiple Maps of Objects
List<Map<String,?>> data = response.path(",,,");
 Extract Map Of Elements
 Map<String,?> data = response.path(",,, .find { it. ,,, == ',,,' }");
 Extract Single Value With Find
  String certain = response.path(",,, .find { it. ,,, == ... }. ,,,");
  Extract Single Value With Highest Value
  String highestNumber = response.path(",,,  .max { it. ,,, }. ,,,");
  Extract Single Value With Lowest Value
  String lowestNumber = response.path(",,, .min { it. ,,,  }. ,,,");
  Extract Multiple Values With Collect And Sum
  int sum = response.path(",,, .collect { it. ,,, }.sum() ");
  Extract Map Of Object With Find All
  Map<String,?> data = response.path(",,, .findAll { it. ,,, == \",,,\" }.find { it. ,,, == \" ,,, \" }");
  Extract Map Of Object With FindAll And Find Using Parameters
  String str = ",,,";
  String trs = ",,,";
   Map<String,?> data = response.path(",,, .findAll { it. ,,, == '%s' }.find { it. ,,, == '%s' }", str , trs);
 Extract List Of Map Of Elements With Multiple FindAlls
 String str = ",,,";
 String trs = ",,,";
 ArrayList<Map<String,?>> data = response.path(",,, .findAll { it. ,,, == '%s' }.findAll { it. ,,, == '%s' }",str, trs);
     */
}
