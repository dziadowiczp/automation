package SmallTests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;


public class WikiTests {

    WebDriver driver;
    String webUrl = "https://www.wikipedia.org/";

    @BeforeEach
    public void driverSetup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to(webUrl);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @AfterEach
    public void driverQuit(){
        driver.close();
        driver.quit();
    }

    @Test
    public void plLanguageChoose(){
        driver.findElement(By.cssSelector("button#js-lang-list-button")).click();
        driver.findElement(By.cssSelector(".langlist-large [lang='pl']")).click();

        Assertions.assertEquals("Wikipedia, wolna encyklopedia", driver.getTitle(), "Incorrect page was opened");
    }

}
