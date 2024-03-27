package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class BusinessPage {
    SelenideElement openAccountButton = $("[role='presentation'] button[data-qa-type='uikit/button']"),
            accountRequest = $("[data-qa-type='uikit/sectionTitle']"),
            currencyTreshHoldTitle = $("[role='main'] [data-test='htmlTag title']"),
            openCurrencyAccountButton = $("[role='main'] button[data-qa-type='uikit/button']"),
            currencyAccountRequestTitle = $("[data-qa-type='uikit/sectionTitle']");
    ElementsCollection thershHoldOptions = $$("[data-qa-type='uikit/filterButton']");

    @Step("Перейти к открытию расчётного счёта")
    public BusinessPage openAccount() {
        openAccountButton.click();
        accountRequest.shouldHave(Condition.text("Заявка на открытие расчетного счета"));
        return this;
    }

    @Step("Для этого диапазона должен быть виден '{title}'")
    public BusinessPage assertCurrencyTitle(String title) {
        currencyTreshHoldTitle.shouldHave(Condition.text(title));
        return this;
    }

    @Step("Поменять диапазон в '{option}' сторону")
    public BusinessPage changeThreshHold(String option) {
        if (option == "<") {
            thershHoldOptions.get(0).click();
        }
        if (option == ">") {
            thershHoldOptions.get(1).click();
        } else System.out.println("Invalid function input. Should be < or >");
        return this;
    }

    @Step("Перейти к открытию валютного счёта")
    public BusinessPage openCurrencyAccount() {
        openAccountButton.click();
        return this;
    }

    @Step("Запрос на открытие валютного счёта должен быть '{title}'")
    public BusinessPage assertCurrencyRequestTitle(String title) {
        currencyAccountRequestTitle.shouldHave(Condition.text(title));
        return this;
    }


}
