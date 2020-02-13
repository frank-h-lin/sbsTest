package ajax;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.openqa.selenium.WebDriver;
import org.junit.Assert;
import java.util.List;

public class API {
    int statusCode;

    public API(){
        super();
    }

    public void getArchiveAudioMp3Files(){
        //Request
        RestAssured.baseURI="https://www.sbs.com.au/guide/ajax_radio_program_catchup_data/language/hindi/location/NSW/sublocation/Sydney";

        RestAssured.requestSpecification = new RequestSpecBuilder().setRelaxedHTTPSValidation().build();

        //Response
        Response response = RestAssured.given().log().uri().get();
        int statusCode = response.getStatusCode();
        //print the status code
        System.out.println("status code: " + statusCode);

       //Assert the status code to 200
        Assert.assertEquals(200,statusCode);

        JsonPath jsonPath = new JsonPath(response.asString());  
        
      //get mp3 list
       List<String> mp3list =  jsonPath.getList("archiveAudio.mp3");
       for(int i=0;i<mp3list.size();i++){
           System.out.println("list of archiveAudio.mp3 : " + mp3list.get(i));
       }

       //get m4a list
       List<String> m4alist =  jsonPath.getList("archiveAudio.m4a");    
       for(int i=0;i<m4alist.size();i++){
           System.out.println("list of archiveAudio.m4a : " + m4alist.get(i));
       }
       
       int total = mp3list.size() + m4alist.size();

       //Assert the status code to 200
       Assert.assertEquals(24,total);
    }

}
