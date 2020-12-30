package ru.job4j.urlshortcut.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.urlshortcut.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {
    User findUserBySite(String site);
}
