package pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LinkedinHomePage extends LinkedinBasePage {

    @FindBy(xpath = "//*[@id='profile-nav-item']")
    private WebElement userIcon;

    @FindBy(xpath = "//*[@role='combobox']")
    private WebElement searchField;

    @FindBy(xpath = "//span[@class='svg-icon-wrap']//li-icon[@type='search-icon']")
    private WebElement searchIcon;

    /**
     * Method initialize WebElements on Page
     * @param driver
     */
    public LinkedinHomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    /**
     * Method checks visibility of WebElement on Page
     * @return true or false
     */
    public boolean isSighedIn() {
        waitUntilElementIsClickable(userIcon);
        return userIcon.isDisplayed();
    }

    /**
     * Method sets @param in Search Field and gets new Page with searching results
     * @param searchTerm
     * @return new Page if method result is correct
     */
    public LinkedinSearchPage getLinkedinSearchPage(String searchTerm) {
        searchField.sendKeys(searchTerm);
        searchIcon.click();
        return new LinkedinSearchPage(driver);
    }

    /**
     * Method waits for WebElement is visible on Page
     * @return true if WebElement is on the Page
     */
    public boolean isLoaded() {
        boolean isLoaded;
        try {
            isLoaded = userIcon.isDisplayed();
        }
        catch (NoSuchElementException e){
            isLoaded = false;
        }
        return isLoaded;
    }
}
