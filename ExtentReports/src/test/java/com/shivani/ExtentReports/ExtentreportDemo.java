package com.shivani.ExtentReports;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentreportDemo {
	ExtentReports extent;

	
	@BeforeClass
	public void setup() {
		// Define the path where the HTML report will be saved
		String reportPath=System.getProperty("user.dir")+"//reports//index.html";
		
		ExtentSparkReporter reporter=new ExtentSparkReporter(reportPath);

		// Initialize the ExtentReports main object and attach the reporter
		extent=new ExtentReports();
		extent.attachReporter(reporter);
		
		// Configure the report's visual properties
		reporter.config().setReportName("Web Automation Result");
		reporter.config().setDocumentTitle("Extent Report Test Result");
		
		// Add optional system environment information to the report dashboard
		extent.setSystemInfo("Tester", "Shivani");
		extent.setSystemInfo("Operating System", "Windows 11");
		extent.setSystemInfo("Browser", "Chrome");
	}

	@AfterClass
	public void teardown() {
		// Write all logs and information to the HTML file
		extent.flush();
	}
	
	@Test
	public void initialDemo() {
		
		// Create a new test entry in the report
		ExtentTest test=extent.createTest("Initial Demo");
		
		WebDriver driver=new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/");
		System.out.println(driver.getTitle());
		
		// Log individual steps and their statuses
        test.log(Status.INFO, "Navigating to the login page");
        test.log(Status.INFO, "Entering username and password");
        test.log(Status.PASS, "Login was successful");
        
		driver.quit();
		
		
		
	}
	
}
