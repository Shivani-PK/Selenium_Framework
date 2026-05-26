package test;

import static org.testng.Assert.assertTrue;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Test1 {
	
	@Parameters({"URL","username"})
	@Test
	public void demo1(String urlName,String userName)
	{
		System.out.println("hello "+urlName+" "+userName);
	}

	@Test
	public void demo2() {
		Assert.assertTrue(false);
		System.out.println("Bye");
	}
	
	@AfterTest
	public void postTestModule() {
		System.out.println("after test");
	}
	
	@BeforeMethod
	public void preMethod() {
		System.out.println("before method");
	}
}
	