package tests;

import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import pages.LoginPage;
import pages.MainPage;
@DisplayName("Тесты на авторизацию")
public class AuthorizeTest extends TestBase{
    MainPage mainPage = new MainPage();
    LoginPage loginPage = new LoginPage();


    @Test
    @DisplayName("Негативный тест на авторизацию с не валидным форматом номера")
    @Tags({
            @Tag("NEGATIVE"),
            @Tag("FUll_SCOPE"),
            @Tag("AUTHORIZATION_TEST")
    })
    @Owner("dar-ia")
    void authorizeWithInvalidNumberFormatTest() {
        mainPage.openPage()
                .initiateLogIn();

        loginPage.tryToLogin("234").
        assertThatLoginFailed();

    }
}
