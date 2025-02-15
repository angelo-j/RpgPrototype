package game;

import ui.MainMenu;
import utils.SoundManager;
import javax.swing.*;

public class Game {

    public Game() {
        // Initialize the SoundManager instance to control music and click sounds
        SoundManager soundManager = new SoundManager();

        // Create the JFrame for the game window
        JFrame gameFrame = new JFrame("RPG Prototype");

        // Set the window size at 16:9 aspect ratio
        int width = 1600;  // Width for 16:9 aspect ratio
        int height = 900;  // Corresponding height
        gameFrame.setSize(width, height); // Set the window size before anything appears

        // Create and set the MainMenu with the SoundManager instance
        MainMenu mainMenu = new MainMenu(soundManager);  // Pass SoundManager here

        // Set up the JFrame
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setContentPane(mainMenu);  // Set the Main Menu as the content
        gameFrame.setLocationRelativeTo(null);  // Center the frame
        gameFrame.setVisible(true);  // Make the frame visible
    }

    public static void main(String[] args) {
        // Start the game by initializing the Game class
        new Game();
    }
}
