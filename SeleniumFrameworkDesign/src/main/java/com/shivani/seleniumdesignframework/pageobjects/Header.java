package com.shivani.seleniumdesignframework.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import com.shivani.seleniumdesignframework.abstractcomponents.Utility;

public class Header extends Utility{
	
	private WebDriver driver;
	private By cartButton=By.cssSelector("button[routerlink*=\"cart\"]");
	private By orderButton=By.cssSelector("[routerlink*='myorders']");
	private By loadingSpinner=By.cssSelector(".ngx-spinner-overlay");
	
	
	public Header(WebDriver driver) {
		super(driver);
		this.driver=driver;
	}


	
	public void clickCart() {
		//wait for loading spinner to vanish
		waitForElementToDisapper(loadingSpinner);
		
		//ensure button is ready
		waitForElementClickable(cartButton);
		
		// click using JavascriptExecutor to bypass the overlay animation
	    JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", driver.findElement(cartButton));
	}
	
	public void clickOrders() {
		//wait for loading spinner to vanish
		waitForElementToDisapper(loadingSpinner);
		
		//ensure button is ready
		waitForElementClickable(orderButton);
		
		// click using JavascriptExecutor to bypass the overlay animation
		JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("arguments[0].click();", driver.findElement(orderButton));	    
	}
}
