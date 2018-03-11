package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.LinkedinHomePage;
import pages.LinkedinLandingPage;
import pages.LinkedinSearchPage;

public class LinkedInSearchTest{
    WebDriver driver;
    LinkedinLandingPage linkedinLandingPage;
    LinkedinHomePage linkedinHomePage;
    LinkedinSearchPage linkedinSearchPage;
    String searchTerm;


    @BeforeMethod
    public void beforeTest() {
        driver = new FirefoxDriver();
        driver.navigate().to("https://www.linkedin.com/");

        linkedinLandingPage = new LinkedinLandingPage(driver);
        linkedinHomePage = new LinkedinHomePage(driver);
        linkedinSearchPage = new LinkedinSearchPage(driver);
        searchTerm = "HR";
    }

    @AfterMethod
    public void afterTest() {
        driver.quit();
    }

    @Test
    public void basicSearchTest() {
        LinkedinHomePage linkedinHomePage = linkedinLandingPage.positiveLogin("iteatest@i.ua", "1q2w3e_4r5t");
        linkedinHomePage.waitUntilElementIsClickable(linkedinHomePage.userIcon, 5);

        linkedinSearchPage.searchField.sendKeys(searchTerm);
        linkedinSearchPage.searchIcon.click();

        linkedinSearchPage.waitUntilElementIsClickable(linkedinSearchPage.totalSearchResults);

        for(int i = 0; i < linkedinSearchPage.searchResultsList.size(); i++)
        {
            linkedinSearchPage.searchResultsList.get(i).click();
            String searchItemName = linkedinSearchPage.searchResultsList.get(i).getText();
            Assert.assertTrue(searchItemName.contains(searchTerm), "Result item does not contain search term in " +(i+1)+" row");
        }
        Assert.assertEquals(linkedinSearchPage.searchResultsList.size(), 10, "There are displayed not 10 results");
    }
}
