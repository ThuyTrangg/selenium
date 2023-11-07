package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_WebElement {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {

		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver.exe");

		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://www.facebook.com/");
	}

	@Test
	public void TC_01() {
		 //Mở link web
		driver.get("");
		// Đóng trình duyệt đang focus
		driver.close();
		// Đóng tất cả trình duyệt
		driver.quit();
		//Trả về URL hiện tại
		driver.getCurrentUrl();
		// Trả về title page
		driver.getTitle();
		// Trả về source code page
		driver.getPageSource();
		
	}
	@Test
	public void TC_02() {
		 //Mở link web
		driver.get("");
		//xóa giá trị tại element
		driver.findElement(By.xpath("")).clear();
		//click vào 1 element
		driver.findElement(By.xpath("")).click();
		//trả về thuộc tính element
		driver.findElement(By.xpath("")).getAttribute("");
		//trả về vị trí phần tử -> thường dùng test UI
		driver.findElement(By.xpath("")).getLocation();
		//trả về chiều rộng cao của element
		driver.findElement(By.xpath("")).getSize();
		//trả về text của element
		driver.findElement(By.xpath("")).getText();
		//kiểm tra element có hiển thị hay không
		driver.findElement(By.xpath("")).isDisplayed();
		// kiểm tra element có enable hoặc không 
		driver.findElement(By.xpath("")).isEnabled();
		//kiểm tra 1 element có được chọn hay không -> checkbox, radio button, dropdown
		driver.findElement(By.xpath("")).isSelected();
		//input một chuỗi vào element -> textbox, dropdown, text area
		driver.findElement(By.xpath("")).sendKeys("");;
		
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
}
