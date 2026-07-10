package com.shivani.seleniumdesignframework.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.shivani.seleniumdesignframework.abstractcomponents.Utility;

public class ConfirmationPage extends Utility{

	WebDriver driver;
	private By confirmationMessage=By.cssSelector("h1.hero-primary");
	
	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
	}

	
	public String getConfirmationMessage() {
		waitForElementToAppear(confirmationMessage);
		return driver.findElement(confirmationMessage).getText();
	}
}

