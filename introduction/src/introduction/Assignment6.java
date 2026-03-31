package introduction;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Assignment6 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/AutomationPractice/");
		
		//rows of table
		List<WebElement> rowList=driver.findElements(By.cssSelector("table[name='courses'] tr"));
		System.out.println("No of rows: "+rowList.size());
		
		//columns of table
		List<WebElement> columnList= driver.findElements(By.cssSelector("table[name='courses'] tr:nth-child(1) th"));
		System.out.println("No of columns: "+columnList.size());
		
		//content of 2nd row
		List<WebElement> secondRowInfo= driver.findElements(By.cssSelector("table[name='courses'] tr:nth-child(3) td"));
		for(WebElement value:secondRowInfo)
		{
			System.out.print(value.getText()+" ");
		}
	}

}
