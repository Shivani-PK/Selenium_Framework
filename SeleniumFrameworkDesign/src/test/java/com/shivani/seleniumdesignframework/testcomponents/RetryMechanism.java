package com.shivani.seleniumdesignframework.testcomponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryMechanism implements IRetryAnalyzer{
	
	int count=0;
	int maxTry=1;

	@Override
	public boolean retry(ITestResult result) {
		
		if(count<maxTry)
		{
			count++;
			return true;	//return true means rerun the failed test
		}
		return false;
	} 
}