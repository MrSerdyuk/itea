package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.LinkedinHomePage;
import pages.LinkedinLandingPage;

public class LinkedInLoginTest {
    WebDriver driver;
    LinkedinLandingPage linkedinLandingPage;

    String initialPageUrl;
    String initialPageTitle;

    @BeforeClass
    public void beforeClass() {
    }

    @AfterClass
    public void afterClass() {
    }

    @BeforeMethod
    public void beforeTest() {
        driver = new FirefoxDriver();
        driver.navigate().to("https://www.linkedin.com/");
        linkedinLandingPage = new LinkedinLandingPage(driver);

        initialPageUrl = linkedinLandingPage.getPageUrl();
        initialPageTitle = linkedinLandingPage.getPageTitle();
    }

    @AfterMethod
    public void afterTest() {
        driver.quit();
    }

    @Test
    public void successfulLoginTest() {
        Assert.assertEquals(initialPageTitle, "LinkedIn: Log In or Sign Up", "Login page title is wrong");

        LinkedinHomePage linkedinHomePage = linkedinLandingPage.positiveLogin("iteatest@i.ua", "1q2w3e_4r5t");
        Assert.assertTrue(linkedinHomePage.isSighedIn(), "User is not signed in");

        Assert.assertNotEquals(linkedinLandingPage.getPageTitle(), initialPageTitle, "Page title did not change after login");
        Assert.assertNotEquals(linkedinLandingPage.getPageUrl(), initialPageUrl, "Page url did not change after login");
    }

    @Test
    public void negativeLoginTest() {
        LinkedinHomePage linkedinHomePage = linkedinLandingPage.positiveLogin("iteatest@i.ua", "1q2w3e");
        Assert.assertFalse(linkedinHomePage.isSighedIn(), "Alert massage is not displayed");
    }
}
