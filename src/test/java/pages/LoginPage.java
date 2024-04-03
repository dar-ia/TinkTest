package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.*;

public class LoginPage extends MainPage {
    SelenideElement phoneNumber = $("input[automation-id='phone-input']");
    SelenideElement submit = $("button[automation-id='button-submit']");
    SelenideElement errorText = $("[automation-id='server-error']");

    @Step("Пользователь вводит номер: {number}")
    public LoginPage setLoginNumber(String number) {
        switchTo().window(1);
        phoneNumber.setValue(number);
        return this;
    }

    @Step("Ползователь подтверждает логин")
    public LoginPage submitLogin(){
        submit.click();
        return this;
    }


    @Step("Проверка, что логин не возможен")
    public LoginPage assertThatLoginFailed() {
        errorText.shouldBe(Condition.text("Введен неверный номер телефона"));
        return this;

    }
}
