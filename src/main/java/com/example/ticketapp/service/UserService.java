package com.example.ticketapp.service;

import com.example.ticketapp.entity.User;
import com.example.ticketapp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String getPasswordByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public Long getIdByEmail(String email) {
        return userRepository.getIdByEmail(email);
    }

    public User getById(Long id) {
        return userRepository.getUserById(id);
    }
}
