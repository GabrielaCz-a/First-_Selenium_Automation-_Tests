package tests;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.MainPage;

import java.util.concurrent.TimeUnit;

import static pages.BasePage.driver;

public class MainPageTest {

    @BeforeAll
    public static void warmUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    void shouldFindProductsThatExist() {
        MainPage mainPage = new MainPage(driver);
        mainPage.goToMainPage();
        mainPage.enterCorrectSearchKeys();
        mainPage.clickSearchButton();
        Assertions.assertEquals("Showing 1 - 4 of 4 items", mainPage.returnTextWithCountOfProducts());

    }

    @Test
    void shouldNotFindProductWithoutAnyData() {
        MainPage mainPage = new MainPage(driver);
        mainPage.goToMainPage();
        mainPage.clickSearchButton();
        Assertions.assertEquals("Please enter a search keyword", mainPage.returnErrorMessage());
    }

    @Test
    void shouldNotFindProductThatDoesNotExist() {
        MainPage mainPage = new MainPage(driver);
        mainPage.goToMainPage();
        mainPage.enterWrongSearchKeys();
        mainPage.clickSearchButton();
        Assertions.assertEquals("No results were found for your search \"sjkaidiai\"", mainPage.returnErrorMessage());
    }

    @Test
    void shouldNotFindProductWithTooManyCharacters() {
        MainPage mainPage = new MainPage(driver);
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
