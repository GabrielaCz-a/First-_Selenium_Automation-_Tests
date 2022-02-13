package pages;

import com.github.javafaker.Faker;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginAndRegistrationPage extends BasePage {
    public LoginAndRegistrationPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(linkText = "Sign in")
    private WebElement signInButton;
    @FindBy(id = "email")
    private WebElement emailInputForLogin;
    @FindBy(id = "passwd")
    private WebElement passwordInput;
    @FindBy(css = "#SubmitLogin > span")
    private WebElement loginButton;
    @FindBy(xpath = "//*[@id=\"center_column\"]/div[1]/ol/li")
    private WebElement errorLoginMessage;
    @FindBy(id = "email_create")
    private WebElement emailInputForCreateAccount;
    @FindBy(name = "SubmitCreate")
    private WebElement createAccountButton;
    @FindBy(xpath = "//*[@id=\"create_account_error\"]/ol/li")
    private WebElement errorCreateAccountMessage;

    Faker faker = new Faker();
    final String correctPassword = "12345";
    final String correctEmail = "klient@niepodam.pl";
    final String wrongPassword = "1234";
    final String wrongEmail = "klientniepodam.pl";
    final String uniqueEmail = faker.name().firstName() + faker.name().lastName() + faker.random().nextInt(10000) + "@gmail.com";

    Wait wait = new WebDriverWait(driver, Duration.ofSeconds(8));

    public void goToLoginAndRegistrationPage() {
        goToMainPage();
        wait.until(ExpectedConditions.visibilityOf(signInButton));
        signInButton.click();
        wait.until(ExpectedConditions.urlContains("controller=authentication&back=my-account"));
    }

    public void enterCorrectEmailForLogin() {
        emailInputForLogin.sendKeys(correctEmail);
    }

    public void enterWrongEmailForLogin() {
        emailInputForLogin.sendKeys(wrongEmail);
    }

    public void enterWrongEmailForCreateAccount() {
        emailInputForCreateAccount.sendKeys(wrongEmail);
    }

    public void enterRegisteredEmailForCreateAccount() {
        emailInputForCreateAccount.sendKeys(correctEmail);
    }

    public void enterCorrectPassword() {
        passwordInput.sendKeys(correctPassword);
    }

    public void enterWrongPassword() {
        passwordInput.sendKeys(wrongPassword);
    }

    public void enterUniqueEmailForLogin() {
        emailInputForLogin.sendKeys(uniqueEmail);
    }

    public void enterUniqueEmailForRegistration() {
        emailInputForCreateAccount.sendKeys(uniqueEmail);
    }

    public void clickLoginButton() {
        loginButton.click();
    }

    public void clickCreateAccountButton() {
        createAccountButton.click();
    }

    public void waitForCreateAccountFormPage() {
        wait.until(ExpectedConditions.urlContains("controller=authentication&back=my-account#account-creation"));
    }

    public String returnErrorLoginMessage() {
        wait.until(ExpectedConditions.visibilityOf(errorLoginMessage));
        return errorLoginMessage.getText();
    }

    public String returnErrorCreateAccountMessage() {
        wait.until(ExpectedConditions.visibilityOf(errorCreateAccountMessage));
        return errorCreateAccountMessage.getText();
    }
}
