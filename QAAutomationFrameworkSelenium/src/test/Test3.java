package test;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Test3 {
	
	@Parameters({"URL"})
	@Test(enabled = true)
	public void webLoginCarLoan(String urlName) {
		//selenium
		System.out.println("Web Login Car "+ urlName);
	}
	
	
	@Test (groups = "Smoke")
	public void mobileLoginCarLoan() {
		//appium
		System.out.println("Mobile Login Car");
	}
	
	@Test(dependsOnMethods = "mobilePaymentCarLoan")
	public void apiLoginCarLoan() {
		//postman
		System.out.println("API Login Car");
	}
	
	@Test (dataProvider = "getData")
	public void mobilePaymentCarLoan(String username, String password) {
		//postman
		System.out.println("mobile payment Login Car");
		System.out.println("username:"+username + " password:"+ password);
	}
	
	@BeforeSuite
	public void preSuite() {
		System.out.println("before suite");
	}
	
	@BeforeClass
	public void preClass() {
		System.out.println("before class");
	}
	
	@AfterClass
	public void postClass() {
		System.out.println("after class");
	}
	
	@DataProvider
	public Object[][] getData()
	{
		Object[][] data=new Object[3][2];
		
		data[0][0]="userName1";
		data[0][1]="password1";
		data[1][0]="userName2";
		data[1][1]="password2";
		data[2][0]="userName3";
		data[2][1]="password3";
		
		return data;
	}

}
