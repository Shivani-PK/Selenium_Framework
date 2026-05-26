package introduction;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.openqa.selenium.support.locators.RelativeLocator.*;

public class NewLocators {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/angularpractice/");
		
		WebElement nameBox= driver.findElement(By.cssSelector("[name='name']"));
		//above --> locator which is above a known web element
		System.out.println(driver.findElement(with(By.tagName("label")).above(nameBox)).getText());
		
		
		WebElement dateOfBirth=driver.findElement(By.cssSelector("[name='bday']"));		
		//below --> locator which is below a known web element
		driver.findElement(with(By.tagName("input")).below(dateOfBirth)).click();
		
		
		WebElement icecream= driver.findElement(By.xpath("//label[text()='Check me out if you Love IceCreams!']"));		
		//left --> locator which is left of a known web element
		driver.findElement(with(By.tagName("input")).toLeftOf(icecream)).click();
		
		
		WebElement radioButton= driver.findElement(By.id("inlineRadio1"));		
		//right --> locator which is right of a known web element
		System.out.println(driver.findElement(with(By.tagName("label")).toRightOf(radioButton)).getText());
	}
	

}
