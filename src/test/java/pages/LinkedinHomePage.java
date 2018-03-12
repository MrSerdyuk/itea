package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LinkedinHomePage extends LinkedinBasePage {

    @FindBy(xpath = "//*[@id='profile-nav-item']")
    public WebElement userIcon;

    @FindBy(xpath = "//*[@role='combobox']")
    public WebElement searchField;

    @FindBy(xpath = "//span[@class='svg-icon-wrap']//li-icon[@type='search-icon']")
    public WebElement searchIcon;

    public LinkedinHomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean isSighedIn() {
        waitUntilElementIsClickable(userIcon);
        return userIcon.isDisplayed();
    }

    public LinkedinSearchPage linkedinSearchPage(String searchTerm) {
        searchField.sendKeys(searchTerm);
        searchIcon.click();
        return PageFactory.initElements(driver, LinkedinSearchPage.class);
    }
}
