import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import static java.lang.Thread.sleep;

public class LinkedInLoginTest {

    @Test
    public void successfulLoginTest() throws InterruptedException {
        WebDriver webDriver = new FirefoxDriver();
        webDriver.navigate().to("https://www.linkedin.com/");
        WebElement emailField = webDriver.findElement(By.xpath("//*[@id='login-email']"));
        WebElement passwordField = webDriver.findElement(By.id("login-password"));
        WebElement signInButton = webDriver.findElement(By.id("login-submit"));

        passwordField.sendKeys("1q2w3e_4r");
        emailField.sendKeys("iteatest@i.ua");
        signInButton.click();

        sleep(3000);

        WebElement launchpadTitle = webDriver.findElement(By.xpath("//*[@class='launchpad__title launchpad__title--is-open Sans-21px-black-85%-dense pb5 fl'][not(text()='')]"));
        WebElement profileNavigationItem = webDriver.findElement(By.xpath("//*[@id='profile-nav-item']"));
        WebElement identityWelcomeMessage = webDriver.findElement(By.xpath("//*[@data-control-name='identity_welcome_message'][not(text()='')]"));

        Assert.assertTrue(launchpadTitle.isDisplayed(), "Launchpad Title Message is not displayed");
        Assert.assertTrue(profileNavigationItem.isDisplayed(), "Profile navigation item is not displayed");
        Assert.assertTrue(identityWelcomeMessage.isDisplayed(), "Profile welcome message is not displayed");

        webDriver.quit();
    }

    @Test
    public void negativeLoginTest() {
        WebDriver webDriver = new FirefoxDriver();
        webDriver.navigate().to("https://www.linkedin.com/");
        WebElement emailField = webDriver.findElement(By.xpath("//*[@id='login-email']"));
        WebElement passwordField = webDriver.findElement(By.id("login-password"));
        WebElement signInButton = webDriver.findElement(By.id("login-submit"));

        passwordField.sendKeys("12345");
        emailField.sendKeys("test@ukr.net");
        signInButton.click();

        WebElement alertMassage = webDriver.findElement(By.xpath("//div[@id='global-alert-queue']//strong[not(text()='')]"));

        Assert.assertTrue(alertMassage.isDisplayed(), "Alert massage is not displayed");

        webDriver.quit();
    }
}
