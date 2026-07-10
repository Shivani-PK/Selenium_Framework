package com.shivani.seleniumdesignframework.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.shivani.seleniumdesignframework.abstractcomponents.Utility;

public class HomePage extends Utility{

	private WebDriver driver;
	private Header header;	
	
	public HomePage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		//this.header=new Header(driver);
		PageFactory.initElements(driver, this);
	}

	//PageFactory --> it is declining because of lazy loading and hard debugging
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	private By productsBy=By.cssSelector(".mb-3");
	private By addTocart=By.cssSelector("button i.fa-shopping-cart");
	private By addedToCartMessage=By.cssSelector("#toast-container");
	private By loadingIcon=By.cssSelector(".ngx-spinner-overlay");
	
	public List<WebElement> getProductList() {		
		waitForElementToAppear(productsBy);
		return products;
		
	}
	
	public WebElement getProductByName(String productName) {
		WebElement product=products.stream().filter(prod->prod.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		return product;
	}
	
	public void addProductToCart(String productName) {
		getProductByName(productName).findElement(addTocart).click();
		waitForElementToAppear(addedToCartMessage);
		waitForElementToDisapper(loadingIcon);
	}
	
	public Header getHeader() {
		if(this.header==null)
		{
			this.header=new Header(driver);
		}
		return this.header;
	}
	
	public CartPage goToCartpage() {
		getHeader().clickCart();
		CartPage cartPage=new CartPage(driver);
		return cartPage;
	}

	public OrderPage goToOrdersPage() {
		getHeader().clickOrders();
		OrderPage orderPage=new OrderPage(driver);
		return orderPage;
	}
}
