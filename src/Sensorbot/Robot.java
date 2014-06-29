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
