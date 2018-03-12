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

    @FindBy(xpath = "//div[@id='global-alert-queue']//strong[not(text()='')]")
    public WebElement alertMassage;

    public LinkedinLandingPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    private <T> T loginAs(String userName, String password, Class<T> expectedPage){
        passwordField.sendKeys(password);
        emailField.sendKeys(userName);
        signInButton.click();
        return PageFactory.initElements(driver, expectedPage);
    }

    public LinkedinHomePage positiveLogin(String userName, String password){
        return loginAs(userName, password, LinkedinHomePage.class);
    }

    public LinkedinLandingPage negativeLogin(String userName, String password){
        return loginAs(userName, password, LinkedinLandingPage.class);
    }
}
