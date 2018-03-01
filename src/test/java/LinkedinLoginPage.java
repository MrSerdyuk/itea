import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LinkedinLoginPage extends LinkedinBasePage {
    WebDriver driver;

    public LinkedinLoginPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    private WebElement emailField;
    private WebElement passwordField;
    private WebElement signInButton;
    private WebElement alertMassage;

    private void initElements() {
        emailField = driver.findElement(By.xpath("//*[@id='login-email']"));
        passwordField = driver.findElement(By.id("login-password"));
        signInButton = driver.findElement(By.id("login-submit"));
    }

    private void initAlertMessage() {
        alertMassage = driver.findElement(By.xpath("//div[@id='global-alert-queue']//strong[not(text()='')]"));
    }

    public boolean isNotSignedIn() {
        initAlertMessage();
        waitUntilElementIsClickable(alertMassage);
        return alertMassage.isDisplayed();
    }

    public LinkedinBasePage loginAs(String userName, String password) {
        try {
            initElements();
            waitUntilElementIsClickable(emailField, 5);
            passwordField.sendKeys(password);
            emailField.sendKeys(userName);
            signInButton.click();
            if (isSighedIn())
                return new LinkedinBasePage(driver);
        } catch (NoSuchElementException e) {
            return new LinkedinLoginPage(driver);
        }
        return null;
    }
}
