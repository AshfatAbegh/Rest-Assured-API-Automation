package Day5;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import java.io.File;

import org.testng.annotations.Test;

public class FileUploadAndDownload {

    // @Test
    // void singleFileUpload() {

    //     File myfile = new File("/home/ashfatrashid/Downloads/Body.json");

    //     given()
    //         .multiPart("file", myfile) //Equivalent to form-data with post request body in postman 
    //         .contentType("multipart/form-data")

    //     .when()
    //         .post("http://localhost:8080/uploadFile")

    //     .then()
    //         .statusCode(200)
    //         .body("filename", equalTo("Body.json"));
    // }


    @Test
    void multipleFilesUpload() {

        File myfile1 = new File("/home/ashfatrashid/Downloads/Body.json");
        File myfile2 = new File("/home/ashfatrashid/Downloads/Body.json");
       
        given()
            .multiPart("files", myfile1) //Equivalent to form-data with post request body in postman 
            .multiPart("files", myfile2) 
            .contentType("multipart/form-data")

        .when()
            .post("http://localhost:8080/uploadMultipleFiles")

        .then()
            .statusCode(200)
            .body("[0].filename", equalTo("Body.json"))
            .body("[1].filename", equalTo("Body2.json"));
    }
}
