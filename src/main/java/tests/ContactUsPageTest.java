package tests;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.ContactUsPage;

import static pages.BasePage.driver;

public class ContactUsPageTest {

    @BeforeAll
    public static void warmUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    void shouldSendMessageWithCorrectData() {
        ContactUsPage contactUsPage = new ContactUsPage(driver);
        contactUsPage.goToContactUsPage();
        contactUsPage.selectSubjectHeading();
        contactUsPage.enterCorrectEmail();
        contactUsPage.enterOrderReference();
        contactUsPage.sendCorrectAttachment();
        contactUsPage.enterMessage();
        contactUsPage.clickSendButton();
        Assertions.assertEquals("Your message has been successfully sent to our team.", contactUsPage.returnSuccessMessage());
    }

    @Test
    void shouldNotSendMessageWithoutAnyData() {
        ContactUsPage contactUsPage = new ContactUsPage(driver);
        contactUsPage.goToContactUsPage();
        contactUsPage.clickSendButton();
        Assertions.assertEquals("Invalid email address.", contactUsPage.returnErrorMessage());
    }

    @Test
    void shouldNotSendMessageWithWrongEmail() {
        ContactUsPage contactUsPage = new ContactUsPage(driver);
        contactUsPage.goToContactUsPage();
        contactUsPage.enterWrongEmail();
        contactUsPage.clickSendButton();
        Assertions.assertEquals("Invalid email address.", contactUsPage.returnErrorMessage());
    }

    @Test
    void shouldNotSendMessageWithoutMessage() {
        ContactUsPage contactUsPage = new ContactUsPage(driver);
        contactUsPage.goToContactUsPage();
        contactUsPage.enterCorrectEmail();
        contactUsPage.clickSendButton();
        Assertions.assertEquals("The message cannot be blank.", contactUsPage.returnErrorMessage());
    }

    @Test
    void shouldNotSendMessageWithWrongFileType() {
        ContactUsPage contactUsPage = new ContactUsPage(driver);
        contactUsPage.goToContactUsPage();
        contactUsPage.selectSubjectHeading();
        contactUsPage.enterCorrectEmail();
        contactUsPage.enterOrderReference();
        contactUsPage.sendWrongAttachment();
        contactUsPage.enterMessage();
        contactUsPage.clickSendButton();
        Assertions.assertEquals("Bad file extension", contactUsPage.returnErrorMessage());
    }

    @Test
    void shouldNotSendMessageWithoutSubjectHeading() {
        ContactUsPage contactUsPage = new ContactUsPage(driver);
        contactUsPage.goToContactUsPage();
        contactUsPage.enterCorrectEmail();
        contactUsPage.enterOrderReference();
        contactUsPage.sendCorrectAttachment();
        contactUsPage.enterMessage();
        contactUsPage.clickSendButton();
        Assertions.assertEquals("Please select a subject from the list provided.", contactUsPage.returnErrorMessage());
    }

    @AfterAll
    public static void tearDown() {
        driver.close();
    }
}

