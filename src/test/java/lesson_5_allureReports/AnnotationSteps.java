package lesson_5_allureReports;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class AnnotationSteps {

    @Step("Открываем главную страницу")
    public void openMainPage() {
        open("https://github.com");
    }

    @Step("Ищем нужный репозиторий")
    public void searchRepository(String repository) {
        $(byName("q")).setValue(repository).pressEnter();
    }

    @Step("Переходим в репозиторий ")
    public void goToRepository(String repository) {
        $(byLinkText(repository)).click();
    }

    @Step("Переходим в таб Issues")
    public void clickOnIssueTab() {
        $(byText("Issues")).click();
    }

    @Step("Проверяем что Issue с номером существует")
    public void shouldSeeIssueWithNumber(int number) {
        $(withText("#" + number))
                .should(Condition.exist);
    }

    @Step("Проверяем что Issue имеет соответствующее название")
    public void shouldSeeIssueWithName(String name) {
        $("#issue_2_link").shouldHave(text(name));
    }
}
