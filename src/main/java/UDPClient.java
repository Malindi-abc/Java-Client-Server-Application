/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author malin
 */
import java.io.*;
import java.net.*;

import java.io.*;
import java.net.*;

public class UDPClient {

    public static void main(String args[]) {
        final int serverPort = 2288; // UDP server port
        final String serverHostname = "localhost"; // Server hostname
        DatagramSocket socket = null;

        try {
            socket = new DatagramSocket();

            // Sending the request for member details
            byte[] requestData = "memberlistObject".getBytes();
            InetAddress serverAddress = InetAddress.getByName(serverHostname);
            DatagramPacket requestPacket = new DatagramPacket(requestData, requestData.length, serverAddress, serverPort);
            socket.send(requestPacket);

            // Receiving the response with member details
            byte[] responseData = new byte[1024]; // Assuming response won't exceed 1024 bytes
            DatagramPacket responsePacket = new DatagramPacket(responseData, responseData.length);
            socket.receive(responsePacket);
            String response = new String(responsePacket.getData(), 0, responsePacket.getLength());
            System.out.println("Received member details from server: " 
                    + response);
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            if (socket != null) {
                socket.close();
            }
        }
    }
}
