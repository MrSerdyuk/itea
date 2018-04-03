package pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LinkedinSuccessfulChangePasswordPage extends LinkedinBasePage{

    @FindBy(xpath = "//header[@class='content__header']")
    WebElement successfulMessage;

    /**
     * Method initialize WebElements on Page
     * @param driver
     */
    public LinkedinSuccessfulChangePasswordPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    /**
     * Method gets WebElement if displayed
     * @return WebElement
     */
    public WebElement getSuccessfulMessage() {
        waitUntilElementIsClickable(successfulMessage);
        return successfulMessage;
    }

    /**
     * Method waits for WebElement is visible on Page
     * @return true if WebElement is on the Page
     */
    public boolean isLoaded() {
        boolean isLoaded;
        try {
            isLoaded = successfulMessage.isDisplayed();
        }
        catch (NoSuchElementException e){
            isLoaded = false;
        }
        return isLoaded;
    }
}
