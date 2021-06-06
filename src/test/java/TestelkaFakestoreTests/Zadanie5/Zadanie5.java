package TestelkaFakestoreTests.Zadanie5;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class Zadanie5 {

    WebDriver driver;
    String wikiUrl = "https://www.wikipedia.org/";
    String nasaUrl = "https://www.nasa.gov/";

    @BeforeEach
    public void driverSetup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(7, TimeUnit.SECONDS);
    }

    @AfterEach
    public void driverQuit(){
        driver.close();
        driver.quit();
    }

    @Test
    public void navigatingBetweenPagesTest(){
        driver.navigate().to(wikiUrl);
        driver.navigate().to(nasaUrl);
        driver.navigate().back();

        Assertions.assertEquals("Wikipedia", driver.getTitle(), "Wikipedia page is not displayed in browser");

        driver.navigate().forward();

        Assertions.assertEquals("NASA", driver.getTitle(), "NASA page is not displayed in browser");
    }

}
