package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import pages.MainPage;
import pages.MobileOperatorPage;
import utils.GenerateRandomData;

import java.util.List;
@DisplayName("Тесты на мобильные планы")
public class MobileTest extends TestBase{
    MainPage mainPage = new MainPage();
    MobileOperatorPage mobilePage = new MobileOperatorPage();
    GenerateRandomData randomData = new GenerateRandomData();

    @Test
    @DisplayName("eSIM выбирается автоматически")
    @Tags({
            @Tag("FUll_SCOPE"),
            @Tag("READ_ONLY_TEST"),
            @Tag("MOBILE_TESTS")
    })
    void connectEsimTest(){
        mainPage.openPage()
                .openSimCards();
        mobilePage.selectElectronicSimonSlidePanel()
                .assertComponentTitle("Закажите сим-карту")
                .assertSimOption("Электронная сим-карта");
    }

    @Test
    @DisplayName("Красивые номера выбираются")
    @Tags({
            @Tag("FUll_SCOPE"),
            @Tag("READ_ONLY_TEST"),
            @Tag("MOBILE_TESTS")
    })
    void selectBeautyNumberTest(){

        int randomNumber = randomData.generateRandomNumber();
        mainPage.openPage()
                .openSimCards();
        mobilePage.selectBeautyNumbersOnSlidePanel();
        List<String> result = mobilePage.fetchBeautyNumberValues(randomNumber);
        String selectedNumber=result.get(0);
        String price=result.get(1);
        mobilePage.selectBeautyNumber(randomNumber)
                .orderSimWithBeautyNumber()
                .assertTheBeautyIsSelectedSuccessfully(selectedNumber, price);

    }

    @Test
    @DisplayName("Номер можно добавить в избранное")
    @Tags({
            @Tag("FUll_SCOPE"),
            @Tag("READ_ONLY_TEST"),
            @Tag("MOBILE_TESTS")
    })
    void addBeautyNumberToFavoritesTest(){
        int randomNumber = randomData.generateRandomNumber();
        mainPage.openPage()
                .openSimCards();
        mobilePage.selectBeautyNumbersOnSlidePanel()
                .clickOnHeart(randomNumber);
        List<String> result = mobilePage.fetchBeautyNumberValues(randomNumber);
        String selectedNumber=result.get(0);

        mobilePage.assertThatNumberIsInFavs(selectedNumber);

    }

    //@Test
    @DisplayName("Данные для роуминга корректные")
    @Tags({
            @Tag("FUll_SCOPE"),
            @Tag("READ_ONLY_TEST"),
            @Tag("MOBILE_TESTS")
    })
    @CsvFileSource(resources = "/test_data/tarif.csv",delimiter = ';')
    @ParameterizedTest(name = "Данные для роуминга корректные в стране {0}")
    void roamingTest(String country,
                     String messengers,
                     String thirtyDaysOption,
                     String thirtyDaysPrice,
                     String sevenDaysOption,
                     String sevenDaysPrice,
                     String oneDayOption,
                     String oneDayPrice,
                     String unlimitedInGooglePrice,
                     String ThirtyMinsPrice,
                     String incomingsPrice,
                     String outgoingsPrice,
                     String smsPrice){
        mainPage.openPage()
                .openSimCards();
        mobilePage.selectRoamingOnSlidePanel()
                .selectRoamingCountry(country)
                .assertTariffResult("Безлимитные мессенджеры",messengers)
                .assertTariffResult(thirtyDaysOption,thirtyDaysPrice)
                .assertTariffResult(sevenDaysOption,sevenDaysPrice)
                .assertTariffResult(oneDayOption,oneDayPrice)
                .assertTariffResult("Безлимит на навигацию Google",unlimitedInGooglePrice)
                .assertTariffResult("30 минут входящих",ThirtyMinsPrice)
                .assertTariffResult("Входящие",incomingsPrice)
                .assertTariffResult("Исходящие",outgoingsPrice)
                .assertTariffResult("СМС",smsPrice);

    }
    //роуминг
    //Таиланд + ОАЭ
    //провалидировать прайсы
}
