package testBasePack;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class YouTube {
	
		WebDriver  driver;
		
		@BeforeMethod
		@Parameters("browser")
		public void setUp(String browser){
			
			if(browser.equalsIgnoreCase("chrome")) {

	        // Optional: Add ChromeOptions for better stability
	        ChromeOptions options = new ChromeOptions();
	        options.addArguments("--start-maximized"); // Start browser maximized
	        options.addArguments("--disable-notifications"); // Disable any pop-up notifications

	        // Initialize WebDriver
	        driver = new ChromeDriver(options);
			}else {
				FirefoxOptions options = new FirefoxOptions();
				options.addArguments("--start-maximized");
				options.addArguments("--disable-notifications");
				// Initialize WebDriver
		        driver = new FirefoxDriver(options);
			}
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // Set implicit wait
		}
		
		
		@Test(invocationCount = 5, threadPoolSize = 5)
		public void playVideo() {
			System.out.println(Thread.currentThread().getId());
	        try {
	            // Open YouTube and navigate to a specific video link
	            String videoUrl = "https://youtu.be/9MkwgGMp9uQ?si=bnxaegGQGHR4_R4v";  // Replace VIDEO_ID with actual ID
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
	                Thread.sleep(100000); // Watch video for 10 seconds before closing
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	            
	        }
	    }
		
		@AfterMethod
		public void closeVideo() {
			driver.quit();
		}

}
