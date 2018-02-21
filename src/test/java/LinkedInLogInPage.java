import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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
        passwordField.sendKeys(password);
        emailField.sendKeys(userName);
        signInButton.click();
    }


}
