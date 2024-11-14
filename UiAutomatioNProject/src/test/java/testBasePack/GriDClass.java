package testBasePack;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class GriDClass {
	WebDriver driver;
	
	
	@BeforeMethod
	@Parameters("browser")
	public void config(String browser) throws MalformedURLException {
	    URL gridUrl = new URL("http://localhost:4444/wd/hub");

	    if (browser.equalsIgnoreCase("chrome")) {
	        ChromeOptions chromeOptions = new ChromeOptions();
	        chromeOptions.setPlatformName("LINUX");  
	        driver = new RemoteWebDriver(gridUrl, chromeOptions);;

	    } else if (browser.equalsIgnoreCase("firefox")) {
	        FirefoxOptions firefoxOptions = new FirefoxOptions();
	        firefoxOptions.setPlatformName("LINUX");
	        driver = new RemoteWebDriver(gridUrl, firefoxOptions);

	    } else if (browser.equalsIgnoreCase("edge")) {
	        EdgeOptions edgeOptions = new EdgeOptions();
	        edgeOptions.setPlatformName("LINUX");
	        driver = new RemoteWebDriver(gridUrl, edgeOptions);

	    } else {
	        throw new IllegalArgumentException("Unsupported browser: " + browser);
	    }

	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	    driver.manage().window().maximize();
	}
	
	@Test
	public void setUp() throws MalformedURLException{
		
		try {
            // Open YouTube and navigate to a specific video link
            String videoUrl = "https://youtu.be/8iG6Q1dpszY?si=7_j0V7U2kXBROh8N";  // Replace VIDEO_ID with actual ID
            driver.get(videoUrl);

            // Wait for video to load (if the play button is needed)
            WebElement playButton = driver.findElement(By.cssSelector("button.ytp-play-button"));
            
            // Check if the video is paused and click play if necessary
            if (playButton.getAttribute("title").contains("Play")) {
                playButton.click(); // Click the play button
                System.out.println("Video is now playing...");
            } else {
                System.out.println("Video is already playing...");
            }

        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        } finally {
            // Keep the browser open for a few seconds to watch the video, then close it
            try {
                Thread.sleep(200000); // Watch video for 10 seconds before closing
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
        }
		
		
		
	}
	
	@AfterMethod
	public void quit() {
		if(driver!=null) {
			driver.quit();
		}
	}

}
