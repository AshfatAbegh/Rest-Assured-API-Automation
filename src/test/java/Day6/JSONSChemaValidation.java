package Day6;

import static io.restassured.RestAssured.when;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

public class JSONSChemaValidation {

    @Test
    void JSONschemaValidation(){
       
        .given()

        .when()
           .get("https://jsonplaceholder.typicode.com/users")

        .then()
            .assertThat().body(JSONSChemaValidator.MatchersJSONSchemaInCLasspath("src/test/resources/JSONSchema/JSONSchema.json"));
    }
}
