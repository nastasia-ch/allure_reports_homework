package mailru.nastasiachernega;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class IssueTestsWithStepAnnotation {

    private static final String REPOSITORY = "junit-team/junit5";
    private static final String firstSearchingIssueTitle = "Set of URLs may behave surprisingly on hashCode/equals";
    private static final String secondSearchingIssueTitle = "User guidehasseveral accessibility issues";

    @ValueSource(strings = {
            firstSearchingIssueTitle,
            secondSearchingIssueTitle,
    })
    @Epic("GitHub")
    @Feature("Navigation in repository")
    @Story("Searching issue in repository " + "'" + REPOSITORY + "'")
    @Owner("Anastasia Chernega")
    @Severity(SeverityLevel.BLOCKER)
    @Link(value = "Link on GitHub (testing resource)", url = "https://github.com/")
    @DisplayName("Check the existence in repository of issue")
    @ParameterizedTest
    void checkIssueTitleWithStepAnnotation(String searchingIssueTitle) {

        SelenideLogger.addListener("allure", new AllureSelenide());
        WebSteps steps = new WebSteps();

        steps.openMainPage().
                typeInSearchLineRepositoryTitle(REPOSITORY).
                clickOnRepositoryLink(REPOSITORY).
                clickOnTabIssues().
                shouldSeeIssueWithTitle(searchingIssueTitle);

    }

}
