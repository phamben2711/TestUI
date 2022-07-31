package AutoScript.ExampleText.Page.common;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v100.browser.Browser;
import org.openqa.selenium.support.ui.Select;
import org.testng.log4testng.Logger;


import AutoScript.ExampleText.Page.BasePage;
import AutoScript.ExampleText.Page.HomePage;


public class CommonAction extends BasePage{
	static Logger log = Logger.getLogger(HomePage.class);
	public CommonAction(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	JavascriptExecutor js=(JavascriptExecutor) driver;
	public void scrollIntoViewIfNeeded(WebElement element) {

		js.executeScript("arguments[0].scrollIntoView(false);",element);
		}
	public void scrollDownBottom() {
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
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

	public  void closeBrowserPopup() {
		log.info("Close browser popup");
		Set<String> allWindows = driver.getWindowHandles();
		String[] allWindownArr = new String[allWindows.size()];
		String mainWindow = allWindows.toArray(allWindownArr)[0];
		js.executeScript("window.close()");
		driver.switchTo().window(mainWindow);
	}
	protected JSONObject getDataFile(String dataFileName) {
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
	}
	

