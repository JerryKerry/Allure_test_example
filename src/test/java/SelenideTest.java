import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.By.linkText;

public class SelenideTest {

    @BeforeEach
    public void beforeEach() {
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
    }

    @Test
    public void testIssueSearch(){
        SelenideLogger.addListener("allure", new AllureSelenide());
        open("https://github.com");
        $(".search-input-container").click();
        $( "#query-builder-test").sendKeys("JerryKerry/Working_with_files");
        $( "#query-builder-test").submit();
        $(linkText("JerryKerry/Working_with_files")).click();
        $("#issues-tab").click();
        $(withText("#1")).should(Condition.exist);

    }
}
