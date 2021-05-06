package com.company.services;

import com.company.models.User;

import java.util.ArrayList;
import java.util.List;

public class UserService {
    private int counter;
    private final List<User> users;

    public UserService() {
        users = new ArrayList<>();
    }

    public int getCounter() {
        return counter;
    }

    public List<User> getUsers() {
        return users;
    }

    public User getUser(int id) {
        return users.get(id);
    }

    public boolean addUser(String name, String email, String phone) {
        // TODO: email unique check
        var user = new User(counter++, name, email, phone);
        users.add(user);
        return true;
    }

}
