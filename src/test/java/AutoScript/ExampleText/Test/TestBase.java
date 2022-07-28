package AutoScript.ExampleText.Test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import AutoScript.ExampleText.Page.HomePage;
import AutoScript.ExampleText.Page.LoginPage;
import utils.logs.Log;

public class TestBase {
	public WebDriver driver;
    public HomePage  homePage;
    public LoginPage loginPage;
    public WebDriver getDriver() {
        return driver;
    }
    @BeforeClass
    public void classLevelSetup() {
        Log.info("Tests is starting!");
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