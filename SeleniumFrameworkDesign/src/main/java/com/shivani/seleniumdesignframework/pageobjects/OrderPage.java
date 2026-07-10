package com.shivani.seleniumdesignframework.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.shivani.seleniumdesignframework.abstractcomponents.Utility;

public class OrderPage extends Utility{

	private WebDriver driver;
	By productNames=By.cssSelector("tr td:nth-child(3)");
	
	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
	}

	public boolean verifyOrderDisplay(String productName) {
		boolean match= driver.findElements(productNames).stream().anyMatch(product->product.getText().equals(productName));
		return match;
	}
}
