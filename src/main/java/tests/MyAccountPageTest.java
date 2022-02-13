package tests;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.MyAccountPage;

import java.time.Duration;


import static pages.BasePage.driver;

public class MyAccountPageTest {
    MyAccountPage myAccountPage = new MyAccountPage(driver);

    @BeforeAll
    public static void warmUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @Test
    public void shouldLogOut() {
        myAccountPage.goToLogOutPage();
        Assertions.assertTrue(driver.getCurrentUrl().contains("controller=authentication&back=my-account"));
    }

    @AfterAll
    public static void teardown() {
        driver.close();
    }
}
