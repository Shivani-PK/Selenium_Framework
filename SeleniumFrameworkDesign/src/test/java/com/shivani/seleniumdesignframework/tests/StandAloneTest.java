package com.shivani.seleniumdesignframework.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.shivani.seleniumdesignframework.pageobjects.LoginPage;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

public class StandAloneTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String email="test@quality.com";
		String password="@Test000";
		String requiredProduct="ZARA COAT 3";
		String requiredCountry="canada";
		
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));

		//implicit wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get("https://rahulshettyacademy.com/client/");
		
		//enter credentials and login		
		driver.findElement(By.id("userEmail")).sendKeys(email);
		driver.findElement(By.id("userPassword")).sendKeys(password);
		driver.findElement(By.id("login")).click();
		
		//wait until products are loaded
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		
		// find required element by applying stream and add to cart
		List <WebElement> products=driver.findElements(By.cssSelector(".mb-3"));
		WebElement prod=products.stream().filter(product->product.findElement(By.cssSelector("b")).getText().equals(requiredProduct)).findFirst().orElse(null);
		prod.findElement(By.cssSelector("button i.fa-shopping-cart")).click();
		
		// wait until loading icon disappears, 'added to cart' confirmation appears and click on cart
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("#toast-container"))));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ngx-spinner-overlay"))));
		driver.findElement(By.cssSelector("button[routerlink*=\"cart\"]")).click();
		
		//grab cart product list and check if required product is there and click checkout
		List<WebElement> cartProducts= driver.findElements(By.cssSelector(".cart h3"));
		boolean match=cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(requiredProduct));
		Assert.assertTrue(match);		
		driver.findElement(By.xpath("//button[text()='Checkout']")).click();
		
		//fill out checkout page details and place order
		WebElement placeOrder = wait.until(
			    ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[normalize-space()='Place Order']")));		
		WebElement cvvlabel=driver.findElement(By.xpath("//div[contains(text(),'CVV Code')]"));
		
		//input cvv
		driver.findElement(with(By.tagName("input")).below(cvvlabel)).sendKeys("123");	//or div[contains(text(),'CVV Code')]/following::input[1]
		
		//input country
		Actions a=new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("input[placeholder='Select Country']")), requiredCountry).build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		List<WebElement> countries= driver.findElements(By.cssSelector(".ta-item"));
		WebElement c= countries.stream().filter(country->country.findElement(By.cssSelector("span")).getText().trim().equalsIgnoreCase(requiredCountry)).findFirst().orElse(null);
		c.click();
		
		//click place order
		placeOrder.click();
		
		// grab confirmation message and verify
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1.hero-primary")));
		String originalMessage=driver.findElement(By.cssSelector("h1.hero-primary")).getText();
		Assert.assertTrue(originalMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
	}

}
