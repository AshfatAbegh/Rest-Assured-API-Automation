package Day7;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Authentications{
    
    @Test(priority = 1)
    void  testBasicAuthentication(){
        
        given()
           .auth().basic("postman", "password")

        .when()
            .get("https://postman-echo.com/basic-auth")

        .then()
            .statusCode(200) // Fix the typo here
            .body("authenticated", equalTo(true))
            .log().all();
    }


    @Test(priority = 2)
    void  testDigestAuthentication(){
        
        given()
           .auth().digest("postman", "password")

        .when()
            .get("https://postman-echo.com/basic-auth")

        .then()
            .statusCode(200) // Fix the typo here
            .body("authenticated", equalTo(true))
            .log().all();
    }


    @Test(priority = 3)
    void  testPreemptiveAuthentication(){
        
        given()
           .auth().preemptive().basic("postman", "password")

        .when()
            .get("https://postman-echo.com/basic-auth")

        .then()
            .statusCode(200) // Fix the typo here
            .body("authenticated", equalTo(true))
            .log().all();
    }

    @Test(priority = 4)
    void  testBearerTokenAuthentication(){
        
        String bearerToken = "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";

        given()
        .headers("Authorization", "Bearer " + bearerToken)

        .when()
           .get("https://api.github.com/user/repos")

        .then()
           .statusCode(200)
           .log().all();
    }
}

