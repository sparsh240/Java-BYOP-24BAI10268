import java.util.Scanner;
import model.User;
import service.AuthService;
import service.TaskService;
import service.StorageService;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static AuthService authService;
    private static TaskService taskService;
    private static User currentUser;

    public static void main(String[] args) {
        StorageService storageService = new StorageService();
        authService = new AuthService(storageService);
        taskService = new TaskService(storageService);

        storageService.loadUsers();
        storageService.loadTasks();

        while (true) {
            System.out.println("\n=== Terminal Task Manager ===");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    register();
                    break;
                case "2":
                    login();
                    break;
                case "3":
                    System.out.println("Goodbye!");
                    storageService.saveAll();
                    System.exit(0);
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private static void register() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine().trim();
        System.out.print("Enter password: ");
        String password = scanner.nextLine().trim();

        try {
            authService.register(username, password);
            System.out.println("User registered successfully!");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void login() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine().trim();
        System.out.print("Enter password: ");
        String password = scanner.nextLine().trim();

        currentUser = authService.login(username, password);
        if (currentUser != null) {
            System.out.println("Login successful!");
            taskMenu();
        } else {
            System.out.println("Invalid credentials.");
        }
    }

    private static void taskMenu() {
        while (true) {
            System.out.println("\n--- Task Menu ---");
            System.out.println("1. Add Task");
            System.out.println("2. View Tasks");
            System.out.println("3. Mark Task Done");
            System.out.println("4. Share Task");
            System.out.println("5. Logout");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    addTask();
                    break;
                case "2":
                    viewTasks();
                    break;
                case "3":
                    markTaskDone();
                    break;
                case "4":
                    shareTask();
                    break;
                case "5":
                    currentUser = null;
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private static void addTask() {
        System.out.print("Enter task title: ");
        String title = scanner.nextLine().trim();
        System.out.print("Enter usernames to share with (comma-separated, or leave empty): ");
        String line = scanner.nextLine().trim();
        String[] sharedUsers = line.isEmpty() ? new String[0] : line.split(",");

        taskService.addTask(title, currentUser.getUsername(), sharedUsers);
        System.out.println("Task added!");
    }

    private static void viewTasks() {
        var tasks = taskService.getTasksForUser(currentUser.getUsername());
        if (tasks.isEmpty()) {
            System.out.println("No tasks found.");
            return;
        }
        System.out.println("Your tasks:");
        for (var task : tasks) {
            System.out.printf("ID: %d | Title: %s | Owner: %s | Shared With: %s\n",
                    task.getId(), task.getTitle(), task.getOwner(), String.join(",", task.getSharedWith()));
        }
    }

    private static void markTaskDone() {
        System.out.print("Enter task ID to mark done: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            boolean removed = taskService.markTaskComplete(id, currentUser.getUsername());
            if (removed) {
                System.out.println("Task marked as done and removed from list.");
            } else {
                System.out.println("Task not found or permission denied.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID.");
        }
    }

    private static void shareTask() {
        System.out.print("Enter task ID to share: ");
        int taskId = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter username to share with: ");
        String otherUser = scanner.nextLine().trim();
        boolean shared = taskService.shareTask(taskId, currentUser.getUsername(), otherUser);
        System.out.println(shared ? "Task shared successfully!" : "Failed to share task.");
    }
}