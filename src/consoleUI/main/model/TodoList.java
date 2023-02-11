package model;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONString;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of To-do with the associated owner of the to-do list
 */
public class TodoList {
    private List<Todo> todos;

    // CONSTRUCTOR
    // MODIFIES: this
    // EFFECTS: constructs an empty list of To-do with the name of the owner
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
    // TODO: throws an InvalidEntryException

    // SORT (ACCORDING TO DUE DATE)
    // MODIFIES: this
    // EFFECTS: sorts a todolist in chronological order according to the dueDate
    //          if there are two to-do items having the same due date,
    //          the item that is inputted first will be put at the front
    public void sort(List<Todo> todoToSort) {
        int i = 1;
        while (i < todoToSort.size()) {
            Todo front = todoToSort.get(i - 1);
            Todo back = todoToSort.get(i);

            if (!(isLaterOrSameDate(front.getDueDate(), back.getDueDate()))) {
                todoToSort.remove(back);
                todoToSort.add(i - 1, back);
                sort(todoToSort.subList(0, i));
            }
            i++;
        }
    }

    // EFFECTS: returns true if date2 is a later date or the same date as date1
    public boolean isLaterOrSameDate(int date1, int date2) {
        return (date2 >= date1);
    }

    // FILTER (BASED ON DUE DATE)
    // REQUIRES: a valid date
    // EFFECTS: filters the list according to the due date inputted
    public List<Todo> filter(int date) {
        List<Todo> result = new ArrayList<>();
        for (Todo todo: this.todos) {
            if (todo.getDueDate() == date) {
                result.add(todo);
            }
        }
        return result;
    }

    // PRINT
    // EFFECTS: prints all the to-dos in the list
    public List<String> print(List<Todo> todos) {
        List<String> result = new ArrayList<>();
        String curr = null;
        for (Todo todo: todos) {
            curr = "Name: " + todo.getName() + "\n"
                    + "Description: " + todo.getDescription() + "\n"
                    + "Due date: " + todo.getDueDate() + "\n"
                    + "Status: " + todo.getStatus() + "\n";
            result.add(curr);
        }
        return result;
    }

    // Create a new JSONObject for a To-do List
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Todo List", todosToJson());
        return json;
    }

    // EFFECTS: returns all the to-dos in this to-do list as a JSON array
    private JSONArray todosToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Todo todo: todos) {
            jsonArray.put(todo.toJson());
        }

        return jsonArray;
    }
}
