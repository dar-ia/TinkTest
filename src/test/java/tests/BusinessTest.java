package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import pages.BusinessPage;
import pages.MainPage;

@DisplayName("Тесты на секцию для бизнес пользователей")
public class BusinessTest extends TestBase{

    MainPage mainPage = new MainPage();
    BusinessPage businessPage = new BusinessPage();

    @Test
    @DisplayName("Заявка на открытие расчётного счёта открывается")
    @Tags({
            @Tag("FUll_SCOPE"),
            @Tag("READ_ONLY_TEST"),
            @Tag("BUSINESS_TEST")
    })

    void accountingCanBeStarted(){
        mainPage.openPage()
                .openBusinessSection()
                .navigateToAccounting();

        businessPage.openAccount();
    }

    @Test
    @DisplayName("Валютный счёт может быть открыт для компаний с разным годовым доходом")
    @Tags({
            @Tag("FUll_SCOPE"),
            @Tag("READ_ONLY_TEST"),
            @Tag("BUSINESS_TEST")
    })
    void currencyThreshHoldTest(){
        mainPage.openPage()
                .openBusinessSection()
                .navigateToCurrency();

        businessPage.assertCurrencyTitle("Валютный счет для ИП и юридических лиц")
                .openCurrencyAccount()
                .assertCurrencyRequestTitle("Заполните заявку на расчетный счет")
                .changeThreshHold(">")
                .assertCurrencyTitle("Банк для ВЭД")
                .openCurrencyAccount()
                .assertCurrencyRequestTitle("Открыть валютный счет онлайн");

    }

}
