package Sensorbot;
import java.io.*;
import java.net.*;

class UDP { 
    InetAddress IPAddress;
    DatagramPacket sendPacket;
    DatagramSocket clientSocket;
    byte[] sendData;
	public UDP(String packet,int porta) throws IOException   {  
                clientSocket = new DatagramSocket();
                IPAddress = InetAddress.getByName("192.168.42.1");
                sendData = new byte[1024];
                sendData = packet.getBytes();
                sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, porta);
                clientSocket.send(sendPacket);
		} 
} 
