package pages;

import com.codeborne.selenide.*;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;

import java.util.List;

import static com.codeborne.selenide.Selenide.*;

public class MobileOperatorPage {
    ElementsCollection slidePanelMobile = $$("[data-test='panel slides'] ul li"),
            simOptions = $$("[data-qa-type='uikit/radioBlock']"),
            beautyNumbers = $$("[data-qa-type='mvno/NumberSelectItem']"),
            numbersTabs = $$("[data-qa-type='uikit/tabs.item']"),
            roamingTariffResults = $$("[data-qa-type='mvno/roamingOptionName']");
    SelenideElement connecteSIMButton = $("[data-test='htmlTag button'] [data-size='l']"),
            frameTitle = $("[data-qa-type='uikit/titleAndSubtitle.textPrimary']"),
            numbersView = $("[data-qa-type='mvno/NumberSelect.numbersList']"),
            orderSimWithNumber = $("[data-qa-type='uikit/button']"),
            selectedOnOrderNumber = $("[data-qa-type='mvno/NumbersSidebar.selectedNumber']"),
            selectedOnOrderPrice = $("[data-qa-type='mvno/NumbersSidebar.selectedNumber.price']"),
            regionTitle = $("[data-qa-type='uikit/titleAndSubtitle.textPrimary']"),
            favoriteNumber = $("[data-qa-type='mvno/NumberSelectListItem.title']"),
            countryRoamingView = $("[data-qa-type='uikit/screenTwinCols.withSidebar.content']"),
            inputCountry = $("[data-qa-type='uikit/inputAutocomplete.value.input']"),
            searchElement = $("[data-qa-type='uikit/inputAutocomplete.inputBox.leftContent']"),
            eSimOnPanel = slidePanelMobile.get(2),
            beautyNumbersOnPanel = slidePanelMobile.get(4),
            roamingOnPanel = slidePanelMobile.get(6);
    String simRadioOptionLocator = "[data-qa-type='uikit/radioBlock.input'] input",
            beautyNumberTitleLocator = "[data-qa-type='mvno/NumberSelectListItem.title']",
            iconHeartOnNumberLocator = "[data-qa-type='mvno/iconHeart']",
            roamingElementPriceLocator = "[data-qa-type='uikit/money']";

    @Step("Нажать на <eSIM> на скользящей панели")
    public MobileOperatorPage selectElectronicSimonSlidePanel() {

        //eSimOnPanel.hover(HoverOptions.withOffset(10, 10)).click(ClickOptions.usingJavaScript());
        eSimOnPanel.click(ClickOptions.usingJavaScript());
        connecteSIMButton.click();
        return this;
    }

    @Step("Нажать на <Красивые номера> на скользящей панели")
    public MobileOperatorPage selectBeautyNumbersOnSlidePanel() {
        beautyNumbersOnPanel.hover(HoverOptions.withOffset(10, 10)).click();
        return this;
    }

    @Step("Нажать на <Роуминг> на скользящей панели")
    public MobileOperatorPage selectRoamingOnSlidePanel() {
        roamingOnPanel.hover(HoverOptions.withOffset(10, 10)).click(ClickOptions.usingJavaScript());
        return this;
    }

    @Step("Окно '{title}' видно")
    public MobileOperatorPage assertComponentTitle(String title) {
        frameTitle.shouldBe(Condition.text(title));
        return this;
    }

    @Step("'{simOption}' выбирается автоматически")
    public MobileOperatorPage assertSimOption(String simOption) {
        SelenideElement selectedOption = simOptions.find(Condition.text(simOption));
        String value = selectedOption.$(simRadioOptionLocator).getAttribute("aria-checked");
        Assertions.assertEquals("true", value);
        return this;
    }

    @Step("Запомнить выбранный номер")
    public List<String> fetchBeautyNumberValues(int num) {
        SelenideElement selectedNumber = beautyNumbers.get(num).scrollTo();
        String number = selectedNumber.$(beautyNumberTitleLocator).getText();
        String price = selectedNumber.$("div").$("span").getText();
        List<String> result = List.of(number, price);
        return result;

    }

    @Step("Выбрать номер из списка")
    public MobileOperatorPage selectBeautyNumber(int num) {
        beautyNumbers.get(num)
                .scrollIntoView(true)
                .click(ClickOptions.usingJavaScript());
        return this;
    }

    @Step("Добавить номер в избранное")
    public MobileOperatorPage clickOnHeart(int num) {
        numbersView.scrollTo();
        beautyNumbers.get(num).hover();
        beautyNumbers.get(num).$(iconHeartOnNumberLocator).click(ClickOptions.usingJavaScript());

        return this;
    }

    @Step("Нажать на <Заказать сим-карту>")
    public MobileOperatorPage orderSimWithBeautyNumber() {
        orderSimWithNumber.click();
        return this;

    }

    @Step("Номер '{number}' добавлен в корзину по цене '{price}'")
    public MobileOperatorPage assertTheBeautyIsSelectedSuccessfully(String number, String price) {
        selectedOnOrderNumber.shouldHave(Condition.text(number));
        selectedOnOrderPrice.shouldHave(Condition.text(price));
        return this;
    }

    @Step("Перейти на табу <Избранное>")
    public MobileOperatorPage moveToFavoritesTab() {
        regionTitle.scrollTo();
        numbersTabs.get(1).click();
        //favoriteNumber.shouldHave(Condition.text(number));
        return this;
    }

    @Step("Убедиться, что номер {number} добавлен в избранное")
    public MobileOperatorPage asserThatNumberIsAdded(String number){
        favoriteNumber.shouldHave(Condition.text(number));
        return this;
    }

    @Step("Выбрать страну '{country}' для роуминга")
    public MobileOperatorPage selectRoamingCountry(String country) {
        countryRoamingView.scrollTo();
        searchElement.click(ClickOptions.usingJavaScript());
        inputCountry.setValue(country);
        return this;
    }

    @Step("'{optionName}' стоит {optionPrice}")
    public MobileOperatorPage assertTariffResult(String optionName, String optionPrice) {
        SelenideElement roamingResultElement = roamingTariffResults.findBy(Condition.text(optionName)).parent();
        String stringPrice = roamingResultElement.$(roamingElementPriceLocator)
                .getText().replaceAll("\n", "");
        Assertions.assertEquals(optionPrice, stringPrice);
        return this;
    }

}
