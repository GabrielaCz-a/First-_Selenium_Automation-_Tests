package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.MyAccountPage;

import static pages.BasePage.driver;

public class MyAccountPageTest {

    @Test
    public void shouldLogOut() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        MyAccountPage myAccountPage = new MyAccountPage(driver);
        myAccountPage.goToLogOutPage();
        Assertions.assertTrue(driver.getCurrentUrl().contains("controller=authentication&back=my-account"));
        driver.close();
    }
}
