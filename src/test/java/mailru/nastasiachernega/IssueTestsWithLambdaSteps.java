package mailru.nastasiachernega;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;

public class IssueTestsWithLambdaSteps {

    private static final String REPOSITORY = "allure-framework/allure2";
    private static final String firstSearchingIssueTitle = "Cant install allure";
    private static final String secondSearchingIssueTitle = "Error in configuration Allure for Jira";
    private static final String thirdSearchingIssueTitle = "Tooltipshows up too high";

    @ValueSource(strings = {
            firstSearchingIssueTitle,
            secondSearchingIssueTitle,
            thirdSearchingIssueTitle
    })
    @Epic("GitHub")
    @Feature("Navigation in repository")
    @Story("Searching issue in repository " + "'" + REPOSITORY + "'")
    @Owner("Anastasia Chernega")
    @Severity(SeverityLevel.BLOCKER)
    @Link(value = "Link on GitHub (testing resource)", url = "https://github.com/")
    @DisplayName("Check the existence in repository of issue ")
    @ParameterizedTest
    void checkIssueTitleWithLambdaSteps(String searchingIssueTitle) {

        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Открываем главную страницу", () -> {
            open("https://github.com/");
        });

        step("Вводим в строку поиска название репозитория " + "'" + REPOSITORY + "'", () -> {
            $("[data-test-selector=nav-search-input]").click();
            $("[data-test-selector=nav-search-input]").sendKeys(REPOSITORY);
            $("[data-test-selector=nav-search-input]").submit();
        });

        step("Переходим по ссылке репозитория " + "'" + REPOSITORY + "'", () -> {
            $(linkText(REPOSITORY)).click();
        });

        step("Кликаем на таб Issues", () -> {
            $("#issues-tab").click();
        });

        step("Проверяем наличие на странице Issue с названием " + "'" + searchingIssueTitle + "'", () -> {
            $("#repo-content-turbo-frame").shouldHave(text(searchingIssueTitle));
        });

    }

}
