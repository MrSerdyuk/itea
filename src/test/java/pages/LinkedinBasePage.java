package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LinkedinBasePage {
    WebDriver driver;

    public LinkedinBasePage(WebDriver driver) {
        this.driver = driver;
    }

    public void waitUntilElementIsClickable(WebElement webElement) {
        waitUntilElementIsClickable(webElement, 5);
    }

    public void waitUntilElementIsClickable(WebElement webElement, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public String getPageUrl() {
        return driver.getCurrentUrl();
    }
}
