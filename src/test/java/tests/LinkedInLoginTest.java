package tests;

import org.testng.Assert;
import org.testng.annotations.*;
import pages.LinkedinHomePage;
import pages.LinkedinLoginPage;

public class LinkedInLoginTest extends LinkedinBaseTest{

    @Test
    public void successfulLoginTest() {
        Assert.assertEquals(initialPageTitle, "LinkedIn: Log In or Sign Up", "Login page title is wrong");

        LinkedinHomePage linkedinHomePage = linkedinLandingPage.loginAs("iteatest@i.ua", "1q2w3e_4r5t");
        Assert.assertTrue(linkedinHomePage.isSighedIn(), "User is not signed in");

        Assert.assertNotEquals(linkedinLandingPage.getPageTitle(), initialPageTitle, "Page title did not change after login");
        Assert.assertNotEquals(linkedinLandingPage.getPageUrl(), initialPageUrl, "Page url did not change after login");
    }

    @DataProvider
    public Object[][] negativeTestCredentialsReturnToLanding(){
        return new Object[][]{
                {"", ""}};
    }

    @Test(dataProvider = "negativeTestCredentialsReturnToLanding")
    public void negativeTestCredentialsReturnToLanding(String email, String password) {
        linkedinLandingPage.loginAs(email, password);
        Assert.assertEquals(linkedinLandingPage.getPageTitle(), initialPageTitle, "Page title did not change after login");
    }

    @DataProvider
    public Object[][] negativeTestCredentialsReturnToLoginPage(){
        return new Object[][]{
                {"xyzxyz", "xyz", "Please enter a valid email address", "The password you provided must have at least 6 characters"}};
    }

    @Test(dataProvider = "negativeTestCredentialsReturnToLoginPage")
    public void negativeTestCredentialsReturnToLoginPage(String email, String password, String emailAlert, String passwordAlert) {
        LinkedinLoginPage linkedinLoginPage = linkedinLandingPage.loginAs(email, password);
        Assert.assertTrue(linkedinLoginPage.isNotSignedIn(), "Alert massage is not displayed");
        Assert.assertNotEquals(linkedinLoginPage.getPageTitle(), initialPageTitle, "Page title did not change after login");

        //создать 2 метода для возврата  String emailAlert, String passwordAlert и вссунуть их в ассерты
    }
}
