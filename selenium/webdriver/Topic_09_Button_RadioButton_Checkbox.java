package webdriver;

//import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.List;

	public class Topic_09_Button_RadioButton_Checkbox {
	    static WebDriver driver;
	    static String projectPath = System.getProperty("user.dir");

	    @BeforeClass
	    public static void beforeClass() {
	        System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver.exe");
	        driver = new FirefoxDriver();
	        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	        driver.manage().window().maximize();
	    }

	    @Test
	    public void TC01_Button() {
	        driver.get("https://www.fahasa.com/customer/account/create");
	        driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();
	        By loginButton = By.cssSelector("button.fhs-btn-login");
	        // Verify button disable
	        Assert.assertFalse(driver.findElement(loginButton).isEnabled());

	        String loginButtonBackground = driver.findElement(loginButton).getCssValue("background-image");
	        Assert.assertTrue(loginButtonBackground.contains("rgb(224, 224, 224)"));
	        // Input data
	        driver.findElement(By.cssSelector("input#login_username")).sendKeys("0987654321");
	        driver.findElement(By.cssSelector("input#login_password")).sendKeys("123456789");
	        Assert.assertTrue(driver.findElement(loginButton).isEnabled());
	        String loginButtonBackgroundColorRbg = driver.findElement(loginButton).getCssValue("background-color");
	        Color loginButtonBackgroundColor = Color.fromString(loginButtonBackgroundColorRbg);
	        Assert.assertEquals(loginButtonBackgroundColor.asHex().toUpperCase(), "#C92127");
	    }

	    @Test
	    public void TC02_Default_CheckBox_Radio() {
	        driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
	        // Click vào
	        driver.findElement(By.xpath("//label[contains(text(), 'Dual-zone air')]/preceding-sibling::input")).click();
	        // Verify
	        Assert.assertTrue(driver.findElement(By.xpath("//label[contains(text(), 'Dual-zone air')]/preceding-sibling::input")).isSelected());
	        // Bỏ chọn
	        driver.findElement(By.xpath("//label[contains(text(), 'Dual-zone air')]/preceding-sibling::input")).click();
	        // Verify
	        Assert.assertFalse(driver.findElement(By.xpath("//label[contains(text(), 'Dual-zone air')]/preceding-sibling::input")).isSelected());

	        // Truy cập trang
	        driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");
	        driver.findElement(By.xpath("//label[contains(text(), '2.0 Petrol, 147kW')]/preceding-sibling::input")).click();
	        // Step số 7 là bài tập cu các bạn tự code, làm xong đẩy code anh review
	    }

//	    @Test
	    public void TC04_Select_all_checkboxes_select_1_in_all() {
	        driver.get("https://leon-kieu.github.io/anhitclub.github.io/multiple-fields");
	        // Khai báo list chưa 29 cái check box
	        List<WebElement> allcheckbox = driver.findElements(By.cssSelector("input.form-checkbox"));
	        for (WebElement checkbox: allcheckbox) {
	            checkbox.click();
	            sleepInsecond(2);
	        }
	        // kiểm tra tất cả check box được chọn
	        for (WebElement checkbox: allcheckbox) {
	            Assert.assertTrue(checkbox.isSelected());
	        }
	        // Từ step số 4 các bạn tự tìm hiu và làm anh review
	    }

	    public void sleepInsecond(long timeInSecond) {
	        try {
	            Thread.sleep(timeInSecond * 1000);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	    }

	    @AfterClass
	    public static void afterClass() {
	        driver.quit();
	    }
	}


