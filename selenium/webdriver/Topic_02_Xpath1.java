package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_Xpath1 {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {

		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver.exe");

		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	//@Test
	public void TC_01_Register_with_empty_data() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		driver.findElement(By.xpath("//div[@class='field_btn']//button[text()='ĐĂNG KÝ']")).click();
		Assert.assertEquals(driver.findElement(By.id("txtFirstname-error")).getText(),"Vui lòng nhập họ tên");
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtEmail-error']")).getText(),"Vui lòng nhập email");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='field']//label[@id='txtCEmail-error']")).getText(),"Vui lòng nhập lại địa chỉ email");
		Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(),"Vui lòng nhập mật khẩu");
		Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(),"Vui lòng nhập lại mật khẩu");
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(),"Vui lòng nhập số điện thoại.");
	}

	//@Test
	public void TC_02_Register_Email_Invalid() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		driver.findElement(By.id("txtFirstname")).sendKeys("AAAA");
		driver.findElement(By.id("txtEmail")).sendKeys("AAAA");
		driver.findElement(By.id("txtCEmail")).sendKeys("AAAA");
		driver.findElement(By.id("txtPassword")).sendKeys("123456");
		driver.findElement(By.id("txtCPassword")).sendKeys("123456");
		driver.findElement(By.id("txtPhone")).sendKeys("0369276478");
		driver.findElement(By.xpath("//div[@class='field_btn']//button[text()='ĐĂNG KÝ']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtEmail-error']")).getText(),"Vui lòng nhập email hợp lệ");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='field']//label[@id='txtCEmail-error']")).getText(),"Email nhập lại không đúng");
		
	}
	//@Test
	public void TC_03_Register_Password_Error() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		driver.findElement(By.id("txtFirstname")).sendKeys("AAAA");
		driver.findElement(By.id("txtEmail")).sendKeys("AAAA@gmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("AAAA@gmail.com");
		driver.findElement(By.id("txtPassword")).sendKeys("1234");
		driver.findElement(By.id("txtCPassword")).sendKeys("1234");
		driver.findElement(By.id("txtPhone")).sendKeys("0369276478");
		driver.findElement(By.xpath("//div[@class='field_btn']//button[text()='ĐĂNG KÝ']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtPassword-error']")).getText(),"Mật khẩu phải có ít nhất 6 ký tự");
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtCPassword-error']")).getText(),"Mật khẩu phải có ít nhất 6 ký tự");
		
	}
	//@Test
		public void TC_04_Register_Incorrect_Confirm_Email() {
			driver.get("https://alada.vn/tai-khoan/dang-ky.html");
			driver.findElement(By.id("txtFirstname")).sendKeys("AAAA");
			driver.findElement(By.id("txtEmail")).sendKeys("AAAA@gmail.com");
			driver.findElement(By.id("txtCEmail")).sendKeys("AAAA");
			driver.findElement(By.id("txtPassword")).sendKeys("123456");
			driver.findElement(By.id("txtCPassword")).sendKeys("123456");
			driver.findElement(By.id("txtPhone")).sendKeys("0369276478");
			driver.findElement(By.xpath("//div[@class='field_btn']//button[text()='ĐĂNG KÝ']")).click();
			Assert.assertEquals(driver.findElement(By.xpath("//div[@class='field']//label[@id='txtCEmail-error']")).getText(),"Email nhập lại không đúng");
		}
		//@Test
		public void TC_05_Register_Incorrect_Confirm_Password() {
			driver.get("https://alada.vn/tai-khoan/dang-ky.html");
			driver.findElement(By.id("txtFirstname")).sendKeys("AAAA");
			driver.findElement(By.id("txtEmail")).sendKeys("AAAA@gmail.com");
			driver.findElement(By.id("txtCEmail")).sendKeys("AAAA@gmail.com");
			driver.findElement(By.id("txtPassword")).sendKeys("123456");
			driver.findElement(By.id("txtCPassword")).sendKeys("123456789");
			driver.findElement(By.id("txtPhone")).sendKeys("0369276478");
			driver.findElement(By.xpath("//div[@class='field_btn']//button[text()='ĐĂNG KÝ']")).click();
			Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtCPassword-error']")).getText(),"Mật khẩu bạn nhập không khớp");
			
		}
		@Test
		public void TC_06_Register_Invalid_Phone() {
			driver.get("https://alada.vn/tai-khoan/dang-ky.html");
			driver.findElement(By.id("txtFirstname")).sendKeys("AAAA");
			driver.findElement(By.id("txtEmail")).sendKeys("AAAA@gmail.com");
			driver.findElement(By.id("txtCEmail")).sendKeys("AAAA@gmail.com");
			driver.findElement(By.id("txtPassword")).sendKeys("123456");
			driver.findElement(By.id("txtCPassword")).sendKeys("123456");
			driver.findElement(By.id("txtPhone")).sendKeys("036927");
			driver.findElement(By.xpath("//div[@class='field_btn']//button[text()='ĐĂNG KÝ']")).click();
			Assert.assertEquals(driver.findElement(By.xpath("//div[@class='field']//label[@id='txtPhone-error']")).getText(),"Số điện thoại phải từ 10-11 số.");
			
		}
		
		
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
}
