package introduction;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class Assignment1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		
		driver.get("https://rahulshettyacademy.com/angularpractice/");
		
		driver.findElement(By.name("name")).sendKeys("shiva");
		driver.findElement(By.name("email")).sendKeys("shiva@gmail.com");
		driver.findElement(By.id("exampleInputPassword1")).sendKeys("shiva");
		driver.findElement(By.id("exampleCheck1")).click();
		Assert.assertEquals(driver.findElement(By.id("exampleCheck1")).isEnabled(),true);
		
		Select options=new Select(driver.findElement(By.id("exampleFormControlSelect1")));
		options.selectByVisibleText("Female");
		
		driver.findElement(By.cssSelector("input[value='option1']")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector("input[value='option1']")).isEnabled(), true);
		
		driver.findElement(By.name("bday")).sendKeys("16071999");
		
		driver.findElement(By.className("btn-success")).click();
		
		//System.out.println(driver.findElement(By.cssSelector("")).getText());
		Assert.assertTrue(driver.findElement(By.className("alert-success")).getText().contains("Success! The Form has been submitted successfully!."));
	}

}
