package stepDefinition;


import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import common.CommonFunction;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import pageObject.CommonPO;
import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import ajax.API;

public class CommonSF {
    WebDriver driver;
    WebDriverWait wait;
    CommonFunction commonFunction;
    String referenceNumber;
    String host;
    API api;

    public CommonSF(CommonFunction commonFunction) throws Throwable {
        setHost();
        this.commonFunction = commonFunction;
        driver = commonFunction.getDriver();
        wait = commonFunction.getWait();
        api = new API();

    }

    void setHost(){
        try {
            FileReader f = new FileReader("C:\\Support\\web_auto\\src\\test\\resources\\env.json");
            JsonObject jsonObject = new JsonParser().parse(f).getAsJsonObject();
            String env = jsonObject.get("env").getAsString();
            this.host = jsonObject.getAsJsonObject("url").get(env).getAsString();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }
    
   
    @Given("^I open the (url|page) \"([^\"]*)?\"$")
    public void that_I_open_the_page(String url){
        driver.get(host +url);
    }
    
    @Then("^I click to play video$")
    public void i_click_play() throws Throwable {
    	commonFunction.waitForelementToBeClickable(CommonPO.playIconLocator);
        driver.findElement(CommonPO.playIconLocator).click();
    }
    
    @Then("^I pause the video$")
    public void i_click_pause() throws Throwable {
    	commonFunction.waitForelementToBeClickable(CommonPO.pauseIconLocator);
    	//Pause
    	commonFunction.waitForSomeTime(20);
        driver.findElement(CommonPO.pauseIconLocator).click();
    }
    
    
    @Then("^I repeat the play and stop after 2 mins$")
	/* 
	 * Play for 2 mins then stop
	 */		
    public void i_click_play_and_pause() throws Throwable {    	
    	commonFunction.waitForSomeTime(10);
    	driver.findElement(CommonPO.pauseIconLocator).click();
    	commonFunction.waitForSomeTime(120);
        driver.findElement(CommonPO.playIconLocator).click();
    }
    
    @Then("^I can verify the api repsonse$")
    public void i_verify_response() throws Throwable {
    	api.getArchiveAudioMp3Files();
    }
    

//    @Then("^I see the \"([^\\\"]*)?\\\" page is loaded successfully$")
//    public void i_can_see_the_page_is_loaded_successfully(String partialTitle) throws Throwable {
//        commonFunction.waitForPageTitle(partialTitle);
//    }
    
    

    @After
    public void afterScenario(Scenario scenario) throws Throwable {
        if (driver != null) {
            commonFunction.takeSnapshot();
            driver.quit();
        }
    }

}
