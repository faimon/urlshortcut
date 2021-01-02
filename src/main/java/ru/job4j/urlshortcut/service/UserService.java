package ru.job4j.urlshortcut.service;

import org.apache.commons.text.RandomStringGenerator;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.job4j.urlshortcut.dto.UserDto;
import ru.job4j.urlshortcut.model.User;
import ru.job4j.urlshortcut.repository.RoleRepository;
import ru.job4j.urlshortcut.repository.UserRepository;

import static org.apache.commons.text.CharacterPredicates.DIGITS;
import static org.apache.commons.text.CharacterPredicates.LETTERS;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roles;
    private final BCryptPasswordEncoder encoder;

    public UserService(UserRepository userRepository, RoleRepository roles, BCryptPasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.roles = roles;
        this.encoder = encoder;
    }

    public UserDto saveUser(String site) {
        User user = new User();
        RandomStringGenerator generator = new RandomStringGenerator.Builder()
                .withinRange('0', 'z')
                .filteredBy(LETTERS, DIGITS)
                .build();
        user.setSite(site);
        user.setRole(roles.findByName("ROLE_USER"));
        user.setLogin(site);
        String password = generator.generate(20);
        user.setPassword(encoder.encode(password));
        userRepository.save(user);
        return new UserDto(true, user.getLogin(), password);
    }

    public boolean userIsExist(String site) {
        User user = userRepository.findUserBySite(site);
        return user != null;
    }

    public User findUserBySite(String site) {
        return userRepository.findUserBySite(site);
    }
}
