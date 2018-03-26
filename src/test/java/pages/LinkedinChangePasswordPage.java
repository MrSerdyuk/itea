package pages;

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
    WebElement resetPasswordSubmitButton;

    public LinkedinChangePasswordPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public LinkedinSuccessfulChangePasswordPage submitNewPassword(String newPassword) {
        waitUntilElementIsClickable(newPasswordInput);
        newPasswordInput.sendKeys(newPassword);
        confirmPasswordInput.sendKeys(newPassword);
        resetPasswordSubmitButton.click();
        return new LinkedinSuccessfulChangePasswordPage(driver);
    }
}
