package mailru.nastasiachernega;

import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;

public class WebSteps {

    @Step("Открываем главную страницу")
    public WebSteps openMainPage() {
        open("https://github.com/");
        return this;
    }

    @Step("Вводим в строку поиска название репозитория {repo}")
    public WebSteps typeInSearchLineRepositoryTitle(String repo) {
        $("[data-test-selector=nav-search-input]").click();
        $("[data-test-selector=nav-search-input]").sendKeys(repo);
        $("[data-test-selector=nav-search-input]").submit();
        return this;
    }

    @Step("Переходим по ссылке репозитория {repo}")
    public WebSteps clickOnRepositoryLink(String repo) {
        $(linkText(repo)).click();
        return this;
    }

    @Step("Кликаем на таб Issues")
    public WebSteps clickOnTabIssues() {
        $("#issues-tab").click();
        return this;
    }

    @Step("Проверяем наличие на странице Issue с названием {searchingIssueTitle}")
    public WebSteps shouldSeeIssueWithTitle(String searchingIssueTitle) {
        $("#repo-content-turbo-frame").shouldHave(text(searchingIssueTitle));
        return this;
    }

    @Attachment(value = "Screenshot", type = "image/png")
    public byte[] makeScreenshot() {
        return ((TakesScreenshot)WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }

}
