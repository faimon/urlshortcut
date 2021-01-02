package ru.job4j.urlshortcut.controller;

import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.job4j.urlshortcut.dto.ApiError;
import ru.job4j.urlshortcut.dto.UrlDto;
import ru.job4j.urlshortcut.model.Url;
import ru.job4j.urlshortcut.service.UrlService;

import java.util.Map;

@RestController
public class UrlController {
    private final UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping("/convert")
    public ResponseEntity<Object> saveUrl(@RequestBody Map<String, String> body,
                                          Authentication auth) {
        String urlAddress = body.get("url");
        UrlValidator urlValidator = new UrlValidator();
        if (urlAddress == null) {
            return new ResponseEntity<>(new ApiError("Not found url"),
                    HttpStatus.BAD_REQUEST);
        }
        if (!urlValidator.isValid(urlAddress)) {
            return new ResponseEntity<>(new ApiError("Invalid url"),
                    HttpStatus.BAD_REQUEST);
        }
        Url findUrl = urlService.findUrl(urlAddress);
        if (findUrl != null) {
            return new ResponseEntity<>(
                    new UrlDto(findUrl.getUrlShort()),
                    HttpStatus.OK);
        }
        Url url = urlService.saveUrl(urlAddress, auth.getPrincipal().toString());
        return new ResponseEntity<>(
                new UrlDto(url.getUrlShort()),
                HttpStatus.OK);
    }
}
