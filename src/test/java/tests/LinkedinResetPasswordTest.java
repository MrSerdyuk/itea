package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LinkedinPasswordResetSubmitPage;
import pages.LinkedinRequestPasswordResetPage;

public class LinkedinResetPasswordTest extends LinkedinBaseTest {

    String userEmail = "iteatest@i.ua";

    @Test
    public void successfulPasswordResetTest() {
        LinkedinRequestPasswordResetPage linkedinRequestPasswordResetPage = linkedinLandingPage.forgotPasswordLinkClick();
        Assert.assertTrue(linkedinRequestPasswordResetPage.isLoaded(), "LinkedinRequestPasswordResetPage is not loaded");

        LinkedinPasswordResetSubmitPage linkedinPasswordResetSubmitPage = linkedinRequestPasswordResetPage.submitEmail(userEmail);
        Assert.assertTrue(linkedinPasswordResetSubmitPage.isLoaded(), "LinkedinPasswordResetSubmitPage is not loaded");




    }

}
