import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleContains;


public class Login {
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
        driver.get("http://localhost/litecart/public_html/admin/");// команда get открытия страницы
        driver.findElement(By.name("username")).sendKeys("admin");//окно ввода
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click(); // кнопка
        wait.until(titleContains("My Store"));

        List<WebElement> listSize = driver.findElements(By.id("app-"));//находим количество li

        for (int i = 0; i < listSize.size(); i++) { //список всех пунктов в меню слева

            List<WebElement> list = driver.findElements(By.id("app-"));//заново находим,тк страница обновляется
            WebElement liElem = list.get(i);
            liElem.click();


            List<WebElement> insideListSize = driver.findElements(By.xpath("//*[contains(@id, 'doc-')]"));

            for (int j = 0; j <insideListSize.size() ; j++) { // вложенные пункты

                List<WebElement> insideList = driver.findElements(By.xpath("//*[contains(@id, 'doc-')]"));//заново находим,тк страница обновляется
                WebElement liInside = insideList.get(j); //запускаем
                liInside.click();//нажимаем
                Assert.assertTrue(driver.findElement(By.tagName("h1")).isDisplayed());//проверяем наличие h1
            }

        }
    }

    @After
    public void stop() {
        driver.quit();//команда quit останавливает драйвер и браузер
        driver = null;
    }
}
