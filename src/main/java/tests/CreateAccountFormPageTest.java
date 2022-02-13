package tests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.CreateAccountFormPage;

import static pages.BasePage.driver;

public class CreateAccountFormPageTest {
    CreateAccountFormPage createAccountFormPage = new CreateAccountFormPage(driver);

    @BeforeAll
    public static void warmUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void shouldCreateNewAccountWithCorrectDataInForm() {
        createAccountFormPage.goToCreateAccountForm();
        createAccountFormPage.enterCorrectFirstName();
        createAccountFormPage.enterCorrectLastName();
        createAccountFormPage.enterCorrectPassword();
        createAccountFormPage.enterAddress();
        createAccountFormPage.enterCity();
        createAccountFormPage.selectState();
        createAccountFormPage.enterCorrectPostalCode();
        createAccountFormPage.enterCorrectMobilePhone();
        createAccountFormPage.enterAlias();
        createAccountFormPage.clickRegisterButton();
        Assertions.assertTrue(driver.getCurrentUrl().contains("controller=my-account"));
        driver.manage().deleteAllCookies();
    }

    @Test
    void shouldNotCreateAccountWithoutAnyDataInForm() {
        createAccountFormPage.goToCreateAccountForm();
        createAccountFormPage.clickRegisterButton();
        Assertions.assertEquals("You must register at least one phone number.", createAccountFormPage.returnFirstErrorMessage());
        Assertions.assertEquals("lastname is required.", createAccountFormPage.returnSecondErrorMessage());
        Assertions.assertEquals("firstname is required.", createAccountFormPage.returnThirdErrorMessage());
        Assertions.assertEquals("passwd is required.", createAccountFormPage.returnFourthErrorMessage());
        Assertions.assertEquals("address1 is required.", createAccountFormPage.returnFifthErrorMessage());
        Assertions.assertEquals("city is required.", createAccountFormPage.returnSixthErrorMessage());
        Assertions.assertEquals("The Zip/Postal code you've entered is invalid. It must follow this format: 00000", createAccountFormPage.returnSeventhErrorMessage());
        Assertions.assertEquals("This country requires you to choose a State.", createAccountFormPage.returnEighthErrorMessage());
    }

    @Test
    void shouldNotCreateAccountWithWrongDataInForm() {
        createAccountFormPage.goToCreateAccountForm();
        createAccountFormPage.enterWrongFirstName();
        createAccountFormPage.enterWrongLastName();
        createAccountFormPage.enterWrongEmailForLogin();
        createAccountFormPage.enterWrongPassword();
        createAccountFormPage.selectWrongDate();
        createAccountFormPage.enterAddress();
        createAccountFormPage.enterCity();
        createAccountFormPage.selectState();
        createAccountFormPage.enterWrongPostalCode();
        createAccountFormPage.enterWrongPhone();
        createAccountFormPage.enterWrongMobilePhone();
        createAccountFormPage.enterAlias();
        createAccountFormPage.clickRegisterButton();
        Assertions.assertEquals("lastname is invalid.", createAccountFormPage.returnFirstErrorMessage());
        Assertions.assertEquals("firstname is invalid.", createAccountFormPage.returnSecondErrorMessage());
        Assertions.assertEquals("passwd is invalid.", createAccountFormPage.returnThirdErrorMessage());
        Assertions.assertEquals("phone is invalid.", createAccountFormPage.returnFourthErrorMessage());
        Assertions.assertEquals("phone_mobile is invalid.", createAccountFormPage.returnFifthErrorMessage());
        Assertions.assertEquals("The Zip/Postal code you've entered is invalid. It must follow this format: 00000", createAccountFormPage.returnSixthErrorMessage());
        Assertions.assertEquals("Invalid date of birth", createAccountFormPage.returnSeventhErrorMessage());
    }

    @Test
    void shouldNotCreateAccountWithTooManyCharactersInInputs() {
        createAccountFormPage.goToCreateAccountForm();
        createAccountFormPage.enterTooManyCharactersInFirstName();
        createAccountFormPage.enterTooManyCharactersInLastName();
        createAccountFormPage.enterCorrectPassword();
        createAccountFormPage.enterTooManyCharactersInCompany();
        createAccountFormPage.enterTooManyCharactersInAddress();
        createAccountFormPage.enterTooManyCharactersInAddressNextLine();
        createAccountFormPage.enterTooManyCharactersInCity();
        createAccountFormPage.selectState();
        createAccountFormPage.enterCorrectPostalCode();
        createAccountFormPage.enterTooManyCharactersInAdditionalInformation();
        createAccountFormPage.enterCorrectMobilePhone();
        createAccountFormPage.enterTooManyCharactersInAlias();
        createAccountFormPage.clickRegisterButton();
        Assertions.assertEquals("lastname is too long. Maximum length: 32", createAccountFormPage.returnFirstErrorMessage());
        Assertions.assertEquals("firstname is too long. Maximum length: 32", createAccountFormPage.returnSecondErrorMessage());
        Assertions.assertEquals("alias is too long. Maximum length: 32", createAccountFormPage.returnThirdErrorMessage());
        Assertions.assertEquals("company is too long. Maximum length: 64", createAccountFormPage.returnFourthErrorMessage());
        Assertions.assertEquals("address1 is too long. Maximum length: 128", createAccountFormPage.returnFifthErrorMessage());
        Assertions.assertEquals("address2 is too long. Maximum length: 128", createAccountFormPage.returnSixthErrorMessage());
        Assertions.assertEquals("city is too long. Maximum length: 64", createAccountFormPage.returnSeventhErrorMessage());
        Assertions.assertEquals("other is too long. Maximum length: 300", createAccountFormPage.returnEighthErrorMessage());

    }

    @AfterAll
    public static void teardown() {
        driver.quit();
    }
}
