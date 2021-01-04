package ru.job4j.urlshortcut.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.job4j.urlshortcut.model.Url;

import javax.transaction.Transactional;

public interface UrlRepository extends CrudRepository<Url, Integer> {
    Url findUrlByUrlShort(String urlShort);

    Url findUrlByUrl(String url);

    @Transactional
    @Modifying
    @Query(value = "UPDATE urls SET count_calls = count_calls + 1 WHERE id = ?1",
            nativeQuery = true)
    void incrementUrlCountById(int id);

    @Override
    @Query("FROM Url AS u JOIN FETCH u.user")
    Iterable<Url> findAll();
}
