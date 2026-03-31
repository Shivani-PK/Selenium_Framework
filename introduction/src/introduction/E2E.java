package introduction;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class E2E {

	public static void main(String[] args) throws InterruptedException {

		WebDriver driver=new ChromeDriver();
		
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/dropdownsPractise/");
		
		
		//select country
		driver.findElement(By.id("autosuggest")).sendKeys("ind");
		Thread.sleep(1000);
		
		List<WebElement> options= driver.findElements(By.cssSelector(".ui-menu-item a"));
		for(WebElement option: options)
		{
			if(option.getText().equalsIgnoreCase("India"))
			{
				option.click();
				break;
			}
		}
		
		//select source
		driver.findElement(By.id("ctl00_mainContent_ddl_originStation1_CTXT")).click();
		driver.findElement(By.xpath("//a[@value='MAA']")).click();
		
		//select destination
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[@id='glsctl00_mainContent_ddl_destinationStation1_CTNR']//a[@value='BLR']")).click();
		
		//select today date
		driver.findElement(By.className("ui-state-active")).click();

		
		//select passengers
		driver.findElement(By.id("divpaxinfo")).click();
		Thread.sleep(1000);
		for(int i=0;i<4;i++)
		{
			driver.findElement(By.cssSelector("#divAdult #hrefIncAdt")).click();
		}
		driver.findElement(By.id("btnclosepaxoption")).click();
		Assert.assertEquals(driver.findElement(By.id("divpaxinfo")).getText(), "5 Adult");
		
		//select currency
		WebElement staticDropdown= driver.findElement(By.id("ctl00_mainContent_DropDownListCurrency"));
		Select dropdown = new Select(staticDropdown);
		dropdown.selectByIndex(3);
		
		Assert.assertEquals(dropdown.getFirstSelectedOption().getText(), "USD");
		dropdown.selectByVisibleText("AED");
		
		//select discount
		boolean check=driver.findElement(By.cssSelector("input[id*='SeniorCitizenDiscount']")).isSelected();
		Assert.assertEquals(check, false);	// or Assert.assertFalse(check);
		
		
		driver.findElement(By.cssSelector("input[id*='SeniorCitizenDiscount']")).click();
		check=driver.findElement(By.cssSelector("input[id*='SeniorCitizenDiscount']")).isSelected();
		Assert.assertEquals(check, true);	// or Assert.assertTrue(check);

		// count no of discount check box
		int noOfCheckbox=driver.findElements(By.cssSelector("#discount-checkbox input[type='checkbox']")).size();
		System.out.println(noOfCheckbox);
		
		
		//check one-way trip --> return date interactive or not. check two-way trip --> return date interactive or not
		driver.findElement(By.cssSelector("input[value='OneWay']")).click();
		if(driver.findElement(By.id("Div1")).getDomAttribute("style").contains("0.5"))
		{
			System.out.println("it's not enabled");
			Assert.assertTrue(true);
		}
		else
		{
			System.out.println("it's enabled");
			Assert.assertTrue(false);
		}

		driver.findElement(By.cssSelector("input[value='RoundTrip']")).click();
		
		if(driver.findElement(By.id("Div1")).getDomAttribute("style").contains("1"))
		{
			System.out.println("it's enabled");
			Assert.assertTrue(true);
		}
		else
		{
			System.out.println("it's not enabled");
			Assert.assertTrue(false);
		}
		
		///click search
		driver.findElement(By.id("ctl00_mainContent_btn_FindFlights")).click();

	}

}
