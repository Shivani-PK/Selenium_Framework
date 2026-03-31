package introduction;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Assignment3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		
		driver.get("https://the-internet.herokuapp.com/windows");
		
		//click to get new window
		driver.findElement(By.partialLinkText("Click")).click();
		
		//new window handler
		Set <String> windows= driver.getWindowHandles();
		
		//variable to iterate set
		Iterator <String> it=windows.iterator();
		String parentId=it.next();
		String childId=it.next();
		
		//switch to new child window
		driver.switchTo().window(childId);
		
		//print element present in new window
		System.out.println(driver.findElement(By.cssSelector("h3")).getText());
		
		//switch to parent window
		driver.switchTo().window(parentId);
		
		//print element present in parent window
		System.out.println(driver.findElement(By.cssSelector("h3")).getText());
	}

}
