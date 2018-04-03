package pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LinkedinRequestPasswordResetPage extends LinkedinBasePage{

    @FindBy(xpath = "//input[@id='userName-requestPasswordReset']")
    private WebElement userNameField;

    @FindBy(xpath = "//input[@id='btnSubmitResetRequest']")
    private WebElement submitButton;

    /**
     * Method initialize WebElements on Page
     * @param driver
     */
    public LinkedinRequestPasswordResetPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    /**
     * Method sends the letter to @param email
     * @param userEmail is email
     * @return new Page
     */
    public LinkedinPasswordResetSubmitPage submitEmail(String userEmail) {
        userNameField.sendKeys(userEmail);
        submitButton.click();
        return new LinkedinPasswordResetSubmitPage(driver);
    }

    /**
     * Method waits for WebElement is visible on Page
     * @return true if WebElement is on the Page
     */
    public boolean isLoaded() {
        boolean isLoaded;
        try {
            isLoaded = submitButton.isDisplayed();
        } catch (NoSuchElementException e) {
            isLoaded = false;
        }
        return isLoaded;
    }
}
