package introduction;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Ecommerce {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		WebDriverWait w=new WebDriverWait(driver, Duration.ofSeconds(5));

		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		
		String[] vegetablesNeeded = { "Cucumber", "Beetroot", "Beans" };

		driver.get("https://rahulshettyacademy.com/seleniumPractise/#/");
		
		//add items to cart
		addItems(driver, vegetablesNeeded);
		
		//go to cart and apply promo code
		driver.findElement(By.cssSelector("img[alt='Cart']")).click();
		driver.findElement(By.xpath("//button[contains(text(),'PROCEED TO CHECKOUT')]")).click();
		
		
		w.until(ExpectedConditions.visibilityOfElementLocated(By.className("promoCode")));
		driver.findElement(By.className("promoCode")).sendKeys("rahulshettyacademy");
		driver.findElement(By.className("promoBtn")).click();
		
		//explicit wait
		w.until(ExpectedConditions.visibilityOfElementLocated(By.className("promoInfo")));
		
		Assert.assertEquals("Code applied ..!", driver.findElement(By.className("promoInfo")).getText());
	
		
	}

	public static void addItems(WebDriver driver, String[] vegetablesNeeded) {
		int flag = 0;
		List<WebElement> products = driver.findElements(By.xpath("//h4[@class='product-name']"));

		for (WebElement product : products) {
			String productName = product.getText().split("-")[0].trim();

			// convert array into array list for easy search
			List<String> vegetablesNeededList = Arrays.asList(vegetablesNeeded);

			if (vegetablesNeededList.contains(productName)) {
				product.findElement(By.xpath("following-sibling::div[@class='product-action']/button")).click();
				flag++;
				if (flag == vegetablesNeededList.size()) {
					break;
				}
			}

		}
	}
}
