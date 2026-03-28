package service;

import model.Task;
import java.util.ArrayList;
import java.util.List;

public class TaskService {
    private StorageService storage;

    public TaskService(StorageService storage) {
        this.storage = storage;
    }

    public void addTask(String title, String owner, String[] sharedUsers) {
        Task task = new Task(title, owner, sharedUsers);
        storage.addTask(task);
        storage.saveTasks();
    }

    public List<Task> getTasksForUser(String username) {
        List<Task> tasks = storage.getTasks();
        List<Task> userTasks = new ArrayList<>();
        for (Task t : tasks) {
            if (t.getOwner().equals(username) || t.getSharedWith().contains(username)) {
                userTasks.add(t);
            }
        }
        return userTasks;
    }

    public boolean markTaskComplete(int id, String username) {
        List<Task> tasks = storage.getTasks();
        for (Task t : tasks) {
            if (t.getId() == id && t.getOwner().equals(username)) {
                t.markComplete();
                tasks.remove(t);
                storage.saveTasks();
                return true;
            }
        }
        return false;
    }

    public boolean shareTask(int id, String owner, String otherUser) {
        List<Task> tasks = storage.getTasks();
        for (Task t : tasks) {
            if (t.getId() == id && t.getOwner().equals(owner)) {
                t.shareWith(otherUser);
                storage.saveTasks();
                return true;
            }
        }
        return false;
    }
}