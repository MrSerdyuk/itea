import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class LinkedinLoginPage extends BaseTestPage{

    public LinkedinLoginPage(WebDriver driver) {
        super(driver);
    }

    public boolean isSighedIn() {
        WebElement userIcon = driver.findElement(By.xpath("//*[@id='profile-nav-item']"));
        waitUntilElementIsClickable(userIcon);
        return userIcon.isDisplayed();
    }

    public boolean isNotSignedIn() {
        WebElement alertMassage = driver.findElement(By.xpath("//div[@id='global-alert-queue']//strong[not(text()='')]"));
        return alertMassage.isDisplayed();
    }

    public LinkedinMainPage loginAs(String userName, String password) {
        try {
            WebElement emailField = driver.findElement(By.xpath("//*[@id='login-email']"));
            WebElement passwordField = driver.findElement(By.id("login-password"));
            WebElement signInButton = driver.findElement(By.id("login-submit"));

            waitUntilElementIsClickable(emailField, 10);

            passwordField.sendKeys(password);
            emailField.sendKeys(userName);
            signInButton.click();
            if (isSighedIn())
                return new LinkedinMainPage(driver);
        } catch (NoSuchElementException e) {
            return null;
        }
        return null;
    }
}
