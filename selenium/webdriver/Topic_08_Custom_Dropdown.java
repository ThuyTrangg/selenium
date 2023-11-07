
package webdriver;

//import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.List;

public class Topic_08_Custom_Dropdown {
    static WebDriver driver;
    static String projectPath = System.getProperty("user.dir");
    static WebDriverWait explicitWait;


    @BeforeClass
    public static void beforeClass() {
        System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver.exe");
//
//        //WebDriverManager.firefoxdriver().setup();

        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        explicitWait = new WebDriverWait(driver, 30);

    }

    @Test
    public void TC_01_Custom() {
        driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
        // KHÔNG DÙNG HÀM
        selectItemInDropdown("span#speed-button", "ul#speed-menu div[role='option']", "Medium");
        sleepInsecond(3);
        // 1. Click vào dropdown để sổ thông tin item
        driver.findElement(By.cssSelector("span#speed-button")).click();
        // 2. Chờ tất cả item load thành công - Phải lấy loactor đại diện cho tất cả các item -> Sau đó lưu list
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("ul#speed-menu div[role='option']")));
        List<WebElement> speedDropdownItems = driver.findElements(By.cssSelector("ul#speed-menu div[role='option']"));

        // 3. Tìm item xem đúng cái đang tìm hay không
        // 3.1 Nếu nó nằm trong khoảng user nhìn thấy thì không cần sroll xuống
        // 3.2 Ngược lại thì sroll xuống
        // 4. Kiểm tra item, nếu đúng thì click nó
        for (WebElement tempItem : speedDropdownItems) {
            String itemText = tempItem.getText();
            if (itemText.equals("Medium")) {
                tempItem.click();
            }
        }
        Assert.assertEquals("Medium", driver.findElement(By.cssSelector("span#speed-button>span.ui-selectmenu-text")).getText(),"Medium");
    }

    public void selectItemInDropdown(String parentCss, String allItemCss, String expectedItem) {
        driver.findElement(By.cssSelector(parentCss)).click();
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(allItemCss)));
        List<WebElement> speedDropdownItems = driver.findElements(By.cssSelector(allItemCss));
        for (WebElement tempItem : speedDropdownItems) {
            String itemText = tempItem.getText();
            if (itemText.equals(expectedItem)) {
                tempItem.click();
                break;
            }
        }
    }

    @Test
    public void TC_04_Edit() {
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
        enterAndSelectItemInDropdown("input.search", "span.text", "Anguilla", "ang");
        Assert.assertEquals("Anguilla", driver.findElement(By.cssSelector("div.divider.text")).getText(),"Anguilla");
    }

    public void enterAndSelectItemInDropdown(String textBoxCss, String allItemCss, String expectedItem, String dataTextBox) {
        driver.findElement(By.cssSelector(textBoxCss)).sendKeys(dataTextBox);
        sleepInsecond(2);
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(allItemCss)));
        List<WebElement> speedDropdownItems = driver.findElements(By.cssSelector(allItemCss));
        for (WebElement tempItem : speedDropdownItems) {
            String itemText = tempItem.getText();
            if (itemText.trim().equals(expectedItem)) {
                tempItem.click();
                break;
            }
        }
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
