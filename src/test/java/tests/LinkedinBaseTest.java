package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import pages.LinkedinLandingPage;

public class LinkedinBaseTest {
    WebDriver driver;
    LinkedinLandingPage linkedinLandingPage;
    String initialPageUrl;
    String initialPageTitle;

    /**
     * Method, with help of TestNG @Parameters and @Optional, sets the browserType and envURL, which will be run
     * before @Test and navigates to chosen URL;
     * in this case, there are possible 3 browsers: firefox, chrome and default IE;
     * then goes to linkedin.com and creates instance of LinkedinLandingPage class, gets page title and page URL
     * @param browserType is a possible(firefox, chrome or default ie) browser to be run
     * @param envURL - is possible URL, in this case https://www.linkedin.com
     */

    @Parameters({"browserType", "envURL"})
    @BeforeMethod
    public void beforeTest(@Optional("chrome") String browserType, @Optional("https://www.linkedin.com/") String envURL) {

        switch (browserType.toLowerCase()){
            case "firefox" :
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case "chrome" :
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            default :
                WebDriverManager.iedriver().setup();
                driver = new InternetExplorerDriver();
        }

        driver.navigate().to(envURL);

        linkedinLandingPage = new LinkedinLandingPage(driver);

        initialPageUrl = linkedinLandingPage.getPageUrl();
        initialPageTitle = linkedinLandingPage.getPageTitle();
    }

    @AfterMethod
    public void afterTest() {
        driver.quit();
    }
}
