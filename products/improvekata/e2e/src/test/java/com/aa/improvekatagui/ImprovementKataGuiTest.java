package com.aa.improvekatagui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
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
        assertTagNameEquals(driver, "[data-testid=title]", "input");

        assertTextEquals(driver, "div[data-testid=fieldAwesomeLabel]", "Awesome");
        assertTagNameEquals(driver, "[data-testid=fieldAwesome]", "textarea");

        assertTextEquals(driver, "div[data-testid=fieldNowLabel]", "Now");
        assertTagNameEquals(driver, "[data-testid=fieldNow]", "textarea");

        assertTextEquals(driver, "div[data-testid=fieldNextLabel]", "Next");
        assertTagNameEquals(driver, "[data-testid=fieldNext]", "textarea");

        assertTextEquals(driver, "div[data-testid=fieldBreakdownLabel]", "Breakdown");
        assertTagNameEquals(driver, "[data-testid=fieldBreakdown]", "textarea");

        assertTagNameEquals(driver, "[data-testid=versionsList]", "ul");

        assertEquals("rgb(170, 170, 255)",
                driver.findElement(By.cssSelector("body")).getCssValue("background-color")
        );

        assertTextEquals(driver, "[data-testid=headerTitle]", "Improvement Kata");
        assertTextEquals(driver, "button[data-testid=insertButton]", "Insert");
        assertTextEquals(driver, "button[data-testid=deleteButton]", "Delete");

        assertTagNameEquals(driver, "[data-testid=message]", "div");

        assertTagNameEquals(driver, "[data-testid=uniqueId][type=hidden]", "input");
    }

    @Test
    void clickDeleteButtonVerifySuccessfulResponseImmediatelyAfterInserting() throws Exception {
        // setup
        String testTitle = "test title " + Math.random();
        assignValue("[data-testid=title]", testTitle);
        assignValue("[data-testid=fieldAwesome]", "field awesome text");
        assignValue("[data-testid=fieldNow]", "field now text");
        assignValue("[data-testid=fieldNext]", "field next text");
        assignValue("[data-testid=fieldBreakdown]", "field breakdown text");

        driver.findElement(By.cssSelector("button[data-testid=insertButton]")).click();
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        String uniqueId = driver.findElement(By.cssSelector("[data-testid=uniqueId]")).getAttribute("value");

        // execution
        driver.findElement(By.cssSelector("button[data-testid=deleteButton]")).click();
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        List uniqueIdDeleted = testRestTemplate.getForObject(baseURL + "/ben/queryByUniqueId/" + uniqueId, List.class);

        // assertion
        assertTrue(uniqueIdDeleted.isEmpty(), "uniqueIdDeleted list was expected to empty but got: " + uniqueIdDeleted.size());

        assertTextEquals(driver, "[data-testid=message]", "Record deleted successfully");

        assertEquals("", driver.findElement(By.cssSelector("[data-testid=title]")).getAttribute("value"));
        assertEquals("", driver.findElement(By.cssSelector("[data-testid=fieldAwesome]")).getAttribute("value"));
        assertEquals("", driver.findElement(By.cssSelector("[data-testid=fieldNow]")).getAttribute("value"));
        assertEquals("", driver.findElement(By.cssSelector("[data-testid=fieldNext]")).getAttribute("value"));
        assertEquals("", driver.findElement(By.cssSelector("[data-testid=fieldBreakdown]")).getAttribute("value"));
        assertEquals("", driver.findElement(By.cssSelector("[data-testid=uniqueId]")).getAttribute("value"));
    }

    @Test
    void insertTwoRecordsRetrieveFirstRecordEnsureFirstIsDeleted() throws Exception {
        assignValue("[data-testid=title]", "test title 1");
        assignValue("[data-testid=fieldAwesome]", "field awesome text 1");
        assignValue("[data-testid=fieldNow]", "field now text 1");
        assignValue("[data-testid=fieldNext]", "field next text 1");
        assignValue("[data-testid=fieldBreakdown]", "field breakdown text 1");
        driver.findElement(By.cssSelector("button[data-testid=insertButton]")).click();
        assignValue("[data-testid=title]", "test title 2");
        assignValue("[data-testid=fieldAwesome]", "field awesome text 2");
        assignValue("[data-testid=fieldNow]", "field now text 2");
        assignValue("[data-testid=fieldNext]", "field next text 2");
        assignValue("[data-testid=fieldBreakdown]", "field breakdown text 2");
        driver.findElement(By.cssSelector("button[data-testid=insertButton]")).click();
        driver.findElement(By.cssSelector("ul[data-testid=versionList]:first-child > a")).click();
//        driver.findElement(By.xpath("//ul[data-testid=versionList]/li[0]/a")).click();
        String uniqueId = driver.findElement(By.cssSelector("[data-testid=uniqueId]")).getAttribute("value");
        driver.findElement(By.cssSelector("button[data-testid=deleteButton]")).click();
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        List uniqueIdDeleted = testRestTemplate.getForObject(baseURL + "/ben/queryByUniqueId/" + uniqueId, List.class);

        // assertion
        assertTrue(uniqueIdDeleted.isEmpty(), "uniqueIdDeleted list was expected to empty but got: " + uniqueIdDeleted.size());

        assertTextEquals(driver, "[data-testid=message]", "Record deleted successfully");

        assertEquals("", driver.findElement(By.cssSelector("[data-testid=title]")).getAttribute("value"));
        assertEquals("", driver.findElement(By.cssSelector("[data-testid=fieldAwesome]")).getAttribute("value"));
        assertEquals("", driver.findElement(By.cssSelector("[data-testid=fieldNow]")).getAttribute("value"));
        assertEquals("", driver.findElement(By.cssSelector("[data-testid=fieldNext]")).getAttribute("value"));
        assertEquals("", driver.findElement(By.cssSelector("[data-testid=fieldBreakdown]")).getAttribute("value"));
        assertEquals("", driver.findElement(By.cssSelector("[data-testid=uniqueId]")).getAttribute("value"));


    }


    //@Test

    void clickDeleteButtonVerifySuccessfulResponseWhenClickingOnATimestampLink() throws Exception {

    }
    @Test
    void clickInsertButtonVerifySuccessfulResponse() throws Exception {
        // setup
        String testTitle = "test title " + Math.random();
        assignValue("[data-testid=title]", testTitle);

        // execution
        driver.findElement(By.cssSelector("button[data-testid=insertButton]")).click();
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);

        // assertion
        assertTextEquals(driver, "[data-testid=message]", "Record inserted successfully");

        String patternUniqueIDTemplate = "[a-z0-9]{8}-[a-z0-9]{4}-[a-z0-9]{4}-[a-z0-9]{4}-[a-z0-9]{12}";
        Pattern patternUniqueID = Pattern.compile(patternUniqueIDTemplate);

        WebElement uniqueIdElement = driver.findElement(By.cssSelector("[data-testid=uniqueId]"));
        String queryByTeamName = testRestTemplate.getForObject(baseURL + "/ben/queryByTeamName/DOD_REACCOM", String.class);
        List<Map<String, Object>> dataInserted = mapper.readValue(queryByTeamName, new TypeReference<>() {
        });
        assertTrue(patternUniqueID.matcher(uniqueIdElement.getAttribute("value")).matches(), "Value [" + uniqueIdElement.getAttribute("value") + "] does not match " + patternUniqueIDTemplate);
        assertEquals(dataInserted.get(dataInserted.size() - 1).get("uniqueId"), uniqueIdElement.getAttribute("value"));

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

        //assertTrue(OffsetDateTime.now().isAfter(printedDateTime), "The printed time on the screen is after the current server time");

        List<Map<String, Object>> dataLatest = mapper.readValue(queryByTeamName, new TypeReference<>() {
        });
        assertEquals(
                driver.findElement(By.cssSelector("[data-testid=title]")).getAttribute("value"),
                dataLatest.get(dataLatest.size() - 1).get("title")
        );
    }
    @Test
    void clickInsertButtonVerifyTimeOutResponse() throws Exception {
        // setup
        driver.get("javascript:setEndpointInsert('/nowhere');");

        // execution
        driver.findElement(By.cssSelector("button[data-testid=insertButton]")).click();

        // assertion
        assertTextEquals(driver, "[data-testid=message]", "Connection to the backend timed out");
        driver.navigate().refresh();
    }

    @Test
    void savingAndRetrievingDifferentGrids() throws Exception {
        testRestTemplate.delete(baseURL + "/ben/deleteTeam/DOD_REACCOM");
        // populate data 1
        assignValue("[data-testid=fieldAwesome]", "Awesome Data 1");
        assignValue("[data-testid=fieldNow]", "Now Data 1");
        assignValue("[data-testid=fieldNext]", "Next Data 1");
        assignValue("[data-testid=fieldBreakdown]", "Breakdown Data 1");
        driver.findElement(By.cssSelector("button[data-testid=insertButton]")).click();

        // refresh page
        driver.navigate().refresh();
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);

        // populate data 1
        assignValue("[data-testid=fieldAwesome]", "Awesome Data 2");
        assignValue("[data-testid=fieldNow]", "Now Data 2");
        assignValue("[data-testid=fieldNext]", "Next Data 2");
        assignValue("[data-testid=fieldBreakdown]", "Breakdown Data 2");
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
        assertFalse("".equals(driver.findElement(By.cssSelector("[data-testid=uniqueId]")).getAttribute("value").trim()));

        // Click on the first instance and verify it
        lis.get(0).click();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
