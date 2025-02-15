package utils;

import javax.sound.sampled.*;
import java.net.URL;

public class SoundManager {
    private static Clip backgroundMusic;
    private static boolean musicPlaying = false;
    private static boolean clickMuted = false;
    private static boolean musicMuted = false;
    private static FloatControl clickVolumeControl;
    private static FloatControl musicVolumeControl;

    private static float defaultClickVolume = 0.7f;  // Default click volume
    private static float defaultMusicVolume = 0.7f;  // Default music volume

    // Play sound effect (click sound)
    public static void playClickSound() {
        if (!clickMuted) {
            playSoundEffect("menuClick.wav");
        }
    }

    // Play background music (continuously)
    public static void playMusic(String musicFile) {
        if (!musicMuted && !musicPlaying) {
            try {
                URL musicURL = SoundManager.class.getClassLoader().getResource("sounds/" + musicFile);
                if (musicURL == null) {
                    System.err.println("Music file not found: " + musicFile);
                    return;
                }
                AudioInputStream audioIn = AudioSystem.getAudioInputStream(musicURL);
                backgroundMusic = AudioSystem.getClip();
                backgroundMusic.open(audioIn);
                backgroundMusic.loop(Clip.LOOP_CONTINUOUSLY);
                backgroundMusic.start();
                musicPlaying = true;

                if (backgroundMusic.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
                    musicVolumeControl = (FloatControl) backgroundMusic.getControl(FloatControl.Type.MASTER_GAIN);
                    // Adjust volume to a valid range using FloatControl's min and max values
                    musicVolumeControl.setValue(convertToControlRange(defaultMusicVolume));
                }
            } catch (Exception e) {
                System.err.println("Error playing music: " + e.getMessage());
            }
        }
    }

    // Stop the music
    public static void stopMusic() {
        if (backgroundMusic != null && backgroundMusic.isRunning()) {
            backgroundMusic.stop();
            musicPlaying = false;
        }
    }

    // Helper method to play any sound effect (such as button clicks)
    private static void playSoundEffect(String soundFile) {
        try {
            URL soundURL = SoundManager.class.getClassLoader().getResource("sounds/" + soundFile);
            if (soundURL == null) {
                System.err.println("Sound file not found: " + soundFile);
                return;
            }
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundURL);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);

            if (clip.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
                clickVolumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                // Adjust volume to a valid range using FloatControl's min and max values
                clickVolumeControl.setValue(convertToControlRange(defaultClickVolume));
            }

            clip.start();
        } catch (Exception e) {
            System.err.println("Error playing sound: " + e.getMessage());
        }
    }

    // Helper method to convert volume (0-1 range) to a FloatControl range
    private static float convertToControlRange(float volume) {
        if (musicVolumeControl != null) {
            float min = musicVolumeControl.getMinimum();
            float max = musicVolumeControl.getMaximum();
            return min + (max - min) * volume;
        }
        return 0f;
    }

    // Toggle mute/unmute for music
    public static void toggleMusicMute() {
        musicMuted = !musicMuted;
        if (musicMuted) {
            stopMusic();
        } else {
            playMusic("mainMenuMusic.wav");
        }
    }

    // Toggle mute/unmute for click sounds
    public static void toggleClickMute() {
        clickMuted = !clickMuted;
    }

    // Set music volume (0-100)
    public static void setMusicVolume(int volume) {
        if (musicVolumeControl != null) {
            float scaledVolume = convertToControlRange(volume / 100.0f);
            musicVolumeControl.setValue(scaledVolume);
        }
    }

    // Set click sound volume (0-100)
    public static void setClickVolume(int volume) {
        if (clickVolumeControl != null) {
            float scaledVolume = convertToControlRange(volume / 100.0f);
            clickVolumeControl.setValue(scaledVolume);
        }
    }

    // Getters for mute states
    public static boolean isMusicMuted() {
        return musicMuted;
    }

    public static boolean isClickMuted() {
        return clickMuted;
    }

    // Getters for volume levels
    public static float getMusicVolume() {
        return defaultMusicVolume;
    }

    public static float getClickVolume() {
        return defaultClickVolume;
    }
}
