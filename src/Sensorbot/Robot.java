package Sensorbot;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Robot {
    private final int port1=5005;
    private final int port2=5006;
    UDP udpa;
    
    public void avanti(){
        try {
                    udpa = new UDP("A",port1);
                } catch (IOException ex) {
                    Logger.getLogger(Robot.class.getName()).log(Level.SEVERE, null, ex);
                } 
    }
    
    public  void indietro(){
        try {
                    udpa = new UDP("I",port1);
                } catch (IOException ex) {
                    Logger.getLogger(Robot.class.getName()).log(Level.SEVERE, null, ex);
                } 
    }
    public void destra(){
        try {
                    udpa = new UDP("D",port2);
                } catch (IOException ex) {
                    Logger.getLogger(Robot.class.getName()).log(Level.SEVERE, null, ex);
                } 
    }
    public void sinistra(){
        try {
                    udpa = new UDP("S",port2);
                } catch (IOException ex) {
                    Logger.getLogger(Robot.class.getName()).log(Level.SEVERE, null, ex);
                } 
    }
}
