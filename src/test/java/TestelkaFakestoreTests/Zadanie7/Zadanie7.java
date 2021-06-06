package TestelkaFakestoreTests.Zadanie7;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class Zadanie7 {

    WebDriver driver;
    String wikiUrlPl = "https://pl.wikipedia.org/wiki/Wikipedia:Strona_g%C5%82%C3%B3wna";
    String wikiTitlePl= "Wikipedia, wolna encyklopedia";
    String wikiSourcePartPl = "\"pageLanguageCode\":\"pl\"";

    String wikiUrlEs = "https://es.wikipedia.org/wiki/Wikipedia:Portada";
    String wikiTitleEs= "Wikipedia, la enciclopedia libre";
    String wikiSourcePartEs = "\"pageLanguageCode\":\"es\"";

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
        driver.navigate().to(wikiUrlPl);

        Assertions.assertEquals(wikiTitlePl, driver.getTitle(), "Wikipedia is not displayed in polish language");
        Assertions.assertEquals(wikiUrlPl, driver.getCurrentUrl(), "Wikipedia is not displayed in polish language");
        Assertions.assertTrue(driver.getPageSource().contains(wikiSourcePartPl), "Wikipedia is not displayed in polish language");

        driver.findElement(By.cssSelector("a[title='hiszpański']")).click();

        Assertions.assertEquals(wikiTitleEs, driver.getTitle(), "Wikipedia is not displayed in spanish language");
        Assertions.assertEquals(wikiUrlEs, driver.getCurrentUrl(), "Wikipedia is not displayed in spanish language");
        Assertions.assertTrue(driver.getPageSource().contains(wikiSourcePartEs), "Wikipedia is not displayed in spanish language");
    }

}
