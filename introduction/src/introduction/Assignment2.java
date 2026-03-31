package introduction;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Assignment2 {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		String username="rahulshettyacademy";
		String password="Learning@830$3mK2";
		
		WebDriver driver=new ChromeDriver();
		WebDriverWait w=new WebDriverWait(driver, Duration.ofSeconds(3));
		
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/loginpagePractise/");
		
		//enter username
		driver.findElement(By.id("username")).sendKeys(username);
		
		//enter password
		driver.findElement(By.id("password")).sendKeys(password);
		
		//select user radio button
		driver.findElement(By.xpath("//label[@class='customradio']/span[contains(text(),'User')]")).click();
		
		//accept alert
		w.until(ExpectedConditions.visibilityOfElementLocated(By.id("okayBtn")));
		driver.findElement(By.id("okayBtn")).click();
		
		// select consultant from dropdown
		Select role=new Select(driver.findElement(By.cssSelector("select.form-control")));
		role.selectByValue("consult");
		
		//tick the checkbox
		driver.findElement(By.cssSelector("input#terms")).click();
		
		//click on signin
		driver.findElement(By.id("signInBtn")).click();
		
		//wait till next page loaded 
		w.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("Checkout")));
		
		//get all devices and add to cart
		//List<WebElement> devices= driver.findElements(By.cssSelector(".card-footer .btn-info"));
		List<WebElement> devices= driver.findElements(By.xpath("//app-card"));

		
		for(WebElement device:devices)
		{
			
			device.findElement(By.xpath(".//button")).click();
		}
		
		//click checkout
		driver.findElement(By.partialLinkText("Checkout")).click();
	}

}
