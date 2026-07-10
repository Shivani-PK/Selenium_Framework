package com.shivani.seleniumdesignframework.tests;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.shivani.seleniumdesignframework.pageobjects.CartPage;
import com.shivani.seleniumdesignframework.pageobjects.CheckOutPage;
import com.shivani.seleniumdesignframework.pageobjects.ConfirmationPage;
import com.shivani.seleniumdesignframework.pageobjects.Header;
import com.shivani.seleniumdesignframework.pageobjects.HomePage;
import com.shivani.seleniumdesignframework.pageobjects.LoginPage;
import com.shivani.seleniumdesignframework.testcomponents.BaseTest;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

public class ErrorValidationTest extends BaseTest{


	@Test (groups = {"ErrorHandling"})
	public void loginErrorValidation() throws IOException
	{
	
		// TODO Auto-generated method stub
		
		String email="test@quality.com";
		String password="@Test";

		
		//instantiate driver and login to application	
		HomePage homePage=loginPage.loginApplication(email, password);
		String actualErrorMessage= loginPage.getErrorMessage();
		String expectedErrorMessage="Incorrect email or password.";
		
		Assert.assertEquals(actualErrorMessage, expectedErrorMessage);
				
	}
	
	@Test
	public void productErrorValidation() throws IOException
	{
	
		// TODO Auto-generated method stub
		
		String email="test1@quality.com";
		String password="@Test001";
		String requiredProduct="ZARA COAT 3";

		
		//instantiate driver and login to application	
		HomePage homePage=loginPage.loginApplication(email, password);
		
		homePage.addProductToCart(requiredProduct);		
		
		//  click on cart
		CartPage cartPage=homePage.goToCartpage();
				
		//grab cart product list and check if required product is there and click checkout
		boolean match=cartPage.verifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);	
	}
}