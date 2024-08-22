package com.mycompany.littlebagshop;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryManagement {
    private static final String FILE_NAME = "categories.txt";

    // Method to save categories to a file
    public static void saveCategories(List<String> categories) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(categories);
        }
    }

    // Method to load categories from a file
    @SuppressWarnings("unchecked")
    public static List<String> loadCategories() throws IOException, ClassNotFoundException {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return new ArrayList<>();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (List<String>) ois.readObject();
        }
    }

    // Method to add a new category
    public static void addCategory(String category) throws IOException, ClassNotFoundException {
        List<String> categories = loadCategories();
        if (!categories.contains(category)) {
            categories.add(category);
            saveCategories(categories);
        }
    }
}

