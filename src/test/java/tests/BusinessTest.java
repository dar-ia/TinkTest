package tests;

import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pages.BusinessPage;
import pages.MainPage;

import java.util.stream.Stream;

@DisplayName("Тесты на секцию для бизнес пользователей")
@Owner("dar-ia")
@Tag("BUSINESS_TEST")
public class BusinessTest extends TestBase {

    MainPage mainPage = new MainPage();
    BusinessPage businessPage = new BusinessPage();

    @Test
    @DisplayName("Заявка на открытие расчётного счёта открывается")
    void accountingCanBeStarted() {
        mainPage.openPage()
                .openBusinessSection()
                .navigateToAccounting();

        businessPage.openAccount()
                .assertAccountOpened();
    }


    @ParameterizedTest(name = "годовым доходом {0} 120")
    @MethodSource
    @DisplayName("Валютный счёт может быть открыт для компаний с ")
    void currencyThreshHoldTest(String threshHold, String currencyTitle, String currencyRequestTitle) {
        mainPage.openPage()
                .openBusinessSection()
                .navigateToCurrency();

        businessPage.changeThreshHold(threshHold)
                .assertCurrencyTitle(currencyTitle)
                .openCurrencyAccount()
                .assertCurrencyRequestTitle(currencyRequestTitle);

    }

    static Stream<Arguments> currencyThreshHoldTest() {
        return Stream.of(
                Arguments.of("<",
                        "Валютный счет для ИП и юридических лиц",
                        "Заполните заявку на расчетный счет"),
                Arguments.of(">",
                        "Банк для ВЭД",
                        "Открыть валютный счет онлайн")
        );

    }

}
