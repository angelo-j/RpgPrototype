package ui;

import javax.swing.*;
import java.awt.*;
import utils.SoundManager;
import utils.SaveManager;
import ui.CharacterCreation;

import entities.Player;
import game.GameWorld;

public class SaveSelectionMenu extends JPanel {
    private SoundManager soundManager;
    private JButton saveSlot1, saveSlot2, saveSlot3;

    public SaveSelectionMenu(JFrame parentFrame, SoundManager soundManager) {
        this.soundManager = soundManager;
        soundManager.playMusic("mainMenuMusic.wav");  // Keep playing the music in the background

        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);

        JLabel titleLabel = new JLabel("Select a Save Slot");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        add(titleLabel, constraints);

        // Initialize save slot buttons with character names from save slots
        saveSlot1 = new JButton(getSaveSlotText(1));
        saveSlot2 = new JButton(getSaveSlotText(2));
        saveSlot3 = new JButton(getSaveSlotText(3));

        // Add action listeners to load or create characters based on save slot
        saveSlot1.addActionListener(e -> loadOrCreateCharacter(parentFrame, 1));
        saveSlot2.addActionListener(e -> loadOrCreateCharacter(parentFrame, 2));
        saveSlot3.addActionListener(e -> loadOrCreateCharacter(parentFrame, 3));

        // Add save slot buttons to layout
        constraints.gridwidth = 1;
        constraints.gridx = 0;
        constraints.gridy = 1;
        add(saveSlot1, constraints);
        constraints.gridx = 1;
        add(saveSlot2, constraints);
        constraints.gridx = 2;
        add(saveSlot3, constraints);

        // Add delete save button
        JButton deleteButton = new JButton("Delete Save");
        deleteButton.addActionListener(e -> {
            soundManager.playClickSound();
            // Prompt user for which save slot to delete
            String slotToDelete = JOptionPane.showInputDialog(this, "Enter save slot number to delete (1-3):");
            if (slotToDelete != null && !slotToDelete.isEmpty()) {
                int slot = Integer.parseInt(slotToDelete);
                SaveManager.deleteSave(slot);  // Delete the save file
                updateSaveSlotButtons();  // Refresh the save slots to reflect the deletion
            }
        });
        constraints.gridwidth = 2;
        constraints.gridx = 0;
        constraints.gridy = 2;
        add(deleteButton, constraints);

        // Add back button to return to Main Menu
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            soundManager.playClickSound();
            parentFrame.setContentPane(new MainMenu(soundManager));  // Return to main menu
            parentFrame.revalidate();
            parentFrame.repaint();  // Ensure the UI is fully refreshed
        });
        constraints.gridy = 3;
        add(backButton, constraints);
    }

    // Refresh the save slot buttons to reflect any changes (like deleting a save)
    private void updateSaveSlotButtons() {
        // After save slot update (deletion or loading), we refresh the slot buttons
        saveSlot1.setText(getSaveSlotText(1));
        saveSlot2.setText(getSaveSlotText(2));
        saveSlot3.setText(getSaveSlotText(3));
    }

    // Get the text for a save slot button (either a name or "New Game")
    private String getSaveSlotText(int slot) {
        Player player = SaveManager.load(slot);
        return (player != null) ? "Save " + slot + ": " + player.getName() : "Save " + slot + ": New Game";
    }

    // Load or create character for the selected save slot
    private void loadOrCreateCharacter(JFrame parentFrame, int slot) {
        soundManager.playClickSound();
        Player player = SaveManager.load(slot);
        if (player == null) {
            parentFrame.setContentPane(new CharacterCreation(parentFrame, slot, soundManager)); // New game
        } else {
            parentFrame.setContentPane(new GameWorld(player, soundManager)); // Load saved state
        }
        parentFrame.revalidate();
        parentFrame.repaint();  // Ensure the UI is fully refreshed
    }
}
