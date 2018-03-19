package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LinkedinLoginPage extends LinkedinBasePage{

    @FindBy(id = "session_key-login")
    private WebElement emailField;

    @FindBy(id = "session_passwaord-login")
    private WebElement passwordField;

    @FindBy(id = "btn-primary")
    private WebElement signInButton;

    @FindBy(xpath = "//div[@id='global-alert-queue']//strong[not(text()='')]")
    private WebElement alertMassage;

    @FindBy(id = "session_key-login-error")
    private WebElement emailErrorMassage;

    @FindBy(id = "session_password-login-error")
    private WebElement passwordErrorMassage;

    @FindBy(id = "domainSuggestion")
    private WebElement domainSuggestionMassage;


    public LinkedinLoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean isNotSignedIn() {
        return alertMassage.isDisplayed();
    }

    public String getEmailErrorMassage() {
        return emailErrorMassage.getText();
    }

    public String getPasswordErrorMassage() {
        return passwordErrorMassage.getText();
    }

    public boolean isDomainSuggestionDisplayed() {
        return domainSuggestionMassage.isDisplayed();
    }
}
