package ru.yandex.praktikum;

import org.apache.commons.lang3.RandomStringUtils;

public class User {
    private String email;
    private String password;
    private String name;

    public String getName() {
        return name;
    }

    public User(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static User getRandomUser() {
        String email = "mail" + RandomStringUtils.randomNumeric(10) + "@yandex.ru";
        String password = RandomStringUtils.randomAlphabetic(6);
        String name = RandomStringUtils.randomAlphabetic(10);
        return new User(email, password, name);
    }

    @Override
    public String toString() {
        return String.format("UserCreation{email='%s', password='%s', name='%s'}", email, password, name);
    }

}
