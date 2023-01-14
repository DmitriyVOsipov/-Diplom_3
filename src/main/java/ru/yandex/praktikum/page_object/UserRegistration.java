package ru.yandex.praktikum.page_object;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Condition.enabled;

public class UserRegistration {
    public static final String Reg_URL = MainPage.BASE_URL + "register";


    //Поле Name
    @FindBy(xpath = "//*[text()='Имя']/following-sibling::input")
    private SelenideElement fieldName;
    //Поле Email
    @FindBy(xpath = "//*[text()='Email']/following-sibling::input")
    private SelenideElement fieldEmail;
    //Поле Password
    @FindBy(xpath = "//*[text()='Пароль']/following-sibling::input")
    private SelenideElement fieldPassword;
    //Кнопка Register
    @FindBy(xpath = "//button[text()='Зарегистрироваться']")
    private SelenideElement buttonRegister;
    //Поле(сообщение) Некорректный пароль
    @FindBy(xpath = "//p[text()='Некорректный пароль']")
    private SelenideElement messageWrongPassword;
    //Поле(сообщение) такой пользователь уже существует
    @FindBy(xpath = "//p[text()='Такой пользователь уже существует']")
    private SelenideElement messageUserExists;
    // Кнопка Войти
    @FindBy(xpath = "//button[text()='Войти']")
    private SelenideElement buttonInput;
    // Ссылка Войти
    @FindBy(xpath = "//a[contains(@class,'Auth_link')]")
    private SelenideElement linkSignIn;

    @Step("Клик по ссылке Войти на странице Регистрации с переходом на страницу Автоизации")
    public UserLogin clickLinkSignIn() {
        linkSignIn.click();
        return Selenide.open(MainPage.BASE_URL + "login", UserLogin.class);
    }

    //Успешная регистрация
    @Step("Регистрация пользователя с переходом на страницу Автоизации")
    public UserLogin setFieldRegistrationForm(String name, String email, String password) {
        fieldName.setValue(name);
        fieldEmail.shouldBe(enabled).setValue(email);
        fieldPassword.shouldBe(enabled).setValue(password);
        buttonRegister.click();
        return Selenide.page(UserLogin.class);

    }

    @Step("Получение текста об ошобке при вводе некорректного пароля")
    public String mesWrongPas() {
        return messageWrongPassword.getText();
//        messageWrongPassword.shouldHave(exactText("Некорректный пароль"));

    }


}
