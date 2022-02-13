package pages;

import com.github.javafaker.Faker;
import com.thedeanda.lorem.LoremIpsum;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ContactUsPage extends BasePage {
    public ContactUsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "contact-link")
    private WebElement contactUsButton;
    @FindBy(id = "id_contact")
    private WebElement subjectHeadingDropDown;
    @FindBy(id = "email")
    private WebElement emailInput;
    @FindBy(id = "id_order")
    private WebElement orderReferenceInput;
    @FindBy(id = "fileUpload")
    private WebElement uploadFileInput;
    @FindBy(id = "message")
    private WebElement messageTextArea;
    @FindBy(id = "submitMessage")
    private WebElement sendButton;
    @FindBy(xpath = "//*[@id=\"center_column\"]/p")
    private WebElement successMessage;
    @FindBy(xpath = "//*[@id=\"center_column\"]/div/ol/li")
    private WebElement errorMessage;

    Faker faker = new Faker();
    LoremIpsum lorem = LoremIpsum.getInstance();

    Wait wait = new WebDriverWait(driver, Duration.ofSeconds(8));

    final String correctEmail = faker.name().firstName() + faker.name().lastName() + faker.random().nextInt(10000) + "@gmail.com";
    final String wrongEmail = faker.name().firstName() + faker.name().lastName() + faker.random().nextInt(10000) + "gmail.com";

    public void goToContactUsPage() {
        goToMainPage();
        wait.until(ExpectedConditions.visibilityOf(contactUsButton));
        contactUsButton.click();
        wait.until(ExpectedConditions.urlContains("controller=contact"));
    }

    public void selectSubjectHeading() {
        Select subjectHeading = new Select(subjectHeadingDropDown);
        subjectHeading.selectByIndex(1);
    }

    public void enterCorrectEmail() {
        emailInput.sendKeys(correctEmail);
    }

    public void enterWrongEmail() {
        emailInput.sendKeys(wrongEmail);
    }

    public void enterOrderReference() {
        orderReferenceInput.sendKeys(String.valueOf(faker.number().randomNumber()));
    }

    public void sendAttachmentWithCorrectFileType() {
      uploadFileInput.sendKeys(System.getProperty("user.dir") + "/src/main/resources/uploadCorrectFile.pdf");
    }

    public void sendAttachmentWithIncorrectFileType() {
       uploadFileInput.sendKeys(System.getProperty("user.dir") + "/src/main/resources/uploadWrongFile.html");
    }

    public void enterMessage() {
        messageTextArea.sendKeys(lorem.getWords(50));
    }

    public void clickSendButton() {
        sendButton.click();
    }

    public String returnSuccessMessage() {
        wait.until(ExpectedConditions.visibilityOf(successMessage));
        return successMessage.getText();
    }

    public String returnErrorMessage() {
        wait.until(ExpectedConditions.visibilityOf(errorMessage));
        return errorMessage.getText();
    }
}
