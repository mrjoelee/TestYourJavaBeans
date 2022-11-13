package com.testyourjavabeans;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Music {
    String filePath = "music/jazzmusic.wav";

    public void play(String pathFile){
        try {
            File musicPath = new File(pathFile);

            if(musicPath.exists()){
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();
            }
        } catch (Exception e) {
            System.out.println("Error with playing sound.");
            e.printStackTrace();
        }
    }

    public void musicPlay(){
        this.play(filePath);
    }

}
