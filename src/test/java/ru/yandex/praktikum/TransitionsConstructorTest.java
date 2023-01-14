package ru.yandex.praktikum;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import ru.yandex.praktikum.page_object.MainPage;

public class TransitionsConstructorTest {
    MainPage page = Selenide.open(MainPage.BASE_URL, MainPage.class);
    String typeSous = "Соус фирменный Space Sauce";
    String typeBun = "Флюоресцентная булка R2-D3";
    String typeFiling = "Сыр с астероидной плесенью";


    @Test
    @DisplayName("Переходы к разделам по клику на название раздела ") // имя теста
    @Description("Проверка, что по клику на раздел происходит переключение ингредиентов")
    public void switchIngredientsTest() {
        page.clickSouse();
        page.checkBunCollections(typeSous);
        page.clickBun();
        page.checkBunCollections(typeBun);
        page.clickFilling();
        page.checkBunCollections(typeFiling);
        page.clickBun();
    }

    @Test
    @DisplayName("Прокрутка разделов с ингредиентами ") // имя теста
    @Description("Проверка, что ингредиенты доступны при прокрутке списка")
    public void ScrollIngredientsTest() {
        page.checkBunCollections(typeSous);
        page.checkBunCollections(typeFiling);
        page.checkBunCollections(typeBun);
    }
}
