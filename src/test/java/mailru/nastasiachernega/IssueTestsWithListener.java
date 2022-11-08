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
import static org.openqa.selenium.By.linkText;

public class IssueTestsWithListener {

    private static final String REPOSITORY = "selenide/selenide";
    private static final String firstSearchingIssueTitle = "Add caching API into standard API";
    private static final String secondSearchingIssueTitle = "Selenide hover doesn't work in Safari";
    private static final String thirdSearchingIssueTitle = "Get console logs from firefox ";

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
    @DisplayName("Check the existence in repository of issue")
    @ParameterizedTest
    void checkIssueTitleWithListener(String searchingIssueTitle) {

        SelenideLogger.addListener("allure", new AllureSelenide());

        open("https://github.com/");
        $("[data-test-selector=nav-search-input]").click();
        $("[data-test-selector=nav-search-input]").sendKeys(REPOSITORY);
        $("[data-test-selector=nav-search-input]").submit();
        $(linkText(REPOSITORY)).click();
        $("#issues-tab").click();
        $("#repo-content-turbo-frame").shouldHave(text(searchingIssueTitle));

    }

}
