package ui;

import javax.swing.*;
import java.awt.*;

public class PauseMenu extends JFrame {
    public PauseMenu() {
        setTitle("Pause Menu");
        setSize(600, 400);
        setLayout(new GridLayout(5, 1));

        JButton statsButton = new JButton("View Stats");
        JButton inventoryButton = new JButton("View Inventory");
        JButton mapButton = new JButton("View Map");
        JButton saveButton = new JButton("Save Game");
        JButton quitButton = new JButton("Quit");

        statsButton.addActionListener(e -> viewStats());
        inventoryButton.addActionListener(e -> viewInventory());
        mapButton.addActionListener(e -> viewMap());
        saveButton.addActionListener(e -> saveGame());
        quitButton.addActionListener(e -> quitGame());

        add(statsButton);
        add(inventoryButton);
        add(mapButton);
        add(saveButton);
        add(quitButton);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void viewStats() {
        // Logic for displaying player stats
    }

    private void viewInventory() {
        // Logic for viewing player's inventory
    }

    private void viewMap() {
        // Logic for viewing the map
    }

    private void saveGame() {
        // Logic for saving the game
    }

    private void quitGame() {
        System.exit(0); // Quit the game
    }
}
