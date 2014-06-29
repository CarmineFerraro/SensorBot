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

import eu.hansolo.steelseries.extras.Battery;
import eu.hansolo.steelseries.extras.Led;
import eu.hansolo.steelseries.gauges.RadialBargraph;
import eu.hansolo.steelseries.tools.BackgroundColor;
import eu.hansolo.steelseries.tools.FrameDesign;
import eu.hansolo.steelseries.tools.LcdColor;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javazoom.jl.decoder.JavaLayerException;
import org.json.JSONException;
import org.json.JSONObject;

public class creaGUI {
        private final  JLabel tiltValue;
	private final RadialBargraph tempValue;
	private final RadialBargraph gasValue;
	private final RadialBargraph luxValue;
	private final RadialBargraph waterValue;
        private JLabel label_laser,logo,car,warning;
        private final Battery battery;
        private final Led ldx,lsx;
        private static final String cmd= "/Applications/VLC.app/Contents/MacOS/VLC http://192.168.42.1:8090";
        static Process p,c,e;
        String emergency=null;
        byte[] b;
        String dataok,data;
        Robot robot;
        JSONObject obj;
        ImageIcon imgdownC,imgupC,imgleftC,imgrightC,carM;
        JButton btnIndietro,btnAvanti,btnDestra,btnSinistra,btnExit;
        Thread alarm;
        soundAlert audio;
        int flagGas=0,flagWater=0,flagTemp=0,flagWarning=0;
    
