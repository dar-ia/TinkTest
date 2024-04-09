package tests;

import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.LoginPage;
import pages.MainPage;

@DisplayName("Тесты на авторизацию")
@Owner("dar-ia")
@Tag("AUTHORIZATION_TEST")
public class AuthorizeTest extends TestBase {
    MainPage mainPage = new MainPage();
    LoginPage loginPage = new LoginPage();

    @Test
    @DisplayName("Негативный тест на авторизацию с не валидным форматом номера")
    void authorizeWithInvalidNumberFormatTest() {
        mainPage.openPage()
                .initiateLogIn();
        loginPage.setLoginNumber("234")
                .submitLogin()
                .assertThatLoginFailed();
    }
}
