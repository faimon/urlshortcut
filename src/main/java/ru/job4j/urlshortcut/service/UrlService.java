package ru.job4j.urlshortcut.service;

import org.apache.commons.text.RandomStringGenerator;
import org.springframework.stereotype.Service;
import ru.job4j.urlshortcut.model.Url;
import ru.job4j.urlshortcut.repository.UrlRepository;

import java.util.List;

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

    public Url findUrlByShortUrl(String shortUrl) {
        return urlRepository.findUrlByUrlShort(shortUrl);
    }

    public Url findUrl(String url) {
        return urlRepository.findUrlByUrl(url);
    }

    public void incrementCountCalls(Url url) {
        url.setCountCalls(url.getCountCalls() + 1);
        urlRepository.save(url);
    }

    public List<Url> findAll() {
        return (List<Url>) urlRepository.findAll();
    }
}