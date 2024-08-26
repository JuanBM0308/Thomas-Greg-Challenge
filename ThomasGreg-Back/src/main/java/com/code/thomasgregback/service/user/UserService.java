package com.code.thomasgregback.service.user;

import com.code.thomasgregback.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAll();

    boolean existsByMailAndPassword(String mail, String password);

    Optional<User> findByMailAndPassword(String mail, String password);

    boolean existsByMail(String mail);

    User save(User user);

    boolean existsById(Long id);

    Optional<User> findById(Long id);

    void deleteById(Long id);
}
