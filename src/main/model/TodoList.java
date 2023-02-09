package model;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Represents a list of To-do
 */
public class TodoList {
    private List<Todo> todos;

    // CONSTRUCTOR
    // MODIFIES: this
    // EFFECTS: constructs an empty list of To-do
    public TodoList() {
        this.todos = new ArrayList<>();
    }

    // GETTERS
    public List<Todo> getTodos() {
        return this.todos;
    }

    // ADD TO-DO
    // REQUIRES: a to-do item with valid fields
    // MODIFIES: this
    // EFFECTS: adds a to-do item to todos
    public void addTodo(String name, String description, int dueDate, int status) {
        Todo item = new Todo(name, description, dueDate, status);
        this.todos.add(item);
    }

    // REMOVE TO-DO
    // REQUIRES: a to-do item that is already in todos
    // MODIFIES: this
    // EFFECTS: removes a to-do item in todos
    public void removeTodo(String name, String description, int dueDate, int status) {
        Todo item = new Todo(name, description, dueDate, status);
        for (Todo currentTodo : todos) {
            if (currentTodo.equals(item)) {
                this.todos.remove(currentTodo);
            }
        }
    }
    // Todo: returns boolean so that error message can be thrown in other methods

    // SORT (ACCORDING TO DUE DATE)
    // MODIFIES: this
    // EFFECTS: sorts a todolist in chronological order according to the dueDate
    //          if there are two to-do items having the same due date,
    //          the item that input first will be put at the front
    public void sort(List<Todo> todoToSort) {
        int i = 1;
        while (i < this.todos.size()) {
            Todo front = this.todos.get(i - 1);
            Todo back = this.todos.get(i);

            if (!(isLaterOrSameDate(front.getDueDate(), back.getDueDate()))) {
                this.todos.remove(back);
                this.todos.add(i - 1, back);
                sort(this.todos.subList(0, i - 1));
            }
            i++;
        }
    }

    // EFFECTS: returns true if date1 >= date2
    public boolean isLaterOrSameDate(int date1, int date2) {
        return (date2 >= date1);
    }

    // FILTER (BASED ON DUE DATE)
    // REQUIRES: a valid date
    // EFFECTS:
    public List<Todo> filter(int date) {
        List<Todo> result = new ArrayList<>();
        for (Todo todo: this.todos) {
            if (todo.getDueDate() == date) {
                result.add(todo);
            }
        }
        return result;
    }
}
