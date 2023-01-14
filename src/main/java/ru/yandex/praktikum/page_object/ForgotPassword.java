package ru.yandex.praktikum.page_object;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;

public class ForgotPassword {
    public static final String FP_URL = MainPage.BASE_URL + "forgot-password";

    //Ссылка Войти
    @FindBy(xpath = "//a[text()='Войти']")
    private SelenideElement linkSignIn;

    @Step("Клик по кнопке Войти на странице Восстановления пароля с переходом на страницу Авторизации")
    public UserLogin clickLinkSignInFP() {
        linkSignIn.click();
        return Selenide.page(UserLogin.class);
    }
}
