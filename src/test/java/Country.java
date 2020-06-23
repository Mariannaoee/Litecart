import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.StringWriter;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleContains;

public class Country {
    private WebDriver driver;
    private WebDriverWait wait;
    private WebElement link;


    @Before
    public void start() {

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, Duration.ofSeconds(4));
    }

    @Test
    public void Login() throws InterruptedException {
        driver.get("http://localhost/litecart/public_html/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");//окно ввода
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click(); // кнопка
        wait.until(titleContains("My Store"));
        driver.get("http://localhost/litecart/public_html/admin/?app=countries&doc=countries");


        List<WebElement> sizeOfCountriesList = driver.findElements(By.className("row"));
        String previous = "";
        for (int i = 0; i < sizeOfCountriesList.size(); i++) {
            int newI = i + 2;
            String currentCountry = driver.findElement(
                    By.xpath("//table/tbody/tr[" + newI + "]/td[5]")).getAttribute("textContent");
            System.out.println("the country is : " + currentCountry);




        }
    }


//    @Test
//    public void MariannaTest() throws InterruptedException {
//        List<String> countryList = Arrays.asList("Afghanistan", "Azerbajan", "Algeria");
//         String previous = "";
//        boolean isAlphabetical = true;
//        for (int i = 0; i < countryList.size(); i++) {
//            String currentCountry = countryList.get(i);
//
//            // function that compare two Strings alphabetically if currentCountry is lower than previous then he return -1
//            // if higher he return 1 if equal return 0
//            System.out.println(" previous country is : " + previous);
//            if (currentCountry.compareTo(previous) < 0) {
//                isAlphabetical = false;
//            }
//            previous = currentCountry;
//            System.out.println(" previous country is : " + previous);
//
//        }
//        Assert.assertTrue(isAlphabetical);
//    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}