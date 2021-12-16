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

public class CreateAccountFormPage extends LoginAndRegistrationPage {
    public CreateAccountFormPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "customer_firstname")
    WebElement firstNameInput;
    @FindBy(id = "customer_lastname")
    WebElement lastNameInput;
    @FindBy(id = "address1")
    WebElement addressInput;
    @FindBy(id = "address2")
    WebElement addressNextLineInput;
    @FindBy(id = "company")
    WebElement companyInput;
    @FindBy(id = "days")
    WebElement daysDropDown;
    @FindBy(id = "months")
    WebElement monthsDropdown;
    @FindBy(id = "years")
    WebElement yearsDropDown;
    @FindBy(id = "city")
    WebElement cityInput;
    @FindBy(id = "id_state")
    WebElement stateDropDown;
    @FindBy(id = "postcode")
    WebElement postalCodeInput;
    @FindBy(id = "phone")
    WebElement phone;
    @FindBy(id = "phone_mobile")
    WebElement mobilePhoneInput;
    @FindBy(id = "other")
    WebElement additionalInformationTextArea;
    @FindBy(id = "alias")
    WebElement aliasInput;
    @FindBy(id = "submitAccount")
    WebElement registerButton;
    @FindBy(xpath = "//*[@id=\"center_column\"]/div/ol/li[1]")
    WebElement firstErrorMessage;
    @FindBy(xpath = "//*[@id=\"center_column\"]/div/ol/li[2]")
    WebElement secondErrorMessage;
    @FindBy(xpath = "//*[@id=\"center_column\"]/div/ol/li[3]")
    WebElement thirdErrorMessage;
    @FindBy(xpath = "//*[@id=\"center_column\"]/div/ol/li[4]")
    WebElement fourthErrorMessage;
    @FindBy(xpath = "//*[@id=\"center_column\"]/div/ol/li[5]")
    WebElement fifthErrorMessage;
    @FindBy(xpath = "//*[@id=\"center_column\"]/div/ol/li[6]")
    WebElement sixthErrorMessage;
    @FindBy(xpath = "//*[@id=\"center_column\"]/div/ol/li[7]")
    WebElement seventhErrorMessage;
    @FindBy(xpath = "//*[@id=\"center_column\"]/div/ol/li[8]")
    WebElement eighthErrorMessage;

    Faker faker = new Faker();
    LoremIpsum lorem = LoremIpsum.getInstance();


    Wait wait = new WebDriverWait(driver, 5000);

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
        postalCodeInput.sendKeys(String.valueOf(faker.number().numberBetween(00000, 99999)));
    }

    public void enterWrongPostalCode() {
        postalCodeInput.sendKeys(String.valueOf(faker.number().numberBetween(0000, 9999)));
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
        String textOfFirstErrorMessage = firstErrorMessage.getText();
        return textOfFirstErrorMessage;
    }

    public String returnSecondErrorMessage() {
        String textOfSecondErrorMessage = secondErrorMessage.getText();
        return textOfSecondErrorMessage;
    }

    public String returnThirdErrorMessage() {
        String textOfThirdErrorMessage = thirdErrorMessage.getText();
        return textOfThirdErrorMessage;
    }

    public String returnFourthErrorMessage() {
        String textOfFourthErrorMessage = fourthErrorMessage.getText();
        return textOfFourthErrorMessage;
    }

    public String returnFifthErrorMessage() {
        String textOfFifthErrorMessage = fifthErrorMessage.getText();
        return textOfFifthErrorMessage;
    }

    public String returnSixthErrorMessage() {
        String textOfSixthErrorMessage = sixthErrorMessage.getText();
        return textOfSixthErrorMessage;
    }

    public String returnSeventhErrorMessage() {
        String textOfSeventhErrorMessage = seventhErrorMessage.getText();
        return textOfSeventhErrorMessage;
    }

    public String returnEighthErrorMessage() {
        String textOfEighthErrorMessage = eighthErrorMessage.getText();
        return textOfEighthErrorMessage;
    }
}




