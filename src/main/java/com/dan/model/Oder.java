package com.dan.model;

import java.sql.Date;

public class Oder {
    private int id;
    private User user;
    private int total; 

    public Oder() {
    }

    public Oder(int id, User user, int total) {
        this.id = id;
        this.user = user;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
    
    
}
