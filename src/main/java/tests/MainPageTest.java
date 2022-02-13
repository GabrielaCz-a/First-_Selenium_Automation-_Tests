package tests;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.MainPage;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static pages.BasePage.driver;

public class MainPageTest {
    MainPage mainPage = new MainPage(driver);

    @BeforeAll
    public static void warmUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));
    }

    @Test
    void shouldNotFindProductWithoutAnyData() {
        mainPage.goToMainPage();
        mainPage.clickSearchButton();
        Assertions.assertEquals("Please enter a search keyword", mainPage.returnErrorMessage());
    }

    @Test
    void shouldNotFindProductThatDoesNotExist() {
        mainPage.goToMainPage();
        mainPage.enterWrongSearchKeys();
        mainPage.clickSearchButton();
        Assertions.assertTrue(mainPage.returnErrorMessage().contains("No results were found for your search"));
    }

    @Test
    void shouldNotFindProductWithTooManyCharacters() {
        mainPage.goToMainPage();
        mainPage.enterTooManySearchKeys();
        mainPage.clickSearchButton();
        Assertions.assertEquals("414 Request-URI Too Long", driver.getTitle());
    }

    @AfterAll
    public static void teardown() {
        driver.close();
    }
}
