package AutoScript.ExampleText.Page;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class HomePage {
WebDriver driver;
private By btn_Burger=(By.xpath("//*[@class='container-fluid px-4']//button[@id='drawerToggle']"));
private By listlinks =(By.xpath("//*[@class='drawer-menu']//div[@class='nav-link-icon']"));
	public HomePage(WebDriver driver) {
		this.driver=driver;
	}
	public HomePage verifyAllLinkV1InBurger() {
		openBurger();
		List<WebElement>  links =driver.findElements(listlinks);
		for(WebElement link:links)
		{
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
