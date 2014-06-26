package Sensorbot;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class DatiArduino {
    String stampa,sentence;
    Socket clientSocket;
    DataOutputStream outToServer;
    BufferedReader inFromServer;
        public DatiArduino() {
        try {
                clientSocket = new Socket("192.168.42.1",5007);
                outToServer = new DataOutputStream(clientSocket.getOutputStream());
                inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                sentence = "h\n";
                outToServer.writeBytes(sentence);
                stampa = inFromServer.readLine();
            }
        catch (IOException ex) {
            System.out.println("errore DatiArduino trasmissione");
        } 
        }   
    
    public String Stampa(){
        return stampa;
    }  
}
