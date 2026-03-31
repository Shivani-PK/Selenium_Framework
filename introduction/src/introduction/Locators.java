package introduction;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class Locators {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriver driver=new ChromeDriver();
		String name="Shiva";
		
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/AutomationPractice/");
		
		// generate alert
		driver.findElement(By.id("name")).sendKeys(name);
		driver.findElement(By.xpath("//input[@value='Alert']")).click();
		
		// switch to alert
		System.out.println(driver.switchTo().alert().getText());	//get text of alert
		driver.switchTo().alert().accept();	//accept alert
		
		driver.findElement(By.xpath("//input[@value='Confirm']")).click();
		
		// switch to alert
		System.out.println(driver.switchTo().alert().getText());	//get text of alert
		driver.switchTo().alert().dismiss();	//dismiss alert
	}

}
