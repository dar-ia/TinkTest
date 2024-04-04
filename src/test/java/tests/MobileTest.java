package tests;

import com.opencsv.exceptions.CsvException;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pages.MainPage;
import pages.MobileOperatorPage;
import utils.GenerateRandomData;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

import static pages.MobileOperatorPage.readData;

@DisplayName("Тесты на мобильного оператора")
@Owner("dar-ia")
@Tag("MOBILE_TESTS")
public class MobileTest extends TestBase {
    MainPage mainPage = new MainPage();
    MobileOperatorPage mobilePage = new MobileOperatorPage();
    GenerateRandomData randomData = new GenerateRandomData();

    @Test
    @DisplayName("Тест на добавление eSIM")
    void connectEsimTest() {
        mainPage.openPage()
                .openSimCards();
        mobilePage.selectElectronicSimonSlidePanel()
                .assertComponentTitle("Закажите сим-карту")
                .assertSimOption("Электронная сим-карта");
    }

    @Test
    @DisplayName("Тест на выбор красивых номеров")
    void selectBeautyNumberTest() {

        int randomNumber = randomData.generateRandomNumber();
        mainPage.openPage()
                .openSimCards();
        mobilePage.selectBeautyNumbersOnSlidePanel();
        List<String> result = mobilePage.fetchBeautyNumberValues(randomNumber);
        String selectedNumber = result.get(0);
        String price = result.get(1);
        mobilePage.selectBeautyNumber(randomNumber)
                .orderSimWithBeautyNumber()
                .assertTheBeautyIsSelectedSuccessfully(selectedNumber, price);

    }

    @Test
    @DisplayName("Тест на добавление номера в избранное")
    void addBeautyNumberToFavoritesTest() {
        int randomNumber = randomData.generateRandomNumber();
        mainPage.openPage()
                .openSimCards();
        mobilePage.selectBeautyNumbersOnSlidePanel()
                .clickOnHeart(randomNumber);
        List<String> result = mobilePage.fetchBeautyNumberValues(randomNumber);
        String selectedNumber = result.get(0);

        mobilePage.moveToFavoritesTab()
                .asserThatNumberIsAdded(selectedNumber);

    }


    @DisplayName("Тест на роуминг тарифы")
    @MethodSource
    @ParameterizedTest(name = "Данные для роуминга корректные в стране {0}")
    void roamingTest(String country, List<String> data) {
        System.out.println();
        String messengers = data.get(1);
        String thirtyDaysOption = data.get(2);
        String thirtyDaysPrice = data.get(3);
        String sevenDaysOption = data.get(4);
        String sevenDaysPrice = data.get(5);
        String oneDayOption = data.get(6);
        String oneDayPrice = data.get(7);
        String unlimitedInGooglePrice = data.get(8);
        String ThirtyMinsPrice = data.get(9);
        String incomingsPrice = data.get(10);
        String outgoingsPrice = data.get(11);
        String smsPrice = data.get(12);

        mainPage.openPage()
                .openSimCards();
        mobilePage.selectRoamingOnSlidePanel()
                .selectRoamingCountry(country)
                .assertTariffResult("Безлимитные мессенджеры", messengers)
                .assertTariffResult(thirtyDaysOption, thirtyDaysPrice)
                .assertTariffResult(sevenDaysOption, sevenDaysPrice)
                .assertTariffResult(oneDayOption, oneDayPrice)
                .assertTariffResult("Безлимит на навигацию Google", unlimitedInGooglePrice)
                .assertTariffResult("30 минут входящих", ThirtyMinsPrice)
                .assertTariffResult("Входящие", incomingsPrice)
                .assertTariffResult("Исходящие", outgoingsPrice)
                .assertTariffResult("СМС", smsPrice);

    }

    static Stream<Arguments> roamingTest() throws IOException, CsvException {
        return Stream.of(
                Arguments.of(readData().get(0).get(0), readData().get(0)),
                Arguments.of(readData().get(1).get(0), readData().get(1))
        );

    }


}
