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
    final String uaLinkedinURL = "https://ua.linkedin.com/";
    final String baseLinkedinURL = "https://linkedin.com/";

    @Parameters({"browserType", "envURL"})
    @BeforeMethod
    public void beforeTest(@Optional("") String browserType, @Optional("") String envURL) {

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

        if(envURL.equals(uaLinkedinURL))
        {
            driver.navigate().to(uaLinkedinURL);
        }
        else
            driver.navigate().to(baseLinkedinURL);

        linkedinLandingPage = new LinkedinLandingPage(driver);

        initialPageUrl = linkedinLandingPage.getPageUrl();
        initialPageTitle = linkedinLandingPage.getPageTitle();
    }

    @AfterMethod
    public void afterTest() {
        driver.quit();
    }
}
