package tests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.LoginAndRegistrationPage;

import static pages.BasePage.driver;

public class LoginAndRegistrationPageTest {
    LoginAndRegistrationPage loginAndRegistrationPage = new LoginAndRegistrationPage(driver);

    @BeforeAll
    public static void warmUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    void shouldLoginWithCorrectData() {
        loginAndRegistrationPage.goToLoginAndRegistrationPage();
        loginAndRegistrationPage.enterCorrectEmailForLogin();
        loginAndRegistrationPage.enterCorrectPassword();
        loginAndRegistrationPage.clickLoginButton();
        Assertions.assertEquals("http://automationpractice.com/index.php?controller=my-account", driver.getCurrentUrl());
        driver.manage().deleteAllCookies();
    }

    @Test
    void shouldNotLoginWithoutAnyData() {
        loginAndRegistrationPage.goToLoginAndRegistrationPage();
        loginAndRegistrationPage.clickLoginButton();
        Assertions.assertEquals("An email address required.", loginAndRegistrationPage.returnErrorLoginMessage());
    }

    @Test
    void shouldNotLoginWithoutPassword() {
        loginAndRegistrationPage.goToLoginAndRegistrationPage();
        loginAndRegistrationPage.enterCorrectEmailForLogin();
        loginAndRegistrationPage.clickLoginButton();
        Assertions.assertEquals("Password is required.", loginAndRegistrationPage.returnErrorLoginMessage());
    }

    @Test
    void shouldNotLoginWithoutEmail() {
        loginAndRegistrationPage.goToLoginAndRegistrationPage();
        loginAndRegistrationPage.enterCorrectPassword();
        loginAndRegistrationPage.clickLoginButton();
        Assertions.assertEquals("An email address required.", loginAndRegistrationPage.returnErrorLoginMessage());
    }

    @Test
    void shouldNotLoginWithWrongEmail() {
        loginAndRegistrationPage.goToLoginAndRegistrationPage();
        loginAndRegistrationPage.enterWrongEmailForLogin();
        loginAndRegistrationPage.enterCorrectPassword();
        loginAndRegistrationPage.clickLoginButton();
        Assertions.assertEquals("Invalid email address.", loginAndRegistrationPage.returnErrorLoginMessage());
    }

    @Test
    void shouldNotLoginWithWrongPassword() {
        loginAndRegistrationPage.goToLoginAndRegistrationPage();
        loginAndRegistrationPage.enterCorrectEmailForLogin();
        loginAndRegistrationPage.enterWrongPassword();
        loginAndRegistrationPage.clickLoginButton();
        Assertions.assertEquals("Invalid password.", loginAndRegistrationPage.returnErrorLoginMessage());
    }

    @Test
    void shouldNotLoginWithEmailWithoutAccount() {
        loginAndRegistrationPage.goToLoginAndRegistrationPage();
        loginAndRegistrationPage.enterUniqueEmailForLogin();
        loginAndRegistrationPage.enterCorrectPassword();
        loginAndRegistrationPage.clickLoginButton();
        Assertions.assertEquals("Authentication failed.", loginAndRegistrationPage.returnErrorLoginMessage());
    }

    @Test
    void shouldNotCreateAccountWithWrongEmail() {
        loginAndRegistrationPage.goToLoginAndRegistrationPage();
        loginAndRegistrationPage.enterWrongEmailForCreateAccount();
        loginAndRegistrationPage.clickCreateAccountButton();
        Assertions.assertEquals("Invalid email address.", loginAndRegistrationPage.returnErrorCreateAccountMessage());
    }

    @Test
    void shouldNotCreateAccountWithRegisteredEmail() {
        loginAndRegistrationPage.goToLoginAndRegistrationPage();
        loginAndRegistrationPage.enterRegisteredEmailForCreateAccount();
        loginAndRegistrationPage.clickCreateAccountButton();
        Assertions.assertEquals("An account using this email address has already been registered. Please enter a valid password or request a new one.", loginAndRegistrationPage.returnErrorCreateAccountMessage());
    }

    @Test
    void shouldCreateAccountWithCorrectData() {
        loginAndRegistrationPage.goToLoginAndRegistrationPage();
        loginAndRegistrationPage.enterUniqueEmailForRegistration();
        loginAndRegistrationPage.clickCreateAccountButton();
        loginAndRegistrationPage.waitForCreateAccountFormPage();
        Assertions.assertTrue(driver.getCurrentUrl().contains("controller=authentication&back=my-account#account-creation"));
    }

    @AfterAll
    public static void tearDown() {
        driver.close();
    }
}
