package model;

import java.util.Date;
import java.util.Objects;

/**
 * Represents a to-do item with a name, description, a due date represented by an 8-digit integer,
 *                              and the status represented by the scale [0-100]
 */
public class Todo {
    private String name;
    private String description;
    private int dueDate;
    private int status;

    // CONSTRUCTOR
    // REQUIRES: status = [0, 100]
    // MODIFIES: this
    // EFFECTS: constructs a To-do item
    public Todo(String name, String description, int dueDate, int status) {
        this.name = name;
        this.description = description;
        this.dueDate = dueDate;
        this.status = status;
    }

    // getters
    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public int getDueDate() {
        return this.dueDate;
    }

    public int getStatus() {
        return this.status;
    }

    // setters
    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDueDate(int dueDate) {
        this.dueDate = dueDate;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Todo)) return false;
        Todo todo = (Todo) o;
        return dueDate == todo.dueDate && status == todo.status && name.equals(todo.name) && description.equals(todo.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, dueDate, status);
    }
}
