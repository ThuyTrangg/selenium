package webdriver;

//import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;
import java.util.Random;

import org.openqa.selenium.By;
//import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

//import io.github.bonigarcia.wdn.WebDriverManager;

public class Topic_07_Default_Dropdown {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String fristName, lastName, emailAddress, companyName, password, day, month, year;
	
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver.exe");

		//WebDriverManager.firefoxdriver().setup();
		
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		fristName = "AAA";
		lastName = "IT";
		emailAddress = "AAAIT@gmail.com";
		companyName = "AIT";
		password = "123456";
		day = "25";
		month ="January";
		year= "1999";
		
		
	}

	@Test
	public void TC_01_Register_account() {
		driver.get("https://demo.nopcommerce.com/");
		driver.findElement(By.xpath("//a[text()='Register']")).click();
		driver.findElement(By.id("FirstName")).sendKeys("AAAA");
		driver.findElement(By.id("LastName")).sendKeys("IT");
		
		new Select(driver.findElement(By.name("DateOfBirthDay"))).selectByVisibleText(day);
		sleepInsecond(2);
		new Select(driver.findElement(By.name("DateOfBirthMonth"))).selectByVisibleText(month);
		sleepInsecond(2);
		new Select(driver.findElement(By.name("DateOfBirthYear"))).selectByVisibleText(year);
		sleepInsecond(2);
		driver.findElement(By.id("Email")).sendKeys(emailAddress);
		driver.findElement(By.id("Company")).sendKeys(companyName);
		driver.findElement(By.id("Password")).sendKeys(password);
		driver.findElement(By.id("ConfirmPassword")).sendKeys(password);
		
		driver.findElement(By.id("register-button")).click();
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div.result")).getText(), "Your registration completed");
		
		driver.findElement(By.cssSelector("a.register-continue-button")).click();
		driver.findElement(By.cssSelector("a.ico-login")).click();
		
		driver.findElement(By.id("Email")).sendKeys(emailAddress);
		driver.findElement(By.id("Password")).sendKeys(password);
		
		driver.findElement(By.cssSelector("button..login-button")).click();
		driver.findElement(By.cssSelector("a.ico-account")).click();
		
		Assert.assertEquals(driver.findElement(By.id("FirstName")).getAttribute("value"),fristName);
		Assert.assertEquals(driver.findElement(By.id("LastName")).getAttribute("value"),lastName);
		
		Assert.assertEquals(new Select(driver.findElement(By.name("DateOfBirthDay"))).getFirstSelectedOption().getText(),day);
		Assert.assertEquals(new Select(driver.findElement(By.name("DateOfBirthMonth"))).getFirstSelectedOption().getText(),month);
		Assert.assertEquals(new Select(driver.findElement(By.name("DateOfBirthYear"))).getFirstSelectedOption().getText(),year);
		
		Assert.assertEquals(driver.findElement(By.id("Email")).getAttribute("value"),emailAddress);
		Assert.assertEquals(driver.findElement(By.id("Company")).getAttribute("value"),companyName);
		
		
		
	}

	public static int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);
	}
	

		public void sleepInsecond (long timeInSecond) {
			try {
				Thread.sleep(timeInSecond * 1000);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
		}	
		
		
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
}
