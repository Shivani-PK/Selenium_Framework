package test;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;

public class Test4 {
	
	@Test
	public void webLoginHomeLoan() {
		//selenium
		System.out.println("Web Login Home");
	}
	
	@Test
	public void mobileLoginHomeLoan() {
		//appium
		System.out.println("Mobile Login Home");
	}
	
	@Test//(groups = "Smoke")
	public void apiLoginHomeLoan() {
		//postman
		System.out.println("API Login Home");
	}
	
	@AfterSuite
	public void postSuite() {
		System.out.println("after suite");
	}

}
