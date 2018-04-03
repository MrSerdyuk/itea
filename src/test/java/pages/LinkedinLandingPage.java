package pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LinkedinLandingPage extends LinkedinBasePage {

    @FindBy(id = "login-email")
    private WebElement emailField;

    @FindBy(id = "login-password")
    private WebElement passwordField;

    @FindBy(id = "login-submit")
    private WebElement signInButton;

    @FindBy(xpath = "//a[@class='link-forgot-password']")
    private WebElement forgotPasswordLink;

    /**
     * Method initialize WebElements on Page
     * @param driver
     */
    public LinkedinLandingPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    /**
     * Method clicks on WebElement
     * @return new Page
     */
    public LinkedinRequestPasswordResetPage forgotPasswordLinkClick() {
        forgotPasswordLink.click();
        return new LinkedinRequestPasswordResetPage(driver);
    }

    /**
     * Method sets @params in WebElements and submits
     * @param userName is parameter for WebElement
     * @param password is parameter for WebElement
     * @param <T>
     * @return three different Pages according to @params
     */
    public  <T> T loginAs(String userName, String password){
        passwordField.sendKeys(password);
        emailField.sendKeys(userName);
        signInButton.click();
        if(getPageUrl().contains("/feed"))
        {
            return (T) new LinkedinHomePage(driver);
        }
        if(getPageUrl().contains("/login-submit"))
        {
            return (T) new LinkedinLoginPage(driver);
        }
        else
            return (T) this;
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
