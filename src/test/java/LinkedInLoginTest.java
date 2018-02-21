import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import static java.lang.Thread.sleep;

public class LinkedInLoginTest {
    WebDriver webDriver;

    @BeforeClass
    public void beforeClass() {
    }

    @AfterClass
    public void afterClass() {
    }

    @BeforeMethod
    public void beforeTest() {
        webDriver = new FirefoxDriver();
        webDriver.navigate().to("https://www.linkedin.com/");
    }

    @AfterMethod
    public void afterTest() {
        webDriver.quit();
    }

    @Test
    public void successfulLoginTest() throws InterruptedException {
        WebElement emailField = webDriver.findElement(By.xpath("//*[@id='login-email']"));
        WebElement passwordField = webDriver.findElement(By.id("login-password"));
        WebElement signInButton = webDriver.findElement(By.id("login-submit"));
        String initialPageUrl = webDriver.getCurrentUrl();
        String initialPageTitle = webDriver.getTitle();

        Assert.assertEquals(initialPageTitle, "LinkedIn: Log In or Sign Up", "Page title visible");

        passwordField.sendKeys("1q2w3e_4r5t");
        emailField.sendKeys("iteatest@i.ua");
        signInButton.click();

        sleep(3000);

        Assert.assertNotEquals(webDriver.getTitle(), initialPageTitle, "Page title is the same");
        Assert.assertNotEquals(webDriver.getCurrentUrl(), initialPageUrl, "Page url is the same as initial page url");

        WebElement launchpadTitle = webDriver.findElement(By.xpath("//*[@class='launchpad__title launchpad__title--is-open Sans-21px-black-85%-dense pb5 fl'][not(text()='')]"));
        WebElement profileNavigationItem = webDriver.findElement(By.xpath("//*[@id='profile-nav-item']"));
        WebElement identityWelcomeMessage = webDriver.findElement(By.xpath("//*[@data-control-name='identity_welcome_message'][not(text()='')]"));

        Assert.assertTrue(launchpadTitle.isDisplayed(), "Launchpad Title Message is not displayed");
        Assert.assertTrue(profileNavigationItem.isDisplayed(), "Profile navigation item is not displayed");
        Assert.assertTrue(identityWelcomeMessage.isDisplayed(), "Profile welcome message is not displayed");
    }

    @Test
    public void negativeLoginTest() {
        WebElement emailField = webDriver.findElement(By.xpath("//*[@id='login-email']"));
        WebElement passwordField = webDriver.findElement(By.id("login-password"));
        WebElement signInButton = webDriver.findElement(By.id("login-submit"));

        passwordField.sendKeys("12345");
        emailField.sendKeys("test@ukr.net");
        signInButton.click();

        WebElement alertMassage = webDriver.findElement(By.xpath("//div[@id='global-alert-queue']//strong[not(text()='')]"));

        Assert.assertTrue(alertMassage.isDisplayed(), "Alert massage is not displayed");
    }
}
