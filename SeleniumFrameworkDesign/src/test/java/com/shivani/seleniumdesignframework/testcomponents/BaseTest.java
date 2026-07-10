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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.shivani.seleniumdesignframework.pageobjects.Header;
import com.shivani.seleniumdesignframework.pageobjects.LoginPage;

import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

public abstract  class BaseTest {
	 private WebDriver driver;
	 protected LoginPage loginPage;

	public WebDriver initializeDriver() throws IOException {

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")
				+ "\\src\\main\\java\\com\\shivani\\seleniumdesignframework\\resources\\GlobalData.properties");
		prop.load(fis);
		String browserName = prop.getProperty("browser");

		if (browserName.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();

		} else if (browserName.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();

		} else if (browserName.equals("firefox")) {
			driver = new FirefoxDriver();

		}

		driver.manage().window().maximize();

		// implicit wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		return driver;

	}
	
	public List<HashMap<String, String>> getJsondataToMap(String filePath) throws IOException {
		
		//read json to string
		String jsonContent= FileUtils.readFileToString(new File(filePath),
				StandardCharsets.UTF_8);
		
		//string to hashmap (Jackson Databind) --> it converts string to hashmap and then stores the hashmap in a list
		ObjectMapper mapper=new ObjectMapper();
		List<HashMap<String, String>> data= mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>() {
		});
		
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
		driver.quit();
	}	
}
