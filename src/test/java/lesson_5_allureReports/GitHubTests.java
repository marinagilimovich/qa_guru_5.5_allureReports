package lesson_5_allureReports;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.github.eroshenkoam.allure.WebSteps;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.openqa.selenium.By;

import javax.xml.soap.Text;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class GitHubTests {

    @BeforeEach
    public void params(){
        Allure.parameter("repositore", Repository);
        Allure.parameter("Issue number", Issue_number);
    }

    private AnnotationSteps steps = new AnnotationSteps();

    public final String URL = "https://github.com";
    public final String Repository = "eroshenkoam/allure-qaguru";
    public final int Issue_number = 2;
    public final String Issue_name = "Добавляем зависимости Allure";


    @Test
    public void selenideSearchIssueTest() {
        open(URL);
        $(byName("q")).setValue(Repository).pressEnter();
        $(byLinkText(Repository)).click();
        $(byText("Issues")).click();
        $(withText("#" + Issue_number)).should(Condition.exist);
        $("#issue_2_link").shouldHave(text(Issue_name));
    }


    @Test
    public void useStepsSearchIssueTest() {
        step("Переход на github", (step) -> {
            step.parameter("url", URL);
            open(URL);
        });

        step("Ищем нужный Issue в репозитории", () -> {
            step("Вводим в поиск название репозитория", () ->
                    $(byName("q")).setValue(Repository).pressEnter());
            step("Переходим в репозиторий", () ->
                    $(byLinkText(Repository)).click());
            step("Переходтим в Issue", () ->
                    $(byText("Issues")).click());
        });

        step("Проверяем, что Issue с заданным номером и названием существует", () -> {;
            step("Проверяем что Issue с номером 2 существует", () ->
                    $(withText("#" + Issue_number)).should(Condition.exist));
            step("Проверяем что Issue с номером 2 называется \'Добавляем зависимости Allure\' ", () ->
                    $("#issue_2_link").shouldHave(text(Issue_name)));
        });
    }


    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Owner("aakulikoff")
    @Feature("Steps")
    @Story("Annotation")
    @DisplayName("Тест с аннотацией")
    public void annotationSearchIssueTest() {
        steps.openMainPage();
        steps.searchRepository(Repository);
        steps.goToRepository(Repository);
        steps.clickOnIssueTab();
        steps.shouldSeeIssueWithNumber(Issue_number);
        steps.shouldSeeIssueWithName(Issue_name);
    }

}
