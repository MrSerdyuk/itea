package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LinkedinRequestPasswordResetPage extends LinkedinBasePage{

    @FindBy(xpath = "//input[@id='userName-requestPasswordReset']")
    private WebElement userNameField;

    @FindBy(xpath = "//input[@id='btnSubmitResetRequest']")
    private WebElement submitButton;

    public LinkedinRequestPasswordResetPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public LinkedinPasswordResetSubmitPage submitEmail(String userEmail) {
        userNameField.sendKeys(userEmail);
        submitButton.click();
        return new LinkedinPasswordResetSubmitPage(driver);
    }

    public boolean isLoaded() {
        boolean isLoaded;
        try {
            isLoaded = submitButton.isDisplayed();
        } catch (NullPointerException e) {
            isLoaded = false;
        }
        return isLoaded;
    }
}
