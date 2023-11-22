package Day6;

import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;



//1st we need to create POJO Class 
     //then
//POJO -> JSON Object = Serialization
//JSON Object -> POJO = Deserialization

public class SerializationDeserialization {
        
    @Test
    void convertPojo2Json(){

        //Created java object using POJO Class
        Student stupojo = new Student();

        stupojo.setName("Rohan");
        stupojo.setLocation("Canada");
        stupojo.setPhone("123456");
        String courseArr[] = {"C","C++"};
        stupojo.setCourses(courseArr);
    }

    //Converting java object -> JSON Object(Serialization) 
    
    ObjectMapper objMapper = new ObjectMapper();
        
    
}
