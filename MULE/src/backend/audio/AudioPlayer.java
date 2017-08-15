package backend.audio;

import javafx.scene.media.AudioClip;

import javax.sound.sampled.DataLine;
import java.io.File;

/**
 * Audio player that is used to play a single audio clip
 */
public class AudioPlayer {

    private AudioClip clip;
    private float audio = 0f;
    private DataLine.Info info;
    private String audioFile;

    /**
     *
     * @param musicName the audio file to play
     * @param volume the default volume
     */
    public AudioPlayer(String musicName, float volume) {
        this.audio = volume;
        this.audioFile = musicName;
    }

    /**
     * Loads and plays the desired audio file at normal volume
     */
    public void play() {
        try {
            clip = new AudioClip(new File("MULE/src/assets/sounds/" + audioFile).toURI().toString());
            int repeat = audioFile.equals("music.wav") ? AudioClip.INDEFINITE : 1;
            clip.setCycleCount(repeat);
            clip.play(audio);
            System.out.println(audioFile);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Decreases the volume of the clip by 10%
     */
    public void decreaseVolume() {
        audio -= .1f;
        setAudio(audio);
    }


    /**
     * Decreases the volume by a certain amount
     * @param amount the ratio the volume should be decreased [0, 1]
     */
    public void decreaseVolume(float amount) {
        if (amount > 0 && amount <= 1f) {
            audio -= amount;
            setAudio(audio);
        }
    }

    /**
     * Sets the audio level to a certain float level
     * @param audioLevel the new audio level [-1, 1]
     */
    public void setAudio(float audioLevel) {
        if (Math.abs((double)audioLevel) <= 1) {
            clip.setVolume(audioLevel);
        }
    }

    /**
     * Increases the volume by 10%
     */
    public void increaseVolume() {
        audio += .1f;
        setAudio(audio);
    }

    /**
     * Increases the volume by a certain ratio
     * @param amount the ratio to increase the volume by [0 - 1]
     */
    public void increaseVolume(float amount) {
        if (amount >= 0 && amount <= 1) {
            audio += amount;
            setAudio(audio);
        }
    }
}
