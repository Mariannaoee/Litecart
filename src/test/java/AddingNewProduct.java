import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
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
        driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @Test
    public void Login() throws InterruptedException {
        loginSite();
        initGeneralPage();
        initInformationPage();
        initPricePage();
    }

    private void loginSite() throws InterruptedException {
        driver.get("http://localhost/litecart/public_html/admin/");
        driver.findElement(By.name("username")).sendKeys("admin"); //окно ввода
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click(); // кнопка

        wait.until(titleContains("My Store"));
        driver.findElement(By.linkText("Catalog")).click();
        wait.until(titleContains("Catalog"));
        driver.findElement(By.linkText("Add New Product")).click();
    }

    public void initGeneralPage() throws InterruptedException {
        //General Page
        wait.until(titleContains("Add New Product"));
        Thread.sleep(2000);
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
        // Create object of SimpleDateFormat class and decide the format
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        //get current date time with Date()
        Date date = new Date();
        // Now format the date
        String date1 = dateFormat.format(date);
        element.sendKeys(date1);
        WebElement element2 = driver.findElement(By.name("date_valid_to"));
        element2.sendKeys("12312020");
    }

    //Information page
    public void initInformationPage() throws InterruptedException {
        driver.findElement(By.linkText("Information")).click();
        Thread.sleep(1000);
        Select manufacturer = new Select(driver.findElement(By.name("manufacturer_id")));
        manufacturer.selectByVisibleText("ACME Corp.");
        Select supplier = new Select(driver.findElement(By.name("supplier_id")));
        supplier.selectByVisibleText("-- Select --");

        sendNameAndKeys("keywords", "I love ducks");
        sendNameAndKeys("short_description[en]", "I want to buy a duck");
        sendNameAndKeys("description[en]", "Hello world!!!");
        sendNameAndKeys("head_title[en]", "Duck world!!!");
        sendNameAndKeys("meta_description[en]", "Lovely world!!!");
    }

    //Prices page
    public void initPricePage() throws InterruptedException {

        driver.findElement(By.linkText("Prices")).click();
        Thread.sleep(1000);
        driver.findElement(By.name("purchase_price")).sendKeys("1");
        Select price = new Select(driver.findElement(By.name("purchase_price_currency_code")));
        price.selectByVisibleText("US Dollars");
        sendNameAndKeys("prices[USD]", "55");
        sendNameAndKeys("prices[EUR]", "50");
        driver.findElement(By.name("save")).submit();


    }

    private void sendNameAndKeys(String name, String keys) {
        driver.findElement(By.name(name)).sendKeys(keys);
    }


}



