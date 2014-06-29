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
