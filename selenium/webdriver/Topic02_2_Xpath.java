package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic02_2_Xpath {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {

		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver.exe");

		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://demo.nopcommerce.com");
	}

	@Test
	public void TC_01_Url() {
		driver.get("https://demo.nopcommerce.com");
		driver.findElement(By.xpath("//a[text()='Register']")).click();
		driver.findElement(By.id("FirstName")).sendKeys("AAAA");
		driver.findElement(By.id("LastName")).sendKeys("BBBB");
		driver.findElement(By.id("Email")).sendKeys("AAAA@gmail.com");
		driver.findElement(By.id("Password")).sendKeys("123456");
		driver.findElement(By.id("ConfirmPassword")).sendKeys("123456");
		driver.findElement(By.xpath("//div[@class='buttons']/button[@id='register-button']")).click();
	}

	

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
}
