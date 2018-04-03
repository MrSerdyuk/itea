package pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LinkedinChangePasswordPage extends LinkedinBasePage{

    @FindBy(xpath = "//input[@name='new_password']")
    WebElement newPasswordInput;

    @FindBy(xpath = "//input[@name='new_password_again']")
    WebElement confirmPasswordInput;

    @FindBy(xpath = "//input[@name='reset']")
    WebElement submitPasswordButton;

    /**
     * Method initialize WebElements on Page
     * @param driver
     */
    public LinkedinChangePasswordPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    /**
     * Method sets @param in WebElements and confirm new password
     * @param newPassword
     * @return new Page if method result is correct
     */
    public LinkedinSuccessfulChangePasswordPage submitNewPassword(String newPassword) {
        waitUntilElementIsClickable(newPasswordInput);
        newPasswordInput.sendKeys(newPassword);
        confirmPasswordInput.sendKeys(newPassword);
        submitPasswordButton.click();
        return new LinkedinSuccessfulChangePasswordPage(driver);
    }

    /**
     * Method waits for WebElement is visible on Page
     * @return true if WebElement is on the Page
     */
    public boolean isLoaded() {
        boolean isLoaded;
        try {
            isLoaded = newPasswordInput.isDisplayed();
        }
        catch (NoSuchElementException e){
            isLoaded = false;
        }
        return isLoaded;
    }
}
