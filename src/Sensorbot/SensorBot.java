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
  


