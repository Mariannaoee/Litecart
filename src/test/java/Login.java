import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class Login {
    private WebDriver driver;


    @Before
    public void start() {

        driver = new ChromeDriver(); //инициализация драйвера (вызов конструктора)

    }
    @Test
    public void Login() {
        driver.get("http://localhost/litecart/public_html/admin/");// команда get открытия страницы
        driver.findElement(By.name("username")).sendKeys("admin");//окно ввода
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click(); // кнопка

    }
    @After
    public void stop() {
        driver.quit();//команда quit останавливает драйвер и браузер
        driver = null;
    }
}
