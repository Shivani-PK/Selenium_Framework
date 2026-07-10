package com.shivani.seleniumdesignframework.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.shivani.seleniumdesignframework.abstractcomponents.Utility;

public class Header extends Utility{
	
	private WebDriver driver;
	private By cartButton=By.cssSelector("button[routerlink*=\"cart\"]");
	private By orderButton=By.cssSelector("[routerlink*='myorders']");
	
	public Header(WebDriver driver) {
		super(driver);
		this.driver=driver;
	}

	
	
	public void clickCart() {
		waitForElementClickable(cartButton);
		driver.findElement(cartButton).click();
	}
	
	public void clickOrders() {
		waitForElementToAppear(orderButton);
		driver.findElement(orderButton).click();
	}
}
