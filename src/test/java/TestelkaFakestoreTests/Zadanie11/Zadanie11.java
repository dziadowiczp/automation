package TestelkaFakestoreTests.Zadanie11;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Zadanie11 {

    WebDriver driver;

    @BeforeEach
    public void driverSetup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @AfterEach
    public void driverQuit(){
        driver.close();
        driver.quit();
    }

    @Test
    public void navigatingBetweenPagesTest(){

        Dimension windowSize = new Dimension(854, 480);
        driver.manage().window().setSize(windowSize);

        Point windowPosition = new Point(445, 30);
        driver.manage().window().setPosition(windowPosition);

        Assertions.assertEquals(windowSize, driver.manage().window().getSize(), "The window has incorrect size");
        Assertions.assertEquals(windowPosition, driver.manage().window().getPosition(), "The window has incorrect position");
    }
}
