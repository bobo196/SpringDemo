package ru.korshun.springdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.korshun.springdemo.model.User;

@Repository
public interface UserRepository
        extends JpaRepository<User, Integer> {
    User findByLogin(String login);
    User findById(long id);
}
