package ui;

import javax.swing.*;
import java.awt.*;
import utils.SoundManager;
import java.util.prefs.Preferences;

public class MainMenu extends JPanel {
    private SoundManager soundManager;
    private Preferences prefs;
    private boolean isFullscreen;

    public MainMenu(SoundManager soundManager) {
        this.soundManager = soundManager;
        soundManager.playMusic("mainMenuMusic.wav");

        prefs = Preferences.userNodeForPackage(MainMenu.class);
        isFullscreen = prefs.getBoolean("fullscreen", false);

        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Welcome to RPG Prototype", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));

        JPanel buttonPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton startButton = new JButton("Start Game");
        JButton optionsButton = new JButton("Options");
        JButton quitButton = new JButton("Exit");

        startButton.addActionListener(e -> {
            soundManager.playClickSound();
            JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(MainMenu.this);
            parentFrame.setContentPane(new SaveSelectionMenu(parentFrame, soundManager));
            parentFrame.revalidate();
        });

        optionsButton.addActionListener(e -> {
            soundManager.playClickSound();
            JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(MainMenu.this);
            parentFrame.setContentPane(new OptionsMenu(soundManager));
            parentFrame.revalidate();
        });

        quitButton.addActionListener(e -> {
            soundManager.playClickSound();
            System.exit(0); // Exit the application
        });

        buttonPanel.add(startButton);
        buttonPanel.add(optionsButton);
        buttonPanel.add(quitButton);

        add(titleLabel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);

        applyWindowSize();
    }

    private void applyWindowSize() {
        JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(MainMenu.this);
        if (parentFrame != null) {
            int width = 1600;
            int height = 900;
            parentFrame.setSize(width, height);

            if (isFullscreen) {
                parentFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
                parentFrame.setUndecorated(true);
            } else {
                parentFrame.setExtendedState(JFrame.NORMAL);
            }
            parentFrame.setVisible(true);
        } else {
            // Handle gracefully if parentFrame is null, but we don’t need to print anything
        }
    }
}
