import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleContains;

public class AddingNewProduct {
    private WebDriver driver;
    private WebDriverWait wait;
    private Object WebElement;
    private org.openqa.selenium.WebElement element;


    @Before
    public void start() {
        driver = new ChromeDriver(); //инициализация драйвера (вызов конструктора)
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, Duration.ofSeconds(4));
    }

    @Test
    public void Login() throws InterruptedException {
        driver.get("http://localhost/litecart/public_html/admin/");
        driver.findElement(By.name("username")).sendKeys("admin"); //окно ввода
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click(); // кнопка
        wait.until(titleContains("My Store"));
        driver.findElement(By.linkText("Catalog")).click();
        wait.until(titleContains("Catalog"));
        driver.findElement(By.linkText("Add New Product")).click();

        //General page
        wait.until(titleContains("Add New Product"));
        Thread.sleep(1000);
        List<WebElement> selectStatus = driver.findElements(By.cssSelector("input[name='status']"));
        // Create a boolean variable which will hold the value (True/False)
        boolean bValue = true;
        // This statement will return True, in case of first Radio button is selected
        bValue = selectStatus.get(0).isSelected();
        if (bValue = true) {
            selectStatus.get(0).click();
        } else {
            selectStatus.get(1).click();
        }

        driver.findElement(By.name("name[en]")).sendKeys("Marianna Duck");
        driver.findElement(By.name("code")).sendKeys("12345");
        driver.findElement(By.cssSelector("input[data-name='Subcategory']")).click();
        Select category = new Select(driver.findElement(By.name("default_category_id")));
        category.selectByVisibleText("Subcategory");
        driver.findElement(By.cssSelector("input[value='1-2']")).click();
        driver.findElement(By.name("quantity")).clear();
        driver.findElement(By.name("quantity")).sendKeys("1");
        Select soldOutStatus = new Select(driver.findElement(By.name("sold_out_status_id")));
        soldOutStatus.selectByVisibleText("Temporary sold out");

        File file = new File("duck.jpg");
        String absolutePath = file.getAbsolutePath();
        System.out.println("AbsolutePath = " + absolutePath);
        Thread.sleep(1000);
        WebElement chooseFile = driver.findElement(By.name("new_images[]"));
        chooseFile.sendKeys(absolutePath);

        WebElement element = driver.findElement(By.name("date_valid_from"));
        Dimension size = element.getSize();
        WebElement body = driver.findElement(By.id("body"));
        System.out.println(element.getSize());
        new Actions(driver).moveToElement(element).moveByOffset(size.width, size.height / 2)
                .click().perform();

//        driver.switchTo().frame(
//                driver.findElement(By.cssSelector("iframe.demo-frame")));
        setDatepicker(driver, "#datepicker", "02/20/2020");
    }
        public void setDatepicker(WebDriver driver, String cssSelector, String date) {
        new WebDriverWait(driver, 30000).until(
                (WebDriver d) -> d.findElement(By.cssSelector(cssSelector)).isDisplayed());
        JavascriptExecutor.class.cast(driver).executeScript(
                String.format("$('%s').datepicker('setDate', '%s')", cssSelector, date));
    }



}




