package AutoScript.ExampleText.Page;

import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.log4testng.Logger;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginPage extends BasePage{
	
	static Logger log = Logger.getLogger(LoginPage.class);
	private By email= (By.name("email"));
	private By password= (By.name("password"));
	private By loginBtn=(By.xpath("//*[@class='ladda-label']"));

	

	public LoginPage (WebDriver driver) {
       super(driver);
	}

	public LoginPage openURl(String url, String typedriver, String setdriver) {
		System.setProperty(typedriver,setdriver); 
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

	
	public LoginPage loginWithEmail(String email, String password) {
		 fillInEmail(email)
		.fillInPassword(password).clickLogin();
		 return this;
	}
	public LoginPage loginWithEmails(String emai, String password)
	{
		Map<String, String> account= new HashMap<String, String>();
		account.put(emai, password);
		for(Map.Entry<String, String> data:account.entrySet())
		{
			loginWithEmail(data.getKey(),data.getValue());
		}
		return this;
	}
	

}
