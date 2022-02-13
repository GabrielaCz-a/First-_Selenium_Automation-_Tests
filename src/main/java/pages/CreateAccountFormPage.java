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

public class CreateAccountFormPage extends LoginAndRegistrationPage {
    public CreateAccountFormPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "customer_firstname")
    private WebElement firstNameInput;
    @FindBy(id = "customer_lastname")
    private WebElement lastNameInput;
    @FindBy(id = "address1")
    private WebElement addressInput;
    @FindBy(id = "address2")
    private WebElement addressNextLineInput;
    @FindBy(id = "company")
    private WebElement companyInput;
    @FindBy(id = "days")
    private WebElement daysDropDown;
    @FindBy(id = "months")
    private WebElement monthsDropdown;
    @FindBy(id = "years")
    private WebElement yearsDropDown;
    @FindBy(id = "city")
    private WebElement cityInput;
    @FindBy(id = "id_state")
    private WebElement stateDropDown;
    @FindBy(id = "postcode")
    private WebElement postalCodeInput;
    @FindBy(id = "phone")
    private WebElement phone;
    @FindBy(id = "phone_mobile")
    private WebElement mobilePhoneInput;
    @FindBy(id = "other")
    private WebElement additionalInformationTextArea;
    @FindBy(id = "alias")
    private WebElement aliasInput;
    @FindBy(id = "submitAccount")
    private WebElement registerButton;
    @FindBy(xpath = "//*[@id=\"center_column\"]/div/ol/li[1]")
    private WebElement firstErrorMessage;
    @FindBy(xpath = "//*[@id=\"center_column\"]/div/ol/li[2]")
    private WebElement secondErrorMessage;
    @FindBy(xpath = "//*[@id=\"center_column\"]/div/ol/li[3]")
    private WebElement thirdErrorMessage;
    @FindBy(xpath = "//*[@id=\"center_column\"]/div/ol/li[4]")
    private WebElement fourthErrorMessage;
    @FindBy(xpath = "//*[@id=\"center_column\"]/div/ol/li[5]")
    private WebElement fifthErrorMessage;
    @FindBy(xpath = "//*[@id=\"center_column\"]/div/ol/li[6]")
    private WebElement sixthErrorMessage;
    @FindBy(xpath = "//*[@id=\"center_column\"]/div/ol/li[7]")
    private WebElement seventhErrorMessage;
    @FindBy(xpath = "//*[@id=\"center_column\"]/div/ol/li[8]")
    private WebElement eighthErrorMessage;

    Faker faker = new Faker();
    LoremIpsum lorem = LoremIpsum.getInstance();

    Wait wait = new WebDriverWait(driver, Duration.ofSeconds(8));

    public void goToCreateAccountForm() {
        goToLoginAndRegistrationPage();
        enterUniqueEmailForRegistration();
        clickCreateAccountButton();
        waitForCreateAccountFormPage();
        wait.until(ExpectedConditions.visibilityOf(firstNameInput));
    }

    public void enterCorrectFirstName() {
        firstNameInput.sendKeys(faker.name().firstName());
    }

    public void enterWrongFirstName() {
        firstNameInput.sendKeys(String.valueOf(faker.number().randomNumber()));
    }

    public void enterTooManyCharactersInFirstName() {
        firstNameInput.sendKeys(lorem.getWords(17));
    }

    public void enterCorrectLastName() {
        lastNameInput.sendKeys(faker.name().lastName());
    }

    public void enterWrongLastName() {
        lastNameInput.sendKeys(String.valueOf(faker.number().randomNumber()));
    }

    public void enterTooManyCharactersInLastName() {
        lastNameInput.sendKeys(lorem.getWords(17));
    }

    public void enterTooManyCharactersInCompany() {
        companyInput.sendKeys(lorem.getWords(33));
    }

    public void selectWrongDate() {
        Select days = new Select(daysDropDown);
        Select months = new Select(monthsDropdown);
        Select years = new Select(yearsDropDown);
        days.selectByValue("31");
        months.selectByValue("2");
        years.selectByValue("2021");
    }

    public void enterAddress() {
        addressInput.sendKeys(faker.address().streetAddress());
    }

    public void enterTooManyCharactersInAddress() {
        addressInput.sendKeys((lorem.getWords(65)));
    }

    public void enterTooManyCharactersInAddressNextLine() {
        addressNextLineInput.sendKeys(lorem.getWords(65));
    }

    public void enterCity() {
        cityInput.sendKeys(faker.address().cityName());
    }

    public void enterTooManyCharactersInCity() {
        cityInput.sendKeys(lorem.getWords(33));
    }

    public void selectState() {
        Select state = new Select(stateDropDown);
        state.selectByIndex(faker.number().numberBetween(1, 50));
    }

    public void enterCorrectPostalCode() {
        postalCodeInput.sendKeys(String.valueOf(faker.number().numberBetween(10000, 99999)));
    }

    public void enterWrongPostalCode() {
        postalCodeInput.sendKeys(String.valueOf(faker.number().numberBetween(1000, 9999)));
    }

    public void enterTooManyCharactersInAdditionalInformation() {
        additionalInformationTextArea.sendKeys(lorem.getWords(151));
    }

    public void enterWrongPhone() {
        phone.sendKeys(lorem.getWords(1));
    }

    public void enterCorrectMobilePhone() {
        mobilePhoneInput.sendKeys(String.valueOf(faker.number().randomNumber()));
    }

    public void enterWrongMobilePhone() {
        mobilePhoneInput.sendKeys(lorem.getWords(1));
    }

    public void enterAlias() {
        aliasInput.sendKeys(lorem.getWords(1));
    }

    public void enterTooManyCharactersInAlias() {
        aliasInput.sendKeys(lorem.getWords(17));
    }

    public void clickRegisterButton() {
        registerButton.click();
    }

    public String returnFirstErrorMessage() {
        wait.until(ExpectedConditions.visibilityOf(firstErrorMessage));
        return firstErrorMessage.getText();
    }

    public String returnSecondErrorMessage() {
        return secondErrorMessage.getText();
    }

    public String returnThirdErrorMessage() {
        return thirdErrorMessage.getText();
    }

    public String returnFourthErrorMessage() {
        return fourthErrorMessage.getText();
    }

    public String returnFifthErrorMessage() {
        return fifthErrorMessage.getText();
    }

    public String returnSixthErrorMessage() {
        return sixthErrorMessage.getText();
    }

    public String returnSeventhErrorMessage() {
        return seventhErrorMessage.getText();
    }

    public String returnEighthErrorMessage() {
        return eighthErrorMessage.getText();
    }
}




