package ui;

import entities.Player;
import game.GameWorld;
import utils.SaveManager;
import utils.SoundManager;

import javax.swing.*;
import java.awt.*;

public class CharacterCreation extends JPanel {
    private JTextField nameField;
    private JFrame parentFrame;
    private int saveSlot;
    private SoundManager soundManager;

    public CharacterCreation(JFrame parentFrame, int saveSlot, SoundManager soundManager) {
        this.parentFrame = parentFrame;
        this.saveSlot = saveSlot;
        this.soundManager = soundManager;

        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);

        JLabel titleLabel = new JLabel("Character Creation");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        add(titleLabel, constraints);

        JLabel nameLabel = new JLabel("Enter your name:");
        nameField = new JTextField(15);
        constraints.gridwidth = 1;
        constraints.gridx = 0;
        constraints.gridy = 1;
        add(nameLabel, constraints);
        constraints.gridx = 1;
        add(nameField, constraints);

        JButton confirmButton = new JButton("Confirm");
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        add(confirmButton, constraints);

        confirmButton.addActionListener(e -> {
            soundManager.playClickSound();
            String characterName = nameField.getText().trim();

            if (characterName.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter a name!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Player newPlayer = new Player(characterName);
            SaveManager.save(saveSlot, newPlayer);
            JOptionPane.showMessageDialog(this, "Save successful for slot " + saveSlot + "!", "Save Confirmation", JOptionPane.INFORMATION_MESSAGE);

            soundManager.stopMusic();
            parentFrame.setContentPane(new GameWorld(newPlayer, soundManager)); // Pass sound manager and new player
            parentFrame.revalidate();
        });

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            soundManager.playClickSound();
            parentFrame.setContentPane(new SaveSelectionMenu(parentFrame, soundManager)); // Pass sound manager
            parentFrame.revalidate();
        });
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        add(backButton, constraints);
    }
}
