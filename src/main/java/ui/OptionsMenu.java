package ui;

import javax.swing.*;
import java.awt.*;
import utils.SoundManager;
import java.util.prefs.Preferences;

public class OptionsMenu extends JPanel {
    private SoundManager soundManager;
    private JSlider musicSlider, clickSlider;
    private JButton muteMusicButton, muteClickButton, toggleFullscreenButton, changeAspectRatioButton;
    private Preferences prefs;

    public OptionsMenu(SoundManager soundManager) {
        this.soundManager = soundManager;
        prefs = Preferences.userNodeForPackage(OptionsMenu.class);

        int musicVolume = prefs.getInt("musicVolume", 50);
        int clickVolume = prefs.getInt("clickVolume", 50);
        boolean musicMuted = prefs.getBoolean("musicMuted", false);
        boolean clickMuted = prefs.getBoolean("clickMuted", false);
        boolean isFullscreen = prefs.getBoolean("fullscreen", false);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Options");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(titleLabel);

        JPanel volumePanel = new JPanel();
        volumePanel.setLayout(new GridLayout(2, 1, 10, 10));

        JLabel musicLabel = new JLabel("Music Volume:");
        musicSlider = new JSlider(0, 100, musicVolume);
        musicSlider.setPreferredSize(new Dimension(300, 50));  // Adjust the width
        volumePanel.add(musicLabel);
        volumePanel.add(musicSlider);

        JLabel clickLabel = new JLabel("Click Volume:");
        clickSlider = new JSlider(0, 100, clickVolume);
        clickSlider.setPreferredSize(new Dimension(300, 50));  // Adjust the width
        volumePanel.add(clickLabel);
        volumePanel.add(clickSlider);

        add(volumePanel);

        muteMusicButton = new JButton(musicMuted ? "Unmute Music" : "Mute Music");
        muteClickButton = new JButton(clickMuted ? "Unmute Click" : "Mute Click");

        muteMusicButton.addActionListener(e -> {
            soundManager.playClickSound();  // Play click sound
            soundManager.toggleMusicMute();
            updateMuteButtonState(muteMusicButton, soundManager.isMusicMuted(), "Mute Music", "Unmute Music");
            prefs.putBoolean("musicMuted", soundManager.isMusicMuted());
        });

        muteClickButton.addActionListener(e -> {
            soundManager.playClickSound();  // Play click sound
            soundManager.toggleClickMute();
            updateMuteButtonState(muteClickButton, soundManager.isClickMuted(), "Mute Click", "Unmute Click");
            prefs.putBoolean("clickMuted", soundManager.isClickMuted());
        });

        add(muteMusicButton);
        add(muteClickButton);

        musicSlider.addChangeListener(e -> {
            soundManager.setMusicVolume(musicSlider.getValue());
            prefs.putInt("musicVolume", musicSlider.getValue());
        });

        clickSlider.addChangeListener(e -> {
            soundManager.setClickVolume(clickSlider.getValue());
            prefs.putInt("clickVolume", clickSlider.getValue());
        });

        toggleFullscreenButton = new JButton(isFullscreen ? "Exit Fullscreen" : "Enter Fullscreen");
        toggleFullscreenButton.addActionListener(e -> {
            soundManager.playClickSound();  // Play click sound
            toggleFullscreen(isFullscreen);
        });

        changeAspectRatioButton = new JButton("Change Aspect Ratio");
        changeAspectRatioButton.addActionListener(e -> {
            soundManager.playClickSound();  // Play click sound
            changeAspectRatio();
        });

        add(toggleFullscreenButton);
        add(changeAspectRatioButton);

        JButton backButton = new JButton("Back");
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.addActionListener(e -> {
            soundManager.playClickSound();  // Play click sound
            JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(OptionsMenu.this);
            parentFrame.setContentPane(new MainMenu(soundManager));
            parentFrame.revalidate();
        });
        add(backButton);
    }

    private void updateMuteButtonState(JButton button, boolean isMuted, String muteText, String unmuteText) {
        button.setText(isMuted ? unmuteText : muteText);
    }

    private void toggleFullscreen(boolean isFullscreen) {
        JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(OptionsMenu.this);
        if (parentFrame == null) {
            System.err.println("Error: parentFrame is null.");
            return;  // Exit early if parentFrame is null
        }

        if (isFullscreen) {
            // Set undecorated before making the frame visible
            parentFrame.setUndecorated(true);
            parentFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            prefs.putBoolean("fullscreen", true);
        } else {
            // Exit fullscreen: set undecorated and reset to normal state
            parentFrame.setExtendedState(JFrame.NORMAL);
            parentFrame.setUndecorated(false);
            prefs.putBoolean("fullscreen", false);
            applyWindowSize();  // Reset to 16:9 size after exiting fullscreen
        }

        parentFrame.setVisible(true);  // Ensure the frame is visible after fullscreen toggle
    }



    private void applyWindowSize() {
        JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(OptionsMenu.this);
        parentFrame.setSize(1600, 900);
    }

    private void changeAspectRatio() {
        String[] options = {"16:9", "4:3", "Custom"};
        String choice = (String) JOptionPane.showInputDialog(this, "Choose Aspect Ratio", "Aspect Ratio", JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
        if (choice != null) {
            switch (choice) {
                case "16:9":
                    applyWindowSize();
                    break;
                case "4:3":
                    setAspectRatio(4, 3);
                    break;
                case "Custom":
                    String widthStr = JOptionPane.showInputDialog(this, "Enter width:");
                    String heightStr = JOptionPane.showInputDialog(this, "Enter height:");
                    try {
                        int width = Integer.parseInt(widthStr);
                        int height = Integer.parseInt(heightStr);
                        setAspectRatio(width, height);
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(this, "Invalid input.");
                    }
                    break;
            }
        }
    }

    private void setAspectRatio(int width, int height) {
        JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(OptionsMenu.this);
        parentFrame.setSize(800 * width / height, 800);
    }
}
