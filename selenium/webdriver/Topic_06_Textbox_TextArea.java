package webdriver;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

//import io.github.bonigarcia.wdn.WebDriverManager;

public class Topic_06_Textbox_TextArea {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	Random rand= new Random();
	String employeeID = String.valueOf(rand.nextInt(99999));
	String passportNumber = "40517-405-96-7203";
	String commentInput = "This is generated  data\n of real people";
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver.exe");

		//WebDriverManager.firefoxdriver().setup();
		
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Register_with_empty_data() {
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		sleepInsecond(5);
		
		driver.findElement(By.name("username")).sendKeys("Admin");
		driver.findElement(By.name("password")).sendKeys("admin123");
		
		driver.findElement(By.cssSelector("button.orangehrm-login-button")).click();
		sleepInsecond(2);
		
		driver.findElement(By.xpath("//span[text()=\"PIM\"]")).click();
		sleepInsecond(5);
		driver.findElement(By.xpath("//a[text()=\"Add Employee\"]")).click();
		sleepInsecond(4);
		
		driver.findElement(By.name("firstName")).sendKeys("AAAA");
		driver.findElement(By.name("lastName")).sendKeys("BBBB");
		
		WebElement employeeIDTextbox = driver.findElement(By.xpath("//label[text()=\"Employee Id\"]/parent::div//following-sibling::div/input"));
		//employeeIDTextbox.sendKeys("employeeID");
		sleepInsecond(2);
		employeeIDTextbox.sendKeys(Keys.chord(Keys.CONTROL,'a'));
		sleepInsecond(2);
		employeeIDTextbox.sendKeys(Keys.DELETE);
		
		
		driver.findElement(By.xpath("//p[text()='Create Login Details']/parent::div/div//span")).click();
		sleepInsecond(2);
		
		driver.findElement(By.xpath("//label[text()='Username']/parent::div/following-sibling::div/input")).sendKeys("AAAA"+employeeID);
		driver.findElement(By.xpath("//label[text()='Password']/parent::div/following-sibling::div/input")).sendKeys("AAAA123!!!");
		driver.findElement(By.xpath("//label[text()='Confirm Password']/parent::div/following-sibling::div/input")).sendKeys("AAAA123!!!");
		
		driver.findElement(By.xpath("//button[@class='oxd-button oxd-button--medium oxd-button--secondary orangehrm-left-space']")).click();
		sleepInsecond(8);
		
		Assert.assertEquals(driver.findElement(By.name("firstName")).getAttribute("value"), "AAAA");
		Assert.assertEquals(driver.findElement(By.name("Last Name")).getAttribute("value"), "BBBB ");
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()=\\\"Employee Id\\\"]/parent::div//following-sibling::div/input")).getAttribute("value"), employeeID);
		
		driver.findElement(By.xpath("//a[text()='Immigration']")).click();
		sleepInsecond(5);
		
		driver.findElement(By.xpath("//h6[text()='Assigned Immigration Records']/following-sibling::button")).click();
		
		driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input")).sendKeys("passportNumber");
		driver.findElement(By.xpath("//label[text()='Comments']/parent::div/following-sibling::div/input")).sendKeys("commentInput");
		
		driver.findElement(By.xpath("//button[contains(string(),'Save')]")).click();
		sleepInsecond(5);
		
		driver.findElement(By.cssSelector("i.bi-pencil-fill")).click();
		sleepInsecond(5);
		
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input")).getAttribute("value"), passportNumber);
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Comments']/parent::div/following-sibling::div/input")).getAttribute("value"), commentInput);
		
		driver.findElement(By.cssSelector("p.oxd-userdropdown-name")).click();
		driver.findElement(By.cssSelector("//a[text()='Logout']")).click();
		sleepInsecond(3);
		
		driver.findElement(By.name("username")).sendKeys("AAAA"+employeeID);
		driver.findElement(By.name("password")).sendKeys("AAAA123!!!");
		
		
		
		
	}

	//@Test
	public void TC_02_Register_Email_Invalid() {
		
		
	}
	//@Test
	public void TC_03_Register_Password_Error() {
		
	}

	
	
		public void TC_05_Register_Incorrect_Confirm_Password() {
			
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
