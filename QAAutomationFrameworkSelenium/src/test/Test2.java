package test;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Test2 {
	
	@Test//(groups = "Smoke")
	public void personalLoan() {
		System.out.println("loan");
	}

	@BeforeTest
	public void preTestModule() {
		System.out.println("before test");
	}
	
	@AfterMethod
	public void postMethod() {
		System.out.println("after method");
	}
	
}
