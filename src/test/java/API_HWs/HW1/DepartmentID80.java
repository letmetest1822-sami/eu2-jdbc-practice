package API_HWs.HW1;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.assertEquals;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.List;

public class DepartmentID80 {

    @BeforeClass
    public void beforeClass() {
        baseURI = ConfigurationReader.getProperty("hrapi_url");
    }

 /*
Q2:
- Given accept type is Json
- Query param value - q={"department_id":80}
- When users sends request to /employees
- Then status code is 200
- And Content - Type is Json
- And all job_ids start with 'SA'
- And all department_ids are 80
- Count is 25
*/

    @Test
    public void DepartmentID() {

//- Given accept type is Json
        Response response = given().accept(ContentType.JSON)

//- Query param value - q={"department_id":80}
                .and().queryParam("q", "{\"department_id\":80}")

//- When users sends request to /employees
                .when().get("/employees");

//- Then status code is 200
        assertEquals(response.statusCode(), 200);

//- And Content - Type is Json
        assertEquals(response.contentType(), "application/json");

//- And all job_ids start with 'SA'

        //response.prettyPrint();
        List<String> JobIDs = response.path("items.job_id");

        for (String jobID : JobIDs) {
            String startsWithSA = jobID.substring(0,2);
            assertEquals(startsWithSA, "SA");
           // System.out.println("str2 = " + startsWithSA);
        }
    }
}
