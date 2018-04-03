package pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class LinkedinSearchPage extends LinkedinBasePage {

    @FindBy(xpath = "//li[contains(@class,'search-result__occluded-item')]")
    private List<WebElement> searchResultsList;

    @FindBy(xpath = "//h3[contains(@class, 'search-results__total')]")
    private WebElement totalSearchResults;

    /**
     * Method initialize WebElements on Page
     * @param driver
     */
    public LinkedinSearchPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    /**
     * Method finds all search elements and writes them in String list after searching
     * @return String list of elements after search
     */
    public List<String> getResults() {
        waitUntilElementIsClickable(totalSearchResults);
        List<String> list = new ArrayList<>();

        for (int i = 0; i < searchResultsList.size(); i++) {
            searchResultsList.get(i).click();
            String searchItemName = searchResultsList.get(i).getText().toLowerCase();
            list.add(searchItemName);
        }
        return list;
    }

    /**
     * @return list of WebElements after search
     */
    public List<WebElement> getSearchResultsList() {
        return searchResultsList;
    }

    /**
     * Method waits for WebElement is visible on Page
     * @return true if WebElement is on the Page
     */
    public boolean isLoaded() {
        boolean isLoaded;
        try {
            isLoaded = totalSearchResults.isDisplayed();
        }
        catch (NoSuchElementException e){
            isLoaded = false;
        }
        return isLoaded;
    }
}
