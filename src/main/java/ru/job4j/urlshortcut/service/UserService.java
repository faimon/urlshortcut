package ru.job4j.urlshortcut.service;

import org.apache.commons.text.RandomStringGenerator;
import org.springframework.stereotype.Service;
import ru.job4j.urlshortcut.model.User;
import ru.job4j.urlshortcut.repository.UserRepository;

import static org.apache.commons.text.CharacterPredicates.DIGITS;
import static org.apache.commons.text.CharacterPredicates.LETTERS;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveUser(String site) {
        User user = new User();
        RandomStringGenerator generator = new RandomStringGenerator.Builder()
                .withinRange('0', 'z')
                .filteredBy(LETTERS, DIGITS)
                .build();
        user.setSite(site);
        user.setLogin(generator.generate(20));
        user.setPassword(generator.generate(30));
        userRepository.save(user);
        return user;
    }

    public boolean userIsExist(String site) {
        User user = userRepository.findUserBySite(site);
        return user != null;
    }

    public User findUserBySite(String site) {
        return userRepository.findUserBySite(site);
    }
}
