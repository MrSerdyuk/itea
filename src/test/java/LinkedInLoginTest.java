import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import static java.lang.Thread.sleep;

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
    public void successfulLoginTest() throws InterruptedException {
        LinkedinLogInPage logInPage = new LinkedinLogInPage(driver);

        String initialPageUrl = logInPage.getPageUrl();
        String initialPageTitle = logInPage.getPageTitle();
        Assert.assertEquals(initialPageTitle, "LinkedIn: Log In or Sign Up", "Login page title is wrong");

        LinkedinBasePage homePage = logInPage.loginAs("iteatest@i.ua", "1q2w3e_4r5t");
        Assert.assertTrue(homePage.isSighedIn(), "User is not signed in");

        Assert.assertNotEquals(homePage.getPageTitle(), initialPageTitle, "Page title did not change after login");
        Assert.assertNotEquals(homePage.getPageUrl(), initialPageUrl, "Page url did not change after login");
    }

    @Test
    public void negativeLoginTest() {
        LinkedinLogInPage logInPage = new LinkedinLogInPage(driver);
        logInPage.loginAs("iteatest@i.ua", "1q2w3e_4r5t");

        WebElement alertMassage = driver.findElement(By.xpath("//div[@id='global-alert-queue']//strong[not(text()='')]"));

        Assert.assertTrue(alertMassage.isDisplayed(), "Alert massage is not displayed");
    }
}
