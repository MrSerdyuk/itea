import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LinkedInLogInPage {
    WebDriver driver;

    public LinkedInLogInPage (WebDriver driver) {
        this.driver = driver;
    }

    private WebElement emailField ;
    private WebElement passwordField;
    private WebElement signInButton;

    private void initElements() {
        emailField = driver.findElement(By.xpath("//*[@id='login-email']"));
        passwordField = driver.findElement(By.id("login-password"));
        signInButton = driver.findElement(By.id("login-submit"));

    }
    public void loginAs(String userName, String password) {
        initElements();
        waitUntilElementIsClickable(emailField, 5);
        passwordField.sendKeys(password);
        emailField.sendKeys(userName);
        signInButton.click();
    }

    public void waitUntilElementIsClickable(WebElement webElement) {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
    }

    public void waitUntilElementIsClickable(WebElement webElement, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
    }
}
