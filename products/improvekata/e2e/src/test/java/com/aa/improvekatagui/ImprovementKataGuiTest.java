package com.aa.improvekatagui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.function.Function;
import java.util.regex.Pattern;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.aa.improvekataben.ImproveKataE2EApplication;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import io.github.bonigarcia.wdm.WebDriverManager;

@SpringBootTest(classes = ImproveKataE2EApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(SpringExtension.class)
@ExtendWith(SeleniumJupiter.class)
public class ImprovementKataGuiTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    private ObjectMapper mapper = new ObjectMapper();

    private WebDriver driver;

    private Wait wait;

    @BeforeAll
    void beforeAll() {
        testRestTemplate.delete("http://localhost/ben/deleteTeam/DOD_REACCOM");
        //
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        options.setHeadless(true);
        driver = new FirefoxDriver(options);
        driver.get("http://localhost");
        //
        wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(2000))
                .pollingEvery(Duration.ofMillis(100))
                .ignoring(NoSuchElementException.class);
    }

    @AfterAll
    void afterAll() {
        testRestTemplate.delete("http://localhost/ben/deleteTeam/DOD_REACCOM");
        driver.close();
    }

    @Test
    void ensureNecessaryFieldsArePresent() throws Exception {
        verifyTagName(driver, "[data-testid=title]", "input");

        verifyTagName(driver, "[data-testid=fieldAwesome]", "textarea");
        verifyTagName(driver, "[data-testid=fieldNow]", "textarea");
        verifyTagName(driver, "[data-testid=fieldNext]", "textarea");
        verifyTagName(driver, "[data-testid=fieldBreakdown]", "textarea");

        verifyTagName(driver, "[data-testid=versionsList]", "ul");

        assertEquals("rgb(170, 170, 255)",
                driver.findElement(By.cssSelector("body")).getCssValue("background-color")
        );

        verifyText(driver, "[data-testid=headerTitle]", "Improvement Kata");
        verifyText(driver, "button[data-testid=insertButton]", "Insert");

        verifyTagName(driver, "[data-testid=message]", "div");
    }

    @Test
    void clickInsertButtonVerifySuccessfulResponse() throws Exception {
        // setup
        String testTitle = "test title " + Math.random();
        driver.findElement(By.cssSelector("[data-testid=title]")).sendKeys(testTitle);

        // execution
        driver.findElement(By.cssSelector("button[data-testid=insertButton]")).click();

        // assertion
        verifyText(driver, "[data-testid=message]", "Record inserted succesfully");

        RemoteWebElement ul = (RemoteWebElement) driver.findElement(By.cssSelector("[data-testid=versionsList]"));
        String patternText = "202\\d-[01]\\d-[0123]\\d [012]\\d:[012345]\\d:[012345]\\d"; // 2020-02-10 10:34:15 am
        Pattern pattern = Pattern.compile(patternText);

        List<WebElement> lis = ul.findElementsByTagName("li");

        for (WebElement li : lis) {
            assertTrue(pattern.matcher(li.getText()).matches(), "Does not match = " + li.getText() + " - tagName = " + li.getTagName());
        }

        OffsetDateTime printedDateTime = OffsetDateTime.parse(
                lis.get(lis.size() - 1).getText() + " -06:00",
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z")
        );

        assertTrue(OffsetDateTime.now().isAfter(printedDateTime), "The printed time on the screen is after the current server time");

        String queryByTeamName = testRestTemplate.getForObject("http://localhost/ben/queryByTeamName/DOD_REACCOM", String.class);
        List<Map<String, Object>> data = mapper.readValue(queryByTeamName, new TypeReference<>() {
        });
        assertEquals(
                driver.findElement(By.cssSelector("[data-testid=title]")).getAttribute("value"),
                data.get(data.size() - 1).get("title")
        );
    }

    @Test
    void clickInsertButtonVerifyTimeOutResponse() throws Exception {
        // setup
        driver.get("javascript:setEndpoint('/nowhere');");

        // execution
        driver.findElement(By.cssSelector("button[data-testid=insertButton]")).click();

        // assertion
        verifyText(driver, "[data-testid=message]", "Connection to the backend timed out");
        driver.navigate().refresh();
    }

    @Test
    void savingAndRetrievingDifferentGrids() throws Exception {
        testRestTemplate.delete("http://localhost/ben/deleteTeam/DOD_REACCOM");
        // populate data 1
        driver.findElement(By.cssSelector("[data-testid=fieldAwesome]")).sendKeys("Awesome Data 1");
        driver.findElement(By.cssSelector("[data-testid=fieldNow]")).sendKeys("Now Data 1");
        driver.findElement(By.cssSelector("[data-testid=fieldNext]")).sendKeys("Next Data 1");
        driver.findElement(By.cssSelector("[data-testid=fieldBreakdown]")).sendKeys("Breakdown Data 1");
        driver.findElement(By.cssSelector("button[data-testid=insertButton]")).click();

        // refresh page
        driver.navigate().refresh();
//        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        wait.until((Function<WebDriver, WebElement>) driver -> driver.findElement(By.cssSelector("[data-testid=fieldAwesome]")));

        // populate data 1
        driver.findElement(By.cssSelector("[data-testid=fieldAwesome]")).sendKeys("Awesome Data 2");
        driver.findElement(By.cssSelector("[data-testid=fieldNow]")).sendKeys("Now Data 2");
        driver.findElement(By.cssSelector("[data-testid=fieldNext]")).sendKeys("Next Data 2");
        driver.findElement(By.cssSelector("[data-testid=fieldBreakdown]")).sendKeys("Breakdown Data 2");
        driver.findElement(By.cssSelector("button[data-testid=insertButton]")).click();

        // refresh page
        driver.navigate().refresh();
//        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        wait.until((Function<WebDriver, WebElement>) driver -> driver.findElement(By.cssSelector("[data-testid=fieldAwesome]")));

        // Capture the list of links
        RemoteWebElement ul = (RemoteWebElement) driver.findElement(By.cssSelector("[data-testid=versionsList]"));
        List<WebElement> lis = ul.findElementsByTagName("li");

        // Click on the second instance and verify it
        lis.get(1).click();
//        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        wait.until((Function<WebDriver, WebElement>) driver -> driver.findElement(By.cssSelector("[data-testid=fieldAwesome]")));

        assertEquals("Awesome Data 2", driver.findElement(By.cssSelector("[data-testid=fieldAwesome]")).getAttribute("value"));
        assertEquals("Now Data 2", driver.findElement(By.cssSelector("[data-testid=fieldNow]")).getAttribute("value"));
        assertEquals("Next Data 2", driver.findElement(By.cssSelector("[data-testid=fieldNext]")).getAttribute("value"));
        assertEquals("Breakdown Data 2", driver.findElement(By.cssSelector("[data-testid=fieldBreakdown]")).getAttribute("value"));

        // Click on the first instance and verify it
        lis.get(0).click();
//        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        wait.until((Function<WebDriver, WebElement>) driver -> driver.findElement(By.cssSelector("[data-testid=fieldAwesome]")));

        assertEquals("Awesome Data 1", driver.findElement(By.cssSelector("[data-testid=fieldAwesome]")).getAttribute("value"));
        assertEquals("Now Data 1", driver.findElement(By.cssSelector("[data-testid=fieldNow]")).getAttribute("value"));
        assertEquals("Next Data 1", driver.findElement(By.cssSelector("[data-testid=fieldNext]")).getAttribute("value"));
        assertEquals("Breakdown Data 1", driver.findElement(By.cssSelector("[data-testid=fieldBreakdown]")).getAttribute("value"));
    }

    private void verifyTagName(WebDriver driver, String selector, String tagName) {
        WebElement element = driver.findElement(By.cssSelector(selector));
        assertEquals(tagName, element.getTagName());
    }

    private void verifyText(WebDriver driver, String selector, String text) {
        WebElement element = driver.findElement(By.cssSelector(selector));
        assertEquals(text, element.getText());
    }

}
