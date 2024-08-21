package com.mycompany.littlebagshop;

import java.io.*;
import java.util.*;

public class LittleBagShop {

    private static List<Bag> bags = new ArrayList<>();
    private static List<Category> categories = new ArrayList<>();
    private static List<User> users = new ArrayList<>();

    public static void main(String[] args) {
        try {
            // Create a manager with a predefined account
            Manager manager = new Manager("admin", "admin123");
            users.add(manager);

            // Load data if available
            loadDataFromFile("shopData.ser");

            // Start the UI
            LittleBagShopUI ui = new LittleBagShopUI();
            ui.setVisible(true);

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void addUser(User user) {
        users.add(user);
    }

    public static void addBag(Bag bag) {
        bags.add(bag);
    }

    public static void addCategory(Category category) {
        categories.add(category);
    }

    public static List<Bag> getBags() {
        return bags;
    }

    public static List<Category> getCategories() {
        return categories;
    }

    public static Category findCategory(String categoryName) {
        for (Category category : categories) {
            if (category.getName().equalsIgnoreCase(categoryName)) {
                return category;
            }
        }
        return null;
    }

    public static User authenticate(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    private static void loadDataFromFile(String fileName) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            bags = (List<Bag>) ois.readObject();
            categories = (List<Category>) ois.readObject();
        }
    }
}
