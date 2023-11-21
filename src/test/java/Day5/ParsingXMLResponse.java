package Day5;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class ParsingXMLResponse {
    
    @Test
    void testXMLResponse(){
       
        //  {
        // //Approach 1

        // given()
        //    .pathParam("id",11133)

        // .when()
        //    .get("http://restapi.adequateshop.com/api/Traveler/{id}")
        
        // .then()
        //     .statusCode(200)
        //     .header("content-type","application/xml; charset=utf-8")
        //     .body("Travelerinformation.id", equalTo("11133"))
        //     .body("Travelerinformation.email", equalTo("Developer12@gmail.com"))
        //     .log().all();
        // }

    //Approach 2 -> Returning the response into variable
     
    Response res = given()
           .pathParam("id",11133)

     .when()
           .get("http://restapi.adequateshop.com/api/Traveler/{id}");
    
    Assert.assertEquals(res.getStatusCode(), 200);
    Assert.assertEquals(res.getHeader("content-type"), "application/xml; charset=utf-8");
    Assert.assertEquals(res.xmlPath().get("Travelerinformation.id").toString(),"11133");
    Assert.assertEquals(res.xmlPath().get("Travelerinformation.email"),"Developer12@gmail.com");
  }
}

