package com.shivani.seleniumdesignframework.pageobjects;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.shivani.seleniumdesignframework.abstractcomponents.Utility;

public class LoginPage extends Utility{

	WebDriver driver;
		
	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	
	//PageFactory --> it is declining because of lazy loading and hard debugging
	@FindBy(id="userEmail")
	private WebElement emailEle;
	private By passwordEle=By.id("userPassword");
	private	By login=By.id("login");
	private By errorMessage=By.cssSelector("[class*='toast-error']");
	
	//getters
	public WebElement getEmailEle() {
		return emailEle;
	}

	public By getLogin() {
		return login;
	}
	
	public String getErrorMessage() {
		waitForElementToAppear(errorMessage);
		return driver.findElement(errorMessage).getText();
		
	}
	
	// go to this page
	public void goTo() throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")
				+ "\\src\\main\\java\\com\\shivani\\seleniumdesignframework\\resources\\GlobalData.properties");
		prop.load(fis);
		String URL = prop.getProperty("url");
		driver.get(URL);
	}
	

	//login to application
	public HomePage loginApplication(String email, String password) {
		emailEle.sendKeys(email);
		driver.findElement(passwordEle).sendKeys(password);
		driver.findElement(login) .click();
		HomePage homePage=new HomePage(driver);
		return homePage;
	}
	

}
