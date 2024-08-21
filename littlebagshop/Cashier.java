package com.mycompany.littlebagshop;

import java.util.List;
import java.util.Scanner;

class Cashier extends User {

    public Cashier(String username, String password) {
        super(username, password);
    }

    @Override
    public void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\n--- Cashier Menu ---");
            System.out.println("1. View Bags");
            System.out.println("2. Add Bag");
            System.out.println("3. Add Category");
            System.out.println("4. Search Bags by Category");
            System.out.println("5. Logout");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    viewBags();
                    break;
                case 2:
                    addBag();
                    break;
                case 3:
                    addCategory();
                    break;
                case 4:
                    searchBagsByCategory();
                    break;
                case 5:
                    System.out.println("Logging out...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);
    }

    @Override
    public void viewBags() {
        List<Bag> bags = LittleBagShop.getBags();
        if (bags.isEmpty()) {
            System.out.println("No bags available.");
        } else {
            for (Bag bag : bags) {
                System.out.println(bag);
            }
        }
    }

    @Override
    public void addBag() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter bag name: ");
        String name = scanner.nextLine();
        System.out.print("Enter category name: ");
        String categoryName = scanner.nextLine();

        Category category = LittleBagShop.findCategory(categoryName);
        if (category == null) {
            System.out.println("Category not found. Please add the category first.");
        } else {
            Bag bag = new Bag(name, category);
            LittleBagShop.addBag(bag);
            System.out.println("Bag added successfully.");
        }
    }

    @Override
    public void addCategory() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter category name: ");
        String categoryName = scanner.nextLine();
        Category category = new Category(categoryName);
        LittleBagShop.addCategory(category);
        System.out.println("Category added successfully.");
    }

    @Override
    public void searchBagsByCategory() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter category name: ");
        String categoryName = scanner.nextLine();
        boolean found = false;
        List<Bag> bags = LittleBagShop.getBags();
        for (Bag bag : bags) {
            if (bag.getCategory().getName().equalsIgnoreCase(categoryName)) {
                System.out.println(bag);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No bags found in this category.");
        }
    }
}
