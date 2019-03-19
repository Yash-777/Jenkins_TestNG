package in.jenkins.test;

import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FireFox_JUnit {
	static WebDriver driver;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		driver = new FirefoxDriver();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		driver.quit();
	}

	@Test
	public void test() {
		fail("Not yet implemented");
		driver.get("http://www.w3schools.com/");
		System.out.format("Application loaded with title: %s \n", driver.getTitle());
		
		Utils.getUserAction();
	}
}
