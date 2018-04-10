package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class LinkedinBasePage {
    WebDriver driver;

    /**
     * Constructor of LinkedinBasePage class which takes WebDriver instance and uses initialized in @BeforeMethod
     * for reuse in LinkedinBasePage class methods
     * @param driver - WebDriver instance
     */
    public LinkedinBasePage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Method waits 5 seconds until WebElement is clickable on Web page
     * @param webElement after wait
     */
    public void waitUntilElementIsClickable(WebElement webElement) {
        waitUntilElementIsClickable(webElement, 5);
    }

    /**
     * Method waits until WebElement is clickable on Web page
     * @param webElement after wait
     * @param timeoutInSeconds is time to wait
     */
    public void waitUntilElementIsClickable(WebElement webElement, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
    }

    /**
     * Method gets Page Title
     * @return Page title of current page
     */
    public String getPageTitle() {
        return driver.getTitle();
    }

    /**
     * Method gets Page URL
     * @return current Page URL
     */
    public String getPageUrl() {
        return driver.getCurrentUrl();
    }

    /**
     * Abstract method waits WebElement is visible on Page
     * @return true of false
     */
    public abstract boolean isLoaded();
}
