package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class TodoListTest {
    private TodoList testTodos;

    @BeforeEach
    void runBefore() {
        testTodos = new TodoList();
    }

    @Test
    void testConstructor() {
        assertTrue(testTodos.getTodos().isEmpty());
    }

    @Test
    void testAddTodo() {
        testTodos.addTodo("Coding Test", "SleekFlow Coding Test", 20230211, 15);
        Todo added = testTodos.getTodos().get(0);
        assertTrue(testTodos.getTodos().contains(added));
        assertEquals("Coding Test", added.getName());
        assertEquals("SleekFlow Coding Test", added.getDescription());
        assertEquals(20230211, added.getDueDate());
        assertEquals(15, added.getStatus());
    }

    @Test
    void testRemoveTodo() {
        // adding todo1, todo2, todo3 and todo4 to the testTodos
        Todo todo1 = new Todo("Coding Test", "SleekFlow Coding Test", 20230211, 15);
        Todo todo2 = new Todo("Math Midterm", "Math 200 Midterm 1", 20230210, 80);
        Todo todo3 = new Todo("Reading", "Finish the reading for EOSC 114", 20230212, 0);
        Todo todo4 = new Todo("Examlet 5", "CPSC 221 Examlet 5", 20230214, 40);

        testTodos.addTodo("Coding Test", "SleekFlow Coding Test", 20230211, 15);
        testTodos.addTodo("Math Midterm", "Math 200 Midterm 1", 20230210, 80);
        testTodos.addTodo("Reading", "Finish the reading for EOSC 114", 20230212, 0);
        testTodos.addTodo("Examlet 5", "CPSC 221 Examlet 5", 20230214, 40);

        // all the to-do items are in testTodos
        assertTrue(testTodos.getTodos().contains(todo1));
        assertTrue(testTodos.getTodos().contains(todo2));
        assertTrue(testTodos.getTodos().contains(todo3));
        assertTrue(testTodos.getTodos().contains(todo4));

        // remove todo3
        testTodos.removeTodo("Reading", "Finish the reading for EOSC 114", 20230212, 0);

        // todo1, todo2, todo4 are contained in testTodos; todo3 is not
        assertTrue(testTodos.getTodos().contains(todo1));
        assertTrue(testTodos.getTodos().contains(todo2));
        assertFalse(testTodos.getTodos().contains(todo3));
        assertTrue(testTodos.getTodos().contains(todo4));

        // remove todo2
        testTodos.removeTodo("Math Midterm", "Math 200 Midterm 1", 20230210, 80);

        // todo1 and todo4 are contained in testTodos; todo2 and todo3 are not
        assertTrue(testTodos.getTodos().contains(todo1));
        assertFalse(testTodos.getTodos().contains(todo2));
        assertFalse(testTodos.getTodos().contains(todo3));
        assertTrue(testTodos.getTodos().contains(todo4));
    }

    @Test
    void testSort() {
        Todo todo1 = new Todo("Coding Test", "SleekFlow Coding Test", 20230211, 15);
        Todo todo2 = new Todo("Math Midterm", "Math 200 Midterm 1", 20230210, 80);
        Todo todo3 = new Todo("Office hour", "CPSC 213 Office hour", 20230301, 0);
        Todo todo4 = new Todo("Examlet 5", "CPSC 221 Examlet 5", 20230218, 40);
        Todo todo5 = new Todo("Tutorial", "English tutorial", 20230211, 50);
        Todo todo6 = new Todo("Tutorial", "Math tutorial", 20230210, 40);

        // sorting an empty list (to make sure the method works with empty list)
        testTodos.sort(testTodos.getTodos());
        assertTrue(testTodos.getTodos().isEmpty());

        // adding one to-do item and sorting a todolist with one item only
        testTodos.addTodo("Coding Test", "SleekFlow Coding Test", 20230211, 15);
        testTodos.sort(testTodos.getTodos());
        assertEquals(todo1, testTodos.getTodos().get(0));

        // adding three to-do items with different due dates to testTodos
        testTodos.addTodo("Math Midterm", "Math 200 Midterm 1", 20230210, 80); // earliest
        testTodos.addTodo("Office hour", "CPSC 213 Office hour", 20230301, 0); // latest
        testTodos.addTodo("Examlet 5", "CPSC 221 Examlet 5", 20230218, 40); // second latest

        // the to-do list is now unsorted (not sorted according to due dates)
        assertEquals(todo1, testTodos.getTodos().get(0));
        assertEquals(todo2, testTodos.getTodos().get(1));
        assertEquals(todo3, testTodos.getTodos().get(2));
        assertEquals(todo4, testTodos.getTodos().get(3));

        // sort the to-do list according to duedate
        testTodos.sort(testTodos.getTodos());

        assertEquals(todo2, testTodos.getTodos().get(0));
        assertEquals(todo1, testTodos.getTodos().get(1));
        assertEquals(todo4, testTodos.getTodos().get(2));
        assertEquals(todo3, testTodos.getTodos().get(3));


        // adding an additional to-do item with the same due date to one of the to-do
        testTodos.addTodo("Tutorial", "English tutorial", 20230211, 50); // second earliest == "Coding Test"

        // sort the to-do list according to duedate
        testTodos.sort(testTodos.getTodos());

        assertEquals(todo2, testTodos.getTodos().get(0));
        assertEquals(todo1, testTodos.getTodos().get(1));
        assertEquals(todo5, testTodos.getTodos().get(2)); // appears after todo1 as todo5 is added later
        assertEquals(todo4, testTodos.getTodos().get(3));
        assertEquals(todo3, testTodos.getTodos().get(4));

        // adding an additional to-do item with the same due dates as one of the to-do
        testTodos.addTodo("Tutorial", "Math tutorial", 20230210, 40);

        // sort the to-do list according to duedate
        testTodos.sort(testTodos.getTodos());
        assertEquals(todo2, testTodos.getTodos().get(0));
        assertEquals(todo6, testTodos.getTodos().get(1)); // appears after todo1 as todo5 is added later
        assertEquals(todo1, testTodos.getTodos().get(2));
        assertEquals(todo5, testTodos.getTodos().get(3));
        assertEquals(todo4, testTodos.getTodos().get(4));
        assertEquals(todo3, testTodos.getTodos().get(5));
    }

    @Test
    void testFilter() {
        Todo todo1 = new Todo("Coding Test", "SleekFlow Coding Test", 20230211, 15);
        Todo todo2 = new Todo("Math Midterm", "Math 200 Midterm 1", 20230210, 80);
        Todo todo3 = new Todo("Office hour", "CPSC 213 Office hour", 20230301, 0);
        Todo todo4 = new Todo("Examlet 5", "CPSC 221 Examlet 5", 20230218, 40);
        Todo todo5 = new Todo("Tutorial", "English tutorial", 20230211, 50);

        // adds todo1 - todo5 to testTodos
        testTodos.addTodo("Coding Test", "SleekFlow Coding Test", 20230211, 15);
        testTodos.addTodo("Math Midterm", "Math 200 Midterm 1", 20230210, 80);
        testTodos.addTodo("Office hour", "CPSC 213 Office hour", 20230301, 0);
        testTodos.addTodo("Examlet 5", "CPSC 221 Examlet 5", 20230218, 40);
        testTodos.addTodo("Tutorial", "English tutorial", 20230211, 50);

        // test a date which has no matching entries in the todolist
        assertTrue(testTodos.filter(20241102).isEmpty());

        // test a date which has one matching entry in the todolist
        List<Todo> filtered1 = testTodos.filter(20230210);
        assertFalse(filtered1.contains(todo1));
        assertTrue(filtered1.contains(todo2)); // filtered1 only contains todo2 which has the duedate 20230210 as inputted
        assertFalse(filtered1.contains(todo3));
        assertFalse(filtered1.contains(todo4));
        assertFalse(filtered1.contains(todo5));

        // test a date which has two matching entries in the todolist
        List<Todo> filtered2 = testTodos.filter(20230211);
        assertTrue(filtered2.contains(todo1)); // filtered2 contains todo1 which has the same duedate as inputted
        assertFalse(filtered2.contains(todo2));
        assertFalse(filtered2.contains(todo3));
        assertFalse(filtered2.contains(todo4));
        assertTrue(filtered2.contains(todo5)); // filtered2 contains todo5 which has the same duedate as inputted
    }

    @Test
    void testPrint() {
        // printing an empty to-do list
        assertEquals(new ArrayList<String>(), testTodos.print(testTodos.getTodos()));

        // printing a to-do list with one element

        Todo todo1 = new Todo("Coding Test", "SleekFlow Coding Test", 20230211, 15);
        Todo todo2 = new Todo("Math Midterm", "Math 200 Midterm 1", 20230210, 80);
        testTodos.addTodo("Coding Test", "SleekFlow Coding Test", 20230211, 15);
        List<String> result = new ArrayList<>();
        String str1 = "Name: " + todo1.getName() + "\n"
                + "Description: " + todo1.getDescription() + "\n"
                + "Due date: " + todo1.getDueDate() + "\n"
                + "Status: " + todo1.getStatus() + "\n";
        result.add(str1);
        assertEquals(result, testTodos.print(testTodos.getTodos()));

        testTodos.addTodo("Math Midterm", "Math 200 Midterm 1", 20230210, 80);
        String str2 = "Name: " + todo2.getName() + "\n"
                + "Description: " + todo2.getDescription() + "\n"
                + "Due date: " + todo2.getDueDate() + "\n"
                + "Status: " + todo2.getStatus() + "\n";
        result.add(str2);
        assertEquals(result, testTodos.print(testTodos.getTodos()));

    }

}
