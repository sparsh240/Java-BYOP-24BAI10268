package service;

import model.User;
import model.Task;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StorageService {
    private static final String USER_FILE = "data/users.dat";
    private static final String TASK_FILE = "data/tasks.dat";

    private List<User> users = new ArrayList<>();
    private List<Task> tasks = new ArrayList<>();

    public void loadUsers() {
        users = load(USER_FILE);
    }

    public void loadTasks() {
        tasks = load(TASK_FILE);
    }

    public void saveUsers() {
        save(USER_FILE, users);
    }

    public void saveTasks() {
        save(TASK_FILE, tasks);
    }

    public void saveAll() {
        saveUsers();
        saveTasks();
    }

    public List<User> getUsers() { return users; }
    public List<Task> getTasks() { return tasks; }

    public void addUser(User user) { users.add(user); }
    public void addTask(Task task) { tasks.add(task); }

    @SuppressWarnings("unchecked")
    private <T> List<T> load(String file) {
        File f = new File(file);
        if (!f.exists()) return new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
            return (List<T>) ois.readObject();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    private <T> void save(String file, List<T> list) {
        File dir = new File("data");
        if (!dir.exists()) dir.mkdir();
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(list);
        } catch (IOException e) {
            System.out.println("Failed to save: " + file);
        }
    }
}