package com.shivani.seleniumdesignframework.testcomponents;

import java.io.IOException;
import java.lang.reflect.Field;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.shivani.seleniumdesignframework.resources.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener{

	ExtentReports extent= ExtentReporterNG.getreportObject();
	ExtentTest test;
	
	ThreadLocal<ExtentTest> threadExtent=new ThreadLocal<ExtentTest>();

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		
		//entry for test to generate extent reports
		test= extent.createTest(result.getMethod().getMethodName());
		threadExtent.set(test);	//unique thread ID
		
		ITestListener.super.onTestStart(result);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		threadExtent.get().log(Status.PASS, "Test Passed");
		ITestListener.super.onTestSuccess(result);
	}

	@Override
	public void onTestFailure(ITestResult result) {
		
	    // If test fails, show the error in the report
	    threadExtent.get().fail(result.getThrowable());
	    
	    // Declare the driver at the method level so the entire method can see it
	    WebDriver driver = null;
	    
	    try {
	        // Fetch the field blueprint from the superclass
	        Field field = result.getTestClass().getRealClass().getSuperclass().getDeclaredField("driver");	
	        
	        // Unlock the field so Java allows us to read it
	        field.setAccessible(true);
	        
	        // Extract the actual WebDriver instance and assign it to our outer variable
	        driver = (WebDriver) field.get(result.getInstance());

	    } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
	        System.out.println("Could not extract WebDriver using Reflection.");
	        e.printStackTrace();
	    }
	    
	    // Only attempt to take and attach a screenshot if the driver was successfully found
	    if (driver != null) {
	        String filePath = null;
	        try {
	            filePath = getScreenshot(result.getMethod().getMethodName(), driver);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        
	        // Only attach if the screenshot successfully generated a file path
	        if (filePath != null) {
	        	threadExtent.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
	        }
	    }

	    ITestListener.super.onTestFailure(result);
	}
	
	@Override
	public void onFinish(ITestContext context) {

		// flush the report
		extent.flush();
		ITestListener.super.onFinish(context);
	}

}
