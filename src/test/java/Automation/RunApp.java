package Automation;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class RunApp {
    public AndroidDriver driver;

    @BeforeTest
    public void setUp() throws MalformedURLException {

        //Setting up the path to the APK file
        File file = new File("src/test/resources");
        File fileDir = new File(file, "General-Store.apk");
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability("platformName", "android");
        cap.setCapability("automationName", "UiAutomator2");
        cap.setCapability("deviceName", "local1");
        cap.setCapability("udid", "emulator-5554");
        cap.setCapability("app", fileDir.getAbsolutePath() );
        cap.setCapability("appPackage","com.androidsample.generalstore");
        cap.setCapability("appActivity","com.androidsample.generalstore.SplashActivity");
        driver = new AndroidDriver(new URL("http://192.168.0.106:4723/"), cap);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        /*cap.setCapability("appPackage","com.androidsample.generalstore");
        cap.setCapability("appActivity","com.androidsample.generalstore.SplashActivity");
        AndroidDriver driver = new AndroidDriver(new URL("http://192.168.0.106:4723/"), cap);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));*/
    }

    @Test
    public void testApp(){
        //driver.findElement(By.id("com.androidsample.generalstore:id/spinnerCountry")).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/spinnerCountry")).click();
        driver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"android:id/text1\" and @text=\"Algeria\"]")).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Test App");
        driver.findElement(By.id("com.androidsample.generalstore:id/radioFemale")).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
        driver.findElement(By.xpath("(//android.widget.TextView[@resource-id=\"com.androidsample.generalstore:id/productAddCart\"])[1]")).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
    }
    
    @AfterTest
    public void tearDown(){
        // Removing the app from the device
        if(driver!=null){
            driver.removeApp("com.androidsample.generalstore");
            driver.quit();
        }
    }
}
