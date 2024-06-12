package piatto.core.soundManager;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class SoundManager {
    Clip clip;
    URL soundURL[] = new URL[30];

    public SoundManager() {

        soundURL[0] = getClass().getResource("/sounds/base-music.wav");
        soundURL[1] = getClass().getResource("/sounds/ingot-pick.wav");
        soundURL[2] = getClass().getResource("/sounds/step.wav");
        soundURL[3] = getClass().getResource("/sounds/win.wav");
    }

    public void setFile(int i) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        } catch (Exception e) {

        }
    }

    public void play() {
        clip.start();
    }

    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        clip.stop();
    }
    public boolean isRunning(){
        return clip.isRunning();
    }
}
