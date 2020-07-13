package API_HWs.HW3_HarryPotter;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.hasItems;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class DigerGrup {
    String pagekey = "$2a$10$1aEwwK7/5SoSNyHEkOCfY.jZ/nNK/ohfoqH1Bkl95inAHJMOiTava";

    /*
    Verify sorting hat
1. Send a get request to /sortingHat. Request includes :
2. Verify status code 200, content type application/json; charset=utf-8
3. Verify that response body contains one of the following houses:
"Gryffindor", "Ravenclaw", "Slytherin", "Hufflepuff"
     */

    @Test
    public void verifySortingHat(){
        Response response = given().contentType(ContentType.JSON)
                .and().auth().basic("letmetest1822@gmail.com", "1!HarryPotter1!")
                .when().get("https://www.potterapi.com/v1/sortingHat");

        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json; charset=utf-8");


        List<String> houses = Arrays.asList("\"Gryffindor\"", "\"Ravenclaw\"", "\"Slytherin\"", "\"Hufflepuff\"");
        response.prettyPrint();

       assertTrue(houses.contains(response.body().asString()));

    }

    /**
     * Verify no key
     * 1. Send a get request to /characters. Request includes :
     * • Header Accept with value application/json
     * 2. Verify status code 409, content type application/json; charset=utf-8
     * 3. Verify response status line include message Conflict
     * 4. Verify that response body says "error": "Must pass API key for request"
     * Verify
     */

    @Test
    public void noKey() {
        given().accept(ContentType.JSON)
                .when().get("https://www.potterapi.com/v1/characters")
                .then().assertThat().statusCode(409)
                .contentType("application/json; charset=utf-8")
                //.statusLine()
                .body("error", equalTo("Must pass API key for request"));

    }


    /**
     * Verify number of characters
     * 1. Send a get request to /characters. Request includes :
     * • Header Accept with value application/json
     * • Query param key with value {{apiKey}}
     * 2. Verify status code 200, content type application/json; charset=utf-8
     * 3. Verify response contains 194 characters
     */

    @Test
    public void numberOfChars() {
        given().accept(ContentType.JSON)
                .and().queryParam("key", "$2a$10$1aEwwK7/5SoSNyHEkOCfY.jZ/nNK/ohfoqH1Bkl95inAHJMOiTava")
                .when().get("https://www.potterapi.com/v1/characters")
                .then()
                .assertThat().statusCode(200)
                .contentType("application/json; charset=utf-8")
                .body(hasSize(195));

    }


    /**
     * Verify number of character id and house
     * 1. Send a get request to /characters. Request includes :
     * • Header Accept with value application/json
     * • Query param key with value {{apiKey}}
     * 2. Verify status code 200, content type application/json; charset=utf-8
     * 3. Verify all characters in the response have id field which is not empty
     * 4. Verify that value type of the field dumbledoresArmy is a boolean in all characters in the response
     * 5. Verify value of the house in all characters in the response is one of the following:
     * "Gryffindor", "Ravenclaw", "Slytherin", "Hufflepuff"
     */

    @Test
    public void numberOfCharIDandHouse() {
        given().accept(ContentType.JSON)
                .and().queryParam("key", "$2a$10$1aEwwK7/5SoSNyHEkOCfY.jZ/nNK/ohfoqH1Bkl95inAHJMOiTava")
                .when().get("https://www.potterapi.com/v1/characters")
                .then()
                .assertThat().statusCode(200)
                .contentType("application/json; charset=utf-8")
                .and().body("_id", hasItem(notNullValue()))
                //solda verilen yığın içerisinde boş olan var mı? Tek param item
                .and().body("dumbledoresArmy", hasItems(true, false))
                //solda verilen yığın içerisinde bu ikisi var mı? Çok param items
                .and().body("house", hasItems("Gryffindor","Ravenclaw","Slytherin", "Hufflepuff"));



        //POJO ile yapmak için ....
    }


    /**
     * Verify all character information
     * 1. Send a get request to /characters. Request includes :
     * • Header Accept with value application/json
     * • Query param key with value {{apiKey}}
     * 2. Verify status code 200, content type application/json; charset=utf-8
     * 3. Select name of any random character
     * 4. Send a get request to /characters. Request includes :
     * • Header Accept with value application/json
     * • Query param key with value {{apiKey}}
     * • Query param name with value from step 3
     * 5. Verify that response contains the same character information from step 3. Compare all fields.
     */

    @Test
    public void allCharInfo() {
        given().accept(ContentType.JSON)
                .and().queryParam("key", "$2a$10$1aEwwK7/5SoSNyHEkOCfY.jZ/nNK/ohfoqH1Bkl95inAHJMOiTava")
                .when().get("https://www.potterapi.com/v1/characters")
                .then()
                .assertThat().statusCode(200)
                .contentType("application/json; charset=utf-8");




    }


/**
 * Verify name search
 * 1. Send a get request to /characters. Request includes :
 * • Header Accept with value application/json
 * • Query param key with value {{apiKey}}
 * • Query param name with value Harry Potter
 * 2. Verify status code 200, content type application/json; charset=utf-8
 * 3. Verify name Harry Potter
 * 4. Send a get request to /characters. Request includes :
 * • Header Accept with value application/json
 * • Query param key with value {{apiKey}}
 * • Query param name with value Marry Potter
 * 5. Verify status code 200, content type application/json; charset=utf-8
 * 6. Verify response body is empty
 */

@Test
public void nameSearch() {
    given().accept(ContentType.JSON)
            .and().queryParam("key", "$2a$10$1aEwwK7/5SoSNyHEkOCfY.jZ/nNK/ohfoqH1Bkl95inAHJMOiTava")
            .when().get("https://www.potterapi.com/v1/characters")
            .then()
            .assertThat().statusCode(200)
            .contentType("application/json; charset=utf-8");




}

/**
 * Verify house members
 * 1. Send a get request to /houses. Request includes :
 * • Header Accept with value application/json
 * • Query param key with value {{apiKey}}
 * 2. Verify status code 200, content type application/json; charset=utf-8
 * 3. Capture the id of the Gryffindor house
 * 4. Capture the ids of the all members of the Gryffindor house
 * 5. Send a get request to /houses/:id. Request includes :
 * • Header Accept with value application/json
 * • Query param key with value {{apiKey}}
 * • Path param id with value from step 3
 * 6. Verify that response contains the same member ids as the step 4
 */

@Test
public void houseMembers() {
    given().accept(ContentType.JSON)
            .and().queryParam("key", "$2a$10$1aEwwK7/5SoSNyHEkOCfY.jZ/nNK/ohfoqH1Bkl95inAHJMOiTava")
            .when().get("https://www.potterapi.com/v1/characters")
            .then()
            .assertThat().statusCode(200)
            .contentType("application/json; charset=utf-8");




}

/**
 * Verify house members again
 * 1. Send a get request to /houses/:id. Request includes :
 * • Header Accept with value application/json
 * • Query param key with value {{apiKey}}
 * • Path param id with value 5a05e2b252f721a3cf2ea33f
 * 2. Capture the ids of all members
 * 3. Send a get request to /characters. Request includes :
 * • Header Accept with value application/json
 * • Query param key with value {{apiKey}}
 * • Query param house with value Gryffindor
 * 4. Verify that response contains the same member ids from step 2
 */

@Test
public void houseMembersAgain() {
    given().accept(ContentType.JSON)
            .and().queryParam("key", "$2a$10$1aEwwK7/5SoSNyHEkOCfY.jZ/nNK/ohfoqH1Bkl95inAHJMOiTava")
            .when().get("https://www.potterapi.com/v1/characters")
            .then()
            .assertThat().statusCode(200)
            .contentType("application/json; charset=utf-8");




}

/**
 * Verify house with most members
 * 1. Send a get request to /houses. Request includes :
 * • Header Accept with value application/json
 * • Query param key with value {{apiKey}}
 * 2. Verify status code 200, content type application/json; charset=utf-8
 * 3. Verify that Gryffindor house has the most members
 */

@Test
public void houseWithMostMembers() {
    given().accept(ContentType.JSON)
            .and().queryParam("key", "$2a$10$1aEwwK7/5SoSNyHEkOCfY.jZ/nNK/ohfoqH1Bkl95inAHJMOiTava")
            .when().get("https://www.potterapi.com/v1/characters")
            .then()
            .assertThat().statusCode(200)
            .contentType("application/json; charset=utf-8");




}

}
