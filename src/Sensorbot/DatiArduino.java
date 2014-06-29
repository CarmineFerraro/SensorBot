// Author: Ferraro Carmine
//   This file is part of Sensorbot.
//
//  Sensorbot is free software: you can redistribute it and/or modify
//  it under the terms of the GNU General Public License as published by
//  the Free Software Foundation, either version 3 of the License, or
//  (at your option) any later version.
//
//  Sensorbot is distributed in the hope that it will be useful,
//  but WITHOUT ANY WARRANTY; without even the implied warranty of
//  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//  GNU General Public License for more details.
//
//  You should have received a copy of the GNU General Public License
//  along with Sensorbot.  If not, see <http://www.gnu.org/licenses/>.

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
