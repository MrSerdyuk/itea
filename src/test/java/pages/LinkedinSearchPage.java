package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class LinkedinSearchPage extends LinkedinBasePage {

    @FindBy(xpath = "//li[contains(@class,'search-result__occluded-item')]")
    public List<WebElement> searchResultsList;

    @FindBy(xpath = "//h3[contains(@class, 'search-results__total')]")
    public WebElement totalSearchResults;

    public LinkedinSearchPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public ArrayList getElementIndexListNotContainsSearchTerm(String searchTerm) {
        ArrayList list = new ArrayList();

        for (int i = 0; i < searchResultsList.size(); i++) {
            searchResultsList.get(i).click();
            String searchItemName = searchResultsList.get(i).getText();
            if (!searchItemName.contains(searchTerm)) {
                list.add(i+1);
            }
        }
        return list;
    }
}
