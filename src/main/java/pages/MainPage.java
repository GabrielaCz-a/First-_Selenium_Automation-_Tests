package pages;

import com.thedeanda.lorem.LoremIpsum;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainPage extends BasePage {
    public MainPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "search_query_top")
    private WebElement searchInput;
    @FindBy(name = "submit_search")
    private WebElement searchButton;
    @FindBy(xpath = "//*[@id=\"center_column\"]/p")
    private WebElement errorMessage;

    LoremIpsum lorem = LoremIpsum.getInstance();

    public void enterWrongSearchKeys() {
        searchInput.sendKeys("dcicn");
    }

    public void enterTooManySearchKeys() {
        searchInput.sendKeys(lorem.getWords(1500));
    }

    public void clickSearchButton() {
        searchButton.click();
    }

    public String returnErrorMessage() {
        return errorMessage.getText();
    }
}
