package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LinkedinChangePasswordPage;
import pages.LinkedinPasswordResetSubmitPage;
import pages.LinkedinRequestPasswordResetPage;
import pages.LinkedinSuccessfulChangePasswordPage;
import utils.GMailService;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LinkedinResetPasswordTest extends LinkedinBaseTest {

    private static final Pattern urlPattern = Pattern.compile(
            "(?:^|[\\W])((ht|f)tp(s?):\\/\\/|www\\.)"
                    + "(([\\w\\-]+\\.){1,}?([\\w\\-.~]+\\/?)*"
                    + "[\\p{Alnum}.,%_=?&#\\-+()\\[\\]\\*$~@!:/{};']*)",
            Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);

    String userEmail = "testiteatest@gmail.com";

    @Test
    public void successfulPasswordResetTest() {
        LinkedinRequestPasswordResetPage linkedinRequestPasswordResetPage = linkedinLandingPage.forgotPasswordLinkClick();
        Assert.assertTrue(linkedinRequestPasswordResetPage.isLoaded(), "LinkedinRequestPasswordResetPage is not loaded");

        LinkedinPasswordResetSubmitPage linkedinPasswordResetSubmitPage = linkedinRequestPasswordResetPage.submitEmail(userEmail);
        Assert.assertTrue(linkedinPasswordResetSubmitPage.isLoaded(), "LinkedinPasswordResetSubmitPage is not loaded");

        String messageSubjectPartial = "here's the link to reset your password";
        String messageToPartial = "testiteatest@gmail.com";
        String messageFromPartial = "security-noreply@linkedin.com";

        GMailService GMailService = new GMailService();
        String message = GMailService.waitForNewMessage(messageSubjectPartial, messageToPartial, messageFromPartial, 60);
        System.out.println("Content: " + message);

        Matcher matcher = urlPattern.matcher(message);
        String link;
        if (matcher.find()) {
            int matchStart = matcher.start(1);
            int matchEnd = matcher.end();
            link = message.substring(matchStart, matchEnd);
            driver.get(link);
        }

        LinkedinChangePasswordPage linkedinChangePasswordPage = new LinkedinChangePasswordPage(driver);
        LinkedinSuccessfulChangePasswordPage linkedinSuccessfulChangePasswordPage = linkedinChangePasswordPage.submitNewPassword("1q2w3e_4r5t");
        Assert.assertTrue(linkedinSuccessfulChangePasswordPage.getSuccessfulMessage().isDisplayed(), "Something went wrong: you didn't change password");
    }

}
