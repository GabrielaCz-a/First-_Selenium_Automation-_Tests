package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.LoginAndRegistrationPage;

import static pages.BasePage.driver;

public class LoginAndRegistrationPageTest {

    @BeforeEach
    public void warmUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    void shouldLoginWithCorrectData() {
        LoginAndRegistrationPage loginAndRegistrationPage = new LoginAndRegistrationPage(driver);
        loginAndRegistrationPage.goToLoginAndRegistrationPage();
        loginAndRegistrationPage.enterCorrectEmailForLogin();
        loginAndRegistrationPage.enterCorrectPassword();
        loginAndRegistrationPage.clickLoginButton();
        Assertions.assertEquals("http://automationpractice.com/index.php?controller=my-account", driver.getCurrentUrl());
    }

    @Test
    void shouldNotLoginWithoutAnyData() {
        LoginAndRegistrationPage loginAndRegistrationPage = new LoginAndRegistrationPage(driver);
        loginAndRegistrationPage.goToLoginAndRegistrationPage();
        loginAndRegistrationPage.clickLoginButton();
        Assertions.assertEquals("An email address required.", loginAndRegistrationPage.returnErrorLoginMessage());
    }

    @Test
    void shouldNotLoginWithoutPassword() {
        LoginAndRegistrationPage loginAndRegistrationPage = new LoginAndRegistrationPage(driver);
        loginAndRegistrationPage.goToLoginAndRegistrationPage();
        loginAndRegistrationPage.enterCorrectEmailForLogin();
        loginAndRegistrationPage.clickLoginButton();
        Assertions.assertEquals("Password is required.", loginAndRegistrationPage.returnErrorLoginMessage());
    }

    @Test
    void shouldNotLoginWithoutEmail() {
        LoginAndRegistrationPage loginAndRegistrationPage = new LoginAndRegistrationPage(driver);
        loginAndRegistrationPage.goToLoginAndRegistrationPage();
        loginAndRegistrationPage.enterCorrectPassword();
        loginAndRegistrationPage.clickLoginButton();
        Assertions.assertEquals("An email address required.", loginAndRegistrationPage.returnErrorLoginMessage());
    }

    @Test
    void shouldNotLoginWithWrongEmail() {
        LoginAndRegistrationPage loginAndRegistrationPage = new LoginAndRegistrationPage(driver);
        loginAndRegistrationPage.goToLoginAndRegistrationPage();
        loginAndRegistrationPage.enterWrongEmailForLogin();
        loginAndRegistrationPage.enterCorrectPassword();
        loginAndRegistrationPage.clickLoginButton();
        Assertions.assertEquals("Invalid email address.", loginAndRegistrationPage.returnErrorLoginMessage());
    }

    @Test
    void shouldNotLoginWithWrongPassword() {
        LoginAndRegistrationPage loginAndRegistrationPage = new LoginAndRegistrationPage(driver);
        loginAndRegistrationPage.goToLoginAndRegistrationPage();
        loginAndRegistrationPage.enterCorrectEmailForLogin();
        loginAndRegistrationPage.enterWrongPassword();
        loginAndRegistrationPage.clickLoginButton();
        Assertions.assertEquals("Invalid password.", loginAndRegistrationPage.returnErrorLoginMessage());
    }

    @Test
    void shouldNotLoginWithEmailWithoutAccount() {
        LoginAndRegistrationPage loginAndRegistrationPage = new LoginAndRegistrationPage(driver);
        loginAndRegistrationPage.goToLoginAndRegistrationPage();
        loginAndRegistrationPage.enterUniqueEmailForLogin();
        loginAndRegistrationPage.enterCorrectPassword();
        loginAndRegistrationPage.clickLoginButton();
        Assertions.assertEquals("Authentication failed.", loginAndRegistrationPage.returnErrorLoginMessage());
    }

    @Test
    void shouldNotCreateAccountWithWrongEmail() {
        LoginAndRegistrationPage loginAndRegistrationPage = new LoginAndRegistrationPage(driver);
        loginAndRegistrationPage.goToLoginAndRegistrationPage();
        loginAndRegistrationPage.enterWrongEmailForCreateAccount();
        loginAndRegistrationPage.clickCreateAccountButton();
        Assertions.assertEquals("Invalid email address.", loginAndRegistrationPage.returnErrorCreateAccountMessage());
    }

    @Test
    void shouldNotCreateAccountWithRegisteredEmail() {
        LoginAndRegistrationPage loginAndRegistrationPage = new LoginAndRegistrationPage(driver);
        loginAndRegistrationPage.goToLoginAndRegistrationPage();
        loginAndRegistrationPage.enterRegisteredEmailForCreateAccount();
        loginAndRegistrationPage.clickCreateAccountButton();
        Assertions.assertEquals("An account using this email address has already been registered. Please enter a valid password or request a new one.", loginAndRegistrationPage.returnErrorCreateAccountMessage());
    }

    @Test
    void shouldCreateAccountWithCorrectData() {
        LoginAndRegistrationPage loginAndRegistrationPage = new LoginAndRegistrationPage(driver);
        loginAndRegistrationPage.goToLoginAndRegistrationPage();
        loginAndRegistrationPage.enterUniqueEmailForRegistration();
        loginAndRegistrationPage.clickCreateAccountButton();
        loginAndRegistrationPage.waitForCreateAccountFormPage();
        Assertions.assertTrue(driver.getCurrentUrl().contains("controller=authentication&back=my-account#account-creation"));
    }

    @AfterEach
    public void tearDown() {
        driver.close();
    }
}
