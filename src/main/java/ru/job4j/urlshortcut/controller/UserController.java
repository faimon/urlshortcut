package ru.job4j.urlshortcut.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.job4j.urlshortcut.dto.UserDto;
import ru.job4j.urlshortcut.service.UserService;

import java.util.Map;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/registration", consumes = "application/json")
    public ResponseEntity<UserDto> createUser(@RequestBody Map<String, String> body) {
        String site = body.get("site");
        if (site == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        UserDto userDto = createUserDtoIfExist(site);
        return new ResponseEntity<>(
                userDto,
                HttpStatus.CREATED);
    }

    private UserDto createUserDtoIfExist(String site) {
        UserDto userDto;
        if (userService.userIsExist(site)) {
            userDto = new UserDto(false, null, null);
        } else {
            userDto = userService.saveUser(site);
        }
        return userDto;
    }
}