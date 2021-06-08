package TestelkaFakestoreTests.Zadanie39;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Zadanie39 {
    WebDriver driver;
    WebDriverWait wait;

    String mainUrl = "https://fakestore.testelka.pl";
    String koscielow = "Grań Kościelców";
    String egipt = "Egipt – El Gouna";
    String mazury = "Kurs żeglarski na Mazurach";

    @BeforeEach
    public void driverStarter() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to(mainUrl);
        wait = new WebDriverWait(driver, 3000);

        demoStoreNoticeClose();
        addingTripToCart(mazury);
        openCartAfterAddingProduct();
    }

    @AfterEach
    public void driverQuit(){
        driver.close();
        driver.quit();
    }

    @DisplayName("Test Parametryzowany")
    @ParameterizedTest(name="nazwa kuponu: {0}")
    @CsvSource({"10procent, Kupon został pomyślnie użyty.",
            "zlykupon, Kupon \"zlykupon\" nie istnieje!",
            "'', Proszę wpisać kod kuponu."})
    public void jakisciwgf(String coupon, String expectedAlertText){
        addingCoupon(coupon);
        Assertions.assertEquals(expectedAlertText, gettingAlertMessage(), "Alert się nie zgadza");
    }

    @Test
    public void couponAddedTwice() {
        addingCoupon("10procent");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("button[name='apply_coupon']")));
        wait.until(ExpectedConditions.stalenessOf(driver.findElement(By.cssSelector("button[name='apply_coupon']"))));

        addingCoupon("10procent");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("button[name='apply_coupon']")));
        wait.until(ExpectedConditions.stalenessOf(driver.findElement(By.cssSelector("button[name='apply_coupon']"))));

        String gettingAlertText = gettingAlertMessage();
        String expectedAlertText = "Kupon został zastosowany!";

        Assertions.assertTrue(gettingAlertText.equals(expectedAlertText));
    }

    public void demoStoreNoticeClose() {
        driver.findElement(By.cssSelector("a.woocommerce-store-notice__dismiss-link")).click();
    }

    public void addingTripToCart(String destination) {
        WebElement addingToCart =
                driver.findElement(By.xpath(".//*[@class='woocommerce-loop-product__title'][text()='" + destination + "']/../following-sibling::*[text()='Dodaj do koszyka']"));
        addingToCart.click();
    }

    public void openCartAfterAddingProduct(){
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[title='Zobacz koszyk']")));
        driver.findElement(By.cssSelector("[title='Zobacz koszyk']")).click();
    }

    public void addingCoupon(String couponName){
        driver.findElement(By.cssSelector("#coupon_code")).sendKeys(couponName);
        driver.findElement(By.cssSelector("button[value='Zastosuj kupon']")).click();
    }

    public String gettingAlertMessage(){
        By alert = By.cssSelector("[role='alert']");
        wait.until(ExpectedConditions.presenceOfElementLocated(alert));
        return driver.findElement(alert).getText();
    }

}
