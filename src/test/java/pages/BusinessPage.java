package pages;

import com.codeborne.selenide.ClickOptions;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class BusinessPage {
    SelenideElement openAccountButton = $("[role='presentation'] button[data-qa-type='uikit/button']"),
            accountRequest = $("[data-qa-type='uikit/sectionTitle']"),
            currencyTreshHoldTitle = $("[role='main'] [data-test='htmlTag title']"),
            openCurrencyAccountButton = $("[role='main'] button[data-qa-type='uikit/button']"),
            currencyAccountRequestTitle = $("[data-qa-type='uikit/sectionTitle']");
    ElementsCollection thershHoldOptions = $$("[data-qa-type='uikit/filterButton']");

    @Step("Нажать на <Открыть счёт>")
    public BusinessPage openAccount() {
        accountRequest.shouldHave(Condition.text("Заявка на открытие расчетного счета"));
        return this;
    }
    @Step("Проверить, что заявка на открытие счёта открыта")
    public BusinessPage assertAccountOpened() {
        accountRequest.shouldHave(Condition.text("Заявка на открытие расчетного счета"));
        return this;
    }

    @Step("Проверить, что для выбранного диапазона виден заголовок '{title}'")
    public BusinessPage assertCurrencyTitle(String title) {
        currencyTreshHoldTitle.shouldHave(Condition.text(title));
        return this;
    }

    @Step("Поменять диапазон в '{option}' сторону")
    public BusinessPage changeThreshHold(String option) {
        if (option == "<") {
            thershHoldOptions.get(0).click(ClickOptions.usingJavaScript());
        }
        if (option == ">") {
            thershHoldOptions.get(1).click(ClickOptions.usingJavaScript());
        } else System.out.println("Invalid function input. Should be < or >");
        return this;
    }

    @Step("Нажать на <Открыть счёт>")
    public BusinessPage openCurrencyAccount() {
        openAccountButton.click(ClickOptions.usingJavaScript());
        return this;
    }

    @Step("Проверить, что запрос на открытие валютного счёта виден и содержит заголовок '{title}'")
    public BusinessPage assertCurrencyRequestTitle(String title) {
        currencyAccountRequestTitle.shouldHave(Condition.text(title));
        return this;
    }


}
