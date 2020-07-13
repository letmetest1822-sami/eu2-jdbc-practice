package Day1_5_apitests;

import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;

public class HamcrestMatchersApiTest {

/*
    given accept type is Json
    And path param id is 15
    When user sends a get request to spartans/{id}
    Then status code is 200
    And content type is Json
    And json data has following
        "id": 15,
        "name": "Meta",
        "gender": "Female",
        "phone": 1938695106
     */

    @Test
    public  void OneSpartanwithHamcrest(){

        given().accept(ContentType.JSON)
                .and().pathParam("id",15)
                .and().auth().basic("admin","admin").
        when().get("http://3.81.99.109:8000/api/spartans/{id}")

                .then()

                .assertThat().statusCode(200)

                .assertThat().contentType("application/json;charset=UTF-8")

                .and()

                .assertThat().body("id", equalTo(15),
                    "name",equalTo("Meta"),
                                         "gender", equalTo("Female"),
                                          "phone", equalTo(1938695106));


    }

    @Test
    public  void teacherTest(){
        given().accept(ContentType.JSON)
                .pathParam("id", 6884)
                .when().get("http://api.cybertektraining.com/teacher/{id}")
                .then().assertThat().statusCode(200)
                .and().contentType("application/json;charset=UTF-8")
                .and().header("Content-Length", equalTo("254"))
                .and().header("Connection", equalTo("Keep-Alive"))
                .and().header("Date", notNullValue())
                .and().body("teachers.firstName[0]", equalTo("Harold"),
                        "teachers.lastName[0]", equalTo("Kim"),
                                             "teachers.gender[0]", equalTo("Male"));



    }

    @Test
    public void TeacherWithdepartments(){

        given().accept(ContentType.JSON).and()
                .pathParam("name", "Computer")
                .when().get("http://api.cybertektraining.com/teacher/department/{name}")
        .then().statusCode(200).and().contentType("application/json;charset=UTF-8")
                .and().assertThat().body("teachers.firstName", hasItems("tvrec","Marteen", "Alexander"));



    }



}
