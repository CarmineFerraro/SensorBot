package Sensorbot;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class soundAlert implements Runnable{ 

FileInputStream fis;
Player playMp3 = null;
String path;
    public soundAlert(String filepath){
    this.path = filepath;
                }

    @Override
    public void run() {
    try {
        fis = new FileInputStream(getClass().getResource(path).getPath());
        playMp3 = new Player(fis);
        playMp3.play();
    } catch (FileNotFoundException | JavaLayerException ex) {
        Logger.getLogger(soundAlert.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
}