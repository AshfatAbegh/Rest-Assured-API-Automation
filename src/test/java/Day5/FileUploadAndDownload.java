package Day5;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import java.io.File;

import org.testng.annotations.Test;

public class FileUploadAndDownload {

    @Test
    void singleFileUpload() {

        File myfile = new File("/home/ashfatrashid/Downloads/Body.json");

        given()
            .multiPart("file", myfile) //Equivalent to form-data with post request body in postman 
            .contentType("multipart/form-data")

        .when()
            .post("http://localhost:8080/uploadFile")

        .then()
            .statusCode(200)
            .body("filename", equalTo("Body.json"));
    }
}
