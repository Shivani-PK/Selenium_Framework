package introduction;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class JavScriptExecutor {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		int valueSum=0;
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/AutomationPractice/");
		
		// JavascriptExecutor object to scroll
		JavascriptExecutor js=(JavascriptExecutor) driver;
		
		//execute script to scroll
		js.executeScript("window.scrollBy(0,500)");
		
		Thread.sleep(2000);
		
		js.executeScript("document.querySelector(\".tableFixHead\").scrollTop=5000");
		
		List<WebElement> values= driver.findElements(By.cssSelector(".tableFixHead tbody tr td:nth-child(4)"));
		for(WebElement value: values) 
		{
			valueSum+=Integer.parseInt(value.getText());
		}
		System.out.println(valueSum);
		
		Assert.assertTrue(driver.findElement(By.className("totalAmount")).getText().contains(Integer.toString(valueSum)));
	}
	
}
