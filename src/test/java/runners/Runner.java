package runners;

import com.cucumber.listener.Reporter;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.AfterClass;
import org.junit.runner.RunWith;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = { "com.cucumber.listener.ExtentCucumberFormatter:output/report.html"},
        tags = {"@SBS"},
        features = {"src/test/feature"},
        glue = {"pageObject","common","stepDefinition"}
)

public class Runner {

    @AfterClass
    public static void setup() {

        Reporter.loadXMLConfig(new File("src/test/resources/extent-config.xml"));
        Reporter.setSystemInfo("user", System.getProperty("user.name"));
        Reporter.setSystemInfo("os", "Windows");
        Reporter.setTestRunnerOutput("Sample test runner output message");

        File f = new File(System.getProperty("user.dir") + "/output/report.html");
        if (f.exists() && !f.isDirectory()) {
            System.out.println("check report.html existing:" + f.getAbsolutePath());
            Long lastModified = f.lastModified();
            Date date = new Date(lastModified);
            SimpleDateFormat format = new SimpleDateFormat("dd-mm-yyyy hh:mm:ss");
            System.out.println("last modify:" + format.format(date));

        } else {

        }
    }




}