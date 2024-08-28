package com.code.thomasgregback.service.user;

import com.code.thomasgregback.entity.User;
import com.code.thomasgregback.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    protected UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAll() {
        return this.userRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    public boolean existsByMailAndPassword(String mail, String password) {
        return this.userRepository.existsByMailAndPassword(mail, password);
    }

    public Optional<User> findByMailAndPassword(String mail, String password) {
        return this.userRepository.findByMailAndPassword(mail, password);
    }

    public boolean existsByMail(String mail) {
        return this.userRepository.existsByMail(mail);
    }

    public User save(User user) {
        return this.userRepository.save(user);
    }

    public boolean existsById(Long id) {
        return this.userRepository.existsById(id);
    }

    public Optional<User> findById(Long id) {
        return this.userRepository.findById(id);
    }

    public void deleteById(Long id) {
        this.userRepository.deleteById(id);
    }
}
