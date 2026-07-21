package com.shivani.seleniumdesignframework.testcomponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.shivani.seleniumdesignframework.pageobjects.Header;
import com.shivani.seleniumdesignframework.pageobjects.LoginPage;

import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

public abstract  class BaseTest {
	 protected WebDriver driver;
	 protected LoginPage loginPage;

	//initialize driver
	public WebDriver initializeDriver() throws IOException {
		
		
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")
				+ "\\src\\main\\java\\com\\shivani\\seleniumdesignframework\\resources\\GlobalData.properties");
		prop.load(fis);
		
		//System.getProperty("browser") retrieves browser property from command line
		//look for browser property in command line first, if it is present use it, else use the browser property from globalData.properties file
		String browserName=System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser");	//ternary operator
		
		//getting global data properties 
		//String browserName = prop.getProperty("browser");

		if (browserName.contains("chrome")) {
			
			ChromeOptions options=new ChromeOptions();
			if(browserName.contains("headless"))
			{
				options.addArguments("headless");
			}
			driver = new ChromeDriver(options);

		} else if (browserName.contains("edge")) {
			EdgeOptions options=new EdgeOptions();
			if(browserName.contains("headless"))
			{
				options.addArguments("headless");
			}
			driver = new EdgeDriver(options);

		} else if (browserName.contains("firefox")) {
			FirefoxOptions options=new FirefoxOptions();
			if(browserName.contains("headless"))
			{
				options.addArguments("headless");
			}
			driver = new FirefoxDriver(options);			
		}

		//when running headed mode
		//driver.manage().window().maximize();
		
		//when running headless
		driver.manage().window().setSize(new Dimension(1440, 900));

		// implicit wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		return driver;
	}
	
	// capture screenshot on failed test
	public String getScreenshot(String testCaseName,WebDriver driver) throws IOException {
		
		TakesScreenshot ts= (TakesScreenshot) driver;
		File source= ts.getScreenshotAs(OutputType.FILE);
		File destination=new File(System.getProperty("user.dir")+"//reports//"+testCaseName+".png");
		
		FileUtils.copyFile(source,destination );
		return System.getProperty("user.dir")+"//reports//"+testCaseName+".png";
	}
	
	//transform data from json to hashmap
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
		
		//read json to string
		String jsonContent= FileUtils.readFileToString(new File(filePath),
				StandardCharsets.UTF_8);
		
		//string to hashmap using ObjectMapper (Jackson Databind) --> used to read and write Json
		ObjectMapper mapper=new ObjectMapper();
		List<HashMap<String, String>> data= mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>() {
		});	//read jsonContent, convert it into given structure --> here it converts into a list of hashmap.
		
		return data;
		//{map,map}
	}

	
	@BeforeMethod(alwaysRun = true)
	public LoginPage launchApplication() throws IOException {
		driver = initializeDriver();
		loginPage = new LoginPage(driver);
		loginPage.goTo();
		return loginPage; 
	}
	
	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		if (driver != null) {
	        driver.quit();
	    }
	}	
}