    public creaGUI(JPanel contentPane,int lar_schermo,int lun_schermo) throws InterruptedException, IOException, JavaLayerException{
                p = Runtime.getRuntime().exec(cmd);
                c = Runtime.getRuntime().exec("pgrep VLC");
                b = new byte[256];
                c.getInputStream().read(b);
                dataok = new String(b);
                data = dataok.trim();
                System.out.println("PID VLC: "+data);
                contentPane.setBackground(new Color(0,0,0,0));
                contentPane.setOpaque(true);
                contentPane.setLayout(null);
                contentPane.setBorder(BorderFactory.createMatteBorder((lun_schermo*22)/800, (lar_schermo*5)/1280, (lun_schermo*36)/800, (lar_schermo*5)/1280, Color.black));
                robot = new Robot();
                emergency = "{'TILT':'0','ACQUA':'0','GAS':'0','RETRODX':'0','RETROSX':'0','TEMP':'0','LUCE':'0'}";
                obj = new JSONObject(emergency);
                logo = new JLabel(new ImageIcon(this.getClass().getResource("/Images/sensorbot.png")));
                logo.setBounds(0,0,(lar_schermo*300)/1280,(lun_schermo*50)/800);
                contentPane.add(logo);
                warning = new JLabel(new ImageIcon(this.getClass().getResource("/Images/warning.png")));
                warning.setVisible(false);
                warning.setBounds(20, 490, 200, 150);
                contentPane.add(warning);
                
                imgdownC = new ImageIcon(this.getClass().getResource("/Images/frecce_dir/downC.png"));
		btnIndietro = new JButton(imgdownC);
                btnIndietro.setBorder(null);
                btnIndietro.setBounds((lar_schermo*1150)/1280,(lun_schermo*625)/800 ,(lar_schermo*imgdownC.getIconWidth())/1280 ,(lun_schermo*imgdownC.getIconHeight())/800);
		btnIndietro.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
                            robot.indietro();
			}
		});
		contentPane.add(btnIndietro);
                imgupC = new ImageIcon(this.getClass().getResource("/Images/frecce_dir/upC.png"));
		btnAvanti = new JButton(imgupC);
                btnAvanti.setBorder(null);
                btnAvanti.setBounds((lar_schermo*1150)/1280,(lun_schermo*520)/800, (lar_schermo*imgupC.getIconWidth())/1280,(lun_schermo* imgupC.getIconHeight())/800);
		btnAvanti.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
                           robot.avanti();
                        }
		});
		contentPane.add(btnAvanti);
                imgleftC = new ImageIcon(this.getClass().getResource("/Images/frecce_dir/leftC.png"));
		btnSinistra = new JButton(imgleftC);
                btnSinistra.setBorder(null);
                btnSinistra.setBounds((lar_schermo*1080)/1280,(lun_schermo*588)/800,(lar_schermo*imgleftC.getIconWidth())/1280,(lun_schermo*imgleftC.getIconHeight())/800);
		btnSinistra.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				robot.sinistra();
			}
		});
		contentPane.add(btnSinistra);
                imgrightC = new ImageIcon(this.getClass().getResource("/Images/frecce_dir/rightC.png"));
		btnDestra = new JButton(imgrightC);
                btnDestra.setBorder(null);
                btnDestra.setBounds((lar_schermo*1185)/1280,(lun_schermo*588)/800 ,(lar_schermo*imgrightC.getIconWidth())/1280,(lun_schermo*imgrightC.getIconHeight())/800);
		btnDestra.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				robot.destra();
			}
		});
		contentPane.add(btnDestra);		                
                btnExit = new JButton("EXIT");
                btnExit.addMouseListener(new MouseAdapter(){
                    @Override
                    public void mouseClicked(MouseEvent f){
                            p.destroy();//for windows
                        try {
                            e = Runtime.getRuntime().exec("kill -9 "+data+"\n");//for UNIX system
                        } catch (IOException ex) {
                           System.out.println("impossibile terminare con kill");
                        }
                        System.exit(0);
                    }
                });
                btnExit.setBounds((lar_schermo*20)/1280,(lun_schermo*50)/800,(lar_schermo*70)/1280,(lun_schermo*40)/800);
                contentPane.add(btnExit);
                tiltValue = new JLabel();
		tiltValue.setBounds((lar_schermo*50)/1280,(lun_schermo*500)/800,(lar_schermo*200)/1280, (lun_schermo*30)/800);
                tiltValue.setText("Ground OK!");
                tiltValue.setForeground(Color.white);
		contentPane.add(tiltValue);
		tempValue = new RadialBargraph();
                tempValue.setMaxValue(50);
		tempValue.setBounds(0,(lun_schermo*90)/800, (lar_schermo*200)/1280, (lun_schermo*200)/800);
                tempValue.setTitle("Temperature");
                tempValue.setUnitString("°C");
                tempValue.setLcdColor(LcdColor.BLUE_LCD);
                tempValue.setFrameDesign(FrameDesign.STEEL);
                tempValue.setBackgroundColor(BackgroundColor.BLUE);
		contentPane.add(tempValue);
		gasValue = new RadialBargraph();
		gasValue.setBounds(0,(lun_schermo*300)/800, (lar_schermo*200)/1280, (lun_schermo*200)/800);
                gasValue.setMaxValue(100);
                gasValue.setBackgroundColor(BackgroundColor.BLUE);
                gasValue.setTitle("Gas");
                gasValue.setFrameDesign(FrameDesign.STEEL);
                gasValue.setUnitString("");
                gasValue.setLcdColor(LcdColor.BLUE_LCD);
		contentPane.add(gasValue);
		luxValue = new RadialBargraph();
                luxValue.setTitle("Brightness");
                luxValue.setUnitString("%");
                luxValue.setBackgroundColor(BackgroundColor.BLUE);
                luxValue.setMaxValue(100);
                luxValue.setLcdColor(LcdColor.BLUE_LCD);
                luxValue.setFrameDesign(FrameDesign.STEEL);
		luxValue.setBounds((lar_schermo*1080)/1280,(lun_schermo*300)/800, (lar_schermo*200)/1280, (lun_schermo*200)/800);
		contentPane.add(luxValue);
		waterValue = new RadialBargraph();
                waterValue.setFrameDesign(FrameDesign.STEEL);
		waterValue.setBounds((lar_schermo*1080)/1280,(lun_schermo*90)/800, (lar_schermo*200)/1280, (lun_schermo*200)/800);
                waterValue.setTitle("Water");
                waterValue.setLcdColor(LcdColor.BLUE_LCD);
                waterValue.setMaxValue(100);
                waterValue.setUnitString("%");
                waterValue.setBackgroundColor(BackgroundColor.BLUE);
		contentPane.add(waterValue);
                battery= new Battery();
                battery.setBounds((lar_schermo*1130)/1280,(lun_schermo*25)/800,(lar_schermo*120)/1280, (lun_schermo*50)/800);
		battery.setValue(100);
                contentPane.add(battery);
                label_laser = new JLabel("Rear Laser Sensors");
		label_laser.setBounds((lar_schermo*1150)/30,(lun_schermo*508)/800,(lar_schermo*200)/1280, (lun_schermo*30)/800);
                label_laser.setForeground(Color.WHITE);
		contentPane.add(label_laser);
                carM = new ImageIcon(this.getClass().getResource("/Images/car.png"));
                car = new JLabel(carM);
                car.setBounds((lar_schermo*5)/1280,(lun_schermo*530)/800, (lar_schermo*250)/1280, (lun_schermo*250)/800);
                contentPane.add(car);
                lsx = new Led();
                lsx.setBounds((lar_schermo*40)/1280,(lun_schermo*610)/800, (lar_schermo*40)/1280, (lun_schermo*40)/800);
                contentPane.add(lsx);
                ldx = new Led();
                ldx.setBounds((lar_schermo*40)/1280,(lun_schermo*660)/800, (lar_schermo*40)/1280, (lun_schermo*40)/800);
                contentPane.add(ldx);
		contentPane.isFocusable();
	
                
                
		AbstractAction upaction= new AbstractAction() {
			private static final long serialVersionUID = 1L;
			@Override
			public void actionPerformed(ActionEvent e) {
				robot.avanti();
			}
		};
		AbstractAction downaction= new AbstractAction() {
			private static final long serialVersionUID = 1L;
			@Override
			public void actionPerformed(ActionEvent e) {
				robot.indietro();
			}
		};
		AbstractAction sxaction= new AbstractAction() {
			private static final long serialVersionUID = 1L;
			@Override
			public void actionPerformed(ActionEvent e) {
				robot.sinistra();
			}
		};
		AbstractAction dxaction;
            dxaction = new AbstractAction() {
                private static final long serialVersionUID = 1L;
                @Override
                public void actionPerformed(ActionEvent e) {
                   robot.destra();
                }
            };
            
		contentPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("UP"),"upaction");
		contentPane.getActionMap().put("upaction", upaction);
		contentPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("DOWN"),"downaction");
		contentPane.getActionMap().put("downaction", downaction);
		contentPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("LEFT"),"sxaction");
		contentPane.getActionMap().put("sxaction", sxaction);
		contentPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("RIGHT"),"dxaction");
		contentPane.getActionMap().put("dxaction", dxaction);
    }

    public void startrefresh(){
                try{
                aggiornaDati dataset = new aggiornaDati();
                Thread s = new Thread(dataset);
                s.start();
                }catch(Exception dataerror){
                    System.out.println("riprovo aggiornamento...");
                    aggiornaDati dataset = new aggiornaDati();
                    Thread s = new Thread(dataset);
                    s.start();
                }
    }
    
    public class aggiornaDati implements Runnable{
        @Override
        public void run(){
                while(true){
                    try {
                        Thread.sleep(500);
                    DatiArduino dati2 = new DatiArduino();
                    String dati3 = dati2.Stampa();
                    if(dati3.isEmpty()){
                        continue;
                    }
                    JSONObject obj2 = new JSONObject(dati3);
                    if(Integer.parseInt(obj2.getString("RETRODX"))==0){
                        ldx.setLedOn(true);
                    }else{
                        ldx.setLedOn(false);
                    }
                    if(Integer.parseInt(obj2.getString("RETROSX"))==0){
                        lsx.setLedOn(true);
                    }
                    else{
                        lsx.setLedOn(false);
                    }
                    flagWarning = 0;
                    Double water = Double.parseDouble(obj2.getString("ACQUA"));
                    water = (1000-water)/10;
                    waterValue.setValue(water);
                    if(water >= 50 && flagWater == 0){
                        warning.setVisible(true);
                        flagWater = 1;
                        flagWarning = 1;
                        audio = new soundAlert("/Audio/warningWater.mp3");
                        alarm = new Thread(audio);
                        alarm.start();
                    } else {
                        if(flagWarning == 0)
                            warning.setVisible(false);
                        flagWater = 0;
                    }
                    
                    Double gas = Double.parseDouble(obj2.getString("GAS"));
                    gas= gas/10;
                    gasValue.setValue(gas);
                    if(gas >= 50.00 && flagGas == 0) {
                        flagGas =1;
                        warning.setVisible(true);
                        flagWarning= 1;
                        audio = new soundAlert("/Audio/warningGas.mp3");
                        alarm = new Thread(audio);
                        alarm.start();
                    }
                    else{
                    flagGas = 0;
                       if(flagWarning == 0)
                            warning.setVisible(false);
                    }
                    
                    if( "0".equals(obj2.getString("TILT"))){
                    tiltValue.setText("Bumpy ground!!!");
                    } else{
                        tiltValue.setText("Ground OK!");
                    }
                    Double temp = Double.parseDouble(obj2.getString("TEMP"));
                    tempValue.setValue(temp);
                    if(temp >= 50 && flagTemp==0){
                        flagTemp =1;
                        warning.setVisible(true);
                        flagWarning = 1;
                        audio = new soundAlert("/Audio/warningTemp.mp3");
                        alarm = new Thread(audio);
                        alarm.start();
                    }
                    else{
                    flagTemp = 0;
                       if(flagWarning == 0)
                            warning.setVisible(false);
                    }    
                    
                    Double lux = Double.parseDouble(obj2.getString("LUCE"));
                    lux = lux/10;
                    luxValue.setValue(lux);
                    alarm.join();
                } catch ( JSONException | NumberFormatException f) {
                        System.out.println("errore ricezione dati");
                        this.run();   
                    } catch (NullPointerException | InterruptedException ex) {
                        System.out.println("Nessun alarm da attendere....");
                    }    
        }
    } 
    }
}
