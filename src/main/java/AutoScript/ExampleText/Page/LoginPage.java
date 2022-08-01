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
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.log4testng.Logger;

import AutoScript.ExampleText.Page.common.CommonAction;
import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginPage extends BasePage{
	public LoginPage loginPage;
	static Logger log = Logger.getLogger(LoginPage.class);
//	private By email= (By.name("email"));
//	private By password= (By.name("password"));
//	private By loginBtn=(By.xpath("//*[@class='ladda-label']"));
	@FindBy(how = How.NAME, using = "email")
	private WebElement input_email;
	@FindBy(how = How.NAME, using = "password")
	private WebElement input_password;
	@FindBy(how = How.XPATH, using = "//*[@class='ladda-label']")
	private WebElement loginBtn;


	

	public LoginPage (WebDriver driver) {
       super(driver);
	}

	public LoginPage openURl(String url, String typedriver, String setdriver) {
		System.setProperty(typedriver,setdriver); 
		driver.get(url);
		return this;
	}
	public LoginPage openUrlByWebDriverManager(String url) {
		driver.get(url);
		return this;
	}
	public LoginPage fillInEmail(String email) {
		log.info("fill in Email");
		CommonAction.waitForElement(15, input_email);
		driver.findElement((By) input_email).sendKeys(email);
		return this;
	}
	
	public LoginPage fillInPassword(String passjson) {
		log.info("fill in Password");
		CommonAction.waitForElement(15, input_password);
		driver.findElement((By) input_password).sendKeys(passjson);
		return this;
	}
	
	public LoginPage clickLogin() {
		log.info("Click On Login");
		driver.findElement((By) loginBtn).click();
		return this;
	}
	
	public LoginPage verifyfillInEmail(String expectEmail) {
		log.info(driver.findElement((By) input_email).getText());
		String actualEmail = driver.findElement((By) input_email).getAttribute("value");
		Assert.assertEquals(actualEmail, expectEmail);
		return this;
	}
	public LoginPage verifyfillInPassword(String expectPassword) {
		log.info(driver.findElement((By) input_password).getText());
		String actualPassword =	driver.findElement((By) input_password).getAttribute("value");
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
