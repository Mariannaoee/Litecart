import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleContains;

public class GetAttribute {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {

        driver = new ChromeDriver(); //инициализация драйвера (вызов конструктора)
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, Duration.ofSeconds(4));
    }
    @Test
    public void Login() throws InterruptedException {
        driver.get("http://localhost/litecart/public_html/en/");// команда get открытия страницы
        wait.until(titleContains("My Store"));
        driver.findElement(By.cssSelector("button[name='login']"));

        WebElement search = driver.findElement(By.cssSelector("input[name='query']"));//value - содержимое полей ввода
        search.sendKeys("marusya");
        String first = search.getAttribute("value");
        System.out.println("value is " + first);

        WebElement link = driver.findElement(By.cssSelector("#breadcrumbs a"));// href,src - получение ссылок, картинок
        String href = link.getAttribute("href");
        System.out.println("link is " + href);

//        driver.findElement(By.className("fancybox-region")).click();//true and null
//        driver.findElement(By.name("currency_code")).click();
//        WebElement currency = driver.findElement(By.cssSelector("option[selected = 'selected']"));
//        String selected = currency.getAttribute("selected");
//        System.out.println("currency is " + selected);

        driver.findElement(By.className("fancybox-region")).click();//true and null
        Select country = new Select(driver.findElement(By.name("zone_code")));
        country.selectByVisibleText("Florida");

        WebElement title = driver.findElement(By.className("title"));//getCssValue
        String color = link.getCssValue("border-color");
        System.out.println("title " + color);
    }

}
