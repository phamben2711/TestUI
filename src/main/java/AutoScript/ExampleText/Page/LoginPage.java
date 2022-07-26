package AutoScript.ExampleText.Page;

import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import AutoScript.ExampleText.Variables.LoginData;
import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginPage {
	
	static Logger log = Logger.getLogger(LoginPage.class);
	public WebDriver driver;
	private By email= (By.name("email"));
	private By password= (By.name("password"));
	private By loginBtn=(By.xpath("//*[@class='ladda-label']"));
	private By Darhboard = (By.xpath("//*[@class='container-fluid px-4']"));
	

	public LoginPage (WebDriver driver) {
         this.driver = driver;
	}

	public LoginPage openURl(String url, String typedriver, String setdriver) {
		System.setProperty(typedriver,setdriver); 
		driver = new ChromeDriver();
		driver.get(url);
		return this;
	}
	public LoginPage openUrlByWebDriverManager(String url) {
		ChromeOptions chromeOptions = new ChromeOptions();
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver(chromeOptions);
		driver.get(url);

		return this;
	}
	public LoginPage fillInEmail(String emailjson) {
		log.info("fill in Email");
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(15));
		wait.until(ExpectedConditions.visibilityOfElementLocated(email));
		driver.findElement(email).sendKeys(emailjson);
		return this;
	}
	
	public LoginPage fillInPassword(String passjson) {
		log.info("fill in Password");
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(15));
		wait.until(ExpectedConditions.visibilityOfElementLocated(password));
		driver.findElement(password).sendKeys(passjson);
		return this;
	}
	
	public LoginPage clickLogin() {
		log.info("Click On Login");
		driver.findElement(loginBtn).click();
		return this;
	}
	
	public LoginPage verifyfillInEmail(String expectEmail) {
		log.info(driver.findElement(email).getText());
		String actualEmail = driver.findElement(email).getAttribute("value");
		Assert.assertEquals(actualEmail, expectEmail);
		return this;
	}
	public LoginPage verifyfillInPassword(String expectPassword) {
		log.info(driver.findElement(password).getText());
		String actualPassword =	driver.findElement(password).getAttribute("value");
		Assert.assertEquals(actualPassword,expectPassword);
		return this;
	}
	public LoginPage verifyHomePageIsPresent() throws InterruptedException  { 
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(Darhboard));
		driver.findElement(Darhboard).isDisplayed();
		log.info("verify Darhboard Is Present");
		driver.quit();
		return this;
	}
	
	
	public LoginPage verifyLoginIsSuccessful(String expectURL)
	{
		String actualUrl = driver.getCurrentUrl().toString();
		
		
		
		return this;
	}
	public LoginPage LoginWithEmail(String email, String password) {
		 fillInEmail(email)
		.fillInPassword(password).clickLogin();
		return this;
	}

}
