package com.crm.qa.TestCases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.BaseClass.TestBase;
import com.crm.qa.Pages.HomePage;
import com.crm.qa.Pages.LoginPage;

public class LoginPageTest extends TestBase
{	
	LoginPage loginPage;
	HomePage homePage;
	
	public LoginPageTest()
	{
		super();
	}
	
	@BeforeMethod
	public void setUp()
	{
		initialization();
		Log.info("Browser Launched Successfully");
		
		loginPage = new LoginPage(); //Here we create objects to access methods from other class
	}
	
	@Test(priority=1) //Test Case 1
	public void loginPageTitleTest()
	{
		String title = loginPage.validateLoginPageTitle();
		Assert.assertEquals(title, "CRMPRO - CRM software for customer relationship management, sales, and support.");
		Log.info("Login Page Title Verified");
	}
	
	@Test(priority=2) //Test Case 2
	public void crmLogoImageTest()
	{
		boolean flag = loginPage.validateCRMImage();
		Assert.assertTrue(flag); //If Flag is True, Assertion will be Passed.
		Log.info("CRM Log Verified");
	}
	
	@Test(priority=3, invocationCount = 1) //Test Case 3
	public void loginTest()
	{
		homePage = loginPage.login(property.getProperty("Username"),property.getProperty("Password"));
		Log.info("Successfully Logged into CRM Application");
	}
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
		Log.info("Browser Terminated");
	}
}
