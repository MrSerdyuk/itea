package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LinkedinPasswordResetSubmitPage;
import pages.LinkedinRequestPasswordResetPage;
import utils.GMailService;

public class LinkedinResetPasswordTest extends LinkedinBaseTest {

    String userEmail = "testiteatest@gmail.com";

    @Test
    public void successfulPasswordResetTest() {
        LinkedinRequestPasswordResetPage linkedinRequestPasswordResetPage = linkedinLandingPage.forgotPasswordLinkClick();
        Assert.assertTrue(linkedinRequestPasswordResetPage.isLoaded(), "LinkedinRequestPasswordResetPage is not loaded");

        LinkedinPasswordResetSubmitPage linkedinPasswordResetSubmitPage = linkedinRequestPasswordResetPage.submitEmail(userEmail);
        Assert.assertTrue(linkedinPasswordResetSubmitPage.isLoaded(), "LinkedinPasswordResetSubmitPage is not loaded");

        String messageSubjectPartial = "here's the link to reset your password";
        String messageToPartial = userEmail;
        String messageFromPartial = "security-noreply@linkedin.com";

        GMailService GMailService = new GMailService();
        String message = GMailService.waitForNewMessage(messageSubjectPartial, messageToPartial, messageFromPartial, 60);
        System.out.println("Content: " + message);


    }

}
