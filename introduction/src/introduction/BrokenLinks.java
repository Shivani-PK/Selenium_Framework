package introduction;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.apache.commons.io.FileSystemUtils;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class BrokenLinks {

	public static void main(String[] args) throws   URISyntaxException, MalformedURLException, IOException {
		// TODO Auto-generated method stub

		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/AutomationPractice/#");
		
		List<WebElement> links= driver.findElements(By.cssSelector(".gf-li a"));
		SoftAssert a=new SoftAssert();
		
		
		for(WebElement link:links)
		{
			String url=link.getAttribute("href");
			HttpURLConnection conn= (HttpURLConnection)new URI(url).toURL().openConnection();
			
			conn.setRequestMethod("HEAD");
			conn.connect();
			int respCpde=conn.getResponseCode();
			System.out.println(respCpde);

			a.assertTrue(respCpde<400,"the link with text "+link.getText()+" is broken with code "+respCpde);
			
			File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			
			FileUtils.copyFile(src,new File("E:\\Learning\\Selenium\\New folder/screenshot.png"));

		}
		
		
		//broken URL
		//step 1 --> get all URLs tied up to link
		//if status code> 400 --> url not working

		a.assertAll();

		
		
	}

}
