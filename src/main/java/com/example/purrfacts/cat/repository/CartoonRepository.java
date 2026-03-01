package com.example.purrfacts.cat.repository;

import com.example.purrfacts.cat.model.Cartoon;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class CartoonRepository {
    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<Cartoon> cartoonRowMapper = (rs, rowNum) -> {
        Cartoon cartoon = new Cartoon();
        cartoon.setAbbreviation(rs.getString("abbreviation"));
        cartoon.setName(rs.getString("name"));
        cartoon.setYear(rs.getInt("year"));
        return cartoon;
    };

    public CartoonRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int save(Cartoon cartoon) {
        String sql = "INSERT INTO cartoons (name, year, abbreviation) VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql, cartoon.getName(), cartoon.getYear(), cartoon.getAbbreviation());
    }

    public Cartoon getByAbb(String abbreviation) {
        String sql = "SELECT * FROM cartoons WHERE abbreviation = ?";
        return jdbcTemplate.query(sql, cartoonRowMapper, abbreviation)
                .stream()
                .findFirst()
                .orElse(null);
    }
}
