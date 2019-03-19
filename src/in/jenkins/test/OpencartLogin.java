package in.jenkins.test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * https://demo.opencart.com/index.php?route=product/product&path=25_28&product_id=42
 * 
 * https://demo.opencart.com/index.php?route=product/product&product_id=41
 * 
 * HTTP Status 403 - Access to the requested resource has been denied | forbidden
	Wrong Credentials. The user credentials are wrong!

 * @author yashwanth.m
 *
 */
public class OpencartLogin {
	public WebDriver driver;

	String appURL = "http://demo.opencart.com/index.php?route=account/login";
	
	String userName = "//*[@id='input-email']";
	String userKey = "yashwanth.merugu@gmail.com";
	
	String password = "//*[@id='input-password']";
	String secretKey = "MySecretPassword";
	
	String login = "//*[@id='content']/div/div[2]/div/form/input";
	
	String verify = "/html/body/div[2]/div[1]";
	String verifyText = 
			//"Warning: No match for E-Mail Address and/or Password.";
			"Warning: Your account has exceeded allowed number of login attempts. Please try again in 1 hour.";
	
	public static void main(String[] args) {
		try {
			OpencartLogin test = new OpencartLogin();

			DesiredCapabilities caps = DesiredCapabilities.firefox();
			caps.setCapability("platform","win7");
			caps.setCapability("version","46");
			caps.setCapability("secnarioname","KT2_Chrome777"); //Yash_Test_Opencart_7");
			
			WebDriver driver = new FirefoxDriver();
			
			test.loginTest( driver );
			test.quitDriver();
		} catch (org.openqa.selenium.UnsupportedCommandException e) {
			System.err.println("HTTP Status 403 - Access to the requested resource has been denied | forbidden");
			System.err.println("Wrong Credentials. "+e.getMessage());
		}
	}
	
	public void loginTest( WebDriver driver ) {
		try {
			this.driver = driver;
			
			driver.get( appURL );
			
			/* Put an Implicit wait, this means that any search for elements on the page could take 
			the time the implicit wait is set for before throwing exception.
			http://toolsqa.com/selenium-webdriver/switch-commands/ */
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			
			// wait up to 10 seconds for the Codes detail page to load
			(new WebDriverWait(driver, 10)).until( new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver driver) {
					String title = driver.getTitle();
					System.out.println("Application Title : "+title);
					return title.equals("Account Login");
				}
			});
			
			WebElement user = driver.findElement(By.xpath( userName ));
			user.sendKeys(userKey);
			
			WebElement secret = driver.findElement(By.xpath( password ));
			secret.sendKeys(secretKey);
			
			WebElement loginbutton = driver.findElement(By.xpath( login ));
			loginbutton.click();
			
			WebElement data = driver.findElement(By.xpath( verify ));
			String text = data.getText();
			System.out.println("Text My Order ["+text+"]");
			
			if( text.equalsIgnoreCase( verifyText ) ) {
				drawBorder(verify, "green");
			} else {
				drawBorder(verify, "red");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void quitDriver() {
		driver.close();
		driver.quit();
	}
	/** 
	 * To highlight an element, it takes XPath and draw border around it.
	 * 
	 * @param xpath
	 * @param color the color around 
	 */
	public void drawBorder(String xpath, String color){
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		WebElement element_node = driver.findElement(By.xpath(xpath));
		String elementStyle = "arguments[0].style.border='3px solid "+color+"'";
		if (driver.getClass().getName().contains("ie") || driver.getClass().getName().contains("safari")) {
			jse.executeScript(elementStyle, element_node);
		} else {
			try {
				jse.executeScript(
				"if (document.evaluate){"
					+ "var element_node = document.evaluate('"+xpath+"', window.document, null, 9, null ).singleNodeValue;"
					+ "element_node.style.setProperty ('border', '3px solid "+color+"', 'important');"
				+"}"
					);
			} catch (Exception draw) {
				jse.executeScript(elementStyle, element_node);
			}
		}
	}
	
	public void sleepThread(long millis) {
		try {
			Thread.sleep( millis );
		} catch (InterruptedException e) {
			System.out.println("Sleep Exception:"+ e.getMessage());
		}
	}
	/**
	 * This method is to check the loading state of the document.
	 * And it will wait until document get loaded completely.
	 * 
	 * <OL> <P><B>loading states are:</B>
	 * <LI> <B>loading</B> « The document is still loading.
	 * <LI> <B>interactive</B> « The document has finished loading and the document has been 
	 * parsed but sub-resources such as images, style-sheets and frames are still loading.
	 * <LI> <B>complete</B> « The document and all sub-resources have finished loading. 
	 * The state indicates that the load event is about to fire.</br>
	 * </OL>
	 * @param jse the Java Script executor object which has reference to current window.
	 * @return return the state as `complete`.
	 * 
	 * @see <a href="https://developer.mozilla.org/en/docs/Web/API/Document/readyState">Document.readyState</a>
	 */
	public synchronized String checkDocumentLoaded( JavascriptExecutor jse ) {
		try {
			wait(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String loadingStatus = (String) jse.executeScript("return window.document.readyState");
		System.out.println("status2 >> " + loadingStatus);
		if (loadingStatus.equalsIgnoreCase("complete"))
			return loadingStatus;
		else
			checkDocumentLoaded(jse);
		return loadingStatus;
	}
}