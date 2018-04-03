package pages;

import org.openqa.selenium.NoSuchElementException;
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


    /**
     * Method initialize WebElements on Page
     * @param driver
     */
    public LinkedinLoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    /**
     * Method checks the result of the negative LogIn attempt
     * @return true if WebElement is displayed on Page
     */
    public boolean isNotSignedIn() {
        return alertMassage.isDisplayed();
    }

    /**
     * Method gets error text of visible WebElement if there is email error
     * @return text of WebElement
     */
    public String getEmailErrorMassage() {
        return emailErrorMassage.getText();
    }

    /**
     * Method gets error text of visible WebElement if there is password error
     * @return text of WebElement
     */
    public String getPasswordErrorMassage() {
        return passwordErrorMassage.getText();
    }

    /**
     * Method gets error text of visible WebElement if there is email domain error
     * @return true if WebElement is displayed on Page
     */
    public boolean isDomainSuggestionDisplayed() {
        return domainSuggestionMassage.isDisplayed();
    }

    /**
     * Method waits for WebElement is visible on Page
     * @return true if WebElement is on the Page
     */
    public boolean isLoaded() {
        boolean isLoaded;
        try {
            isLoaded = emailField.isDisplayed();
        }
        catch (NoSuchElementException e){
            isLoaded = false;
        }
        return isLoaded;
    }
}
