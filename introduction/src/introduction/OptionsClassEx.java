package introduction;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class OptionsClassEx {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Proxy proxy=new Proxy();
		proxy.setHttpProxy("");
		
		//ChromeOptions class --> allows to configure and customize chrome browser 
		ChromeOptions options=new ChromeOptions();
		
		// accept insecure certificates
		options.setAcceptInsecureCerts(true);
		
		// add browser extension to automated browser. Only .crx files are supported. 
		//options.addExtensions(new File(""));
		
		// Add arguments
        options.addArguments("--start-maximized");	// Start browser maximized
        options.addArguments("--disable-notifications");	// Disable pop-up notifications
        options.addArguments("--incognito");	// Open in incognito mode
        //options.addArguments("--headless");		// run in headless mode (no GUI)
        //options.setProxy(proxy);	//set proxy
		
		WebDriver driver=new ChromeDriver(options);
		
		//driver.manage().window().maximize();
		driver.get("https://expired.badssl.com/");
		System.out.println(driver.getTitle());
		
		//set automatic download directory
        String downloadFilepath = "C:/Downloads/TestFiles";	// Path for automatic downloads

        Map<String, Object> prefs = new HashMap<>();	// Chrome preferences
        prefs.put("download.default_directory", downloadFilepath); // Set default download folder
        
        options.setExperimentalOption("prefs", prefs);
        
        driver.manage().deleteAllCookies();
        driver.manage().deleteCookieNamed("cookieName");
		

	}

}
