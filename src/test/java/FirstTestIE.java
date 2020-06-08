import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.sql.Driver;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleContains;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;


public class FirstTestIE {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {

        driver = new InternetExplorerDriver();
        //driver firefoxDriver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        //чтобы драйвер дожидался появления элементов на страницк
        wait = new WebDriverWait(driver, Duration.ofSeconds(4));
    }

    @Test
    public void FirstTest() {
        driver.get("http://www.google.com/");// команда get открытия страницы
        //driver.findElement(By.className("hOoLGe")).click();//клавиатура
        driver.findElement(By.name("q")).sendKeys("webdriver");//окно ввода в google
        driver.findElement(By.name("btnK")).click(); // кнопка google search
        wait.until(titleContains("webdriver"));

    }

    @After
    public void stop() {
        driver.quit();//команда quit останавливает драйвер и браузер
        driver = null;
    }

}
