package com.shivani.seleniumdesignframework.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.shivani.seleniumdesignframework.abstractcomponents.Utility;

public class CheckOutPage extends Utility{

	WebDriver driver;
	private By placeOrder = By.xpath("//a[normalize-space()='Place Order']");	
	private By cvvInput=By.xpath("//div[contains(text(),'CVV Code')]/following::input[1]"); 
	private By country=By.cssSelector("input[placeholder='Select Country']");
	private By countryListResult=By.cssSelector(".ta-results");
	private By countryList=By.cssSelector(".ta-item");
	
	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
	}

	public void enterCVV(String cvv) {
		waitForElementToAppear(placeOrder);
		driver.findElement(cvvInput).sendKeys(cvv);
	}
	
	public void enterAndSelectCountry(String countryName) {
		Actions a=new Actions(driver);
		a.sendKeys(driver.findElement(country), countryName).build().perform();
		waitForElementToAppear(countryListResult);
		List<WebElement> countries= driver.findElements(countryList);
		WebElement c= countries.stream().filter(country->country.findElement(By.cssSelector("span")).getText().trim().equalsIgnoreCase(countryName)).findFirst().orElse(null);
		c.click();
	}
	
	public ConfirmationPage clickPlaceOrder() {
		
		scrollTo(driver.findElement(placeOrder));
		driver.findElement(placeOrder).click();
		ConfirmationPage confirmationPage= new ConfirmationPage(driver);
		return confirmationPage;
	}
}
