package API_HWs.HW1;


import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;

public class DepartmentID80_Hamcrest {


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

        given().accept(ContentType.JSON)
                .queryParam("q", "{\"department_id\":80}")
                .when().get("http://3.81.99.109:1000/ords/hr/employees")
                .then()
                .statusCode(200)
                .assertThat().contentType("application/json")
                .body("items.job_id",hasItems(startsWith("SA")),
                        "items.department_id", hasItems(equalTo(80)),
                        "count", equalTo(25));

    }
}
