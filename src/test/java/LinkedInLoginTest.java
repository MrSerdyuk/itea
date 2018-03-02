import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class LinkedInLoginTest {
    WebDriver driver;

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
    }

    @AfterMethod
    public void afterTest() {
        driver.quit();
    }

    @Test
    public void successfulLoginTest() {
        LinkedinLoginPage logInPage = new LinkedinLoginPage(driver);

        String initialPageUrl = logInPage.getPageUrl();
        String initialPageTitle = logInPage.getPageTitle();
        Assert.assertEquals(initialPageTitle, "LinkedIn: Log In or Sign Up", "Login page title is wrong");

        logInPage.loginAs("iteatest@i.ua", "1q2w3e_4r5t");
        Assert.assertTrue(logInPage.isSighedIn(), "User is not signed in");

        Assert.assertNotEquals(logInPage.getPageTitle(), initialPageTitle, "Page title did not change after login");
        Assert.assertNotEquals(logInPage.getPageUrl(), initialPageUrl, "Page url did not change after login");
    }

    @Test
    public void negativeLoginTest() {
        LinkedinLoginPage logInPage = new LinkedinLoginPage(driver);
        logInPage.loginAs("iteatest@i.ua", "1q2w3e");

        Assert.assertTrue(logInPage.isNotSignedIn(), "Alert massage is not displayed");
    }
}
