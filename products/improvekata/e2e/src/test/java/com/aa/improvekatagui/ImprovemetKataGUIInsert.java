package com.aa.improvekatagui;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.seljup.SeleniumJupiter;

@ExtendWith(SeleniumJupiter.class)
public class ImprovemetKataGUIInsert {
    @Test
    void testDiv1(FirefoxDriver driver) {
        driver.get("http://localhost:3000");
        assertNotNull(driver.findElement(By.cssSelector("[data-testid=field1]")));
    }

}
