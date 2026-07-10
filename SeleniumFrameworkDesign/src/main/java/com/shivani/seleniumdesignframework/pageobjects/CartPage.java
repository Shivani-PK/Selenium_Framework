package com.shivani.seleniumdesignframework.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.shivani.seleniumdesignframework.abstractcomponents.Utility;

public class CartPage extends Utility{

	WebDriver driver;
	
	private By productTitles = By.cssSelector(".cart h3");
	private By checkoutButton=By.xpath("//button[text()='Checkout']");
	
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
	}
	
	public boolean verifyProductDisplay(String productName) {
		boolean match=driver.findElements(productTitles).stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public CheckOutPage goToCheckoutPage() {
		driver.findElement(checkoutButton).click();
		CheckOutPage checkOutPage=new CheckOutPage(driver);
		return checkOutPage;
	}
}
