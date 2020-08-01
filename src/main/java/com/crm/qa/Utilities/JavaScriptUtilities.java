package com.crm.qa.Utilities;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptUtilities
{
	public static JavascriptExecutor javaScript;
	
	//To Highlight WebElement using JavaScript Executor.
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
	
	//To Change Color of WebElement using JavaScript Executor.
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
			e.printStackTrace();
		}
	}	
	
	//To Draw a Border for WebElement using JavaScript Executor.
	public static void drawElementBorderByJavaScript(WebElement element, WebDriver driver) 
	{
		javaScript = ((JavascriptExecutor) driver);
		javaScript.executeScript("arguments[0].style.border='3px solid red'", element);
	}

	//To Generate an Alert using JavaScript Executor.
	public static void generateAlertByJavaScript(WebDriver driver, String alertMessage) 
	{
		javaScript = ((JavascriptExecutor) driver);
		javaScript.executeScript("alert('" + alertMessage + "')");
	}
	
	//To Click on WebElement using JavaScript Executor.
	public static void clickElementByJavaScript(WebElement element, WebDriver driver) 
	{
		javaScript = ((JavascriptExecutor) driver);
		javaScript.executeScript("arguments[0].click();", element);
	}

	//To Refresh Browser using JavaScript Executor.
	public static void refreshBrowserByJavaScript(WebDriver driver)
	{
		javaScript = ((JavascriptExecutor) driver);
		javaScript.executeScript("history.go(0)");
	}

	//To Get Title of Page using JavaScript Executor.
	public static void getPageTitleByJavaScript(WebDriver driver) 
	{
		javaScript = ((JavascriptExecutor) driver);
		String pageTitle = javaScript.executeScript("return document.title;").toString();
		System.out.println("The Title of the Page is ::: " + pageTitle);
	}
	
	//To Get Page Inner Text using JavaScript Executor.
	public static void getPageInnerTextByJavaScript(WebDriver driver) 
	{
		javaScript = ((JavascriptExecutor) driver);
		String pageText = javaScript.executeScript("return document.documentElement.innerText;").toString();
		System.out.println("The Text of the Page is ::: " + pageText);
	}

	//To Scroll Down using JavaScript Executor.
	public static void scrollDownPageByJavaScript(WebDriver driver) 
	{
		javaScript = ((JavascriptExecutor) driver);
		javaScript.executeScript("window.scrollTo(0,document.body.scrollHeight)");
	}

	//To Scroll into WebElement View using JavaScript Executor.
	public static void scrollIntoElementByJavaScript(WebElement element, WebDriver driver) 
	{
		javaScript = ((JavascriptExecutor) driver);
		javaScript.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	//To Send Input Data to Text Field using JavaScript Executor.
	public static void inputToTextFieldByJavaScript(WebElement element, WebDriver driver, String data) {
		javaScript = ((JavascriptExecutor) driver);
		javaScript.executeScript("arguments[0].value='" + data + "'", element);
	}

	//To Select Calendar Date Or Data Picker using Java Script Executor.
	public static void selectDateByJS(WebDriver driver, WebElement element, String dateValue) 
	{
		javaScript= ((JavascriptExecutor) driver);
		javaScript.executeScript("arguments[0].setAttribute('value','" + dateValue + "');", element);
	}
}