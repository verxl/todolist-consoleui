package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TodoTest {
    private Todo testTodoItem;

    @BeforeEach
    void runBefore() {
        testTodoItem = new Todo("Coding Test", "SleekFlow Coding Test", 20230211, 15);
    }

    @Test
    void testConstructor() {
        assertEquals("Coding Test", testTodoItem.getName());
        assertEquals("SleekFlow Coding Test", testTodoItem.getDescription());
        assertEquals(20230211, testTodoItem.getDueDate());
        assertEquals(15, testTodoItem.getStatus());
    }

}