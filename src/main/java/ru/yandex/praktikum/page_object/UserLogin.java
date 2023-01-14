package ru.yandex.praktikum.page_object;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Selenide.$;
import static ru.yandex.praktikum.page_object.MainPage.BASE_URL;

public class UserLogin {
    public static final String Login_URL = BASE_URL + "login";

    //Техт Вход
    @FindBy(xpath = "//div[@class='Auth_login__3hAey']/h2")
    private SelenideElement inputText;
    //Поле Email
    @FindBy(xpath = "//fieldset[contains(@class,'Auth')][1]//input")
    private SelenideElement fieldEmailLogin;
    // Поле  Пароль
    @FindBy(xpath = "//fieldset[contains(@class,'Auth')][2]//input")
    private SelenideElement fieldPasswordLogin;
    // Кнопка Войти
    @FindBy(xpath = "//form[@class='Auth_form__3qKeq mb-20']/button")
    private SelenideElement buttonInput;
    //Ссылка зарегистрироваться
    @FindBy(xpath = "//a[text()='Зарегистрироваться']")
    private SelenideElement linkRegister;

    @Step("Клик по ссылке Регистрации на странице авторизации с переходом на страницу Регистрации")
    public UserRegistration linkRegister() {
        $(By.xpath("//a[text()='Зарегистрироваться']")).scrollIntoView(false).click();
        return Selenide.page(UserRegistration.class);
    }

    @Step("Клик по ссылке Востановить пароль на странице авторизации с переходом на страницу Восстановление пароля")
    public ForgotPassword restorePassword() {
        $(By.xpath("//a[text()='Восстановить пароль']")).scrollIntoView(false).click();
        return Selenide.page(ForgotPassword.class);
    }


    @Step("Заполнение обязательных полей авторизации и клик по кнопке Войти")
    public void LoginUser(String email, String password) {
        fieldEmailLogin.clear();
        fieldEmailLogin.setValue(email);
        fieldPasswordLogin.clear();
        fieldPasswordLogin.setValue(password);
        buttonInput.click();
    }

    @Step("Заполнение обязательных полей авторизации и клик по кнопке Войти")
    public void clickInput() {
        buttonInput.click();
    }

    @Step("Получение текста кнопки Войти")
    public String getInputText() {
        return inputText.getText();
    }


}
