package Day1_5_apitests;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class HrApiWithJsonPathTestSalary {

    @BeforeClass
    public void beforeClass() {
        RestAssured.baseURI = ConfigurationReader.getProperty("hrapi_url");
    }


    @Test
    public void testSalary() {
        Response response = given().queryParam("limit", 107)
                .get("/employees");
        assertEquals(response.statusCode(),200);
        JsonPath json = response.jsonPath();
/*
        List<String> names = json.getList("items.findAll {it.salary>=10000}.first_name");
        System.out.println(names);

        List<String>emails = json.getList("items.findAll {it.job_id=='IT_PROG'}.email");
        System.out.println(emails);

*/
        String nameMaxSalary = json.get("items.max {it.salary}.first_name");
        System.out.println(nameMaxSalary);

        String nameMinSalary = json.get("items.min {it.salary}.first_name");
        System.out.println(nameMinSalary);

        
    }

}