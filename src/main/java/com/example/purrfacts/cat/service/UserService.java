package com.example.purrfacts.cat.service;

import com.example.purrfacts.cat.model.User;
import com.example.purrfacts.cat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Create a new user
    public User createUser(User user) {
        return userRepository.save(user);
    }

    // Create a new user with name and age
    public User createUser(String name, Integer age) {
        User user = new User(name, age);
        return userRepository.save(user);
    }

    // Get all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Get user by ID
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    // Get user by name
    public Optional<User> getUserByName(String name) {
        return userRepository.findByName(name);
    }

    // Get users by age
    public List<User> getUsersByAge(Integer age) {
        return userRepository.findByAge(age);
    }

    // Get users older than specified age
    public List<User> getUsersOlderThan(Integer age) {
        return userRepository.findByAgeGreaterThan(age);
    }

    // Get users younger than specified age
    public List<User> getUsersYoungerThan(Integer age) {
        return userRepository.findByAgeLessThan(age);
    }

    // Update user
    public User updateUser(Long id, User userDetails) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        
        user.setName(userDetails.getName());
        user.setAge(userDetails.getAge());
        
        return userRepository.save(user);
    }

    // Delete user by ID
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }

    // Check if user exists
    public boolean userExists(Long id) {
        return userRepository.existsById(id);
    }

    // Count all users
    public long countUsers() {
        return userRepository.count();
    }
}
