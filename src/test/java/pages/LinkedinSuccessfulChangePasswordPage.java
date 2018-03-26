package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LinkedinSuccessfulChangePasswordPage extends LinkedinBasePage{

    @FindBy(xpath = "//header[@class='content__header']")
    WebElement successfulMessage;

    public LinkedinSuccessfulChangePasswordPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public WebElement getSuccessfulMessage() {
        waitUntilElementIsClickable(successfulMessage);
        return successfulMessage;
    }
}
