package AutoScript.ExampleText.Page;

import java.awt.Desktop.Action;
import java.awt.Window;
import java.io.File;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.log4testng.Logger;

import java.io.IOException;


public class HomePage extends BasePage{
	static Logger log = Logger.getLogger(HomePage.class);
	private By btn_Burger = (By.xpath("//*[@class='container-fluid px-4']//button[@id='drawerToggle']"));
	private String listlinks = "//*[@class='drawer-menu']//div[@class='nav-link-icon']";
	private By Darhboard = (By.xpath("//*[@class='container-fluid px-4']"));

	public HomePage(WebDriver driver) {
		super(driver);
	}
	
	public HomePage verifyAllLinkV1InBurger() {
		List<WebElement> links = driver.findElements(By.xpath(listlinks));
		int index = 0;
		for (WebElement link : links) {
			index++;
			ChromeDriver chrome = new ChromeDriver();
			chrome.executeScript("window.scrollBy(0,100)");
//			driver.executeScript("document.getElementById('text-8').scrollIntoView(true);");
			log.info("Open item " + index + " in new tab.");
			Actions action = new Actions(driver);
			List<String> wid = new ArrayList<String>(driver.getWindowHandles());
			action.keyDown(Keys.SHIFT);
			driver.findElement((By) link).click();
			action.keyUp(Keys.SHIFT).perform();
			driver.switchTo().window(wid.get(index));
			System.out.println("Page title of active tab: " + driver.getTitle());
			String actualURL = driver.getCurrentUrl();
			String expectedURL = String.valueOf(index);
			Assert.assertEquals(actualURL, expectedURL,
					"Verify URL of this page is correctly. Actual: " + actualURL + "Expected: " + expectedURL);
			driver.close();
			driver.switchTo().window(wid.get(0));
		}

		return this;

	}
//	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
//	wait.until(ExpectedConditions.visibilityOfElementLocated(btn_Burger));
	public HomePage openBurgerMenu(WebDriver driver) {
		log.info("Click On Burger Menu");
		Actions actions= new Actions(driver);
		actions.scrollToElement((WebElement) btn_Burger).click().perform();
//			driver.findElement(btn_Burger).click();
		return this;
	}

	public HomePage verifyBrokenLinks(List<WebElement> links) {
		for (int i = 0; i < links.size(); i++) {
			WebElement E1 = links.get(i);
			String url = E1.getAttribute("a");
			verifyLinks(url);
		}
		return this;
	}

	public HomePage verifyLinks(String linkURl) {
		try {
			URL url = new URL(linkURl);

			// Now we will be creating url connection and getting the response code
			HttpURLConnection httpURLConnect = (HttpURLConnection) url.openConnection();
			httpURLConnect.setConnectTimeout(5000);
			httpURLConnect.connect();
			if (httpURLConnect.getResponseCode() >= 400) {
				System.out.println(linkURl + " - " + httpURLConnect.getResponseMessage() + "is a broken link");
			}

			// Fetching and Printing the response code obtained
			else {
				System.out.println(linkURl + " - " + httpURLConnect.getResponseMessage());
			}
		} catch (Exception e) {
		}

		return this;
	}

	public HomePage screenShootFull() {
		// Take the screenshot
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		// Copy the file to a location and use try catch block to handle exception
		return this;
	}
	public HomePage verifyHomePageIsPresent() throws InterruptedException  { 
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(1000));
		wait.until(ExpectedConditions.visibilityOfElementLocated(Darhboard));
		Assert.assertTrue(driver.findElement(Darhboard).isDisplayed());
		log.info("verify HomePage is displayed");
		return this;
	}
}
