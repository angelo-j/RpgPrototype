package utils;

import entities.Player;
import java.io.*;

public class SaveManager {

    public static void save(int slot, Player player) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("save" + slot + ".dat"))) {
            out.writeObject(player); // Save only the Player object
        } catch (IOException e) {
            System.err.println("Error saving: " + e.getMessage());
        }
    }

    public static Player load(int slot) {
        File saveFile = new File("save" + slot + ".dat");
        if (!saveFile.exists()) {
            return null;
        }

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(saveFile))) {
            return (Player) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading save: " + e.getMessage());
        }
        return null;
    }

    public static boolean isSaveSlotEmpty(int slot) {
        return !new File("save" + slot + ".dat").exists();
    }

    public static void deleteSave(int slot) {
        File saveFile = new File("save" + slot + ".dat");
        if (saveFile.exists()) {
            saveFile.delete();
        }
    }
}
