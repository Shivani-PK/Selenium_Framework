package com.shivani.seleniumdesignframework.tests;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.shivani.seleniumdesignframework.pageobjects.CartPage;
import com.shivani.seleniumdesignframework.pageobjects.CheckOutPage;
import com.shivani.seleniumdesignframework.pageobjects.ConfirmationPage;
import com.shivani.seleniumdesignframework.pageobjects.Header;
import com.shivani.seleniumdesignframework.pageobjects.HomePage;
import com.shivani.seleniumdesignframework.pageobjects.LoginPage;
import com.shivani.seleniumdesignframework.pageobjects.OrderPage;
import com.shivani.seleniumdesignframework.testcomponents.BaseTest;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

public class SubmitOrderTest extends BaseTest{


	@Test(dataProvider = "getData",groups = "Purchase")
	public void submitOrder(HashMap<String, String> input) throws IOException
	{
	
		// TODO Auto-generated method stub
		

		String requiredCountry="canada";
		String cvv="123";
		
		//instantiate driver and login to application	
		HomePage homePage=loginPage.loginApplication(input.get("email"), input.get("password"));
				
		// wait for page to load, get product list, find required element by applying stream and add to cart. 
		// wait until loading icon disappears, 'added to cart' confirmation appears
		homePage.addProductToCart(input.get("requiredProduct"));		
		
		//  click on cart
		CartPage cartPage=homePage.goToCartpage();
		
		
		//grab cart product list and check if required product is there and click checkout
		boolean match=cartPage.verifyProductDisplay(input.get("requiredProduct"));
		Assert.assertTrue(match);	
		CheckOutPage checkOutpage= cartPage.goToCheckoutPage();
		
		//fill out checkout page details and place order		
		//enter cvv
		checkOutpage.enterCVV(cvv);		
		//enter and select country
		checkOutpage.enterAndSelectCountry(requiredCountry);		
		//click place order
		ConfirmationPage confirmationPage= checkOutpage.clickPlaceOrder();
			
		// grab confirmation message and verify
		String originalMessage=confirmationPage.getConfirmationMessage();
		Assert.assertTrue(originalMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));	
	}
	
	//to verify product is displayed in orders page
	@Test(dependsOnMethods = {"submitOrder"})	// depends on method --> below test runs only after the mentioned test is executed 
	public void validateOrderHistory() {
		String email="test@quality.com";
		String password="@Test000";
		String requiredProduct="ZARA COAT 3";
		
		HomePage homePage=loginPage.loginApplication(email, password);
		OrderPage orderPage= homePage.goToOrdersPage();
		Assert.assertTrue(orderPage.verifyOrderDisplay(requiredProduct));
	}
	
	
	@DataProvider
	public Object[][] getData() throws IOException{
		
//		HashMap<String, String> map=new HashMap();
//		map.put("email", "test@quality.com");
//		map.put("password", "@Test000");
//		map.put("requiredProduct", "ZARA COAT 3");
//		
//		HashMap<String, String> map1=new HashMap();
//		map1.put("email", "test1@quality.com");
//		map1.put("password", "@Test001");
//		map1.put("requiredProduct", "ADIDAS ORIGINAL");
		
		List<HashMap<String, String>> data=getJsondataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\com\\shivani\\seleniumdesignframework\\data\\PurchaseOrderData.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
	
//	@DataProvider
//	public Object[][] getData() {
//		
//		return new Object[][] {{"test@quality.com","@Test000","ZARA COAT 3"},{"test1@quality.com","@Test001","ADIDAS ORIGINAL"}};
//		
//	}
	
	

}
