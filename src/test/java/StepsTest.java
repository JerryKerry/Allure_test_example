import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.withText;
import static io.qameta.allure.Allure.step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;

public class StepsTest {
    private static final String REPOSITORY = "JerryKerry/Working_with_files";
    private static final int ISSUE = 1;

    @Test
    public void testLambdaStep(){
        step("Открываем главную страницу", () -> {
            open("https://github.com");
        });

        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Ищем репозиторий " + REPOSITORY, () -> {
            $(".search-input-container").click();
            $("#query-builder-test").sendKeys(REPOSITORY);
            $("#query-builder-test").submit();
        });
        step("Кликаем по ссылке репозитория" + REPOSITORY, () -> {
            $(linkText(REPOSITORY)).click();
        });
        step("Открываем таб Issues", () -> {
            $("#issues-tab").click();
        });
        step("Проверяем наличие Issue с номером" + ISSUE, () -> {
            $(withText("#" + ISSUE)).should(Condition.exist);
        });
    }

    @Test
    public void testAnnotatedStep(){
        WebSteps steps = new WebSteps();
        steps.openMainPage();
        steps.searchForRepository(REPOSITORY);
        steps.clickOneRepository(REPOSITORY);
        steps.openIssuesTab();
        steps.shouldSeeIssueWithNumber(ISSUE);
    }
}
