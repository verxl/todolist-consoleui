package persistence;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import model.TodoList;

public class JsonReader {
    private String source;

    // EFFECTS: constructs a reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads a To-do List from file and returns it
    public TodoList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseTodoList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parse a To-do list from JSONObject and returns it
    private TodoList parseTodoList(JSONObject jsonObject) {
        TodoList todos = new TodoList();
        addTodos(todos, jsonObject);
        return todos;
    }

    // MODIFIES: todos
    // EFFECTS: parses to-do entries from JSONObject and adds them to todos
    private void addTodos(TodoList todos, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("Todo List");
        for (Object json : jsonArray) {
            JSONObject nextTodo = (JSONObject) json;
            addTodo(todos, nextTodo);
        }
    }

    // MODIFIES: todos
    // EFFECTS: parses a to-do entry from JSONObject and adds it to todos
    private void addTodo(TodoList todos, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String description = jsonObject.getString("description");
        int dueDate = jsonObject.getInt("due date");
        int status = jsonObject.getInt("status");
        todos.addTodo(name, description, dueDate, status);
    }
}
