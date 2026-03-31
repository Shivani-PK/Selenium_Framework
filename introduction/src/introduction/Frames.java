package introduction;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Frames {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		
		
		driver.get("https://jqueryui.com/droppable/");
		
		//switch to frame
		driver.switchTo().frame(driver.findElement(By.className("demo-frame")));
		
		WebElement source=driver.findElement(By.id("draggable"));
		WebElement target=driver.findElement(By.id("droppable"));
		
		// drag and drop
		//create action class
		Actions a=new Actions(driver);
		a.dragAndDrop(source, target).build().perform();
		
		//switch to default content
		driver.switchTo().defaultContent();
	}

}
