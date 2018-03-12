package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.LinkedinHomePage;
import pages.LinkedinLandingPage;
import pages.LinkedinSearchPage;

import java.util.ArrayList;

public class LinkedInSearchTest{
    public static final String SEARCH_TERM = "HR";

    WebDriver driver;
    LinkedinLandingPage linkedinLandingPage;
    LinkedinHomePage linkedinHomePage;
    LinkedinSearchPage linkedinSearchPage;


    @BeforeMethod
    public void beforeTest() {
        driver = new FirefoxDriver();
        driver.navigate().to("https://www.linkedin.com/");

        linkedinLandingPage = new LinkedinLandingPage(driver);
        linkedinHomePage = new LinkedinHomePage(driver);
        linkedinSearchPage = new LinkedinSearchPage(driver);
    }

    @AfterMethod
    public void afterTest() {
        driver.quit();
    }

    @Test
    public void basicSearchTest() {
        LinkedinHomePage linkedinHomePage = linkedinLandingPage.positiveLogin("iteatest@i.ua", "1q2w3e_4r5t");
        linkedinHomePage.waitUntilElementIsClickable(linkedinHomePage.userIcon, 5);

        LinkedinSearchPage linkedinSearchPage = linkedinHomePage.linkedinSearchPage(SEARCH_TERM);

        linkedinSearchPage.waitUntilElementIsClickable(linkedinSearchPage.totalSearchResults);

        ArrayList elementIndexNotContainsSearchTerm = linkedinSearchPage.getElementIndexListNotContainsSearchTerm(SEARCH_TERM);
        Assert.assertTrue(elementIndexNotContainsSearchTerm.isEmpty(),
                "Result item does not contain search term in " +elementIndexNotContainsSearchTerm+" rows");

        Assert.assertEquals(linkedinSearchPage.searchResultsList.size(), 10, "There are displayed not 10 results");
    }
}
