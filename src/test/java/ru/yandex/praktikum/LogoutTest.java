package ru.yandex.praktikum;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.praktikum.page_object.MainPage;
import ru.yandex.praktikum.page_object.UserLogin;
import ru.yandex.praktikum.page_object.UserProfile;

import static org.hamcrest.MatcherAssert.assertThat;
import static ru.yandex.praktikum.Api.*;
import static ru.yandex.praktikum.User.getRandomUser;


public class LogoutTest {

    User user;
    String token;
    MainPage page = Selenide.open(MainPage.BASE_URL, MainPage.class);

    @Before
    public void init() {
        user = getRandomUser();
        sucUserReg(user);
    }

    @Test
    @DisplayName("Выход по кнопке «Выйти» в личном кабинете.") // имя теста
    @Description("Проверка, чосуществляется выход мз аккаунта по кнопке «Выйти»")
    public void ExitToProfileTest() {
        UserLogin userLogin = page.clickLKAuth();
        userLogin.LoginUser(user.getEmail(), user.getPassword());
        UserProfile userProfile = page.clickLKProfile();
        userProfile.pressExitLink();
        assertThat("Ожидается текст: " + userLogin.getInputText(), CoreMatchers.containsString("Вход"));
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
