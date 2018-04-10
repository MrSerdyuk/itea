package pages;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.GMailService;

public class LinkedinPasswordResetSubmitPage extends LinkedinBasePage{

    @FindBy(xpath = "//a[@class='status-link btn-resend-link']")
    WebElement resetLinkButton;

    /**
     * Constructor of LinkedinPasswordResetSubmitPage class that takes WebDriver instance from LinkedinBasePage class
     * and initialize LinkedinPasswordResetSubmitPage WebElements via PageFactory
     * @param driver - WebDriver instance
     */
    public LinkedinPasswordResetSubmitPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    /**
     * Method waits for WebElement is visible on Page
     * @return true if WebElement is on the Page
     */
    public boolean isLoaded() {
        boolean isLoaded;
        try {
            isLoaded = resetLinkButton.isDisplayed();
        }
        catch (NoSuchElementException e){
            isLoaded = false;
        }
        return isLoaded;
    }

    /**
     * Method gets @param and open it browser with WebDriver
     * @param resetPasswordLink
     * @return new reset password Page
     */
    public LinkedinChangePasswordPage navigateToResetPasswordLink(String resetPasswordLink) {
        driver.get(resetPasswordLink);
        return new LinkedinChangePasswordPage(driver);
    }

    /**
     * Method find the link in the email
     * @param messageToPartial is users email
     * @return link to reset password page
     */
    public String getResetPasswordLinkFromEmail(String messageToPartial) {
        String messageSubjectPartial = "here's the link to reset your password";
        String messageFromPartial = "security-noreply@linkedin.com";
        GMailService GMailService = new GMailService();
        String message = GMailService.waitForNewMessage(messageSubjectPartial, messageToPartial, messageFromPartial, 60);

        String resetPasswordLink = StringUtils.substringBetween(message, "browser:", "This link").trim();
        return resetPasswordLink;
    }
}
