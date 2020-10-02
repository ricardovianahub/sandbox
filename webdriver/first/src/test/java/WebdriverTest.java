import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.StringContains.containsString;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.seljup.SeleniumJupiter;

@ExtendWith(SeleniumJupiter.class)
class WebdriverTest {

    @Test
    void testTitle(HtmlUnitDriver driver) {
        driver.get("https://www.viana.ws/");
        assertThat(driver.getTitle(),
                containsString("viana.ws"));
    }

    @Test
    void testDiv1(HtmlUnitDriver driver) {
        driver.get("https://www.viana.ws/");
        String div1Text = driver.findElement(By.id("div1")).getText();
        assertThat(div1Text, containsString("content of div1"));
    }

    @Test
    void compilationIssue() {
        WebDriver driver = new HtmlUnitDriver();
        {
            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(
                    ExpectedConditions.presenceOfElementLocated(By.cssSelector("#ext-gen1565 > .x-grid-cell-inner")));
        }
    }

//    @Test
//    void readTopMatchOnGoogleWithFirefox(FirefoxDriver driver) throws InterruptedException {
//        driver.get("https://www.google.com");
//        WebElement searchKey = driver.findElement(By.cssSelector("[name=q]"));
//        String searchString = "automated testing";
//        searchKey.sendKeys(searchString);
//        searchKey.submit();
//        Thread.sleep(3000);
//        String firstMatch = driver.findElement(By.cssSelector(".r")).getText();
//        assertThat(firstMatch, containsStringIgnoringCase(searchString));
//        System.out.println("\n\n===============\n" + firstMatch);
//    }
}
