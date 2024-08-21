package com.mycompany.littlebagshop;

import java.util.Scanner;

class Manager extends Cashier {

    public Manager(String username, String password) {
        super(username, password);
    }

    public Cashier createCashier(String username, String password) {
        Cashier newCashier = new Cashier(username, password);
        System.out.println("New cashier account created successfully.");
        return newCashier;
    }

    @Override
    public void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\n--- Manager Menu ---");
            System.out.println("1. View Bags");
            System.out.println("2. Add Bag");
            System.out.println("3. Add Category");
            System.out.println("4. Search Bags by Category");
            System.out.println("5. Create Cashier Account");
            System.out.println("6. Logout");
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
                    createCashierAccount();
                    break;
                case 6:
                    System.out.println("Logging out...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 6);
    }

    private void createCashierAccount() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter new cashier username: ");
        String username = scanner.nextLine();
        System.out.print("Enter new cashier password: ");
        String password = scanner.nextLine();
        Cashier newCashier = createCashier(username, password);
        LittleBagShop.addUser(newCashier);
    }
}
