package game;

import javax.swing.*;
import java.awt.*;

public class FastTravel extends JFrame {
    // Declare a reference to the game world
    private game.GameWorld gameWorld;

    // Constructor for the FastTravel screen
    public FastTravel(game.GameWorld gameWorld) {
        // Initialize the gameWorld object, which contains the player's position and movement logic
        this.gameWorld = gameWorld;

        // Set the window title and size
        setTitle("Fast Travel");
        setSize(400, 300);

        // Use GridLayout for arranging components in 3 rows and 1 column
        setLayout(new GridLayout(3, 1));

        // Create a label at the top to prompt the player to choose a location
        JLabel label = new JLabel("Choose a Fast Travel Location:", SwingConstants.CENTER);

        // Create buttons for each travel location
        JButton location1 = new JButton("Downtown");
        JButton location2 = new JButton("Industrial Zone");
        JButton location3 = new JButton("Residential Area");

        // Add action listeners to the buttons that define the locations for fast travel
        location1.addActionListener(e -> fastTravelTo(5, 5)); // Coordinates for Downtown
        location2.addActionListener(e -> fastTravelTo(20, 15)); // Coordinates for Industrial Zone
        location3.addActionListener(e -> fastTravelTo(10, 25)); // Coordinates for Residential Area

        // Add components to the frame (the label and the buttons)
        add(label);
        add(location1);
        add(location2);
        add(location3);

        // Center the window on the screen
        setLocationRelativeTo(null);

        // Make the window visible
        setVisible(true);
    }

    // Method to move the player to the specified coordinates (x, y)
    private void fastTravelTo(int x, int y) {
        // Move the player by calling movePlayer() from the gameWorld object,
        // adjusting their position relative to their current position
        gameWorld.movePlayer(x - gameWorld.getPlayerX(), y - gameWorld.getPlayerY());

        // Close the FastTravel window after the player has been moved
        dispose();
    }
}
