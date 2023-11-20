package Day4;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class ParsingJsonResponseData {
    
    @Test
    void testJsonResponse(){
         
        // Approach 1 -> Direct Validation on then() Reponse

        // given()
        //     .contentType("ContentType.JSON")
        // .when()
        //     .get("https://jsonplaceholder.typicode.com/posts")
        // .then()
        //     .statusCode(200)
        //     .header("Content-Type","application/json; charset=utf-8")
        //     .body("[4].title",equalTo("nesciunt quas odio"));
        

        // Approach 2 -> If we want to perform validation on a seperate response variable and then validate the response

        Response res = given()
           .contentType("application/json; charset=utf-8")

        .when()
           .get("https://jsonplaceholder.typicode.com/posts");
        
        Assert.assertEquals(res.getStatusCode(), 200); // Validation 1 ->Assert.assertEquals is a TestNG Annotation 
        Assert.assertEquals(res.header("Content-Type"),"application/json; charset=utf-8"); //Validation 2
        
        String title = res.jsonPath().get("[4].title");
        Assert.assertEquals(title, "nesciunt quas odio");
    
    
        //JSON Object Class
        //Search for title of the user in json -> Validation

        boolean status = false;

         JSONObject jo = new JSONObject(res.toString()); //converting response to json object type
        
         for(int i=0; i<jo.length(); i++){
             String newTitle = jo.getJSONArray(title).getJSONObject(i).get("title").toString();
             System.out.println(newTitle);

             if(newTitle.equals("nesciunt quas odio")){
                status = true;
                break;
             }
         }

         Assert.assertEquals(status, true);
   }
}

