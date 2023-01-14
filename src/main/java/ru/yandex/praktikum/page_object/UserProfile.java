package ru.yandex.praktikum.page_object;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;

public class UserProfile {
    public static final String Prof_URL = MainPage.BASE_URL + "profile";
    //ССылка Профиль
    @FindBy(xpath = "//li[contains(@class,'Account_listItem')][1]/a")
    private SelenideElement linkProfile;
    //Сылка История заказов
    @FindBy(xpath = "//li[contains(@class,'Account_listItem')][2]/a")
    private SelenideElement linkPurchaseHistory;
    //Ссылка Выход
    @FindBy(xpath = "//li[contains(@class,'Account_listItem')]/button")
    private SelenideElement exitLink;
    //Поле Имя
    @FindBy(xpath = "//li[contains(@class,'Profile')][1]/div[contains(@class,'input')]/div[contains(@class,'input')]/input[@value]")
    private SelenideElement fieldName;

    //Поле Логин
    @FindBy(xpath = "//li[contains(@class,'Profile')][2]/div[contains(@class,'input')]/div[contains(@class,'input')]/input")
    private SelenideElement fieldLogin;
    //Поле Пароль
    @FindBy(xpath = "li[contains(@class,'Profile_profileListItem')][3]/div")
    private SelenideElement fieldPassword;
    //Логотип SB
    @FindBy(xpath = "//div[contains(@class,'AppHeader_header')]")
    private SelenideElement logoSB;
    //Конструктор.
    @FindBy(xpath = "//div[contains(@class,'AppHeader_header')]")
    private SelenideElement constructor;

    @Step(" ")
    public String getTextProfile() {
        return fieldName.getText();

    }

    @Step("клик по логотипу SB на странице Профиля с переходом на главную страницу ")
    public MainPage clickLogoSB() {
        logoSB.click();
        return Selenide.page(MainPage.class);
    }

    @Step("клик по конструктору на странице Профиля с переходом на главную страницу ")
    public MainPage clickConstructorSB() {
        constructor.click();
        return Selenide.page(MainPage.class);
    }

    @Step("Получение текста ссылки Профиль на странице Профиля ")
    public String getLinkProfile() {
        return linkProfile.getText();
    }

    @Step("Клик по ссылке История заказов на странице Профиль с переходом на страницу Авторизации ")
    public OrderHistory clickLinkPurchaseHistory() {
        linkPurchaseHistory.click();
        return Selenide.page(OrderHistory.class);
    }

    @Step("Клик по ссылке Выход на странице Профиль с переходом на страницу Авторизации ")
    public UserLogin pressExitLink() {
        exitLink.click();
        return Selenide.page(UserLogin.class);
    }


}
