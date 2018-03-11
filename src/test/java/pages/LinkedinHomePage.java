package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LinkedinHomePage extends LinkedinBasePage {

    @FindBy(xpath = "//*[@id='profile-nav-item']")
    public WebElement userIcon;

    public LinkedinHomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean isSighedIn() {
        waitUntilElementIsClickable(userIcon);
        return userIcon.isDisplayed();
    }

}
