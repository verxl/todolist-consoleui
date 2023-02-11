package persistence;

import model.Todo;
import model.TodoList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest {

    @Test
    void testWriterInvalidFile() {
        try {
            TodoList todos = new TodoList();
            JsonWriter writer = new JsonWriter("./data/\0illegal,file.json");
            writer.open();
            Assertions.fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyTodoList() {
        try {
            TodoList todos = new TodoList();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyTodoList.json");
            writer.open();
            writer.write(todos);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyTodoList.json");
            TodoList readTodoFile = reader.read();
            assertTrue(readTodoFile.getTodos().isEmpty());
        } catch (IOException e) {
            fail("Exception is not expected.");
        }
    }

    @Test
    void testWriterTodoListWithEntries() {

        Todo todo1 = new Todo("Coding Test", "SleekFlow Coding Test", 20230211, 15);
        Todo todo2 = new Todo("Math Midterm", "Math 200 Midterm 1", 20230210, 80);
        Todo todo3 = new Todo("Reading", "Finish the reading for EOSC 114", 20230212, 0);
        Todo todo4 = new Todo("Examlet 5", "CPSC 221 Examlet 5", 20230214, 40);

        try {
            TodoList todos = new TodoList();
            todos.addTodo("Coding Test", "SleekFlow Coding Test", 20230211, 15);
            todos.addTodo("Math Midterm", "Math 200 Midterm 1", 20230210, 80);
            todos.addTodo("Reading", "Finish the reading for EOSC 114", 20230212, 0);
            todos.addTodo("Examlet 5", "CPSC 221 Examlet 5", 20230214, 40);
            JsonWriter writer = new JsonWriter("./data/testWriterTodoListWithEntries.json");
            writer.open();
            writer.write(todos);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterTodoListWithEntries.json");
            TodoList readTodoFile = reader.read();
            assertTrue(readTodoFile.getTodos().contains(todo1));
            assertTrue(readTodoFile.getTodos().contains(todo2));
            assertTrue(readTodoFile.getTodos().contains(todo3));
            assertTrue(readTodoFile.getTodos().contains(todo4));

        } catch (IOException e) {
            fail("Exception is not expected.");
        }
    }
}
