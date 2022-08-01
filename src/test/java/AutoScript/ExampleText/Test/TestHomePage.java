package AutoScript.ExampleText.Test;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import AutoScript.ExampleText.Page.HomePage;
import AutoScript.ExampleText.Page.LoginPage;
import AutoScript.ExampleText.Variables.LoginData;
import utils.Listener.TestListener;


@Listeners(TestListener.class)
public class TestHomePage extends TestBase{
	 


	@Test
public void testHomePageWithOpenBurger()throws InterruptedException 
{
		loginPage.openURl(LoginData.url, LoginData.typedriver, LoginData.setdriver).loginWithEmail("admin@phptravels.com","demoadmin");
		homePage.verifyHomePageIsPresent();
//		.openBurgerMenu(driver).verifyAllLinkV1InBurger().verifyLinks(LoginData.url);
}
}
