package com.example.purrfacts.cat.repository;

import com.example.purrfacts.cat.model.Cartoon;
import com.example.purrfacts.cat.model.Task;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TaskRepository {
  private final JdbcTemplate jdbcTemplate;

  private final RowMapper<Task> taskRowMapper =
      (rs, rowNum) -> {
        Task task = new Task();
        task.setTask(rs.getString("task"));
         task.setTaskId(rs.getInt("taskId"));
        return task;
      };

  public TaskRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public int save(Task task) {
    String sql = "INSERT INTO tasks (task, taskId) VALUES (?, ?)";
    return jdbcTemplate.update(
        sql, task.getTask(), task.getTaskId());
  }

  public List<Task> getAll() {
    String sql = "SELECT * FROM tasks";
    return jdbcTemplate.query(sql, taskRowMapper);
  }
}
