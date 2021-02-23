package com.aa.improvekatagui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.aa.improvekataben.ImproveKataE2EApplication;
import com.codeborne.selenide.webdriver.WebDriverFactory;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gargoylesoftware.htmlunit.BrowserVersion;

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

    @BeforeAll
    void beforeAll() {
        driver = initWebDriver();
    }

    private WebDriver initWebDriver() {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        options.setHeadless(true);  // <-- headless set here
        return new FirefoxDriver(options);
    }

    @Test
    void ensureNecessaryFieldsArePresent() throws Exception {
        driver.get("http://localhost");

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
        String testTitle = "test title " + Math.random();
        driver.get("http://localhost");
        driver.findElement(By.cssSelector("[data-testid=title]")).sendKeys(testTitle);

        // execution
        driver.findElement(By.cssSelector("button[data-testid=insertButton]")).click();

        // assertion
        verifyText(driver, "[data-testid=message]", "Record inserted succesfully");
//        verifyText(driver, "[data-testid=versionsList]", "WRONG");

        String queryByTeamName = testRestTemplate.getForObject("http://localhost/ben/queryByTeamName/DOD_REACCOM", String.class);
        List<Map<String, Object>> data = mapper.readValue(queryByTeamName, new TypeReference<>(){});
        assertEquals(driver.findElement(By.cssSelector("[data-testid=title]")).getAttribute("value"), data.get(data.size() - 1).get("title"));
    }

    @Test
    void clickInsertButtonVerifyTimeOutResponse() throws Exception {
        // setup
        driver.get("http://localhost");
        driver.get("javascript:setEndpoint('/nowhere');");

        // execution
        driver.findElement(By.cssSelector("button[data-testid=insertButton]")).click();

        // assertion
        verifyText(driver, "[data-testid=message]", "Connection to the backend timed out");
    }

    @Test
    void sdfsdfsdf() {
        // setup
        driver.get("http://localhost");

        // populate data 1
        driver.findElement(By.cssSelector("[data-testid=fieldAwesome]")).sendKeys("Awesome Data 1");
        driver.findElement(By.cssSelector("[data-testid=fieldNow]")).sendKeys("Now Data 1");
        driver.findElement(By.cssSelector("[data-testid=fieldNext]")).sendKeys("Next Data 1");
        driver.findElement(By.cssSelector("[data-testid=fieldBreakdown]")).sendKeys("Breakdown Data 1");
        driver.findElement(By.cssSelector("button[data-testid=insertButton]")).click();

        // populate data 1
        driver.findElement(By.cssSelector("[data-testid=fieldAwesome]")).sendKeys("Awesome Data 2");
        driver.findElement(By.cssSelector("[data-testid=fieldNow]")).sendKeys("Now Data 2");
        driver.findElement(By.cssSelector("[data-testid=fieldNext]")).sendKeys("Next Data 2");
        driver.findElement(By.cssSelector("[data-testid=fieldBreakdown]")).sendKeys("Breakdown Data 2");
        driver.findElement(By.cssSelector("button[data-testid=insertButton]")).click();

        // refresh page
        driver.navigate().refresh();


        // execution

        // assertion
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

