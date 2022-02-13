package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
    public static WebDriver driver;
    String mainPageUrl = "http://automationpractice.com";

    BasePage(WebDriver driver) {
        BasePage.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public void goToMainPage() {
        driver.get(mainPageUrl);
    }
}
