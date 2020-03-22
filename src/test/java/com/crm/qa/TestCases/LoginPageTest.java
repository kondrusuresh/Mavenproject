package com.crm.qa.TestCases;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
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
	
	@Parameters("Browser")
	@BeforeMethod
	public void setUp(String Browser)
	{
		initialization(Browser);
		Log.info("Application Launched Successfully");
		
		loginPage = new LoginPage();
	}
	
	@Test(priority=1, enabled=true)
	public void loginPageTitleTest(Method method)
	{
		extentTest = extent.startTest(method.getName());
		String title = loginPage.validateLoginPageTitle();
		Assert.assertEquals(title, "CRMPRO - CRM software for customer relationship management, sales, and support.");
		Log.info("Login Page Title Verified");
	}
	
	@Test(priority=2, enabled=true)
	public void crmLogoImageTest(Method method)
	{
		extentTest = extent.startTest(method.getName());
		boolean flag = loginPage.validateCRMImage();
		Assert.assertTrue(flag);
		Log.info("CRM Logo Verified");
	}
	
	@Test(priority=3, enabled=true, invocationCount=1) 
	public void loginTest(Method method)
	{
		extentTest = extent.startTest(method.getName());
		homePage = loginPage.login(property.getProperty("Username"),property.getProperty("Password"));
		Log.info("Successfully Logged into CRM Application");
	}
}
