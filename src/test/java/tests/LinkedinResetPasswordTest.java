package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LinkedinChangePasswordPage;
import pages.LinkedinPasswordResetSubmitPage;
import pages.LinkedinRequestPasswordResetPage;
import pages.LinkedinSuccessfulChangePasswordPage;

public class LinkedinResetPasswordTest extends LinkedinBaseTest {

    String userEmail = "testiteatest@gmail.com";
    String newPassword = "1q2w3e_4r5t";

    @Test
    public void successfulPasswordResetTest() {
        LinkedinRequestPasswordResetPage linkedinRequestPasswordResetPage = linkedinLandingPage.forgotPasswordLinkClick();
        Assert.assertTrue(linkedinRequestPasswordResetPage.isLoaded(), "LinkedinRequestPasswordResetPage is not loaded");

        LinkedinPasswordResetSubmitPage linkedinPasswordResetSubmitPage = linkedinRequestPasswordResetPage.submitEmail(userEmail);
        String resetPasswordLink = linkedinPasswordResetSubmitPage.getResetPasswordLinkFromEmail(userEmail);
        Assert.assertTrue(linkedinPasswordResetSubmitPage.isLoaded(), "LinkedinPasswordResetSubmitPage is not loaded");

        LinkedinChangePasswordPage linkedinChangePasswordPage = linkedinPasswordResetSubmitPage.navigateToResetPasswordLink(resetPasswordLink);
        Assert.assertTrue(linkedinChangePasswordPage.isLoaded(), "chooseNewPasswordPage is not loaded");

        LinkedinSuccessfulChangePasswordPage linkedinSuccessfulChangePasswordPage = linkedinChangePasswordPage.submitNewPassword(newPassword);
        Assert.assertTrue(linkedinSuccessfulChangePasswordPage.getSuccessfulMessage().isDisplayed(), "Something went wrong: you didn't change password");
    }

}
