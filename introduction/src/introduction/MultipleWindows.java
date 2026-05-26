package introduction;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;

public class MultipleWindows {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		WebDriver driver=new ChromeDriver();
		
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/angularpractice/");
		
		
		//invoking new window or tab
		driver.switchTo().newWindow(WindowType.TAB); // driver.switchTo().newWindow(WindowType.WINDOW);
		
		// store window handles
		Set<String> handles= driver.getWindowHandles();
		Iterator<String> it= handles.iterator();
		String window1=it.next();
		String window2=it.next();
		
		//changing control to new tab
		driver.switchTo().window(window2);
		driver.get("https://rahulshettyacademy.com/course-library");
		
		String courseName=driver.findElement(By.xpath("//section[contains(@class,'container')]//div[contains(@class,'grid')]/div[1]//h3")).getText();
		
		//switch back to parent window
		driver.switchTo().window(window1);
		
		WebElement name=driver.findElement(By.cssSelector("input[name='name']"));
		name.sendKeys(courseName);
		
		//screenshot of web element
		File file=name.getScreenshotAs(OutputType.FILE); 
		FileUtils.copyFile(file, new File("logo.png"));
		
		
		//get height and width of web element
		int height=name.getRect().getDimension().getHeight();
		System.out.println("height "+height);
		
		int width=name.getRect().getDimension().getWidth();
		System.out.println("width "+width);
	}

}
