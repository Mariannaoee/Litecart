package testsfull;//Задание 11. Сделайте сценарий регистрации пользователя
//        Сделайте сценарий для регистрации нового пользователя в учебном приложении litecart (не в админке, а в клиентской части магазина).
//
//        Сценарий должен состоять из следующих частей:
//
//        1) регистрация новой учётной записи с достаточно уникальным адресом электронной почты (чтобы не конфликтовало с ранее созданными пользователями, в том числе при предыдущих запусках того же самого сценария),
//        2) выход (logout), потому что после успешной регистрации автоматически происходит вход,
//        3) повторный вход в только что созданную учётную запись,
//        4) и ещё раз выход.
//
//        В качестве страны выбирайте United States, штат произвольный. При этом формат индекса -- пять цифр.
//
//        Можно оформить сценарий либо как тест, либо как отдельный исполняемый файл.
//
//        Проверки можно никакие не делать, только действия -- заполнение полей, нажатия на кнопки и ссылки. Если сценарий дошёл до конца, то есть созданный пользователь смог выполнить вход и выход -- значит создание прошло успешно.
//
//        В форме регистрации есть капча, её нужно отключить в админке учебного приложения на вкладке Settings -> Security.


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
        Thread.sleep(1000);
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