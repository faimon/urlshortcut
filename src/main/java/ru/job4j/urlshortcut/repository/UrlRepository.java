package ru.job4j.urlshortcut.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.job4j.urlshortcut.model.Url;

public interface UrlRepository extends CrudRepository<Url, Integer> {
    Url findUrlByUrlShort(String urlShort);

    Url findUrlByUrl(String url);

    @Override
    @Query("FROM Url AS u JOIN FETCH u.user")
    Iterable<Url> findAll();
}
