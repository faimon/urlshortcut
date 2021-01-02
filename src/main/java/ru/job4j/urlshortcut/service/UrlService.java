package ru.job4j.urlshortcut.service;

import org.apache.commons.text.RandomStringGenerator;
import org.springframework.stereotype.Service;
import ru.job4j.urlshortcut.model.Url;
import ru.job4j.urlshortcut.repository.UrlRepository;

import static org.apache.commons.text.CharacterPredicates.DIGITS;
import static org.apache.commons.text.CharacterPredicates.LETTERS;

@Service
public class UrlService {
    private final UrlRepository urlRepository;
    private final UserService userService;


    public UrlService(UrlRepository urlRepository, UserService userService) {
        this.urlRepository = urlRepository;
        this.userService = userService;
    }

    public Url saveUrl(String urlAddress, String login) {
        Url url = new Url();
        url.setCountCalls(0);
        url.setUrl(urlAddress);
        url.setUser(userService.findUserByLogin(login));
        RandomStringGenerator generator = new RandomStringGenerator.Builder()
                .withinRange('0', 'z')
                .filteredBy(LETTERS, DIGITS)
                .build();
        url.setUrlShort(generator.generate(10));
        urlRepository.save(url);
        return url;
    }

    public Url findUrl(String url) {
        return urlRepository.findUrlByUrl(url);
    }
}