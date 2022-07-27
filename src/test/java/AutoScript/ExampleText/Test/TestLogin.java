package AutoScript.ExampleText.Test;


import org.openqa.selenium.WebDriver;

import org.testng.annotations.Test;
import org.testng.Assert;

import AutoScript.ExampleText.Page.HomePage;
import AutoScript.ExampleText.Page.LoginPage;
import AutoScript.ExampleText.Variables.LoginData;

public class TestLogin {
	
	public WebDriver driver;
	
	@Test
public void TestLoginPage() throws InterruptedException {
	LoginPage login = new LoginPage(driver);
	login.openURl(LoginData.url, LoginData.typedriver, LoginData.setdriver).
	LoginWithEmail("admin@phptravels.com","demoadmin")
//	fillInEmail("admin@phptravels.com")
//			.fillInPassword("demoadmin").verifyfillInEmail("admin@phptravels.com").verifyfillInPassword("demoadmin")
			.clickLogin();
	new HomePage(driver).verifyHomePageIsPresent();
}
	
}