import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

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
        WebElement searchField = driver.findElement(By.xpath("//*[@role='combobox']"));
        WebElement searchIcon = driver.findElement(By.xpath("//span[@class='svg-icon-wrap']//li-icon[@type='search-icon']"));

        logInPage.waitUntilElementIsClickable(launchpadTitle);

        Assert.assertTrue(launchpadTitle.isDisplayed(), "Launchpad Title Message is not displayed");
        String searchTerm = "HR";
        searchField.sendKeys(searchTerm);
        searchIcon.click();

        sleep(5000);

        List<WebElement> searchResultsList = driver.findElements(By.xpath("//li[contains(@class,'search-result__occluded-item')]"));

        for(int i = 0; i < searchResultsList.size(); i++)
        {
            searchResultsList.get(i).click();
            String searchItemName = searchResultsList.get(i).getText();

            System.out.println(searchItemName);
            Assert.assertTrue(searchItemName.contains(searchTerm), "Result item does not contain search term in " +(i+1)+" row");
        }

        Assert.assertEquals(searchResultsList.size(), 10, "There are displayed not 10 results");
    }
}
