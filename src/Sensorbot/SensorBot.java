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
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javazoom.jl.decoder.JavaLayerException;

public class SensorBot extends JFrame {
	private static final long serialVersionUID = 1L;
	private  JPanel contentPane;
        Toolkit t;
        Dimension screenSize;
        int lar_schermo,lun_schermo;
        creaGUI gui;
      
	public static void main(String[] args) throws InterruptedException {
            SensorBot main = new SensorBot();
            main.setExtendedState(JFrame.MAXIMIZED_BOTH);
            main.setVisible(true);  
        }

    public SensorBot()  {
               super("Pannello di Controllo SensorBot");
            try {
                t = Toolkit.getDefaultToolkit();
                screenSize = t.getScreenSize();
                lar_schermo = (int) screenSize.getWidth();
                lun_schermo = (int) screenSize.getHeight();
                System.out.println("Dimensioni schermo: "+Integer.toString(lar_schermo)+" "+Integer.toString(lun_schermo));
                this.setLayout(null);
                this.setUndecorated(rootPaneCheckingEnabled);
                this.setBackground(new Color(0,0,0,0));
                setLocationRelativeTo(null);
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                contentPane = new JPanel();
                setContentPane(contentPane);
                gui = new creaGUI(contentPane,lar_schermo,lun_schermo);
                gui.startrefresh();
            } catch (InterruptedException | IOException | JavaLayerException ex) {
                Logger.getLogger(SensorBot.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
}
  


