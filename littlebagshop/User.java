package com.mycompany.littlebagshop;

abstract class User {
    protected String username;
    protected String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public abstract void displayMenu();
    public abstract void viewBags();
    public abstract void addBag();
    public abstract void addCategory();
    public abstract void searchBagsByCategory();
}
