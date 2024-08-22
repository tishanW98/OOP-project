package com.mycompany.littlebagshop;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BagFileManager {
    private static final String FILE_NAME = "bags.txt";

    public static void saveBags(List<Bag> bags) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(bags);
        }
    }

    public static List<Bag> loadBags() throws IOException, ClassNotFoundException {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return new ArrayList<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (List<Bag>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            // If there's an issue with reading the file, like a corrupted file, log the error and start with an empty list.
            System.err.println("Error loading bags from file, starting with an empty list. " + e.getMessage());
            // Optionally, delete the corrupted file to avoid repeated errors
            if (file.exists()) {
                file.delete();
            }
            return new ArrayList<>();
        }
    }
}
