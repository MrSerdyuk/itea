import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LinkedinBasePage {
    WebDriver driver;
    WebElement userIcon;

    public LinkedinBasePage (WebDriver driver) {
        this.driver = driver;
    }

    private void initElements() {
        userIcon = driver.findElement(By.xpath("//*[@id='profile-nav-item']"));
    }

    public boolean isSighedIn() {
        initElements();
        waitUntilElementIsClickable(userIcon);
        return userIcon.isDisplayed();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public String getPageUrl() {
        return driver.getCurrentUrl();
    }

    public void waitUntilElementIsClickable(WebElement webElement) {
        waitUntilElementIsClickable(webElement, 5);
    }

    public void waitUntilElementIsClickable(WebElement webElement, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
    }
}
