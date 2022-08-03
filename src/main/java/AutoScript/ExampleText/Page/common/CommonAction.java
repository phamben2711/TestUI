package AutoScript.ExampleText.Page.common;

import java.io.FileReader;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.log4testng.Logger;

import AutoScript.ExampleText.Page.BasePage;
import AutoScript.ExampleText.Page.HomePage;



public class CommonAction extends BasePage {

	public CommonAction(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	static Logger log = Logger.getLogger(HomePage.class);

	private static JavascriptExecutor js=(JavascriptExecutor) driver;
	public static void scrollIntoViewIfNeeded(WebElement element) {

		js.executeScript("arguments[0].scrollIntoView(false);",element);
		}
	
	public static void scrollDownBottom() {
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	} 
	
	public static void clickToElementByJS(WebElement element) {
	js.executeScript("arguments[0].click();",element);
	} 
	
	public  String getComputedStyle(WebElement element, String style) {
		String jsQuery = String.format("return window.getComputedStyle(arguments[0], null).getPropertyValue('%s')",
				style);
		String computedStyle = (String) js.executeScript(jsQuery,element);
		return computedStyle;
	}
	
	public static List<String> getAllOptionsOnSelectElement(WebElement selectElement) {
		List<String> optionValues = new ArrayList<>();
		List<WebElement> optionsElements = new Select(selectElement).getOptions();
		for (int i = 0; i < optionsElements.size(); i++) {
			optionValues.add(optionsElements.get(i).getText().trim());
		}
		return optionValues;
	}
	
	public static void sleep(long milisecond) {
		try {
			Thread.sleep(milisecond);
		} catch (Exception e) {
		}
	}
	public static void waitForElement(int seconds, WebElement element){
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
	    wait.until(ExpectedConditions.visibilityOfElementLocated
	              ((By) element));
	}
	
	public static void closeBrowserPopup() {
		log.info("Close browser popup");
		Set<String> allWindows = driver.getWindowHandles();
		String[] allWindownArr = new String[allWindows.size()];
		String mainWindow = allWindows.toArray(allWindownArr)[0];
		js.executeScript("window.close()");
		driver.switchTo().window(mainWindow);
	}
	
	protected static JSONObject getDataFile(String dataFileName) {
	    String dataFilePath = "src/test/resources/";
	    JSONObject testObject = null; 
	    try {
	        FileReader reader = new FileReader(dataFilePath + dataFileName);                        
	        JSONParser jsonParser = new JSONParser();
	        JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
	        testObject = (JSONObject) jsonObject;
	    } catch (Exception ex) {
	        ex.printStackTrace();
	    }
	    return testObject;
	}
	
	public static Boolean isStringIsCorrectPattern(String text, String regex) {
		Pattern pattern = Pattern.compile(regex);
		return pattern.matcher(text).matches();
	}
	public static void search(WebElement element, String data) {
		driver.findElement((By) element).sendKeys(data);
		log.info("Search with key");
		Actions action = new Actions(driver);	
		action.keyDown(Keys.ENTER);
	}
	private static String getWindowWithUrl(String url, WebDriver driver){
		String expWindow = "";
		String wUrl = "";
		String currentWindow = driver.getWindowHandle();
		List<String> newWindows = driver.getWindowHandles().stream().filter(w -> !w.equals(currentWindow)).collect(Collectors.toList());

		for(String w : newWindows){
			driver.switchTo().defaultContent();
			wUrl = driver.switchTo().window(w).getCurrentUrl();
			if(wUrl.equals(url)){
				expWindow = w;
				return expWindow;
			}
			driver.switchTo().window(currentWindow);
		}

		return expWindow;
	}
	public static void waitAndSwitchToNewWindowWithUrl(String url, int timeOut){
	
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOut));
		wait.until((ExpectedCondition<Boolean>) d-> driver.getWindowHandles().size() > 1);
		wait.until((ExpectedCondition<Boolean>) d ->
			!getWindowWithUrl(url, driver).equals("")
		);
	}
	public static boolean waitForElementIsDisplayed(WebElement element, int timeOut) {
		try {
			return new WebDriverWait(driver, Duration.ofSeconds(timeOut)).until(new Function<WebDriver, Boolean>() {
				public Boolean apply(WebDriver driver) {
					try {
						return element.isDisplayed();
					} catch (Exception e) {
						e.printStackTrace();
					}
					return false;
				}
			});
		} catch (Exception e) {
			return false;
		}
	}
	}
	

