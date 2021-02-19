package com.aa.improvekatagui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.seljup.SeleniumJupiter;

@ExtendWith(SeleniumJupiter.class)
public class ImprovementKataGuiTest {
    @Test
    void ensureNecessaryFieldsArePresent(FirefoxDriver driver) throws Exception {
        driver.get("http://localhost");

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
    void clickInsertButtonVerifySuccessfulResponse(FirefoxDriver driver) throws Exception {
        // setup
        driver.get("http://localhost");

        // execution
        driver.findElementByCssSelector("button[data-testid=insertButton]").click();

        // assertion
        verifyText(driver, "[data-testid=message]", "Record inserted succesfully");
        verifyText(driver, "[data-testid=versionsList]", "WRONG");
    }

    @Test
    void clickInsertButtonVerifyTimeOutResponse(FirefoxDriver driver) throws Exception {
        // setup
        driver.get("http://localhost");
        driver.executeScript("setEndpoint('/nowhere');");

        // execution
        driver.findElementByCssSelector("button[data-testid=insertButton]").click();

        // assertion
        verifyText(driver, "[data-testid=message]", "Connection to the backend timed out");
    }

    @Test
    void sdfsdfsdf(FirefoxDriver driver) {
        // setup
        driver.get("http://localhost");

        // populate data 1
        driver.findElementByCssSelector("[data-testid=fieldAwesome]").sendKeys("Awesome Data 1");
        driver.findElementByCssSelector("[data-testid=fieldNow]").sendKeys("Now Data 1");
        driver.findElementByCssSelector("[data-testid=fieldNext]").sendKeys("Next Data 1");
        driver.findElementByCssSelector("[data-testid=fieldBreakdown]").sendKeys("Breakdown Data 1");
        driver.findElementByCssSelector("button[data-testid=insertButton]").click();

        // populate data 1
        driver.findElementByCssSelector("[data-testid=fieldAwesome]").sendKeys("Awesome Data 2");
        driver.findElementByCssSelector("[data-testid=fieldNow]").sendKeys("Now Data 2");
        driver.findElementByCssSelector("[data-testid=fieldNext]").sendKeys("Next Data 2");
        driver.findElementByCssSelector("[data-testid=fieldBreakdown]").sendKeys("Breakdown Data 2");
        driver.findElementByCssSelector("button[data-testid=insertButton]").click();

        // refresh page
        driver.navigate().refresh();



        // execution

        // assertion
    }

    private void verifyTagName(FirefoxDriver driver, String selector, String tagName) {
        WebElement element = driver.findElement(By.cssSelector(selector));
        assertEquals(tagName, element.getTagName());
    }
    private void verifyText(FirefoxDriver driver, String selector, String text) {
        WebElement element = driver.findElement(By.cssSelector(selector));
        assertEquals(text, element.getText());
    }

}
