package webdriver;

//import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
//import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

//import io.github.bonigarcia.wdn.WebDriverManager;

public class Topic_10_Custom_Button_Radioutton_Checkbox {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	JavascriptExecutor jsExecutor;
	
	
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver.exe");

		//WebDriverManager.firefoxdriver().setup();
		
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		jsExecutor = (JavascriptExecutor) driver;
		
	}

	// hàm selected chỉ dùng được với thẻ input bị ẩn thì click k đc( bị che bởi thẻ div/span/lable và không có kích thước)
	
	@Test
	public void TC_01_() {
		// Case 1: chạy lỗi
		// Thẻ input bị ẩn/ bị che bởi các thẻ div ở trên nên k thao tác được
		// chọn thẻ input
		// dùng thẻ input để Verify vì hàm Selected chỉ thao tác được với thẻ input
		// > case này chạy bị lỗi vì dùng thẻ input bị ẩn để click
		driver.get("");
		// click thẻ input bị ẩn
		driver.findElement(By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div/input")).click();
		sleepInsecond(3);	
		// verify đã chọn radio button
		Assert.assertTrue(driver.findElement(By.xpath("")).isSelected());
		
	}
	@Test
	public void TC_02_() {
		// cASE 02: chạy lỗi
		// THẺ khác input dùng để click(dùng thẻ dic trên input)
		// dùng chính thẻ này để verify (div)
		driver.get("");
		driver.findElement(By.xpath("")).click();
		Assert.assertTrue(driver.findElement(By.xpath("")).isSelected());
		
		
	}

	@Test
    public void TC01_() {
        // Case 1: Case chạy lỗi
        // Thẻ input bị ẩn, bị che bởi các thẻ div ở trên nên không thao tác được
        // Chọn thẻ input
        // Dùng thẻ input để verify vì hàm selected chỉ thao tác với thẻ input
        // => Case này chạy bị lỗi 100% vì dùng thẻ input bị ẩn
        driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
        // Click thẻ input bị ẩn
        driver.findElement(By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div/input")).click();
        sleepInsecond(3);
        // Verify đã chọn radio button
        Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div/input")).isSelected());
    }

    @Test
    public void TC02_() {
        // Case 02:Case chạy lỗi
        // Thẻ khác input dùng để click (dùng thẻ div trên thẻ input)
        // dùng chính thẻ này để verify (div)
        driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
        driver.findElement(By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div[@class='mat-radio-container']")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div[@class='mat-radio-container']")).isSelected());
    }

    @Test
    public void TC03_() {
        // Case 03: case chạy đúng
        // Thẻ khác input dùng để click (dùng thẻ div trên thẻ input)
        // Dùng thẻ input để verify (Vì hàm selected dùng được với thẻ input)
        driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
        driver.findElement(By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div[@class='mat-radio-container']")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div/input")).isSelected());
        // Trường hợp này nếu demo d án cho sếp để show kiến thức thì rất ok
        // Nhưng nếu apply dự án thì không nên, vì 1 element phải define tới nhiều locator nên sẽ mất thời gian bảo trì
        // => Hướng nên dùng JS
    }

    @Test
    public void TC04_() {
        // Case 03: case chạy đúng
        // thẻ input vẫn bị ẩn nhưng mình vẫn dùng để click
        // Hàm click của element seledium sẽ không tháo tác ới element bị ẩn được
        // Nên s dùng hàm click của javascript để thao tác vì nó vẫn click được
        // Seledium sẽ cung cấp 1 thư viện để có thể nhúng code javaScript vào test được

        // thao tác click chọn
        driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
        By radioButton = By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div/input");

        jsExecutor.executeScript("arguments[0].click();", driver.findElement(radioButton));

        // Verify
        Assert.assertTrue(driver.findElement(radioButton).isSelected());
    	
    
    
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
