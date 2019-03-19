package in.jenkins.test;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * https://mvnrepository.com/artifact/org.testng/testng
 * 
 * @author yashwanth.m
 *
 */
public class FireFox_TestNG {
	WebDriver driver;
	
	@BeforeTest public void beforeTest() {
		// Created the Driver instance here so that the tests uses it
	}
	
	/**
	 * https://www3.ntu.edu.sg/home/ehchua/programming/java/JavaUnitTesting.html#zz-7.4
	 * 
	 * The @Parameters annotation can only used to pass simple type (such as String and int). 
	 * To pass objects, you need to use @DataProvider annotation.
	 * @param value1
	 * @param value2
	 */
	@Test(testName="FireFox")
	@Parameters(value={"value1", "value2", "URL"})
	public void FireFox(@Optional("null") int value1, @Optional("null") int value2, String URL) {
		//String URL = "http://www.w3schools.com/";
		System.out.println("=========================================");
		System.out.println("value1=" + value1 + " value2=" + value2 );
		
	}

	@AfterTest public void afterTest() {
		// Quit the driver
	}
}
