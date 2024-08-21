
package com.mycompany.littlebagshop;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class LittleBagShopUI extends javax.swing.JFrame {

    private CardLayout cardLayout;
    private JPanel mainPanel;
    
    public LittleBagShopUI() {
        //initComponents();
        customInitComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    private void customInitComponents() {
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        mainPanel.add(createLoginPanel(), "login");
        mainPanel.add(createCashierMenuPanel(), "cashierMenu");
        mainPanel.add(createManagerMenuPanel(), "managerMenu");

        getContentPane().add(mainPanel);
        cardLayout.show(mainPanel, "login");
    }

    private JPanel createLoginPanel() {
        JPanel loginPanel = new JPanel(new GridLayout(3, 2));
        JLabel userLabel = new JLabel("Username:");
        JTextField userText = new JTextField();
        JLabel passLabel = new JLabel("Password:");
        JPasswordField passText = new JPasswordField();
        JButton loginButton = new JButton("Login");

        loginButton.addActionListener(e -> {
            String username = userText.getText();
            String password = new String(passText.getPassword());
            User user = LittleBagShop.authenticate(username, password);
            if (user != null) {
                if (user instanceof Manager) {
                    cardLayout.show(mainPanel, "managerMenu");
                } else {
                    cardLayout.show(mainPanel, "cashierMenu");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Invalid credentials. Please try again.");
            }
        });

        loginPanel.add(userLabel);
        loginPanel.add(userText);
        loginPanel.add(passLabel);
        loginPanel.add(passText);
        loginPanel.add(new JLabel());
        loginPanel.add(loginButton);

        return loginPanel;
    }

    private JPanel createCashierMenuPanel() {
        JPanel cashierMenuPanel = new JPanel(new GridLayout(5, 1));
        JButton viewBagsButton = new JButton("View Bags");
        JButton addBagButton = new JButton("Add Bag");
        JButton addCategoryButton = new JButton("Add Category");
        JButton searchBagsButton = new JButton("Search Bags by Category");
        JButton logoutButton = new JButton("Logout");

        viewBagsButton.addActionListener(e -> viewBags());
        addBagButton.addActionListener(e -> addBag());
        addCategoryButton.addActionListener(e -> addCategory());
        searchBagsButton.addActionListener(e -> searchBagsByCategory());
        logoutButton.addActionListener(e -> cardLayout.show(mainPanel, "login"));

        cashierMenuPanel.add(viewBagsButton);
        cashierMenuPanel.add(addBagButton);
        cashierMenuPanel.add(addCategoryButton);
        cashierMenuPanel.add(searchBagsButton);
        cashierMenuPanel.add(logoutButton);

        return cashierMenuPanel;
    }

    private JPanel createManagerMenuPanel() {
        JPanel managerMenuPanel = new JPanel(new GridLayout(6, 1));
        JButton viewBagsButton = new JButton("View Bags");
        JButton addBagButton = new JButton("Add Bag");
        JButton addCategoryButton = new JButton("Add Category");
        JButton searchBagsButton = new JButton("Search Bags by Category");
        JButton createCashierButton = new JButton("Create Cashier Account");
        JButton logoutButton = new JButton("Logout");

        viewBagsButton.addActionListener(e -> viewBags());
        addBagButton.addActionListener(e -> addBag());
        addCategoryButton.addActionListener(e -> addCategory());
        searchBagsButton.addActionListener(e -> searchBagsByCategory());
        createCashierButton.addActionListener(e -> createCashierAccount());
        logoutButton.addActionListener(e -> cardLayout.show(mainPanel, "login"));

        managerMenuPanel.add(viewBagsButton);
        managerMenuPanel.add(addBagButton);
        managerMenuPanel.add(addCategoryButton);
        managerMenuPanel.add(searchBagsButton);
        managerMenuPanel.add(createCashierButton);
        managerMenuPanel.add(logoutButton);

        return managerMenuPanel;
    }

    private void viewBags() {
        List<Bag> bags = LittleBagShop.getBags();
        if (bags.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No bags available.");
        } else {
            StringBuilder sb = new StringBuilder();
            for (Bag bag : bags) {
                sb.append(bag).append("\n");
            }
            JOptionPane.showMessageDialog(this, sb.toString());
        }
    }

    private void addBag() {
        JTextField nameField = new JTextField();
        JTextField categoryField = new JTextField();
        Object[] message = {
                "Bag Name:", nameField,
                "Category Name:", categoryField
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Add Bag", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String name = nameField.getText();
            String categoryName = categoryField.getText();
            Category category = LittleBagShop.findCategory(categoryName);
            if (category == null) {
                JOptionPane.showMessageDialog(this, "Category not found. Please add the category first.");
            } else {
                Bag bag = new Bag(name, category);
                LittleBagShop.addBag(bag);
                JOptionPane.showMessageDialog(this, "Bag added successfully.");
            }
        }
    }

    private void addCategory() {
        String categoryName = JOptionPane.showInputDialog(this, "Enter category name:");
        if (categoryName != null && !categoryName.trim().isEmpty()) {
            Category category = new Category(categoryName);
            LittleBagShop.addCategory(category);
            JOptionPane.showMessageDialog(this, "Category added successfully.");
        }
    }

    private void searchBagsByCategory() {
        String categoryName = JOptionPane.showInputDialog(this, "Enter category name:");
        if (categoryName != null && !categoryName.trim().isEmpty()) {
            boolean found = false;
            List<Bag> bags = LittleBagShop.getBags();
            StringBuilder sb = new StringBuilder();
            for (Bag bag : bags) {
                if (bag.getCategory().getName().equalsIgnoreCase(categoryName)) {
                    sb.append(bag).append("\n");
                    found = true;
                }
            }
            if (found) {
                JOptionPane.showMessageDialog(this, sb.toString());
            } else {
                JOptionPane.showMessageDialog(this, "No bags found in this category.");
            }
        }
    }

    private void createCashierAccount() {
        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        Object[] message = {
                "Username:", usernameField,
                "Password:", passwordField
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Create Cashier Account", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            Cashier newCashier = new Cashier(username, password);
            LittleBagShop.addUser(newCashier);
            JOptionPane.showMessageDialog(this, "New cashier account created successfully.");
        }
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LittleBagShopUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
