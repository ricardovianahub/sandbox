package com.aa.improvekatagui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Pattern;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.aa.improvekataben.ImproveKataE2EApplication;
import com.aa.improvekataben.data.ImprovementGrid;
import com.aa.targetendpoint.EndPointResolver;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import io.github.bonigarcia.wdm.WebDriverManager;

@SpringBootTest(classes = ImproveKataE2EApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(SpringExtension.class)
@ExtendWith(SeleniumJupiter.class)
public class ImprovementKataGuiTest {

    private final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private TestRestTemplate testRestTemplate;

    private WebDriver driver;

    private String baseURL;

    @BeforeAll
    void beforeAll() {
        baseURL = EndPointResolver.retrieveBaseURL();
        //
        testRestTemplate.delete(baseURL + "/ben/deleteTeam/DOD_REACCOM");
        //
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        options.setHeadless(true);
        driver = new FirefoxDriver(options);
        driver.get(baseURL);
        delay();
    }

    private void delay() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @BeforeEach
    void beforeEach() {
        testRestTemplate.delete(baseURL + "/ben/deleteTeam/DOD_REACCOM");
        driver.navigate().refresh();
        delay();
    }

    @AfterAll
    void afterAll(){
        testRestTemplate.delete(baseURL + "/ben/deleteTeam/DOD_REACCOM");
        driver.close();
    }

    @Test
    void enableInsertButtonWhenMandatoryFieldsArePopulated() {
        WebElement insertButton = driver.findElement(By.cssSelector("button[data-testid=insertButton]"));

        assignValueBySelector("[id=title]", "title");
        assignValueBySelector("[id=field1Awesome]", "awesome");

        assertTrue(insertButton.isEnabled(), "insert button expected to be enabled but it is NOT");
        assertEquals("enabledButton", insertButton.getAttribute("class"));
    }

    @Test
    void disableInsertButtonWhenMandatoryFieldsAreEmpty() {
        WebElement insertButton = driver.findElement(By.cssSelector("button[data-testid=insertButton]"));
        assertFalse(insertButton.isEnabled(), "insert button expected to be disabled but it is NOT");
        assertEquals("disabledButton", insertButton.getAttribute("class"));
    }

    @Test
    void disableDeleteButtonWhenMandatoryFieldsAreEmpty() {
        WebElement deleteButton = driver.findElement(By.cssSelector("button[data-testid=deleteButton]"));
        assertFalse(deleteButton.isEnabled(), "Delete button expected to be disabled but it is NOT");
        assertEquals("disabledButton", deleteButton.getAttribute("class"));
    }

    @Test
    void enableDeleteButtonAfterRecordInserted() {
        insertRecordTimes(1);
        WebElement deleteButton = driver.findElement(By.cssSelector("button[data-testid=deleteButton]"));
        assertTrue(deleteButton.isEnabled(), "Delete button expected to be enabled but it is NOT");
        assertEquals("enabledButton", deleteButton.getAttribute("class"));
    }

    @Test
    void disableDeleteButtonWhenThereAreNoRecords() {
        insertRecordTimes(1);

        // execution
        WebElement deleteButton = driver.findElement(By.cssSelector("button[data-testid=deleteButton]"));
        deleteButton.click();
        delay();

        assertFalse(deleteButton.isEnabled(), "Delete button expected to be disabled but it is NOT");
    }

    @Test
    void ensureNecessaryFieldsArePresent() {
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
    void clickDeleteButtonVerifySuccessfulResponseImmediatelyAfterInserting() {
        // setup
        insertRecordTimes(1);
        String uniqueId = elementValue("uniqueId");

        // execution
        driver.findElement(By.cssSelector("button[data-testid=deleteButton]")).click();
        delay();
        List uniqueIdDeleted = testRestTemplate.getForObject(baseURL + "/ben/queryByUniqueId/" + uniqueId, List.class);

        // assertion
        assertTrue(uniqueIdDeleted.isEmpty(), "uniqueIdDeleted list was expected to empty but got: " + uniqueIdDeleted.size());

        assertTextEquals(driver, "[data-testid=message]", "Record deleted successfully");

        assertFormIsEmpty();
    }

    @Test
    void clickDeleteDoesNotWipeTheFormWhenADifferentRecordIsClicked() {
        // setup
        insertRecordTimes(3);
        WebElement li = driver.findElement(By.cssSelector("ul[data-testid=versionsList] > li:nth-child(2)"));
        String uniqueId = li.getAttribute("uniqueId");

        // execution
        WebElement spanX = li.findElement(By.cssSelector("span:last-child"));
        spanX.click();
        delay();
        List uniqueIdDeleted = testRestTemplate.getForObject(baseURL + "/ben/queryByUniqueId/" + uniqueId, List.class);

        // assertion
        assertTrue(uniqueIdDeleted.isEmpty(), "uniqueIdDeleted list was expected to empty but got: " + uniqueIdDeleted.size());
        assertEquals(2, driver.findElements(By.cssSelector("ul[data-testid=versionsList] > li")).size());
        assertNotEquals("", elementValue("title"));
        assertNotEquals("", elementValue("fieldAwesome"));
        assertNotEquals("", elementValue("fieldNow"));
        assertNotEquals("", elementValue("fieldNext"));
        assertNotEquals("", elementValue("fieldBreakdown"));
        assertNotEquals("", elementValue("uniqueId"));
    }

    @Test
    void clickDeleteWipesTheFormWhenTheSameRecordIsClicked() {
        // setup
        insertRecordTimes(3);
        WebElement li = driver.findElement(By.cssSelector("ul[data-testid=versionsList] > li:nth-child(3)"));
        String uniqueId = li.getAttribute("uniqueId");

        // execution
        WebElement spanX = li.findElement(By.cssSelector("span:last-child"));
        spanX.click();
        delay();
        List uniqueIdDeleted = testRestTemplate.getForObject(baseURL + "/ben/queryByUniqueId/" + uniqueId, List.class);

        // assertion
        assertTrue(uniqueIdDeleted.isEmpty(), "uniqueIdDeleted list was expected to empty but got: " + uniqueIdDeleted.size());
        assertEquals(2, driver.findElements(By.cssSelector("ul[data-testid=versionsList] > li")).size());
        assertFormIsEmpty();
    }


    @Test
    void insertTwoRecordsRetrieveFirstRecordEnsureFirstIsDeleted() {
        insertRecordTimes(2);

        driver.findElement(By.cssSelector("ul[data-testid=versionsList] > li:first-child")).click();
        String uniqueId = elementValue("uniqueId");
        driver.findElement(By.cssSelector("button[data-testid=deleteButton]")).click();
        delay();
        List uniqueIdDeleted = testRestTemplate.getForObject(baseURL + "/ben/queryByUniqueId/" + uniqueId, List.class);

        // assertion
        assertTrue(uniqueIdDeleted.isEmpty(), "uniqueIdDeleted list was expected to empty but got: " + uniqueIdDeleted.size());
        assertTextEquals(driver, "[data-testid=message]", "Record deleted successfully");

        assertFormIsEmpty();

        assertEquals(1, driver.findElements(By.cssSelector("ul[data-testid=versionsList] > li")).size());
    }

    @Test
    void ensureEachLineOfTimestampsIsUniquelyIdentifiable() {
        insertRecordTimes(5);

        List<WebElement> elements = driver.findElements(
                By.cssSelector("ul[data-testid=versionsList] > li")
        );
        Pattern patternUniqueID = Pattern.compile(ImprovementGrid.PATTERN_UNIQUE_ID_TEMPLATE);
        for (WebElement element : elements) {
            String uniqueId = element.getAttribute("uniqueId");
            assertNotNull(uniqueId);
            assertTrue(patternUniqueID.matcher(uniqueId).matches(),
                    "Value [" + uniqueId + "] does not match "
                            + ImprovementGrid.PATTERN_UNIQUE_ID_TEMPLATE
            );
        }
    }

    @Test
    void clickInsertButtonVerifySuccessfulResponse() throws JsonProcessingException {
        // setup & execution
        insertRecordTimes(1);

        // assertion
        assertTextEquals(driver, "[data-testid=message]", "Record inserted successfully");

        Pattern patternUniqueID = Pattern.compile(ImprovementGrid.PATTERN_UNIQUE_ID_TEMPLATE);

        WebElement uniqueIdElement = driver.findElement(By.cssSelector("[data-testid=uniqueId]"));
        String queryByTeamName = testRestTemplate.getForObject(
                baseURL + "/ben/queryByTeamName/DOD_REACCOM", String.class
        );
        List<Map<String, Object>> dataInserted = mapper.readValue(queryByTeamName, new TypeReference<>() {
        });
        assertTrue(patternUniqueID.matcher(uniqueIdElement.getAttribute("value")).matches(),
                "Value [" + uniqueIdElement.getAttribute("value") + "] does not match "
                        + ImprovementGrid.PATTERN_UNIQUE_ID_TEMPLATE);
        assertEquals(dataInserted.get(dataInserted.size() - 1).get("uniqueId"),
                uniqueIdElement.getAttribute("value")
        );

        RemoteWebElement ul = driver.findElement(
                By.cssSelector("[data-testid=versionsList]")
        );
        Pattern patternDate = Pattern.compile(ImprovementGrid.PATTERN_DATE_TEMPLATE);

        List<WebElement> lis = ul.findElementsByTagName("li");

        for (WebElement li : lis) {
            assertTrue(patternDate.matcher(li.findElement(By.className("blueButton")).getText()).matches(),
                    "[" + li.findElement(By.className("blueButton")).getText() + "] does not match Pattern " + ImprovementGrid.PATTERN_DATE_TEMPLATE + " - tagName = " + li.getTagName()
            );
        }

        OffsetDateTime printedDateTime = OffsetDateTime.parse(
                lis.get(lis.size() - 1).findElement(By.className("blueButton")).getText() + " Z",
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z")
        );

        assertTrue(OffsetDateTime.now(ZoneOffset.UTC).isAfter(printedDateTime),
                "The printed time on the screen is after the current server time"
        );

        List<Map<String, Object>> dataLatest = mapper.readValue(queryByTeamName, new TypeReference<>() {
        });
        assertEquals(elementValue("title"), dataLatest.get(dataLatest.size() - 1).get("title"));
    }

    @Test
    void clickInsertButtonVerifyTimeOutResponse() {
        // setup
        driver.get("javascript:setEndpointInsert('/nowhere');");
        assignValueBySelector("[id=title]", "title");
        assignValueBySelector("[id=field1Awesome]", "awesome");

        // execution
        driver.findElement(By.cssSelector("button[data-testid=insertButton]")).click();

        // assertion
        assertTextEquals(driver, "[data-testid=message]",
                "Connection to the backend timed out"
        );
        driver.navigate().refresh();
    }

    @Test
    void savingAndRetrievingDifferentGrids() {
        // populate data 1 & 2
        insertRecordTimes(2);

        // Capture the list of links
        RemoteWebElement ul = driver.findElement(By.cssSelector("[data-testid=versionsList]"));
        List<WebElement> lis = ul.findElementsByTagName("li");

        // Click on the second instance and verify it
        lis.get(1).click();
        delay();

        assertEquals("field awesome text 2", elementValue("fieldAwesome"));
        assertEquals("field now text 2", elementValue("fieldNow"));
        assertEquals("field next text 2", elementValue("fieldNext"));
        assertEquals("field breakdown text 2", elementValue("fieldBreakdown"));
        assertNotEquals("", elementValue("uniqueId").trim());

        // Click on the first instance and verify it
        lis.get(0).click();
        delay();

        assertEquals("field awesome text 1", elementValue("fieldAwesome"));
        assertEquals("field now text 1", elementValue("fieldNow"));
        assertEquals("field next text 1", elementValue("fieldNext"));
        assertEquals("field breakdown text 1", elementValue("fieldBreakdown"));
        assertNotEquals("", elementValue("uniqueId"));
    }

    @Test
    void ensureResetButtonIsPresent() {
        assertTagNameEquals(driver, "[data-testid=resetButton]", "button");
        assertTextEquals(driver, "[data-testid=resetButton]", "Reset");
    }

    private String elementValue(String dataTestId) {
        return driver.findElement(By.cssSelector("[data-testid=" + dataTestId + "]")).getAttribute("value");
    }

    private void insertRecordTimes(int numberOfRecords) {
        for (int i = 1; i <= numberOfRecords; i++) {
            String testTitle = "test title " + UUID.randomUUID();
            assignValueBySelector("[data-testid=title]", testTitle);
            assignValueBySelector("[data-testid=fieldAwesome]", "field awesome text " + i);
            assignValueBySelector("[data-testid=fieldNow]", "field now text " + i);
            assignValueBySelector("[data-testid=fieldNext]", "field next text " + i);
            assignValueBySelector("[data-testid=fieldBreakdown]", "field breakdown text " + i);
            driver.findElement(By.cssSelector("button[data-testid=insertButton]")).click();
            delay();
        }
    }

    private void assignValueBySelector(String selector, String value) {
        driver.findElement(By.cssSelector(selector)).clear();
        driver.findElement(By.cssSelector(selector)).sendKeys(value);
    }

    private void assertTagNameEquals(WebDriver driver, String selector, String tagName) {
        WebElement element = driver.findElement(By.cssSelector(selector));
        assertEquals(tagName, element.getTagName());
    }

    private void assertTextEquals(WebDriver driver, String selector, String text) {
        WebElement element = driver.findElement(By.cssSelector(selector));
        assertEquals(text, element.getText());
    }

    private void assertFormIsEmpty() {
        assertEquals("", elementValue("title"));
        assertEquals("", elementValue("fieldAwesome"));
        assertEquals("", elementValue("fieldNow"));
        assertEquals("", elementValue("fieldNext"));
        assertEquals("", elementValue("fieldBreakdown"));
        assertEquals("", elementValue("uniqueId"));
    }
}
