package com.crm.qa.Utilities;

import com.crm.qa.Constants.Constants;

public class StaticWaits 
{
	public static void staticShortWait()
	{
		try 
		{
			Thread.sleep(Constants.SHORT_WAIT);
		} 
		catch(InterruptedException e) 
		{
			e.printStackTrace();
		}
	}
	
	public static void staticMediumWait()
	{
		try 
		{
			Thread.sleep(Constants.MEDIUM_WAIT);
		} 
		catch(InterruptedException e) 
		{
			e.printStackTrace();
		}
	}
	
	public static void staticLongWait()
	{
		try 
		{
			Thread.sleep(Constants.LONG_WAIT);
		} 
		catch(InterruptedException e) 
		{
			e.printStackTrace();
		}
	}
}
