import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import static java.lang.Thread.sleep;

public class LinkedInSearchTest{
    WebDriver driver;

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
    public void basicSearchTest() throws InterruptedException {
        LinkedInLogInPage logInPage = new LinkedInLogInPage(driver);
        logInPage.loginAs("iteatest@i.ua", "1q2w3e_4r5t");

        sleep(5000);

        WebElement launchpadTitle = driver.findElement(By.xpath("//*[@class='launchpad__title launchpad__title--is-open Sans-21px-black-85%-dense pb5 fl'][not(text()='')]"));
        WebElement searchInput = driver.findElement(By.xpath("//*[@role='combobox']"));
        WebElement searchButton = driver.findElement(By.xpath("//span[@class='svg-icon-wrap']//li-icon[@type='search-icon']"));

        Assert.assertTrue(launchpadTitle.isDisplayed(), "Launchpad Title Message is not displayed");
        String searchWord = "hr";
        searchInput.sendKeys(searchWord);
        searchButton.click();

        sleep(5000);
        WebElement searchResultText = driver.findElement(By.xpath("//h3[contains(@class,'search-results__total')]"));
        String actualTitle = driver.getTitle();
        String expectedTitle = "\"" + searchWord +"\"" + " | Search | LinkedIn";

        Assert.assertEquals(expectedTitle, actualTitle, "Search word is not correct");
        Assert.assertTrue(searchResultText.isDisplayed(), "Search result text is not displayed");
    }
}
