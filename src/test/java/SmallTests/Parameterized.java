package SmallTests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class Parameterized {

    WebDriver driver;
    String webUrl = "https://pl.wikipedia.org/wiki/Wikipedia:Strona_g%C5%82%C3%B3wna";

    @BeforeEach
    public void driverStarter(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.navigate().to(webUrl);
    }

    @AfterEach
    public void driverQuit(){
        driver.close();
        driver.quit();
    }

    @DisplayName("Wyszukiwanie w Wikipedii")
    @ParameterizedTest(name = "hasło szukane to {0}")
    @CsvSource({"Wiki", "Stalowa Wola", "Kraina Wielkich Jezior Mazurskich", "Muszynianka"})
    public void szukajka(String szukaneSłowo){
        driver.findElement(By.cssSelector("input#searchInput")).sendKeys(szukaneSłowo);
        driver.findElement(By.cssSelector("input#searchInput")).submit();

//        String pobranaWartosc = driver.findElement(By.cssSelector("h1#firstHeading")).getText();
        String pobranaWartosc = driver.getTitle();
        Assertions.assertTrue(pobranaWartosc.contains(szukaneSłowo), "Nie znajdujesz się na właściwej stronie");
    }

}
