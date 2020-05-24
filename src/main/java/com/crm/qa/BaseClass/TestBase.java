package com.crm.qa.BaseClass;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.crm.qa.Constants.Constants;
import com.crm.qa.Utilities.TestUtility;
import com.crm.qa.Utilities.WebEventListener;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class TestBase
{
	public static WebDriver driver; 
	public static Properties property;
	public static ChromeOptions chromeOptions;
	public static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;
	public static Logger Log;
	public static ExtentReports extent;
	public static ExtentTest extentTest;
		
	public TestBase()
	{
		Log = Logger.getLogger(this.getClass());
		try 
		{
			property = new Properties();
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/com/crm/qa/Configuration/Configuration.properties");
			property.load(ip);
		} 
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	@BeforeTest
	public void setExtent()
	{
		TestUtility.setDateForLog4j();
		
		extent = new ExtentReports(System.getProperty("user.dir") + "/CRMExtentResults/CRMExtentReport" + TestUtility.getSystemDate() + ".html");
		
		extent.addSystemInfo("Host Name", "Pavan's Windows System");
		extent.addSystemInfo("User Name", "Pavan Kumar K J");
		extent.addSystemInfo("Environment", "Automation Test Report");
	}
	
	public static void initialization(String Browser)
	{
		if(Browser.equals("chrome"))
		{
			chromeOptions = new ChromeOptions();
			chromeOptions.setExperimentalOption("useAutomationExtension", false);
			chromeOptions.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
			System.setProperty("webdriver.chrome.driver", Constants.CHROME_DRIVER_PATH);
			driver = new ChromeDriver(chromeOptions);
		}
		else if(Browser.equals("IE"))
		{
			System.setProperty("webdriver.ie.driver", Constants.INTERNET_EXPLORER_DRIVER_PATH);
			driver = new InternetExplorerDriver();
		}
		else if(Browser.equals("firefox"))
		{
			System.setProperty("webdriver.gecko.driver", Constants.FIREFOX_DRIVER_PATH);
			driver = new FirefoxDriver();
		}
		else
		{
			System.out.println("Path of Driver Executable is not Set for any Browser");
		}
		
		e_driver = new EventFiringWebDriver(driver);
		
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(Constants.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(Constants.IMPLICIT_WAIT, TimeUnit.SECONDS);
		
		driver.get(property.getProperty("Url"));
	}
	
	@AfterTest
	public void endReport()
	{
		extent.flush();
		extent.close();
	}
	
	@AfterMethod(alwaysRun=true)
	public void tearDown(ITestResult result) throws IOException
	{
		if(result.getStatus()==ITestResult.FAILURE)
		{
			extentTest.log(LogStatus.FAIL, "Test Case Failed is "+result.getName());
			extentTest.log(LogStatus.FAIL, "Test Case Failed is "+result.getThrowable()); 
		
			String screenshotPath = TestUtility.getScreenshot(driver, result.getName());
			extentTest.log(LogStatus.FAIL, extentTest.addScreenCapture(screenshotPath)); 
		}
		else if(result.getStatus()==ITestResult.SKIP)
		{
			extentTest.log(LogStatus.SKIP, "Test Case Skipped is " +result.getName());
		}
		else if(result.getStatus()==ITestResult.SUCCESS)
		{
			extentTest.log(LogStatus.PASS, "Test Case Passed is " +result.getName());
		}
		extent.endTest(extentTest); 
		driver.quit();
		Log.info("Browser Terminated");
		Log.info("-----------------------------------------------");
	}
}
