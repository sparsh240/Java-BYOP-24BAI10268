package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Task implements Serializable {
    private static int counter = 1;

    private int id;
    private String title;
    private String owner;
    private List<String> sharedWith;
    private boolean completed;

    public Task(String title, String owner, String[] sharedUsers) {
        this.id = counter++;
        this.title = title;
        this.owner = owner;
        this.sharedWith = new ArrayList<>();
        for (String user : sharedUsers) {
            sharedWith.add(user.trim());
        }
        this.completed = false;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getOwner() { return owner; }
    public List<String> getSharedWith() { return sharedWith; }
    public boolean isCompleted() { return completed; }

    public void markComplete() { this.completed = true; }
    public void shareWith(String username) { sharedWith.add(username); }
}