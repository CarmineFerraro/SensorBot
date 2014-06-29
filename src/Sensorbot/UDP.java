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
