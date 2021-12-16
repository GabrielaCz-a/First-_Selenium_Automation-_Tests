package pages;

import com.github.javafaker.Faker;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginAndRegistrationPage extends BasePage {
    public LoginAndRegistrationPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(linkText = "Sign in")
    WebElement signInButton;
    @FindBy(id = "email")
    WebElement emailInputForLogin;
    @FindBy(id = "passwd")
    WebElement passwordInput;
    @FindBy(css = "#SubmitLogin > span")
    WebElement loginButton;
    @FindBy(xpath = "//*[@id=\"center_column\"]/div[1]/ol/li")
    WebElement errorLoginMessage;
    @FindBy(id = "email_create")
    WebElement emailInputForCreateAccount;
    @FindBy(name = "SubmitCreate")
    WebElement createAccountButton;
    @FindBy(xpath = "//*[@id=\"create_account_error\"]/ol/li")
    WebElement errorCreateAccountMessage;

    Faker faker = new Faker();
    String correctPassword = "12345";
    String correctEmail = "klient@niepodam.pl";
    String wrongPassword = "1234";
    String wrongEmail = "klientniepodam.pl";
    String uniqueEmail = faker.name().firstName() + faker.name().lastName() + faker.random().nextInt(10000) + "@gmail.com";

    Wait wait = new WebDriverWait(driver, 5000);

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
        String textOfErrorMessage = errorLoginMessage.getText();
        return textOfErrorMessage;
    }

    public String returnErrorCreateAccountMessage() {
        wait.until(ExpectedConditions.visibilityOf(errorCreateAccountMessage));
        String textOfErrorMessage = errorCreateAccountMessage.getText();
        return textOfErrorMessage;
    }
}
