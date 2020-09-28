import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.StringContains.containsString;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import io.github.bonigarcia.seljup.SeleniumJupiter;

@ExtendWith(SeleniumJupiter.class)
class WebdriverTest {

    @Test
    void testWithHtmlUnit(HtmlUnitDriver driver) {
        driver.get("https://www.viana.ws/");
        assertThat(driver.getTitle(),
                containsString("viana.ws"));
    }

}
