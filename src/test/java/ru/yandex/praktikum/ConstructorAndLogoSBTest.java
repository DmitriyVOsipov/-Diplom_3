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

import static org.junit.Assert.assertEquals;
import static ru.yandex.praktikum.Api.*;
import static ru.yandex.praktikum.User.getRandomUser;

public class ConstructorAndLogoSBTest {

    User user;
    String token;
    MainPage page = Selenide.open(MainPage.BASE_URL, MainPage.class);

    @Before
    public void init() {
        user = getRandomUser();
        sucUserReg(user);
        UserLogin userLogin = page.clickLKAuth();
        userLogin.LoginUser(user.getEmail(), user.getPassword());

    }

    @Test
    @DisplayName("Переход из личного кабинета по клику на лого SB") // имя теста
    @Description("Проверка, что из личного кабинета по клику на лого SB происходит переход на главную страницу с конструктором бургера")
    public void fromLKToLogoTest() {

        UserProfile userProfile = page.clickLKProfile();
        userProfile.clickLogoSB();
        assertEquals(page.getTextSB(), "Соберите бургер");

    }

    @Test
    @DisplayName("Переход из личного кабинета по клику на лого SB") // имя теста
    @Description("Проверка, что из личного кабинета по клику на лого SB происходит переход на главную страницу с конструктором бургера")
    public void fromLKToConstructorTest() {

        UserProfile userProfile = page.clickLKProfile();
        userProfile.clickConstructorSB();
        assertEquals(page.getTextSB(), "Соберите бургер");

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
