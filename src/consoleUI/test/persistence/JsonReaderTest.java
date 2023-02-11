package persistence;

import model.Todo;
import model.TodoList;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            TodoList testTodos = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyAccountOverview() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyTodoList.json");
        try {
            TodoList testTodos = reader.read();
            assertTrue(testTodos.getTodos().isEmpty());
        } catch (IOException e) {
            fail("Exception is not expected.");
        }
    }

    @Test
    void testReaderGeneralAccountOverview() {
        Todo todo1 = new Todo("Coding Test", "SleekFlow Coding Test", 20230211, 15);
        Todo todo2 = new Todo("Math Midterm", "Math 200 Midterm 1", 20230210, 80);
        Todo todo3 = new Todo("Reading", "Finish the reading for EOSC 114", 20230212, 0);
        Todo todo4 = new Todo("Examlet 5", "CPSC 221 Examlet 5", 20230214, 40);

        JsonReader reader = new JsonReader("./data/testReaderTodoListWithEntries.json");
        try {
            TodoList todos = reader.read();

            assertTrue(todos.getTodos().contains(todo1));
            assertTrue(todos.getTodos().contains(todo2));
            assertTrue(todos.getTodos().contains(todo3));
            assertTrue(todos.getTodos().contains(todo4));

        } catch (IOException e) {
            fail("Exception is not expected.");
        }
    }
}
