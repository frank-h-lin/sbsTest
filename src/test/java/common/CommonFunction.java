package common;

import org.openqa.selenium.*;
import pageObject.CommonPO;
import com.cucumber.listener.Reporter;

import cucumber.api.DataTable;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

public class CommonFunction {


    WebDriver driver;
    WebDriverWait wait;
    String randomValue;
    final int timeout = 120;

    public CommonFunction() {
        startDriver();
    }

    public void startDriver() {
        System.setProperty("webdriver.chrome.driver", "C:\\support\\chromedriver.exe");
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.addArguments("disable-infobars");
        options.addArguments("--disable-extensions");
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);

        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
        capabilities.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
        capabilities.setBrowserName("chrome");

        try {
            driver = new RemoteWebDriver(new URL("http://127.0.0.1:4444/wd/hub"), capabilities);
            driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
            driver.manage().window().maximize();
             wait = new WebDriverWait(this.driver, timeout);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public WebDriver getDriver() {
        return this.driver;
    }

    public WebDriverWait getWait() {return this.wait; }

    public void takeSnapshot() {
        try {
            String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
            String snapshotPath = System.getProperty("user.dir")
                    + "/output/screenshot_active_screen" + timeStamp + ".png";
            FileUtils.copyFile(((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE),
                    new File(snapshotPath));
            Reporter.addScreenCaptureFromPath(snapshotPath);

            Screenshot screenshot = new AShot()
                    .shootingStrategy(ShootingStrategies.viewportPasting(timeout))
                    .takeScreenshot(driver);
            snapshotPath = snapshotPath.replace("active", "whole");
            ImageIO.write(screenshot.getImage(), "png",
                    new File(snapshotPath));
            Reporter.addScreenCaptureFromPath(snapshotPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void waitForelementToBeClickable(By locator) {

        wait.until(ExpectedConditions.elementToBeClickable(locator));

    }
    
	public void waitForSomeTime(int timeInSeconds)
	{
		try {
//			Thread.currentThread();
			System.out.println("Waiting for:"+timeInSeconds+ " seconds");
			Thread.sleep(timeInSeconds*2000);
		} 
		catch(Exception e)
		{
			System.out.println("Exception thrown");
		}
	}

    public void waitForPageTitle(String titlePartialText) {
        wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.titleContains(titlePartialText));

    }

}
