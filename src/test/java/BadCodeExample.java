import org.apache.http.util.Asserts;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.*;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

public class BadCodeExample {

    public static void main(String[] args) throws InterruptedException {

        WebDriver webDriver = new FirefoxDriver();
        webDriver.navigate().to("https://www.google.com/");
        webDriver.findElement(By.id("sb_ifc0")).findElement(By.id("lst-ib")).sendKeys("selenium");
        webDriver.findElement(By.name("btnK")).submit();

        sleep(5000);

        int titleNumber =  webDriver.findElements(By.cssSelector("h3.r")).size();
        int count = 0;
        for(int i = 0; i < titleNumber; i++)
        {
            String s = webDriver.findElements(By.cssSelector("h3.r")).get(i).getText().toLowerCase();
            if (s.contains("selenium")){
                count+=1;
            }
        }

        if (count == titleNumber)
            System.out.println("true");
        else
            System.out.println("false");

        webDriver.close();
    }
}
