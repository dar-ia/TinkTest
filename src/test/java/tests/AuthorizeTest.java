package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import pages.LoginPage;
import pages.MainPage;
@DisplayName("Authorization form tests")
public class AuthorizeTest extends TestBase{
    MainPage mainPage = new MainPage();
    LoginPage loginPage = new LoginPage();


    @Test
    @DisplayName("Authorization failed for invalid number format")
    @Tags({
            @Tag("NEGATIVE"),
            @Tag("FUll_SCOPE"),
            @Tag("AUTHORIZATION_TEST")
    })
    void authorizeWithInvalidNumberFormatTest() {
        mainPage.openPage()
                .initiateLogIn();

        loginPage.tryToLogin("234").
        assertThatLoginFailed();

    }
}
