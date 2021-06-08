package TestelkaFakestoreTests.Zadanie32;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class Zadanie32 {

    WebDriver driver;
    String webAddress = "https://fakestore.testelka.pl/moje-konto/";
    String correctEmail = "opdzstwa@gmail.com";
    String correctUsername = "opdzstwa";
    String correctPassword = "Te$T0we12";
    String incorrectUsername = "niepoprawne";
    String incorrectPassword = "niepoprawne";
    String blankString = "";
    String expectedAlert;
    String correctLoginSelector;

    @BeforeEach
    public void driverSetup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.navigate().to(webAddress);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
    }

    @AfterEach
    public void driverQuit() {
        driver.close();
        driver.quit();
    }

    @Test
    public void correctLoginWithEmail() {
        logInForm(correctEmail, correctPassword);
        correctLoginSelector = driver.findElement(By.cssSelector(".woocommerce-MyAccount-content>p")).getText();
        Assertions.assertTrue(correctLoginSelector.contains(correctUsername), "Użytkownik " + correctUsername + " nie jest zalogowany");
    }

    @Test
    public void correctLoginWithUsername() {
        logInForm(correctUsername, correctPassword);
        correctLoginSelector = driver.findElement(By.cssSelector(".woocommerce-MyAccount-content>p")).getText();
        Assertions.assertTrue(correctLoginSelector.contains(correctUsername), "Użytkownik " + correctUsername + " nie jest zalogowany");
    }

    @Test
    public void correctEmailIncorrectPassword() {
        logInForm(correctEmail, incorrectPassword);
        expectedAlert = "BŁĄD: Dla adresu email " + correctEmail + " podano nieprawidłowe hasło. Nie pamiętasz hasła?";
        Assertions.assertEquals(expectedAlert, getAlertMessage(), "Komunikat błędu jest nieprawidłowy");
    }

    @Test
    public void correctUsernameIncorrectPassword() {
        logInForm(correctUsername, incorrectPassword);
        expectedAlert = "Błąd: Wprowadzone hasło dla użytkownika " + correctUsername + " jest niepoprawne. Nie pamiętasz hasła?";
        Assertions.assertEquals(expectedAlert, getAlertMessage(), "Komunikat błędu jest nieprawidłowy");
    }

    @Test
    public void correctUsernameBlankPassword() {
        logInForm(correctUsername, blankString);
        expectedAlert = "Błąd: Hasło jest puste.";
        Assertions.assertEquals(expectedAlert, getAlertMessage(), "Komunikat błędu jest nieprawidłowy");
    }

    @Test
    public void blankUsernameAndPassword() {
        logInForm(blankString, blankString);
        expectedAlert = "Błąd: Nazwa użytkownika jest wymagana.";
        Assertions.assertEquals(expectedAlert, getAlertMessage(), "Komunikat błędu jest nieprawidłowy");
    }

    @Test
    public void incorrectUsernameIncorrectPassword() {
        logInForm(incorrectUsername, incorrectPassword);
        expectedAlert = "Nieznany użytkownik. Proszę spróbować ponownie lub użyć adresu email.";
        Assertions.assertEquals(expectedAlert, getAlertMessage(), "Komunikat błędu jest nieprawidłowy");
    }


    public void logInForm(String wpiszLoginEmail, String wpiszHaslo) {
        WebElement selectorLogin = driver.findElement(By.cssSelector("input#username"));
        WebElement selectorPassword = driver.findElement(By.cssSelector("input#password"));
        WebElement selectorButtonLogin = driver.findElement(By.cssSelector("button[name='login']"));

        selectorLogin.sendKeys(wpiszLoginEmail);
        selectorPassword.sendKeys(wpiszHaslo);
        selectorButtonLogin.click();
    }

    public String getAlertMessage() {
        return driver.findElement(By.cssSelector("ul[role='alert']")).getText();
    }


}
