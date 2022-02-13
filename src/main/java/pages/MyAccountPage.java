package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MyAccountPage extends LoginAndRegistrationPage {
    public MyAccountPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(linkText = "Sign out")
    private WebElement signOutButton;

    Wait wait = new WebDriverWait(driver, Duration.ofSeconds(8));

    public void goToLogOutPage() {
        goToLoginAndRegistrationPage();
        enterCorrectEmailForLogin();
        enterCorrectPassword();
        clickLoginButton();
        wait.until(ExpectedConditions.visibilityOf(signOutButton));
        signOutButton.click();
    }
}
