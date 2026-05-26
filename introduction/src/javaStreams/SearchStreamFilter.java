package javaStreams;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class SearchStreamFilter {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/seleniumPractise/#/offers");
		
		String search="Rice";
		driver.findElement(By.id("search-field")).sendKeys(search);
		
		List<WebElement> webElementList= driver.findElements(By.xpath("//table/tbody/tr/td[1]"));
		List<String> originalList= webElementList.stream().map(s->s.getText()).collect(Collectors.toList());
		
		
		List<WebElement> WebFilteredList= webElementList
		.stream()
		.filter(s->s.getText().contains(search))
		.collect(Collectors.toList());
		
		List<String> filteredList= WebFilteredList.stream().map(s->s.getText()).collect(Collectors.toList());
		
		Assert.assertEquals(originalList, filteredList);
		
	}

}
