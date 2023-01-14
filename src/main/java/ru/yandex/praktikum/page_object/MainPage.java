package ru.yandex.praktikum.page_object;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Condition.*;

public class MainPage {
    public static final String BASE_URL = "https://stellarburgers.nomoreparties.site/";
    //Кнопка Личный кабинет
    @FindBy(xpath = "//p[text()='Личный Кабинет']")
    private SelenideElement buttonLC;
    //Кнопка Войти в аккаунт
    @FindBy(xpath = "//*[text()='Войти в аккаунт']")
    private SelenideElement buttonSignIn;
    //Логотип Stellar Burgers
    @FindBy(xpath = "//div[contains(@class,'AppHeader_header')]")
    private SelenideElement logoSB;
    //Срберите бургер
    @FindBy(xpath = "//section[contains(@class,'BurgerIngredients')]/h1")
    private SelenideElement textSB;
    //Лента заказов
    @FindBy(xpath = "//li[contains(@class,'undefined')]")
    private SelenideElement orderFeed;

    //Ссылка Булки
    @FindBy(xpath = "//div[contains(@class,'tab_tab')]/span[text()='Булки']")
    private SelenideElement bun;
    //Ссылка Соусы
    @FindBy(xpath = "//div[contains(@class,'tab_tab')]/span[text()='Соусы']")
    private SelenideElement souse;
    //Ссылка Начинки
    @FindBy(xpath = "//div[contains(@class,'tab_tab')]/span[text()='Начинки']")
    private SelenideElement filling;
    //коллекция ингредиентов
    @FindBy(xpath = "//a[contains(@class,'BurgerIngredient_ingredient')]")
    ElementsCollection bunCollection;
    //Кнопка закрыть в появляющемся окне с описанием ингредента
    @FindBy(xpath = "//button[contains(@class,'Modal_modal__close')]")
    private SelenideElement close;


    @Step("Переход к разделу Булки")
    public void clickBun() {
        bun.click();
    }

    @Step("Переход к разделу Соусы")
    public void clickSouse() {
        souse.click();

    }

    @Step("Переход к разделу Начинки")
    public void clickFilling() {
        filling.click();
    }

    @Step("Выбор ингредиента бургера")
    public void checkBunCollections(String type) {
        bunCollection.filter(Condition.text(type)).first().should(exist).scrollTo().click();
        close.click();

    }


    @Step("Клик по кнопке Лента заказов")
    public OrderFeed clickOrderFeed() {

        orderFeed.click();
        return Selenide.page(OrderFeed.class);
    }

    @Step("Клик по кнопке LK с переходом на страницу авторизации")
    public UserLogin clickLKAuth() {
        buttonLC.click();
        return Selenide.page(UserLogin.class);
    }

    @Step("Клик по кнопке LK с переходом на страницу Профиль")
    public UserProfile clickLKProfile() {
        buttonLC.click();
        return Selenide.page(UserProfile.class);
    }

    @Step("Получение текста логотипа SB")
    public String getTextSB() {
        return textSB.getText();
    }

    @Step("Клик по кнопке Войти в аккаунт с переходом на страницу Авторизации")
    public UserLogin buttonClickSignIn() {
        buttonSignIn.click();
        return Selenide.page(UserLogin.class);
    }


}
