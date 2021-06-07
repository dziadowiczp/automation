package TestelkaFakestoreTests.Zadanie15;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Zadanie15 {

    WebDriver driver;
    String fakestoreUrl = "https://fakestore.testelka.pl/moje-konto/";


    @BeforeEach
    public void driverSetup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.navigate().to(fakestoreUrl);
    }

    @AfterEach
    public void driverQuit(){
        driver.close();
        driver.quit();
    }

    @Test
    public void nazwaKlasy() {
        driver.findElement(By.cssSelector("[placeholder='Szukaj produktów…']"));
        driver.findElement(By.id("username"));
        driver.findElement(By.id("password"));
        driver.findElement(By.name("login"));
        driver.findElement(By.name("rememberme"));
        driver.findElement(By.linkText("Nie pamiętasz hasła?"));
        driver.findElement(By.linkText("Żeglarstwo"));
    }
}
