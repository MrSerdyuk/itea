package tests;

import org.testng.Assert;
import org.testng.annotations.*;
import pages.LinkedinHomePage;
import pages.LinkedinLoginPage;

public class LinkedInLoginTest extends LinkedinBaseTest{

    @DataProvider
    public Object[][] successfulCredentialsLoginTest(){
        return new Object[][]{
                {"ITEATEST@I.UA", "1q2w3e_4r5t"},
                {"iteatest@i.ua", "1q2w3e_4r5t"}};
    }
    @Test(dataProvider = "successfulCredentialsLoginTest")
    public void successfulLoginTest(String email, String password) {
        Assert.assertEquals(initialPageTitle, "LinkedIn: Log In or Sign Up", "Login page title is wrong");

        LinkedinHomePage linkedinHomePage = linkedinLandingPage.loginAs(email, password);
        Assert.assertTrue(linkedinHomePage.isSighedIn(), "User is not signed in");

        Assert.assertNotEquals(linkedinLandingPage.getPageTitle(), initialPageTitle, "Page title did not change after login");
        Assert.assertNotEquals(linkedinLandingPage.getPageUrl(), initialPageUrl, "Page url did not change after login");
    }

    @DataProvider
    public Object[][] negativeTestCredentialsReturnToLanding(){
        return new Object[][]{
                {"", ""},
                {"iteatest@i.ua", ""},
                {"", "1q2w3e_4r5t"}};
    }
    @Test(dataProvider = "negativeTestCredentialsReturnToLanding")
    public void negativeTestCredentialsReturnToLanding(String email, String password) {
        linkedinLandingPage.loginAs(email, password);
        Assert.assertEquals(linkedinLandingPage.getPageTitle(), initialPageTitle, "Page title did not change after login");
    }

    @DataProvider
    public Object[][] negativeTestCredentialsReturnToLoginPage(){
        return new Object[][]{
                {"xyzxyz", "xyz", "Please enter a valid email address.", "The password you provided must have at least 6 characters."}};
    }
    @Test(dataProvider = "negativeTestCredentialsReturnToLoginPage")
    public void negativeTestCredentialsReturnToLoginPage(String email, String password, String emailAlert, String passwordAlert) {
        LinkedinLoginPage linkedinLoginPage = linkedinLandingPage.loginAs(email, password);
        Assert.assertTrue(linkedinLoginPage.isNotSignedIn(), "Alert massage is not displayed");
        Assert.assertNotEquals(linkedinLoginPage.getPageTitle(), initialPageTitle, "Page title did not change after login");
        Assert.assertEquals(linkedinLoginPage.getEmailErrorMassage(), emailAlert, "Email alert message is not displayed");
        Assert.assertEquals(linkedinLoginPage.getPasswordErrorMassage(), passwordAlert, "Password alert message is not displayed");
    }

    @DataProvider
    public Object[][] negativeLoginTestWithoutRightPassword(){
        return new Object[][]{
                {"iteatest@i.ua", "xyzzyx", "Hmm, that's not the right password. Please try again or request a new one."}};
    }
    @Test(dataProvider = "negativeLoginTestWithoutRightPassword")
    public void negativeLoginTestWithoutRightPassword(String email, String password, String passwordAlert) {
        LinkedinLoginPage linkedinLoginPage = linkedinLandingPage.loginAs(email, password);
        Assert.assertTrue(linkedinLoginPage.isNotSignedIn(), "Alert massage is not displayed");
        Assert.assertNotEquals(linkedinLoginPage.getPageTitle(), initialPageTitle, "Page title did not change after login");
        Assert.assertEquals(linkedinLoginPage.getPasswordErrorMassage(), passwordAlert, "Password alert message is not displayed");
    }

    @DataProvider
    public Object[][] negativeTestWithDomainSuggestionMessage(){
        return new Object[][]{
                {"iteatest@i.u", "1q2w3e_4r5t"}};
    }
    @Test(dataProvider = "negativeTestWithDomainSuggestionMessage")
    public void negativeTestWithDomainSuggestionMessage(String email, String password) {
        LinkedinLoginPage linkedinLoginPage = linkedinLandingPage.loginAs(email, password);
        Assert.assertTrue(linkedinLoginPage.isNotSignedIn(), "Alert massage is not displayed");
        Assert.assertNotEquals(linkedinLoginPage.getPageTitle(), initialPageTitle, "Page title did not change after login");
        Assert.assertTrue(linkedinLoginPage.isDomainSuggestionDisplayed(), "Domain suggestion message is not displayed");

    }
}
