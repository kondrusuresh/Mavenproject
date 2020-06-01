package com.crm.qa.Utilities;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomUtilities 
{
	public static String randomString()
	{
		String generatedRandomString = RandomStringUtils.randomAlphabetic(7);
		return generatedRandomString;
	}
	
	public static String randomNumber()
	{
		String generatedRandomNumber = RandomStringUtils.randomNumeric(7);
		return generatedRandomNumber;
	}
	
	public static String randomAlphaNumeric()
	{
		String generatedAlphaNumeric = RandomStringUtils.randomAlphanumeric(7);
		return generatedAlphaNumeric;	
	}
}
 