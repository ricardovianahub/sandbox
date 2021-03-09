package com.aa.improvekatagui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
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

import com.aa.TestUtils;
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

    private String baseURL;

    @BeforeAll
    void beforeAll() throws Exception {
        baseURL = TestUtils.retrieveBaseURL();
        //
        testRestTemplate.delete(baseURL + "/ben/deleteTeam/DOD_REACCOM");
        //
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        options.setHeadless(true);
        driver = new FirefoxDriver(options);
        driver.get(baseURL);
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
    }

    @AfterAll
    void afterAll() {
        testRestTemplate.delete(baseURL + "/ben/deleteTeam/DOD_REACCOM");
        driver.close();
    }

    @Test
    void ensureNecessaryFieldsArePresent() throws Exception {
        verifyTagName(driver, "[data-testid=title]", "input");

        verifyText(driver, "div[data-testid=fieldAwesomeLabel]", "Awesome");
        verifyTagName(driver, "[data-testid=fieldAwesome]", "textarea");

        verifyText(driver, "div[data-testid=fieldNowLabel]", "Now");
        verifyTagName(driver, "[data-testid=fieldNow]", "textarea");

        verifyText(driver, "div[data-testid=fieldNextLabel]", "Next");
        verifyTagName(driver, "[data-testid=fieldNext]", "textarea");

        verifyText(driver, "div[data-testid=fieldBreakdownLabel]", "Breakdown");
        verifyTagName(driver, "[data-testid=fieldBreakdown]", "textarea");

        verifyTagName(driver, "[data-testid=versionsList]", "ul");

        assertEquals("rgb(170, 170, 255)",
                driver.findElement(By.cssSelector("body")).getCssValue("background-color")
        );

        verifyText(driver, "[data-testid=headerTitle]", "Improvement Kata");
        verifyText(driver, "button[data-testid=insertButton]", "Insert");

        verifyTagName(driver, "[data-testid=message]", "div");

        verifyTagName(driver, "[data-testid=uniqueKey][type=hidden]", "input");
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

        String patternUniqueIDTemplate = "[a-z0-9]{8}-[a-z0-9]{4}-[a-z0-9]{4}-[a-z0-9]{4}-[a-z0-9]{12}";
        Pattern patternUniqueID = Pattern.compile(patternUniqueIDTemplate);

        WebElement uniqueKeyElement = driver.findElement(By.cssSelector("[data-testid=uniqueKey]"));
        assertTrue(patternUniqueID.matcher(uniqueKeyElement.getText()).matches());


        RemoteWebElement ul = (RemoteWebElement) driver.findElement(By.cssSelector("[data-testid=versionsList]"));
        String patternDateTemplate = "202\\d-[01]\\d-[0123]\\d [012]\\d:[012345]\\d:[012345]\\d"; // 2020-02-10 10:34:15 am
        Pattern patternDate = Pattern.compile(patternDateTemplate);

        List<WebElement> lis = ul.findElementsByTagName("li");

        for (WebElement li : lis) {
            assertTrue(patternDate.matcher(li.getText()).matches(), "Does not match = " + li.getText() + " - tagName = " + li.getTagName());
        }

        OffsetDateTime printedDateTime = OffsetDateTime.parse(
                lis.get(lis.size() - 1).getText() + " -06:00",
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z")
        );

        assertTrue(OffsetDateTime.now().isAfter(printedDateTime), "The printed time on the screen is after the current server time");

        String queryByTeamName = testRestTemplate.getForObject(baseURL + "/ben/queryByTeamName/DOD_REACCOM", String.class);
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
        testRestTemplate.delete(baseURL + "/ben/deleteTeam/DOD_REACCOM");
        // populate data 1
        driver.findElement(By.cssSelector("[data-testid=fieldAwesome]")).sendKeys("Awesome Data 1");
        driver.findElement(By.cssSelector("[data-testid=fieldNow]")).sendKeys("Now Data 1");
        driver.findElement(By.cssSelector("[data-testid=fieldNext]")).sendKeys("Next Data 1");
        driver.findElement(By.cssSelector("[data-testid=fieldBreakdown]")).sendKeys("Breakdown Data 1");
        driver.findElement(By.cssSelector("button[data-testid=insertButton]")).click();

        // refresh page
        driver.navigate().refresh();
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);

        // populate data 1
        driver.findElement(By.cssSelector("[data-testid=fieldAwesome]")).sendKeys("Awesome Data 2");
        driver.findElement(By.cssSelector("[data-testid=fieldNow]")).sendKeys("Now Data 2");
        driver.findElement(By.cssSelector("[data-testid=fieldNext]")).sendKeys("Next Data 2");
        driver.findElement(By.cssSelector("[data-testid=fieldBreakdown]")).sendKeys("Breakdown Data 2");
        driver.findElement(By.cssSelector("button[data-testid=insertButton]")).click();

        // refresh page
        driver.navigate().refresh();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

        // Capture the list of links
        RemoteWebElement ul = (RemoteWebElement) driver.findElement(By.cssSelector("[data-testid=versionsList]"));
        List<WebElement> lis = ul.findElementsByTagName("li");

        // Click on the second instance and verify it
        lis.get(1).click();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

        assertEquals("Awesome Data 2", driver.findElement(By.cssSelector("[data-testid=fieldAwesome]")).getAttribute("value"));
        assertEquals("Now Data 2", driver.findElement(By.cssSelector("[data-testid=fieldNow]")).getAttribute("value"));
        assertEquals("Next Data 2", driver.findElement(By.cssSelector("[data-testid=fieldNext]")).getAttribute("value"));
        assertEquals("Breakdown Data 2", driver.findElement(By.cssSelector("[data-testid=fieldBreakdown]")).getAttribute("value"));
        assertFalse("".equals(driver.findElement(By.cssSelector("[data-testid=uniqueKey]")).getAttribute("value").trim()));

        // Click on the first instance and verify it
        lis.get(0).click();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
//        wait.until((Function<WebDriver, WebElement>) driver -> driver.findElement(By.cssSelector("[data-testid=fieldAwesome]")));

        assertEquals("Awesome Data 1", driver.findElement(By.cssSelector("[data-testid=fieldAwesome]")).getAttribute("value"));
        assertEquals("Now Data 1", driver.findElement(By.cssSelector("[data-testid=fieldNow]")).getAttribute("value"));
        assertEquals("Next Data 1", driver.findElement(By.cssSelector("[data-testid=fieldNext]")).getAttribute("value"));
        assertEquals("Breakdown Data 1", driver.findElement(By.cssSelector("[data-testid=fieldBreakdown]")).getAttribute("value"));
        assertFalse("".equals(driver.findElement(By.cssSelector("[data-testid=uniqueKey]")).getAttribute("value").trim()));
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
