package AutoScript.ExampleText.Page;

import java.awt.Desktop.Action;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.mozium.api.manage.session.MoziumExecutionManager;

import io.netty.handler.codec.AsciiHeadersEncoder.NewlineType;


public class HomePage {
WebDriver driver;
private By btn_Burger=(By.xpath("//*[@class='container-fluid px-4']//button[@id='drawerToggle']"));
private WebElement listlinks =driver.findElement(By.xpath("//*[@class='drawer-menu']//div[@class='nav-link-icon']"));
	public HomePage(WebDriver driver) {
		this.driver=driver;
	}
	public HomePage verifyAllLinkV1InBurger() {
		openBurger();
		List<WebElement>  links =driver.findElements(By.xpath(listlinks));
		for(WebElement link:links)
		{
			Actions action = new Actions(driver);
			action.keyDown(Keys.SHIFT);
			driver.findElement((By) link).click();
			action.keyUp(Keys.SHIFT).perform();
			
			link.getText();
		}
		
		return this;
		
	}
	public HomePage openBurger()
	{
		driver.findElement(btn_Burger).click();
		return this;
	}
}
