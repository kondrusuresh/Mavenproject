package com.crm.qa.Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Set;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.crm.qa.BaseClass.TestBase;
import com.crm.qa.Constants.Constants;

public class TestUtility extends TestBase 
{
	public static Workbook book;
	public static Sheet sheet;
	
	public static JavascriptExecutor javaScript;
	public static Actions actions;
	public static Select select;
	public static Alert alert;

	//1.
	//DataProvider Utility is used for getting Data from Excel ==>> Should be used with @DataProvider.
	public static Object[][] getTestData(String sheetName) 
	{
		FileInputStream file = null;
		try 
		{
			file = new FileInputStream(Constants.TEST_DATA_SHEET_PATH);
		} 
		catch(FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		try 
		{
			book = WorkbookFactory.create(file);
		} 
		catch(IOException e) 
		{
			e.printStackTrace();
		}
		
		sheet = book.getSheet(sheetName);
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		for(int i = 0; i < sheet.getLastRowNum(); i++) 
		{
			for(int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) 
			{
				data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
			}
		}
		return data;
	}

	//2.
	//To Switch into a Frame using Name.
	public void switchToFrame(String frameName) 
	{
		try 
		{
			driver.switchTo().frame(frameName);
			System.out.println("Navigated to Frame with Name ::: " +frameName);
		} 
		catch (NoSuchFrameException e) 
		{
			System.out.println("Unable to Locate Frame with Name ::: " +frameName +e.getStackTrace());
		} 
		catch (Exception e) 
		{
			System.out.println("Unable to Navigate to Frame with Name ::: " +frameName +e.getStackTrace());
		}
	}

	//3.
	//To Switch into a Frame using Index.
	public void switchToFrame(int frame) 
	{
		try 
		{
			driver.switchTo().frame(frame);
			System.out.println("Navigated to Frame with Index ::: " +frame);
		} 
		catch(NoSuchFrameException e) 
		{
			System.out.println("Unable to Locate Frame with Index ::: " +frame +e.getStackTrace());
		} 
		catch(Exception e) 
		{
			System.out.println("Unable to Navigate to Frame with Index ::: " +frame +e.getStackTrace());
		}
	}

	//4.
	//To take Screenshot at End Of Test.
	public static void takeScreenshotAtEndOfTest() throws IOException 
	{
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir");
		FileUtils.copyFile(scrFile, new File(currentDir + "/Screenshots/" + System.currentTimeMillis() + ".png"));
	}

	//5.
	//Explicit Wait to Click on any WebElement.
	public static void clickOn(WebDriver driver, WebElement element, int timeout) 
	{
		new WebDriverWait(driver, timeout).until(ExpectedConditions.elementToBeClickable(element));
		element.click();
	}

	//6.
	//Explicit Wait to Send Data to any WebElement.
	public static void sendKeys(WebDriver driver, WebElement element, int timeout, String value) 
	{
		new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOf(element));
		element.sendKeys(value);
	}

