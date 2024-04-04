package pages;

import com.codeborne.selenide.ClickOptions;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.*;

public class MainPage {

    ElementsCollection slidingPanel = $$("[data-test='panel slides'] ul li"),
            navigateMenu = $$("[data-schema-path='config.menu.response'] li");
    SelenideElement loginButton = $("[data-test='loginButton']"),
            loginOption = $("[data-test='clickableArea login-first']"),
            businessOption = navigateMenu.findBy(Condition.text("Бизнесу")),
            accountingMenu = $("[data-item-name='Расчетный счет']"),
            currencyMenu = $("[data-item-name='Валютный счет']").$("a");

    @Step("Открыть главную страницу")
    public MainPage openPage() {
        open("");
        return this;
    }

    @Step("Перейти к личному кабинету (Интернет-Банк)")
    public MainPage initiateLogIn() {
        loginButton.hover();
        loginOption.click();
        return this;
    }

    @Step("Нажать на <Сим-карта> на скользящей панели")
    public MainPage openSimCards() {
        slidingPanel.get(6).click();
        return this;
    }

    @Step("Навести курсор на <Бизнесу> на верхней панели")
    public MainPage openBusinessSection() {
        businessOption.shouldBe(Condition.visible);
        businessOption.hover();
        return this;
    }

    @Step("Выбрать <Расчетный счет>")
    public MainPage navigateToAccounting() {
        accountingMenu.click();
        return this;
    }

    @Step("Выбрать <Валютный счет>")
    public MainPage navigateToCurrency() {
        currencyMenu.click(ClickOptions.usingJavaScript());
        return this;
    }

}
