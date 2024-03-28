package pages;

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

    @Step("Перейти к личному кабинету (Интрнет-Банк)")
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

    @Step("Открыть меню для бизнеса")
    public MainPage openBusinessSection() {
        sleep(1000);
        businessOption.hover();
        return this;
    }

    @Step("Перейти к расчётному счёту")
    public MainPage navigateToAccounting() {
        accountingMenu.click();
        return this;
    }

    @Step("Перейти к валютам")
    public MainPage navigateToCurrency() {
        currencyMenu.click();
        return this;
    }

}
