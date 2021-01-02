package ru.job4j.urlshortcut.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.job4j.urlshortcut.UrlShortcutApplication;
import ru.job4j.urlshortcut.dto.UserDto;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

@SpringBootTest (classes = UrlShortcutApplication.class)
class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    public void whenSaveUser() {
        String site = "www.rbc.ru";
        UserDto user = userService.saveUser(site);
        assertThat(user.getLogin(), is(site));
        assertNotNull(user.getLogin());
        assertNotNull(user.getPassword());
    }
}