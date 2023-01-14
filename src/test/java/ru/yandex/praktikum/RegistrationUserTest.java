package ru.yandex.praktikum;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.praktikum.page_object.MainPage;
import ru.yandex.praktikum.page_object.UserLogin;
import ru.yandex.praktikum.page_object.UserProfile;
import ru.yandex.praktikum.page_object.UserRegistration;

import static org.junit.Assert.assertEquals;
import static ru.yandex.praktikum.Api.authUserReg;
import static ru.yandex.praktikum.Api.deleteUser;

public class RegistrationUserTest {

    User user;
    String token;
    MainPage page = Selenide.open(MainPage.BASE_URL, MainPage.class);

    @Before
    public void setUp() {
        user = User.getRandomUser();
    }

    @Test
    @DisplayName("Регисрация пользователя через ЛК") // имя теста
    @Description("Проверка Успешной регистрации через ЛК")
    public void successLKRegUserTest() {
        UserLogin userLogin = page.clickLKAuth();
        UserRegistration userRegistration = userLogin.linkRegister();
        userRegistration.setFieldRegistrationForm(user.getName(), user.getEmail(), user.getPassword());
        userLogin.LoginUser(user.getEmail(), user.getPassword());
        UserProfile userProfile = page.clickLKProfile();
        assertEquals(userProfile.getLinkProfile(), "Профиль");
    }

    @Test
    @DisplayName("Регисрация пользователя через кнопку Войти в аккаунт на главной странице") // имя теста
    @Description("Проверка Успешной регистрации через кнопку Войти в аккаунт на главной странице")
    public void successSigInRegUserTest() {
        UserLogin userLogin = page.buttonClickSignIn();
        UserRegistration userRegistration = userLogin.linkRegister();
        userRegistration.setFieldRegistrationForm(user.getName(), user.getEmail(), user.getPassword());
        userLogin.LoginUser(user.getEmail(), user.getPassword());
        UserProfile userProfile = page.clickLKProfile();
        assertEquals(userProfile.getLinkProfile(), "Профиль");
    }

    @Test
    @DisplayName("Некорректный пароль при регистрации") // имя теста
    @Description("Проверка, что если пароль меньше 6 символов, то регистрация невозможна")
    public void noRegistrationUserTest() {
        String password = "QweR3";
        String text = "Некорректный пароль";

        UserLogin userLogin = page.clickLKAuth();
        UserRegistration userRegistration = userLogin.linkRegister();
        userRegistration.setFieldRegistrationForm(user.getName(), user.getEmail(), password);
        assertEquals(userRegistration.mesWrongPas(), "Некорректный пароль");
    }

    @After
    public void clear() {
        AuthorizationClient authorizationClient = new AuthorizationClient(user.getEmail(), user.getPassword());
        Response responseAuth = authUserReg(authorizationClient);
        token = responseAuth.body().jsonPath().getString("accessToken");
        if (token != null) {
            deleteUser(token);
        }
    }
}
