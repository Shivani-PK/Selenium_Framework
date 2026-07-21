package com.shivani.seleniumdesignframework.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {

	public static ExtentReports getreportObject() {
		String reportPath=System.getProperty("user.dir")+"//reports//index.html";
		
		ExtentSparkReporter reporter=new ExtentSparkReporter(reportPath);
		
		ExtentReports extent=new ExtentReports();
		extent.attachReporter(reporter);
		return extent;
	}
}