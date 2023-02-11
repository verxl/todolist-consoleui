package ui;

import model.TodoList;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class TodoListApp {
    private static final String JSON_STORE = "./data/account.json";
    private TodoList todos;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: runs the tracker
    public TodoListApp() throws FileNotFoundException {
        runTodoListApp();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runTodoListApp() throws FileNotFoundException {
        boolean keepGoing = true;
        String command = null;

        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("end")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nSee You Next Time!");
    }

    // MODIFIES: this
    // EFFECTS: initialize a to-do list for the user
    private void init() {
        todos = new TodoList();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("add -> add a todo");
        System.out.println("remove -> remove a todo");
        System.out.println("sort -> sort the todo list by due date");
        System.out.println("filter -> filter the todo list by due date");
        System.out.println("save -> save account to file");
        System.out.println("load -> load account from file");
        System.out.println("end -> quit the application\n");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("add")) {
            doAddTodo();
        } else if (command.equals("remove")) {
            doRemoveTodo();
        } else if (command.equals("sort")) {
            doSortTodo();
        } else if (command.equals("filter")) {
            doFilterTodo();
        } else if (command.equals("save")) {
            saveTodoList();
        } else if (command.equals("load")) {
            loadTodoList();
        } else {
            System.out.println("Selection not valid... \nPlease select again.");
        }
    }

    // MODIFIES: this
    // EFFECTS: adds a to-do
    private void doAddTodo() {
        System.out.println("Enter the name of the todo:");
        String name = input.next();

        System.out.println("Enter the description of this todo:");
        String description = input.next();

        System.out.println("Enter the due date (in YYYYMMDD) of this todo:");
        int dueDate = input.nextInt();

        System.out.println("Enter the status of this todo:");
        int status = input.nextInt();

        todos.addTodo(name, description, dueDate, status);
        System.out.println("The todo has been added to the list. \n");

        printTodoList();
    }

    // MODIFIES: this
    // EFFECTS: remove a to-do
    private void doRemoveTodo() {
        System.out.println("Enter the name of the todo to be deleted:");
        String name = input.next();

        System.out.println("Enter the description of the todo to be deleted:");
        String description = input.next();

        System.out.println("Enter the due date (in YYYYMMDD) of the todo to be deleted:");
        int dueDate = input.nextInt();

        System.out.println("Enter the status of the todo to be deleted:");
        int status = input.nextInt();

//        try {
//            todos.removeTodo(name, description, dueDate, status);
//            System.out.println("The todo has been removed to the list. \n");
//        } catch (InvalidEntryException) {
//            System.out.println("The input is invalid...");
//        }

        todos.removeTodo(name, description, dueDate, status);
        System.out.println("The todo has been removed to the list. \n");

        printTodoList();
    }

    // MODIFIES: this
    // EFFECTS: sorts the to-do list
    private void doSortTodo() {
        todos.sort(todos.getTodos());
        printTodoList();
    }

    // MODIFIES: this
    // EFFECTS: filters the to-do list by due date
    private void doFilterTodo() {
        System.out.println("Enter the due date (in YYYYMMDD) for the todo items you are looking for:");
        int dueDate = input.nextInt();

        System.out.println("Todo List:\n" + todos.print(todos.filter(dueDate)) + "\n");

    }


    // EFFECTS: prints all the todos in the to-do list
    private void printTodoList() {
        System.out.println("Todo List:\n" + todos.print(todos.getTodos()) + "\n");
    }

    // EFFECTS: saves TodoList to file
    private void saveTodoList() {
        try {
            jsonWriter.open();
            jsonWriter.write(todos);
            jsonWriter.close();
            System.out.println("Saved Todo List to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads TodoList from file
    private void loadTodoList() {
        try {
            todos = jsonReader.read();
            System.out.println("Loaded Todo List from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}
