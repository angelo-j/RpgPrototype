package game;

import entities.Player;
import ui.MainMenu;
import javax.swing.*;
import java.awt.*;
import utils.SoundManager;

public class GameWorld extends JPanel {

    private Player player; // The player's character
    private int playerX, playerY; // Player's position on the map
    private SoundManager soundManager; // Add this line to hold the SoundManager

    public GameWorld(Player player, SoundManager soundManager) {
        this.player = player;
        this.soundManager = soundManager; // Initialize the SoundManager passed to GameWorld

        // Default starting position
        this.playerX = 10;
        this.playerY = 10;

        // Configure game world UI
        setLayout(new BorderLayout());
        JLabel worldLabel = new JLabel("Welcome, " + player.getName() + "!", JLabel.CENTER);
        worldLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(worldLabel, BorderLayout.NORTH);

        // Quit button that should return to the Main Menu
        JButton quitButton = new JButton("Quit");
        quitButton.addActionListener(e -> {
            // Ensure no save-related action is triggered when quitting
            JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
            if (parentFrame != null) {
                // Switch to the main menu
                parentFrame.setContentPane(new MainMenu(soundManager));  // Pass the sound manager to keep the music
                parentFrame.revalidate();
                parentFrame.repaint();
            }
        });
        add(quitButton, BorderLayout.SOUTH);  // Position the quit button at the bottom
    }

    public int getPlayerX() {
        return playerX;
    }

    public int getPlayerY() {
        return playerY;
    }

    public void movePlayer(int dx, int dy) {
        playerX += dx;
        playerY += dy;
        System.out.println("Player moved to: (" + playerX + ", " + playerY + ")");
    }
}
