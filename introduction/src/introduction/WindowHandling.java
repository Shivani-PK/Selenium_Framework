package introduction;

import java.util.Iterator;
import java.util.Set;

import javax.crypto.spec.ChaCha20ParameterSpec;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WindowHandling {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/loginpagePractise/");
		
		driver.findElement(By.xpath("//a[contains(text(),'Free Access')]")).click();
		
		//new window handling
		Set<String> windows=driver.getWindowHandles();	//[parentid,childid]
		
		//variable to iterate set
		Iterator<String> it= windows.iterator();
		String parentId=it.next();
		String childId=it.next();
		
		//switch to child window
		driver.switchTo().window(childId);
		
		String email=driver.findElement(By.cssSelector(".im-para.red")).getText();
		email=email.split(" ")[4].trim();
		
		//switch to parent window
		driver.switchTo().window(parentId);
		
		driver.findElement(By.id("username")).sendKeys(email);
		
	}

}
