package com.crm.qa.TestCases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.qa.BaseClass.TestBase;
import com.crm.qa.Pages.ContactsPage;
import com.crm.qa.Pages.DealsPage;
import com.crm.qa.Pages.HomePage;
import com.crm.qa.Pages.LoginPage;
import com.crm.qa.Utilities.TestUtility;

public class ContactsPageTest extends TestBase
{
	LoginPage loginPage;
	HomePage homePage;
	TestUtility testUtil;
	ContactsPage contactsPage;
	DealsPage dealsPage;
	
	String sheetName = "Contacts"; //Passing Excel Sheet Name
	
	public ContactsPageTest()
	{
		super();
	}
	
	@BeforeMethod
	public void setUp()
	{
		initialization();
		testUtil = new TestUtility();
		Log.info("Browser Launched Successfully");
		
		loginPage = new LoginPage();
		contactsPage = new ContactsPage();
		dealsPage = new DealsPage();
		homePage = loginPage.login(property.getProperty("Username"),property.getProperty("Password"));
		testUtil.switchToFrame(); //For Both Methods We need to Switch into Frame before Clicking on Contacts Link.
		contactsPage = homePage.clickOnContactsLink();
	}
	
	@Test(priority=1)
	public void verifyContactsPageLabelTest()
	{
		Assert.assertTrue(contactsPage.verifyContactsLabel(), "Contacts Label is Missing in the Page");
		Log.info("Verified Contacts Page Label");
		//"Contacts Label is Missing in the Page" - Will be Printed only if "Assertion" Fails.
	}
	
	@Test(priority=2)
	public void selectSingleContactsTest()
	{
		contactsPage.selectContactByName("Sai Baba");
		Log.info("Verified Single Contacts");
	}
	
	@Test(priority=3)
	public void selectMultipleContactsTest()
	{
		contactsPage.selectContactByName("Sai Baba");
		contactsPage.selectContactByName("Pavan KrishnanReddy");
		Log.info("Verified Multiple Contacts");
	}
	
	//We are using Data Provider here to Access Data from Excel Sheet
	@DataProvider
	public Object[][] getCRMContactsTestData() //To Access Sheet from Test Data Sheet
	{
		Object data [][] = TestUtility.getTestData(sheetName);
		return data;
	}
	
	@Test(priority=4, dataProvider="getCRMContactsTestData")
	public void validateCreateNewContactTest(String Title, String FirstName, String LastName, String Company)
	//We Must Pass Parameters as it is like we have given in Excel Column Names to Access Data
	{
		homePage.clickOnNewContactLink();
		//contactsPage.createNewContact("Mr.", "Tom", "Peter", "Google"); //Hard Coding Values Here
		contactsPage.createNewContact(Title, FirstName, LastName, Company);
		Log.info("New Contacts Created Successfully");
	}
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
		Log.info("Browser Terminated");
	}
}
