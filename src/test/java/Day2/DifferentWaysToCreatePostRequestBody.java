package Day2;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

public class DifferentWaysToCreatePostRequestBody {
     
    @Test(priority = 1)
    void testPostUsingHashMap(){

       HashMap data = new HashMap();

       data.put("name","Nadim");
       data.put("location","France");
       data.put("phone","0123456");

       String courseArr[] =  {"C","C++"};
       data.put("course","courseArr");

       given()
           .contentType("application/json")
           .body(data)

        .when()
            .post("https://localhost:3000/students")
        
        .then()
             .statusCode(201)
             .body("name",equalTo("Nadim"))
             .body("location",equalTo("France"))
             .body("phone",equalTo("0123456"))
             .body("courses[0]",equalTo("C"))
             .body("courses[1]",equalTo("C++"))

             .header("Content-Type", "application/json; charset=utf-8")
    
             .log().all();
    }   
    

    @Test(priority = 2)
    void testDelete(){
        
        given()

          .when()
            .delete("https://localhost:3000/students/4")

            .then()
                 .statusCode(201);
    }
}
