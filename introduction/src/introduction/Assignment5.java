package introduction;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class Assignment5 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/AutomationPractice/");
		
		//select check box
		WebElement checkBox= driver.findElement(By.xpath("//div[@id='checkbox-example']//label[2]"));
		checkBox.click();
		
		//grab label check box
		String optionName= checkBox.getText().trim();
		
		//select the same option in dropdown
		Select s=new Select(driver.findElement(By.id("dropdown-class-example")));
		s.selectByVisibleText(optionName);
		
		//enter option in alert box
		driver.findElement(By.id("name")).sendKeys(optionName);
		driver.findElement(By.id("alertbtn")).click();
		String alertText= driver.switchTo().alert().getText();

		//assert to check if alert text has option name
		Assert.assertTrue(alertText.contains(optionName));
		

	}

}
