package javaStreams;

import java.text.CollationElementIterator;
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

		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/seleniumPractise/#/offers");

		// click on column
		// capture all web elements into list
		// capture text into new list (original list)
		// apply sort -> sorted list

		// click on column
		driver.findElement(By.xpath("//th[span[text()='Veg/fruit name']]")).click();

		// capture all web elements into list
		List<WebElement> webElementsList = driver.findElements(By.xpath("//table//tbody/tr/td[1]"));

		// capture text into new list (original list)
		List<String> textList = webElementsList.stream().map(s -> s.getText()).collect(Collectors.toList());

		List<String> sortedList = textList.stream().sorted().collect(Collectors.toList());

		Assert.assertEquals(textList, sortedList);

		// scan vegetable column and get text
		// once vegetable selected, get price
		boolean found = false;
		List<String> price;

		
		do {
			List<WebElement> webElementsList1 = driver.findElements(By.xpath("//table//tbody/tr/td[1]"));
			price = webElementsList1.stream().filter(s -> s.getText().equals("Rice")).map(s -> getVeggiePrice(s))
					.collect(Collectors.toList());

			if (price.size() > 0) { // element found

				
				price.forEach(a -> System.out.println(a));
				found = true;

			} 
			else { // click next
				
				WebElement next = driver.findElement(By.cssSelector("[aria-label='Next']"));
				if (next.getAttribute("aria-disabled").contains("true")) {
					System.out.println("veggie not present");
					break;
				}
				next.click();

			}
		} while (!found);

	}

	private static String getVeggiePrice(WebElement s) {

		String price = s.findElement(By.xpath("following-sibling::td[1]")).getText();
		return price;

	}
	
	

}
