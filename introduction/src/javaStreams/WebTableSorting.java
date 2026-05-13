package javaStreams;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class WebTableSorting {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/seleniumPractise/#/offers");
		
		//click on column
		//capture all web elements into list
		//capture text into new list (original list)
		//apply sort -> sorted list
		
		
		//click on column
		driver.findElement(By.xpath("//th[span[text()='Veg/fruit name']]")).click();
		
		//capture all web elements into list
		List<WebElement> webElementsList= driver.findElements(By.xpath("//table//tbody/tr/td[1]"));
		
		//capture text into new list (original list)
		List<String> textList= webElementsList.stream().map(s->s.getText()).collect(Collectors.toList());
		
		List<String> sortedList= textList.stream().sorted().collect(Collectors.toList());
		
		Assert.assertEquals(textList, sortedList);
	}

}
