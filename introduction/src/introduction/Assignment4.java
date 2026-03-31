package introduction;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Assignment4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		
		driver.get("https://the-internet.herokuapp.com/nested_frames");
		
		//switch to outer frame
		driver.switchTo().frame("frame-top");
		
		//switch to inner middle frame
		driver.switchTo().frame("frame-middle");
		
		//print
		System.out.println(driver.findElement(By.id("content")).getText());
		
	}

}
