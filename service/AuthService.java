package service;

import model.User;
import util.PasswordUtil;
import util.SaltUtil;

import java.util.List;

public class AuthService {
    private StorageService storage;

    public AuthService(StorageService storage) {
        this.storage = storage;
    }

    public void register(String username, String password) {
        if (findUser(username) != null) {
            throw new IllegalArgumentException("Username already exists.");
        }
        String salt = SaltUtil.generateSalt();
        String hash = PasswordUtil.hashPassword(password, salt);
        User user = new User(username, salt, hash);
        storage.addUser(user);
        storage.saveUsers();
    }

    public User login(String username, String password) {
        User user = findUser(username);
        if (user == null) return null;
        String hash = PasswordUtil.hashPassword(password, user.getSalt());
        return hash.equals(user.getPasswordHash()) ? user : null;
    }

    private User findUser(String username) {
        List<User> users = storage.getUsers();
        for (User u : users) {
            if (u.getUsername().equals(username)) return u;
        }
        return null;
    }
}