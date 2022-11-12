package mailru.nastasiachernega;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.attachment;
import static io.qameta.allure.Allure.step;

public class IssueTestScreenshot {

    private static final String REPOSITORY = "allure-framework/allure2";
    private static final String searchingIssueTitle = "Missing x axis labels on Trend charts";

    @Epic("GitHub")
    @Feature("Open main page")
    @Owner("Anastasia Chernega")
    @Severity(SeverityLevel.BLOCKER)
    @Link(value = "Link on GitHub (testing resource)", url = "https://github.com/")
    @DisplayName("Open main pade of GitHub")
    @Test
    void testMakeScreenshotWithLambdaSteps() {

        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Открываем главную страницу", () -> {
            open("https://github.com/");
            attachment("Source", webdriver().driver().source());
        });

    }

    @Epic("GitHub")
    @Feature("Open main page")
    @Owner("Anastasia Chernega")
    @Severity(SeverityLevel.BLOCKER)
    @Link(value = "Link on GitHub (testing resource)", url = "https://github.com/")
    @DisplayName("Open main pade of GitHub")
    @Test
    void testMakeScreenshotWithStepAnnotation() {

        SelenideLogger.addListener("allure", new AllureSelenide());
        WebSteps steps = new WebSteps();

        steps.openMainPage().
              makeScreenshot();

    }

}
