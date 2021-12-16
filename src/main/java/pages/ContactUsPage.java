package pages;

import com.github.javafaker.Faker;
import com.thedeanda.lorem.LoremIpsum;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class ContactUsPage extends BasePage {
    public ContactUsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "contact-link")
    WebElement contactUsButton;
    @FindBy(id = "id_contact")
    WebElement subjectHeadingDropDown;
    @FindBy(id = "email")
    WebElement emailInput;
    @FindBy(id = "id_order")
    WebElement orderReferenceInput;
    @FindBy(id = "fileUpload")
    WebElement uploadFileInput;
    @FindBy(id = "message")
    WebElement messageTextArea;
    @FindBy(id = "submitMessage")
    WebElement sendButton;
    @FindBy(xpath = "//*[@id=\"center_column\"]/p")
    WebElement successMessage;
    @FindBy(xpath = "//*[@id=\"center_column\"]/div/ol/li")
    WebElement errorMessage;

    Faker faker = new Faker();
    LoremIpsum lorem = LoremIpsum.getInstance();

    Wait<WebDriver> wait;

    {
        wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(NoSuchElementException.class);
    }


    int[] valueOfSubjectHeadingDropDown = {0, 1, 2};

    WebElement upload_file = uploadFileInput;
    String pathToCorrectAttachment = "C:\\Users\\User\\IdeaProjects\\FinalProjectSoftieRefactor\\src\\main\\resources\\uploadCorrectFile.pdf";
    String pathToWrongAttachment = "C:\\Users\\User\\IdeaProjects\\FinalProjectSoftieRefactor\\src\\main\\resources\\uploadWrongFile.html";

    String correctEmail = faker.name().firstName() + faker.name().lastName() + faker.random().nextInt(10000) + "@gmail.com";
    String wrongEmail = faker.name().firstName() + faker.name().lastName() + faker.random().nextInt(10000) + "gmail.com";


    public void goToContactUsPage() {
        goToMainPage();
        wait.until(ExpectedConditions.visibilityOf(contactUsButton));
        contactUsButton.click();
        wait.until(ExpectedConditions.urlContains("controller=contact"));
    }

    public void selectSubjectHeading() {
        Select subjectHeading = new Select(subjectHeadingDropDown);
        subjectHeading.selectByIndex(valueOfSubjectHeadingDropDown[1]);
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

    public void sendCorrectAttachment() {
        upload_file.sendKeys(pathToCorrectAttachment);
    }

    public void sendWrongAttachment() {
        upload_file.sendKeys(pathToWrongAttachment);
    }

    public void enterMessage() {
        messageTextArea.sendKeys(lorem.getWords(50));
    }

    public void clickSendButton() {
        sendButton.click();
    }

    public String returnSuccessMessage() {
        wait.until(ExpectedConditions.visibilityOf(successMessage));
        String textOfSuccessMessage = successMessage.getText();
        return textOfSuccessMessage;
    }

    public String returnErrorMessage() {
        wait.until(ExpectedConditions.visibilityOf(errorMessage));
        String textOfErrorMessage = errorMessage.getText();
        return textOfErrorMessage;
    }
}
