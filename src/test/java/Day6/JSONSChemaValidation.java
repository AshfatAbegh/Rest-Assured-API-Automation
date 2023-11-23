package Day6;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class JSONSChemaValidation {

    @Test
    void JSONschemaValidation() {

        given()

        .when()
           .get("https://jsonplaceholder.typicode.com/users")

        .then()
            .assertThat().body(matchesJsonSchemaInClasspath("src/test/resources/JSONSchema/JSONSchema.json"));
    }
}