	//7.
	//Explicit Wait for Element To Be Visible.
	public static void waitForElementToBeVisible(WebDriver driver, By locator, int timeout)
	{
		new WebDriverWait(driver, timeout).
		until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	//8.
	//To Handle Multiple Windows or Switch Between Multiple Windows.
	public void switchWindow(WebDriver driver, String firstWindow, String secondWindow) 
	{
		Set<String> windowHandles = driver.getWindowHandles();
		for(String windows : windowHandles) 
		{
			if(!windows.equals(firstWindow) && !windows.equals(secondWindow)) 
			{
				driver.switchTo().window(windows);
			}
		}
	}

	//19.
	//To Check Element is Displayed or No.
	public static void isElementDisplayed(WebElement element) 
	{
		boolean elementDisplayed = element.isDisplayed();
		if(elementDisplayed) 
		{
			System.out.println("Element is Displayed");
		} 
		else 
		{
			System.out.println("Element is not Displayed");
		}
	}

	//10.
	//To Check Element is Enabled or No.
	public static void isElementEnabled(WebElement element) 
	{
		boolean elementEnabled = element.isEnabled();
		if(elementEnabled) 
		{
			System.out.println("Element is Enabled");
		} 
		else 
		{
			System.out.println("Element is not Enabled");
		}
	}

	//11.
	//To Select a value from Drop Down by using SelectByVisibleTest Method.
	public static void selectValueFromDropDownByText(WebElement element, String value) 
	{
		select = new Select(element);
		select.selectByVisibleText(value);
	}

	//12.
	//To Select a value from Drop Down by using SelectByIndex Method.
	public static void selectValueFromDropDownByIndex(WebElement element, int value) 
	{
		select = new Select(element);
		select.selectByIndex(value);
	}

	//13.
	//To Select a value from Drop Down by using SelectByValue Method.
	public static void selectValueFromDropDownByValue(WebElement element, String value) 
	{
		select = new Select(element);
		select.selectByValue(value);
	}

	//14.
	//To Print all the Values and Select a Required Value from Drop Down.
	public static void selectDropDownValue(String xpathValue, String value) 
	{
		List<WebElement> monthList = driver.findElements(By.xpath(xpathValue));
		System.out.println(monthList.size());

		for(int i=0; i<monthList.size(); i++) 
		{
			System.out.println(monthList.get(i).getText());
			if(monthList.get(i).getText().equals(value)) 
			{
				monthList.get(i).click();
				break;
			}
		}
	}

	//15.
	//To Accept Alert Pop-Up.
	public static void acceptAlertPopup() throws InterruptedException 
	{
		try 
		{
			alert = driver.switchTo().alert();
			System.out.println(alert.getText());
			Thread.sleep(2000);
			alert.accept();
			System.out.println("Alert Accepted Successfully");
		} 
		catch(Exception e) 
		{
			System.out.println("Something Went Wrong ==>> Please Check ::: " +e.getMessage());
		}
	}

	//16.
	//To Dismiss Alert Pop-Up.
	public static void dismissAlertPopup() throws InterruptedException 
	{
		try 
		{
			alert = driver.switchTo().alert();
			System.out.println(alert.getText());
			Thread.sleep(2000);
			alert.dismiss();
			System.out.println("Alert Dismissed Successfully");
		} 
		catch(Exception e) 
		{
			System.out.println("Something Went Wrong ==>> Please Check ::: " + e.getMessage());
		}
	}

	//17.
	//To Match Value with List of Elements and Click on it.
	public void clickOnMatchingValue(List<WebElement> listOfElements, String valueToBeMatched) 
	{
		for(WebElement element : listOfElements) 
		{
			if(element.getText().equalsIgnoreCase(valueToBeMatched)) 
			{
				element.click();
				return;
			}
		}
	}

	//18.
	//To Click on Element using Actions Class.
	public void clickOnElementUsingActions(WebElement element) 
	{
		actions = new Actions(driver);
		actions.moveToElement(element).click().perform();
	}
		
	//19.
	//To Mouse Hover and Click Or Select an Element using Actions Class.
	public static void moveToElement(WebDriver driver, WebElement element) 
	{
		actions = new Actions(driver);
		actions.moveToElement(element).build().perform();
	}

	//20.
	//To Perform Drag and Drop action using Actions Class - 1.
	public static void dragAndDrop_1(WebDriver driver, WebElement sourceElement, WebElement destinationElement) 
	{
		actions = new Actions(driver);
		actions.dragAndDrop(sourceElement, destinationElement).pause(Duration.ofSeconds(2)).release().build().perform();
	}

	//21.
	//To Perform Drag and Drop action using Actions Class - 2.
	public static void dragAndDrop_2(WebDriver driver, WebElement sourceElement, WebElement destinationElement) 
	{
		actions = new Actions(driver);
		actions.clickAndHold(sourceElement).pause(Duration.ofSeconds(2)).moveToElement(destinationElement).pause(Duration.ofSeconds(2)).release().build().perform();
	}

	//22.
	//To Perform Right Click action using Actions Class.
	public static void rightClick(WebDriver driver, WebElement element) 
	{
		actions = new Actions(driver);
		actions.contextClick(element).build().perform();
	}

	//23.
	//To perform Double Click action using Actions Class.
	public static void doubleClick(WebDriver driver, WebElement element) 
	{
		actions = new Actions(driver);
		actions.doubleClick(element).build().perform();
	}

	//24.
	//To Highlight WebElement by using JavaScript Executor.
	public static void highlightElementByJavaScript(WebElement element, WebDriver driver) 
	{
		javaScript = ((JavascriptExecutor) driver);
		String backgroundColor = element.getCssValue("backgroundColor");
		for(int i = 0; i < 10; i++) 
		{
			changeColorByJavaScript("rgb(0, 200, 0)", element, driver);
			changeColorByJavaScript(backgroundColor, element, driver);
		}
	}

	//25.
	//To Change the Color of WebElement by using JavaScript Executor.
	public static void changeColorByJavaScript(String color, WebElement element, WebDriver driver) 
	{
		javaScript = ((JavascriptExecutor) driver);
		javaScript.executeScript("arguments[0].style.backgroundColor = '" + color + "'", element);
		try 
		{
			Thread.sleep(2000);
		} 
		catch(InterruptedException e) 
		{

		}
	}

	//26.
	//To Draw a Border for WebElement by using JavaScript Executor.
	public static void drawElementBorderByJavaScript(WebElement element, WebDriver driver) 
	{
		javaScript = ((JavascriptExecutor) driver);
		javaScript.executeScript("arguments[0].style.border='3px solid red'", element);
	}

	//27.
	//To Generate an Alert by using JavaScript Executor.
	public static void generateAlertByJavaScript(WebDriver driver, String alertMessage) 
	{
		javaScript = ((JavascriptExecutor) driver);
		javaScript.executeScript("alert('" + alertMessage + "')");
	}

	//28.
	//To Click on any WebElement by using JavaScript Executor.
	public static void clickElementByJavaScript(WebElement element, WebDriver driver) 
	{
		javaScript = ((JavascriptExecutor) driver);
		javaScript.executeScript("arguments[0].click();", element);
	}

	//39.
	//To Refresh Browser by using JavaScript Executor.
	public static void refreshBrowserByJavaScript(WebDriver driver)
	{
		javaScript = ((JavascriptExecutor) driver);
		javaScript.executeScript("history.go(0)");
	}

	//30.
	//To Get Title of the Page by using JavaScript Executor.
	public static void getPageTitleByJavaScript(WebDriver driver) 
	{
		javaScript = ((JavascriptExecutor) driver);
		String pageTitle = javaScript.executeScript("return document.title;").toString();
		System.out.println("The Title of the Page is ::: " + pageTitle);
	}

	//31.
	//To Get the Page Inner Text by using JavaScript Executor.
	public static void getPageInnerTextByJavaScript(WebDriver driver) 
	{
		javaScript = ((JavascriptExecutor) driver);
		String pageText = javaScript.executeScript("return document.documentElement.innerText;").toString();
		System.out.println("The Text of the Page is ::: " + pageText);
	}

	//32.
	//To Scroll Down the Page by using JavaScript Executor.
	public static void scrollDownPageByJavaScript(WebDriver driver) 
	{
		javaScript = ((JavascriptExecutor) driver);
		javaScript.executeScript("window.scrollTo(0,document.body.scrollHeight)");
	}

	//33.
	//To Scroll into WebElement View by using JavaScript Executor.
	public static void scrollIntoElementByJavaScript(WebElement element, WebDriver driver) 
	{
		javaScript = ((JavascriptExecutor) driver);
		javaScript.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	//34.
	//To Send Input Data to Text Field by using JavaScript Executor.
	public static void inputToTextFieldByJavaScript(WebElement element, WebDriver driver, String data) {
		javaScript = ((JavascriptExecutor) driver);
		javaScript.executeScript("arguments[0].value='" + data + "'", element);
	}

	//35.
	//To Select Calendar Date Or Data Picker Using Java Script Executor.
	public static void selectDateByJS(WebDriver driver, WebElement element, String dateValue) 
	{
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].setAttribute('value','" + dateValue + "');", element);
	}

	//36.
	//Extent Report - 1.
	public static String getSystemDate() 
	{
		DateFormat dateFormat = new SimpleDateFormat("_ddMMyyyy_HHmmss");
		Date date = new Date();
		return dateFormat.format(date);
	}

	//37.
	//Extent Report - 2.
	public static String getScreenshot(WebDriver driver, String screenshotName) throws IOException 
	{
		String dateName = new SimpleDateFormat("_ddMMyyyy_HHmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);

		String destination = System.getProperty("user.dir") + "/FailedTestsScreenshots/" + screenshotName + dateName + ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}

	//38.
	//Set Date For Log4J.
	public static void setDateForLog4j()
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat("_ddMMyyy_hhmmss");
		System.setProperty("current_date", dateFormat.format(new Date()));
		PropertyConfigurator.configure("./src/main/resources/log4j.properties");
	}
}
