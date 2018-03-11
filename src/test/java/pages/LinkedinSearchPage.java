package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class LinkedinSearchPage extends LinkedinBasePage {

    @FindBy(xpath = "//*[@role='combobox']")
    public WebElement searchField;

    @FindBy(xpath = "//span[@class='svg-icon-wrap']//li-icon[@type='search-icon']")
    public WebElement searchIcon;

    @FindBy(xpath = "//li[contains(@class,'search-result__occluded-item')]")
    public List<WebElement> searchResultsList;

    @FindBy(xpath = "//h3[contains(@class, 'search-results__total')]")
    public WebElement totalSearchResults;

    public LinkedinSearchPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
}
