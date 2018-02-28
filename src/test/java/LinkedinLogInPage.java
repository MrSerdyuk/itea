import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LinkedinLogInPage extends LinkedinBasePage{
    WebDriver driver;
    WebElement alertMassage;

    public LinkedinLogInPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    private WebElement emailField ;
    private WebElement passwordField;
    private WebElement signInButton;

    private void initElements() {
        emailField = driver.findElement(By.xpath("//*[@id='login-email']"));
        passwordField = driver.findElement(By.id("login-password"));
        signInButton = driver.findElement(By.id("login-submit"));
        alertMassage = driver.findElement(By.xpath("//div[@id='global-alert-queue']//strong[not(text()='')]"));
    }
    public LinkedinBasePage loginAs(String userName, String password) {
        initElements();
        waitUntilElementIsClickable(emailField, 5);
        passwordField.sendKeys(password);
        emailField.sendKeys(userName);
        signInButton.click();
        if (isSighedIn())
            return new LinkedinBasePage(driver);
        else
            return new LinkedinLogInPage(driver);
    }


}
