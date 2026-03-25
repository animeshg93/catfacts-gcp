package com.example.purrfacts.cat.model;

public class Task {
  private String task;
  private int taskId;

  // Constructors
  public Task() {}

  public Task(String task, int taskId) {
    this.task = task;
    this.taskId = taskId;
  }

  public String getTask() {
    return task;
  }

  public void setTask(String task) {
    this.task = task;
  }

  public int getTaskId() {
    return taskId;
  }

  public void setTaskId(int taskId) {
    this.taskId = taskId;
  }

}
