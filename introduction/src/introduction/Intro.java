package introduction;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByLinkText;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;

public class Intro {

	public static void main(String[] args) throws InterruptedException {
		// System.setProperty("webdriver.chrome.driver", "C:\\Program
		// Files\\chromedriver\\chromedriver.exe");
		// firefox launch
		// WebDriver driver = new FirefoxDriver();

		// edge launch
		// WebDriver driver = new EdgeDriver();

		// chrome launch
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		String username="shiv";
		String password="hello123";
		
		driver.get("https://rahulshettyacademy.com/locatorspractice/");
		driver.findElement(By.id("inputUsername")).sendKeys(username);
		driver.findElement(By.name("inputPassword")).sendKeys(password);
		driver.findElement(By.className("signInBtn")).click();
		System.out.println(driver.findElement(By.cssSelector("p.error")).getText());
		
		password=getPassword(driver);
		
		driver.findElement(By.className("go-to-login-btn")).click();
		
		Thread.sleep(1000);
		driver.findElement(By.id("inputUsername")).sendKeys(username);
		driver.findElement(By.name("inputPassword")).sendKeys(password);
		driver.findElement(By.className("signInBtn")).click();

		Thread.sleep(1000);
		Assert.assertEquals(driver.findElement(By.tagName("p")).getText(), "You are successfully logged in.");
		
		driver.findElement(By.xpath("//button[text()='Log Out']")).click();
		
	}
	
	public static String getPassword(WebDriver driver ) throws InterruptedException {
		driver.findElement(By.linkText("Forgot your password?")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[text()=\"Reset Login\"]")).click();
		String password=(driver.findElement(By.cssSelector("form p.infoMsg")).getText());

		String[] finalPassword=password.split("'");
		return finalPassword[1];
	}

}