//        wait.until((Function<WebDriver, WebElement>) driver -> driver.findElement(By.cssSelector("[data-testid=fieldAwesome]")));

        assertEquals("Awesome Data 1", driver.findElement(By.cssSelector("[data-testid=fieldAwesome]")).getAttribute("value"));
        assertEquals("Now Data 1", driver.findElement(By.cssSelector("[data-testid=fieldNow]")).getAttribute("value"));
        assertEquals("Next Data 1", driver.findElement(By.cssSelector("[data-testid=fieldNext]")).getAttribute("value"));
        assertEquals("Breakdown Data 1", driver.findElement(By.cssSelector("[data-testid=fieldBreakdown]")).getAttribute("value"));
        assertFalse("".equals(driver.findElement(By.cssSelector("[data-testid=uniqueId]")).getAttribute("value").trim()));
    }

    private void assertTagNameEquals(WebDriver driver, String selector, String tagName) {
        WebElement element = driver.findElement(By.cssSelector(selector));
        assertEquals(tagName, element.getTagName());
    }

    private void assertTextEquals(WebDriver driver, String selector, String text) {
        WebElement element = driver.findElement(By.cssSelector(selector));
        assertEquals(text, element.getText());
    }

    private void assignValue(String selector, String value) {
        driver.findElement(By.cssSelector(selector)).sendKeys(value);
    }
}
