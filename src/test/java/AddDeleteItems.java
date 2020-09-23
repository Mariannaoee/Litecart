//[x] Задание 13. Сделайте сценарий работы с корзиной
//        Сделайте сценарий для добавления товаров в корзину и удаления товаров из корзины.
//        1) открыть главную страницу
//        2) открыть первый товар из списка
//        2) добавить его в корзину (при этом может случайно добавиться товар, который там уже есть, ничего страшного)
//        3) подождать, пока счётчик товаров в корзине обновится
//        4) вернуться на главную страницу, повторить предыдущие шаги ещё два раза, чтобы в общей сложности в корзине было 3 единицы товара
//        5) открыть корзину (в правом верхнем углу кликнуть по ссылке Checkout)
//        6) удалить все товары из корзины один за другим, после каждого удаления подождать, пока внизу обновится таблица

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.NoSuchElementException;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class AddDeleteItems {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver(); //инициализация драйвера (вызов конструктора)
        driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @Test
    public void InitBasket() throws InterruptedException {
        login();
        selectItem();
        isSizePresent();
        basketIsUpdated();
        checkout();
    }

    public void login() throws InterruptedException {
        driver.get("https://litecart.stqa.ru/en/");// команда get открытия страницы
        wait.until(titleContains("My Store"));
    }

    public void selectItem() throws InterruptedException {
        driver.findElement(By.className("product")).click();
        WebDriverWait wait = new WebDriverWait(driver, 10/*seconds*/);
        WebElement element = driver.findElement(By.tagName("h1"));
        wait.until(visibilityOf(element));
    }

    public boolean isSizePresent() {
        try {
            Select size = new Select(driver.findElement(By.name("options[Size]")));
            size.selectByVisibleText("Medium +$2.50");
            driver.findElement(By.name("add_cart_product")).click();
            return true;
        } catch (NoSuchElementException e) {
            driver.findElement(By.name("add_cart_product")).click();
            return true;
        }
    }

    public void basketIsUpdated() throws InterruptedException {
        for (int i = 0; i < 3; i++) {
            login();
            selectItem();
            isSizePresent();
            wait.until(ExpectedConditions.textToBePresentInElement(driver.findElement(By.className("quantity")),
                    "" + (i + 1)));
        }
    }

    public void checkout() {
        driver.findElement(By.linkText("Checkout »")).click();
        for (int i = 0; i < 3; i++) {
            driver.findElement(By.name("remove_cart_item")).click();
            WebElement element = driver.findElement(By.className("quantity"));
            wait.until(ExpectedConditions.stalenessOf(element));
        }
    }


}


