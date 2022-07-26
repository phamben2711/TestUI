package AutoScript.ExampleText.Test;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import AutoScript.ExampleText.Page.HomePage;
import AutoScript.ExampleText.Page.LoginPage;
import AutoScript.ExampleText.Variables.LoginData;

public class TestHomePage {

	public WebDriver driver;
	@Test
public void testHomePageWithOpenBurger()throws InterruptedException 
{
		HomePage homePage = new HomePage(driver);
		new LoginPage(driver).openURl(LoginData.url, LoginData.typedriver, LoginData.setdriver).LoginWithEmail("admin@phptravels.com","demoadmin").verifyHomePageIsPresent();
		homePage.verifyAllLinkV1InBurger().verifyLinks(LoginData.url);
		
}
}
