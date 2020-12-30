package ru.job4j.urlshortcut.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.job4j.urlshortcut.dto.UserDto;
import ru.job4j.urlshortcut.model.User;
import ru.job4j.urlshortcut.service.UserService;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/registration")
    public ResponseEntity<UserDto> createUser(@RequestBody String site) {
        boolean userIsExist = userService.userIsExist(site);
        User user;
        UserDto userDto;
        if (userIsExist) {
            user = userService.findUserBySite(site);
            userDto = new UserDto(false, user.getLogin(), user.getPassword());
        } else {
            user = userService.saveUser(site);
            userDto = new UserDto(true, user.getLogin(), user.getPassword());
        }
        return new ResponseEntity<>(
                userDto,
                HttpStatus.CREATED);
    }
}