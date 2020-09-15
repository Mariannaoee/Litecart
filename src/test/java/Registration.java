import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;


public class Registration {
    final String randomEmail = randomEmail();
    private WebDriver driver;
    private WebDriverWait wait;
    private WebElement link;
    private Thread time;


    @Before
    public void start() {

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);//implicit wait -  неявное ожидание
    }

    @Test
    public void Login() throws InterruptedException {

        driver.get("http://localhost/litecart/public_html/en/");
        driver.findElement(By.linkText("New customers click here")).click();

        // new customer registration
//        WebDriverWait wait = new WebDriverWait(driver,70);
//        wait.until(ExpectedConditions.elementToBeClickable(By.name("firstname")));
        driver.findElement(By.name("firstname")).sendKeys("Mariannaoe");
        driver.findElement(By.name("lastname")).sendKeys("Estrinaoe");
        driver.findElement(By.name("address1")).sendKeys("Azanoe");
        driver.findElement(By.name("postcode")).sendKeys("32355");
        driver.findElement(By.name("city")).sendKeys("Holonoe");
        Select country = new Select(driver.findElement(By.name("country_code")));
        country.selectByVisibleText("United States");

        WebElement email = driver.findElement(By.name("email"));
        email.sendKeys(randomEmail);

        driver.findElement(By.name("phone")).sendKeys("05232345");
        driver.findElement(By.name("password")).sendKeys("45678567");
        driver.findElement(By.name("confirmed_password")).sendKeys("45678567");
        driver.findElement(By.name("create_account")).click();

        WebDriverWait wait2 = new WebDriverWait(driver,90);
        wait2.until(ExpectedConditions.visibilityOfElementLocated(By.name("zone_code")));
        Select zone = new Select(driver.findElement(By.name("zone_code")));
        zone.selectByVisibleText("Florida");

        driver.findElement(By.name("password")).sendKeys("45678567");
        driver.findElement(By.name("confirmed_password")).sendKeys("45678567");
        driver.findElement(By.name("create_account")).click();

        // logout
        driver.findElement(By.linkText("Logout")).click();

        //login
        driver.findElement(By.name("email")).sendKeys(randomEmail);
        driver.findElement(By.name("password")).sendKeys("45678567");
        driver.findElement(By.name("login")).click();

        // logout
        driver.findElement(By.linkText("Logout")).click();
    }



    private static String randomEmail() {
        return "random-" + UUID.randomUUID().toString() + "@example.com";
    }


    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}