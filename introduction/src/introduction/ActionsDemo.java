package introduction;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ActionsDemo {

	public static void main(String[] args)     {
		// TODO Auto-generated method stub
		
		WebDriver driver=new ChromeDriver();
		WebDriverWait w=new WebDriverWait(driver, Duration.ofSeconds(5));

		driver.manage().window().maximize();
			
		driver.get("https://demoqa.com/books");

		w.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Git Pocket Guide']")));

		WebElement searchBox=driver.findElement(By.id("searchBox"));
		Actions a=new Actions(driver);

		//moves mouse to specific element. press shift, send keys , double click
		a.moveToElement(searchBox).click().keyDown(Keys.SHIFT).sendKeys("git").doubleClick().build().perform();
		
		//perform mouse right click 
		a.moveToElement(searchBox).contextClick().build().perform(); 
	}

}
