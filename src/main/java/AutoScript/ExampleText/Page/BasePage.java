package AutoScript.ExampleText.Page;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class BasePage {
	  public WebDriver driver;
	  private static ThreadLocal<WebDriver> sessionDriver = new InheritableThreadLocal<WebDriver>();
	  
	  public WebDriverWait wait;
	  public BasePage(WebDriver driver) {
		  this.driver=driver;
		  PageFactory.initElements(driver, this);
	        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    }
	  public WebDriver getDriver() {
		    
			
			return driver;
	     
	    }
		public static WebDriver getDriver(){
			if (sessionDriver.get() == null) {
				System.out.print("Driver is not instantiated yet, there is a problem some where");
				Assert.fail();
			}
			return sessionDriver.get();
		}
		public static void setDriver(WebDriver _driver) {
			sessionDriver.set(_driver);
		}
		
}
