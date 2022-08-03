package AutoScript.ExampleText.Test;





import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;



import AutoScript.ExampleText.Variables.LoginData;
import utils.Listener.TestListener;

@Listeners(TestListener.class)
public class TestLogin extends TestBase{
	

	@Test
public void TestLoginPage(Method method) throws InterruptedException {
	loginPage.openUrlByWebDriverManager(LoginData.url)
			.loginWithEmail("admin@phptravels.com", "demoadmin")
//.verifyfillInEmail("admin@phptravels.com").verifyfillInPassword("demoadmin")
			.clickLogin();
	homePage.verifyHomePageIsPresent();
}
	
}