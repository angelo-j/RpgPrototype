package ui;

import game.GameWorld;
import javax.swing.*;
import java.awt.*;

public class WorldMap extends JFrame {

    private GameWorld gameWorld; // Reference to the game world

    public WorldMap(GameWorld gameWorld) {
        this.gameWorld = gameWorld;

        // Set up window properties
        setTitle("World Map");
        setSize(400, 300);
        setLayout(new BorderLayout());

        // Display the player's current location
        JLabel locationLabel = new JLabel("Current Position: (" + gameWorld.getPlayerX() + ", " + gameWorld.getPlayerY() + ")", JLabel.CENTER);
        add(locationLabel, BorderLayout.CENTER);

        // Close button to exit map view
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> dispose());
        add(closeButton, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);
    }
}
