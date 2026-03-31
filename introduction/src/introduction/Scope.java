package introduction;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Scope {

	public static void main(String[] args) {
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();		
		driver.get("https://rahulshettyacademy.com/AutomationPractice/");
		
		// give me count of links in a page
		System.out.println(driver.findElements(By.tagName("a")).size());
		
		//links in footer page
		WebElement footerDriver=driver.findElement(By.cssSelector("div#gf-BIG"));
		System.out.println(footerDriver.findElements(By.tagName("a")).size());
		
		//links count of first column in footer section
		WebElement columnDriver= footerDriver.findElement(By.xpath(".//tbody/tr/td[1]/ul"));
		System.out.println(columnDriver.findElements(By.tagName("a")).size());
		
		//click on each link in column and check if the pages are opened
		for(int i=1;i<columnDriver.findElements(By.tagName("a")).size();i++)
		{
			String pressControlAndEnter=Keys.chord(Keys.CONTROL,Keys.ENTER);			
			columnDriver.findElements(By.tagName("a")).get(i).sendKeys(pressControlAndEnter);
		}
		
		Set<String> windows=driver.getWindowHandles();
		Iterator<String> it=windows.iterator();
		it.next();
		
		while(it.hasNext())
		{				
			driver.switchTo().window(it.next());
			System.out.println(driver.getTitle());			
		}
	}

}
