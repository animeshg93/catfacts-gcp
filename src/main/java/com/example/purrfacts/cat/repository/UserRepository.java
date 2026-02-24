package com.example.purrfacts.cat.repository;

import com.example.purrfacts.cat.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    // Custom query methods
    Optional<User> findByName(String name);
    
    List<User> findByAge(Integer age);
    
    List<User> findByAgeGreaterThan(Integer age);
    
    List<User> findByAgeLessThan(Integer age);
}
