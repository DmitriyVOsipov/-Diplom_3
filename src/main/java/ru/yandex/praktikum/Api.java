package ru.yandex.praktikum;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static ru.yandex.praktikum.page_object.MainPage.BASE_URL;

public class Api {

    @Step("Регистрация клиента")
    public static Response sucUserReg(User user) {

        return (Response) given()
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .post(BASE_URL + "api/auth/register")
                .then().log().all().extract();

    }

    public static Response authUserReg(AuthorizationClient authorizationClient) {

        return (Response) given()
                .body(authorizationClient)
                .when()
                .contentType(ContentType.JSON)
                .post(BASE_URL + "api/auth/login")
                .then().log().all().extract();
    }

    @Step("Удаление Юзера")
    public static Boolean deleteUser(String token) {

        return given()
                .headers(Map.of("authorization", token))
                .when()
                .contentType(ContentType.JSON)
                .delete(BASE_URL + "api/auth/user")
                .then()
                .assertThat().log().all()
                .statusCode(202)
                .extract()
                .path("ok");
    }
}
