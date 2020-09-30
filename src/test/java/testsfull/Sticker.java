package testsfull;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleContains;


public class Sticker {
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

        List <WebElement> listDucks = driver.findElements(By.className("product"));//список товаров
        for (int i = 0; i < listDucks.size(); i++) {
            WebElement duckElement = listDucks.get(i);//получаем количество товаров

            List<WebElement> stickers = duckElement.findElements(By.className("sticker"));//список стикеров
            Assert.assertTrue(stickers.size() == 1);//проверяем что стикер только один для каждой одной утки

        }
    }

    @Test
    public void TestMarianna() throws InterruptedException {
        int [] mariaannaNumbers = {0,1,7,5,9};
       for( int i=0 ;i<mariaannaNumbers.length;i++){
           int mariannaNumber = mariaannaNumbers[i];
           System.out.println("mariannna number is : " + mariannaNumber);
       }
    }




    @After
    public void stop() {
        driver.quit();//команда quit останавливает драйвер и браузер
        driver = null;
    }
        }

