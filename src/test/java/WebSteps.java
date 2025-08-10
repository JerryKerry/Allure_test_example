import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;

public class WebSteps {
    @Step("Открываем главную страницу")
    public void openMainPage() {
        open("https://github.com");
    }

    @Step("Ищем репозиторий {repositoryName}")
    public void searchForRepository(String repositoryName){
        $(".search-input-container").click();
        $("#query-builder-test").sendKeys(repositoryName);
        $("#query-builder-test").submit();
    }

    @Step("Кликаем по ссылке репозитория")
    public void clickOneRepository(String repositoryName){
        $(linkText(repositoryName)).click();
    }

    @Step("Открываем таб Issues")
    public void openIssuesTab(){
        $("#issues-tab").click();
    }

    @Step("Проверяем наличие Issue с номером")
    public void shouldSeeIssueWithNumber(int issueNumber){
        $(withText("#" + issueNumber)).should(Condition.exist);
    }

}
