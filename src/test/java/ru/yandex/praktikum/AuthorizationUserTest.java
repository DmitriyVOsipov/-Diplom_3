package ru.yandex.praktikum;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.praktikum.page_object.*;
import static org.junit.Assert.assertEquals;
import static ru.yandex.praktikum.Api.*;
import static ru.yandex.praktikum.User.getRandomUser;


public class AuthorizationUserTest {


    User user;
    String token;
    MainPage page = Selenide.open(MainPage.BASE_URL, MainPage.class);

    @Before
    public void init() {
        user = getRandomUser();
        sucUserReg(user);
    }
    @Test
    @DisplayName("Авторизация пользователя через кнопку Войти в аккаунт на главной странице") // имя теста
    @Description("Пользователь может зарегистрироваться через Войти в аккаунт")
    public void successSighInAuthUserTest() {

        UserLogin userLogin = page.buttonClickSignIn();
        userLogin.LoginUser(user.getEmail(), user.getPassword());
        UserProfile userProfile = page.clickLKProfile();
        assertEquals(userProfile.getLinkProfile(), "Профиль");

    }


    @Test
    @DisplayName("Авторизация пользователя через ЛК") // имя теста
    @Description("Пользователь может зарегистрироваться, перейдя в ЛК")
    public void successLKAuthUserTest() {

        UserLogin userLogin = page.clickLKAuth();
        userLogin.LoginUser(user.getEmail(), user.getPassword());
        UserProfile userProfile = page.clickLKProfile();
        assertEquals(userProfile.getLinkProfile(), "Профиль");

    }




    @Test
    @DisplayName("Авторизация пользователя через ссылку Зарегистрироваться на форме регистрации") // имя теста
    @Description("Пользователь может зарегистрироваться через ссылку Зарегистрироваться на форме регистрации")
    public void successLinkSighInAuthUserTest() {

        UserLogin userLogin = page.clickLKAuth();
        UserRegistration userRegistration = userLogin.linkRegister();
        userRegistration.clickLinkSignIn();
        userLogin.LoginUser(user.getEmail(), user.getPassword());
        UserProfile userProfile = page.clickLKProfile();
        assertEquals(userProfile.getLinkProfile(), "Профиль");

    }



    @Test
    @DisplayName("Авторизация пользователя через ссылку Восстановить пароль на форме регистрации") // имя теста
    @Description("Пользователь может зарегистрироваться через ссылку Восстановить пароль на форме регистрации")
    public void successPasRestoreAuthUserTest() {

        UserLogin userLogin = page.clickLKAuth();
        ForgotPassword forgotPassword = userLogin.restorePassword();
        forgotPassword.clickLinkSignInFP();
        System.out.println("стоп");
        userLogin.LoginUser(user.getEmail(), user.getPassword());
        UserProfile userProfile = page.clickLKProfile();
        assertEquals(userProfile.getLinkProfile(), "Профиль");

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
