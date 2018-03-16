package pages;

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

    public LinkedinLandingPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

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
}
