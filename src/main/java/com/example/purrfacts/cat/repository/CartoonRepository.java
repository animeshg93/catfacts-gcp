package com.example.purrfacts.cat.repository;

import com.example.purrfacts.cat.model.Cartoon;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CartoonRepository {
    private final JdbcTemplate jdbcTemplate;


    public CartoonRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int save(Cartoon cartoon) {
        String sql = "INSERT INTO cartoon (name, year) VALUES (?, ?)";
        return jdbcTemplate.update(sql, cartoon.getName(), cartoon.getYear());
    }
}
