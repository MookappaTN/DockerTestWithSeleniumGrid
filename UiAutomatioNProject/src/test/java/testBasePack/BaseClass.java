package testBasePack;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import utils.ConfigReader;


public class BaseClass {

    private WebDriver driver;
    
    private static  Logger logs = LogManager.getLogger(BaseClass.class);


    @BeforeMethod
    @Parameters("browser")
    public void getBrowser(String browserName) {
    	
    	if(browserName.equalsIgnoreCase("chrome")) {
        // Initialize the WebDriver
        driver = new ChromeDriver();
        
    	}
    	
    	else {
    		driver = new FirefoxDriver();
    	}
        driver.get(ConfigReader.getProps("url"));
   
        
        // Maximize window and set timeouts
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }

    @Test
    public void getRegisterPage() {
        // Click the Register link
        driver.findElement(By.xpath("(//*[contains(text(),'Register')])[last()]")).click();


        // Wait for Register/Sign in element to be visible
        By locator = By.xpath("//*[contains(text(),'Register/Sign in')]");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

        // Log the text of the located element
        String elementText = driver.findElement(locator).getText();
   
    }
    
    @Test(dataProvider = "emailIDs")
    public void verifyDisablityOfContinueButton(String id) {
    	 driver.findElement(By.xpath("(//*[contains(text(),'Register')])[last()]")).click();
    	driver.findElement(By.xpath("//*[@label='Email']")).clear();
    	driver.findElement(By.xpath("//*[@label='Email']")).sendKeys(id);
    	boolean checkIsEnabled = driver.findElement(By.xpath("//*[text()='Continue']")).isEnabled();
    	if(!id.contains("@gmail.com") && checkIsEnabled==true) {
    		
    		logs.error("test fails continue is enabled for wrong data");
    	}
    	else {
    		logs.error("testing passed");
    	}
    	
    	
    	
    }
    
    @DataProvider(name="emailIDs")
    public static Object[][] emailid() {
    	
    	return new Object[][] {
    		{"mookappat@gmail.com"}
    		,{"mookappa"}};
    	
    }
    
    @AfterMethod
    public void quit() {
        if (driver != null) {
            driver.quit();
  
        }
    }
}
