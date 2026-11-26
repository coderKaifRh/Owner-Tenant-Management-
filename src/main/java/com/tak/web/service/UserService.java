package com.tak.web.service;

import com.tak.web.model.User;
import com.tak.web.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> authenticate(String username, String password, String role) {
        return userRepository.findByUsernameAndPasswordAndRole(username, password, role);
    }

    public User register(String username, String password, String role, String phone) throws Exception {
        if (phone == null || !phone.startsWith("+8801") || phone.length() != 14) {
            throw new IllegalArgumentException("Invalid Phone! Must be in format +8801XXXXXXXXX (14 characters)");
        }
        if (userRepository.findByUsername(username).isPresent()) {
            throw new IllegalArgumentException("Username already exists!");
        }

        User user = new User(username, password, role);
        return userRepository.save(user);
    }

    public Optional<User> findById(Integer id) {
        return userRepository.findById(id);
    }
}
