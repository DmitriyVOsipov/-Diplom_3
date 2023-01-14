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

import static java.awt.SystemColor.text;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static ru.yandex.praktikum.Api.*;
import static ru.yandex.praktikum.User.getRandomUser;


public class InputLKTest {

    User user;
    String token;
    MainPage page = Selenide.open(MainPage.BASE_URL, MainPage.class);

    @Before
    public void init() {
        user = getRandomUser();
        sucUserReg(user);
    }

    @Test
    @DisplayName("Переход по клику на «Личный кабинет» с главной страницы без авторизации") // имя теста
    @Description("Проверка, что по клику на ЛК попадаешь на страницу входа")
    public void MainPageInLkTest() {

        UserLogin userLogin = page.clickLKAuth();
        String textInput = userLogin.getInputText();
        assertEquals(UserLogin.Login_URL, "https://stellarburgers.nomoreparties.site/login");
        assertThat("Ожидается текст: " + text, textInput, CoreMatchers.containsString("Вход"));


    }

    @Test
    @DisplayName("Переход по клику в «Личный кабинет» со  страницы авторизации без авторизации клиента") // имя теста
    @Description("Проверка, что по клику на ЛК попадаешь на страницу входа")
    public void inputInLkTest() {

        UserLogin userLogin = page.buttonClickSignIn();
        page.clickLKAuth();
        assertEquals(UserLogin.Login_URL, "https://stellarburgers.nomoreparties.site/login");
        assertThat("Ожидается текст: " + userLogin.getInputText(), CoreMatchers.containsString("Вход"));


    }

    @Test
    @DisplayName("Переход по клику на «Личный кабинет» после авторизации клиента") // имя теста
    @Description("Проверка, что по клику на ЛК попадаешь на страницу Профиля")
    public void SignInInLkTest() {
        UserLogin userLogin = page.clickLKAuth();
        userLogin.LoginUser(user.getEmail(), user.getPassword());
        UserProfile userProfile = page.clickLKProfile();
        assertEquals(userProfile.getLinkProfile(), "Профиль");
    }

    @Test
    @DisplayName("Переход по клику на «Личный кабинет» из Ленты заказов без авторизации") // имя теста
    @Description("Проверка, что по клику на ЛК попадаешь на страницу Входа в аккаунт")
    public void OrderFeedInLkNoAuthTest() {
        page.clickOrderFeed();
        UserLogin userLogin = page.clickLKAuth();
        String textInput = userLogin.getInputText();
        assertThat("Ожидается текст: " + text, textInput, CoreMatchers.containsString("Вход"));
    }

    @Test
    @DisplayName("Переход по клику на «Личный кабинет» из Ленты заказов без авторизации") // имя теста
    @Description("Проверка, что по клику на ЛК попадаешь на страницу Профиль")
    public void OrderFeedInLkAuthTest() {
        UserLogin userLogin = page.clickLKAuth();
        userLogin.LoginUser(user.getEmail(), user.getPassword());
        page.clickOrderFeed();
        UserProfile userProfile = page.clickLKProfile();
        assertEquals(userProfile.getLinkProfile(), "Профиль");
    }

    @Test
    @DisplayName("Переход по клику на «Личный кабинет» из Профиля -  История заказов") // имя теста
    @Description("Проверка, что по клику на ЛК попадаешь на страницу Профиль")
    public void HistoryInLkTest() {
        UserLogin userLogin = page.clickLKAuth();
        userLogin.LoginUser(user.getEmail(), user.getPassword());
        UserProfile userProfile = page.clickLKProfile();
        userProfile.clickLinkPurchaseHistory();
        page.clickLKProfile();
        assertEquals(userProfile.getLinkProfile(), "Профиль");
    }

    @Test
    @DisplayName("Переход по клику на «Личный кабинет» из Профиля -  Выход") // имя теста
    @Description("Проверка, что по клику на ЛК попадаешь на страницу Вход")
    public void LinkExitInLkTest() {
        UserLogin userLogin = page.clickLKAuth();
        userLogin.LoginUser(user.getEmail(), user.getPassword());
        UserProfile userProfile = page.clickLKProfile();
        userProfile.pressExitLink();
        page.clickLKAuth();
        assertThat("Ожидается текст: " + userLogin.getInputText(), CoreMatchers.containsString("Вход"));
    }

    @Test
    @DisplayName("Переход по клику на «Личный кабинет» перейдя по ссылку Зарегистрироваться") // имя теста
    @Description("Проверка, что по клику на ЛК попадаешь на страницу Вход")
    public void LinkRegInLkTest() {
        UserLogin userLogin = page.clickLKAuth();
        userLogin.linkRegister();
        page.clickLKAuth();
        assertThat("Ожидается текст: " + userLogin.getInputText(), CoreMatchers.containsString("Вход"));
    }

    @Test
    @DisplayName("Переход по клику на «Личный кабинет» перейдя по ссылку Восстановить пароль") // имя теста
    @Description("Проверка, что по клику на ЛК попадаешь на страницу Вход")
    public void LinkRestorePasInLkTest() {
        UserLogin userLogin = page.clickLKAuth();
        userLogin.restorePassword();
        page.clickLKAuth();
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
