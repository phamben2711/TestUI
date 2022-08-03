package AutoScript.ExampleText.Test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import AutoScript.ExampleText.Page.BasePage;
import AutoScript.ExampleText.Page.HomePage;
import AutoScript.ExampleText.Page.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import utils.logs.Log;

public class TestBase   {


	public static ThreadLocal<WebDriver> sessionDriver  = new InheritableThreadLocal<WebDriver>();
    public HomePage  homePage;
    public LoginPage loginPage;
  
    public static void setDriver(WebDriver driver) {
		sessionDriver.set(driver);
    }
    @BeforeClass
    public void classLevelSetup() {
        Log.info("Tests is starting!");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }
    @BeforeMethod
    public void methodLevelSetup() {
    	loginPage = new LoginPage(driver);
    	homePage = new HomePage(driver);
    }
    @AfterClass
    public void teardown() {
        Log.info("Tests are ending!");
        driver.quit();
    }
}
