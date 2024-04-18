package com.agave.tests.Auth;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

import java.sql.SQLException;
import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.agave.tests.Utilities.DB;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class EmailRegistration {

    private String authorizationCode;
    
    private String getApiToken() {
        RestAssured.baseURI = "http://agave-api-test:8080/api/";
        
        Response response = given()
                .queryParam("app_id", "agave")
                .when()
                  .get(baseURI + "v1/auth/otp/api-token")
                .then()
                  .contentType("application/json")
                  .extract()
                  .response();

        return response.jsonPath().getString("api_token");
    }

    private void shootOtp(String apiToken) {
        HashMap<String, String> hm = new HashMap<>();
        hm.put("app_id", "agave");
        hm.put("api_token", apiToken);
        hm.put("email","araf@gmail.com");

        RestAssured.baseURI = "http://agave-otp-test:8080/api/";
        
        given()
                .contentType(ContentType.JSON)
                .body(hm)
                .when()
                .post(baseURI + "shoot-otp/email");
    }

    private String validateOtp(String apiToken) {
        RestAssured.baseURI = "http://agave-otp-test:8080/api/";

        HashMap<String, String> requestBody = new HashMap<>();
        requestBody.put("app_id", "agave");
        requestBody.put("api_token", apiToken);
        requestBody.put("email", "araf@gmail.com");
        requestBody.put("otp", "1111");

        return given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post(baseURI + "validate-otp/email")
                .path("authorization_code");
    }

     @BeforeMethod
     public void authorizationCodesetup() {
        String apiToken = getApiToken();
        shootOtp(apiToken);
        authorizationCode = validateOtp(apiToken);;
    }

    @Test
    public void userShouldNotBeRegisteredWithAlreadyExistingEmail() throws SQLException {
        DB.getConnection().executeUpdate("TRUNCATE TABLE `profiles`");
        DB.getConnection().executeUpdate("INSERT INTO `profiles` (`id`, `email`, `isEmailVerified`, `isPhoneVerified`) VALUES (1, 'araf@gmail.com', b'0', b'0')");

        // ResultSet resultSet = DB.getConnection().executeQuery("SELECT * FROM `profiles`");
        // resultSet.next();
        // System.out.println(resultSet.getString("id"));
        // System.out.println(resultSet.getString("email"));

        RestAssured.baseURI = "http://agave-api-test:8080/api/";

        HashMap<String, String> requestBody = new HashMap<>();
        requestBody.put("email", "araf@gmail.com");
        requestBody.put("password", "Araf9878");
        requestBody.put("authorization_code", authorizationCode); 

        Response response = given()
                 .contentType(ContentType.JSON)
                 .body(requestBody)
                 .header("Accept-Language", "jp")
                 .when()
                 .post(baseURI + "v1/auth/register-auth-email");

        System.out.println(response.asString());

        Assert.assertEquals(response.getStatusCode(), 401);

        String message = response.getBody().jsonPath().getString("message");
        Assert.assertEquals(message, "このメールは既に存在します");
    }

    public String getAuthorizationCode(){
        return authorizationCode;
    }
}
