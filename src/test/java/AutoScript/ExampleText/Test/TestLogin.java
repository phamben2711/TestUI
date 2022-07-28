package AutoScript.ExampleText.Test;



import org.testng.annotations.Listeners;
import org.testng.annotations.Test;



import AutoScript.ExampleText.Variables.LoginData;
import utils.Listener.TestListener;

@Listeners(TestListener.class)
public class TestLogin extends TestBase{
	
	
	@Test
public void TestLoginPage() throws InterruptedException {

	loginPage.openURl(LoginData.url, LoginData.typedriver, LoginData.setdriver)
			.LoginWithEmail("admin@phptravels.com", "demoadmin")
//.verifyfillInEmail("admin@phptravels.com").verifyfillInPassword("demoadmin")
			.clickLogin();
	homePage.verifyHomePageIsPresent();
}
	
}