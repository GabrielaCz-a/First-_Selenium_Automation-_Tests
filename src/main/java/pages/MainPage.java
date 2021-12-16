package pages;

import com.thedeanda.lorem.LoremIpsum;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends BasePage {
    public MainPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "search_query_top")
    WebElement searchInput;
    @FindBy(name = "submit_search")
    WebElement searchButton;
    @FindBy(xpath = "//*[@id=\"center_column\"]/p")
    WebElement errorMessage;
    @FindBy(className = "product-count")
    WebElement countOfProducts;

    LoremIpsum lorem = LoremIpsum.getInstance();

    public void enterCorrectSearchKeys() {
        searchInput.sendKeys("summer dresses");
    }

    public void enterWrongSearchKeys() {
        searchInput.sendKeys("sjkaidiai");
    }

    public void enterTooManySearchKeys() {
        searchInput.sendKeys(lorem.getWords(1500));
    }

    public void clickSearchButton() {
        searchButton.click();
    }

    public String returnTextWithCountOfProducts() {
        String textWithCountOfProducts = countOfProducts.getText();
        return textWithCountOfProducts;
    }

    public String returnErrorMessage() {
        String textOfErrorMessage = errorMessage.getText();
        return textOfErrorMessage;
    }
}
