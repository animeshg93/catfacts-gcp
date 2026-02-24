package com.example.purrfacts.cat.controller;

import com.example.purrfacts.cat.model.User;
import com.example.purrfacts.cat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * POST endpoint to create a new User
     *
     * @param user User object containing name and age
     * @return ResponseEntity with created User and HTTP status 201 CREATED
     */
    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User createdUser = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    /**
     * GET endpoint to retrieve all users
     *
     * @return ResponseEntity with list of all users and HTTP status 200 OK
     */
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    /**
     * GET endpoint to retrieve a single user by ID
     *
     * @param id ID of the user to retrieve
     * @return ResponseEntity with the user and HTTP status 200 OK,
     *         or 404 NOT FOUND if the user does not exist
     */
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    /**
     * PUT endpoint to update an existing user by ID
     *
     * @param id          ID of the user to update
     * @param userDetails User object containing updated details
     * @return ResponseEntity with updated User and HTTP status 200 OK,
     *         or 404 NOT FOUND if the user does not exist
     */
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @Valid @RequestBody User userDetails) {
        try {
            User updatedUser = userService.updateUser(id, userDetails);
            return ResponseEntity.ok(updatedUser);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /**
     * DELETE endpoint to delete a user by ID
     *
     * @param id ID of the user to delete
     * @return ResponseEntity with HTTP status 204 NO CONTENT,
     *         or 404 NOT FOUND if the user does not exist
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
