package tests;

import org.testng.Assert;
import org.testng.annotations.*;
import pages.LinkedinHomePage;
import pages.LinkedinSearchPage;

import java.util.List;

public class LinkedInSearchTest extends LinkedinBaseTest{
    public static final String SEARCH_TERM = "hr";

    @Test
    public void basicSearchTest() {
        LinkedinHomePage linkedinHomePage = linkedinLandingPage.loginAs("iteatest@i.ua", "1q2w3e_4r5t");

        LinkedinSearchPage linkedinSearchPage = linkedinHomePage.getLinkedinSearchPage(SEARCH_TERM);

        List<String> results = linkedinSearchPage.getResults();
        for(String result : results) {
            Assert.assertTrue(result.toLowerCase().contains(SEARCH_TERM),"Searchterm "+SEARCH_TERM+" not found in cart");
        }
        Assert.assertEquals(linkedinSearchPage.getSearchResultsList().size(), 10, "There are displayed not 10 results");
    }
}
