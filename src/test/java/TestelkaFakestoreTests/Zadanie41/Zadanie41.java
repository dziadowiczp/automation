package TestelkaFakestoreTests.Zadanie41;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class Zadanie41 {
    WebDriver driver;

    @BeforeEach
    public void driverSetup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.navigate().to("https://fakestore.testelka.pl/metody-na-elementach/");
    }

    @AfterEach
    public void driverQuit(){
        driver.close();
        driver.quit();
    }

    @Test
    public void nazwaKlasy(){

        WebElement mainPageButton = driver.findElement(By.cssSelector("[name='main-page']"));
        WebElement hiddenButton = driver.findElement(By.cssSelector("[class='button'][value='żeglarstwo']"));
        List<WebElement> yelllowButtonsList = driver.findElements(By.cssSelector("a.button"));
        WebElement selectedCheckbox = driver.findElement(By.cssSelector("input[name='selected-checkbox']"));
        WebElement selectedRadiobutton = driver.findElement(By.cssSelector("input[name='selected-radio']"));
        WebElement unselectedCheckbox = driver.findElement(By.cssSelector("input[name='not-selected-checkbox']"));
        WebElement unselectedRadiobutton = driver.findElement(By.cssSelector("input[name='not-selected-radio']"));

//        String color = "rgba(245, 233, 101, 1)";

        System.out.println(hiddenButton.getCssValue("background-color"));

        Assertions.assertAll("Getting information",
                ()-> Assertions.assertTrue(!mainPageButton.isEnabled(), "The button is not enabled"),
                ()-> Assertions.assertTrue(!hiddenButton.isDisplayed(), "The button is not displayed"),
                ()-> Assertions.assertTrue(selectedCheckbox.isSelected(), "Checkbox is not selected"),
                ()-> Assertions.assertFalse(unselectedCheckbox.isSelected(), "Checkbox is selected"),
                ()-> Assertions.assertTrue(selectedRadiobutton.isSelected(), "Radio Button is not selected"),
                ()-> Assertions.assertFalse(unselectedRadiobutton.isSelected(), "Radio Button is selected")
        );

        for(WebElement buttonElement : yelllowButtonsList){
            String color = buttonElement.getCssValue("background-color");
            System.out.println(buttonElement.getCssValue("background-color"));
            Assertions.assertEquals("rgba(245, 233, 101, 1)", color, "nope");
        }
    }

}