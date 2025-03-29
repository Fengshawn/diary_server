package com.example.user.service;

import com.example.user.model.User;
import com.example.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User register(User user) {
        // 检查电话是否已注册
//        if (userRepository.findByPhone(user.getPhone()).isPresent()) {
//            throw new RuntimeException("Phone already registered");
//        }
        // 检查邮箱是否已注册
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("Email already registered");
        }
        // 检查 unionId 是否已存在
        if (userRepository.findByUnionId(user.getUnionId()).isPresent()) {
            throw new RuntimeException("Union ID already exists");
        }
        return userRepository.save(user);
    }

    public User login(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent() && userOptional.get().getPassword().equals(password)) {
            return userOptional.get();
        }
        throw new RuntimeException("Invalid email or password");
    }
}