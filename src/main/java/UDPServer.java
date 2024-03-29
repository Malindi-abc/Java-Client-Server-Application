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

public class UDPServer {

    public static void main(String args[]) {
        final int serverPort = 2288; // UDP server port
        DatagramSocket socket = null;

        try {
            socket = new DatagramSocket(serverPort);

            while (true) {
                byte[] requestData = new byte[1024]; // Assuming request won't exceed 1024 bytes
                DatagramPacket requestPacket = new DatagramPacket(requestData, requestData.length);
                socket.receive(requestPacket);
                String request = new String(requestPacket.getData(), 0, requestPacket.getLength());

                // Print client request
                System.out.println("Client request: " + request);

                // Prepare and send response
                String responseData = "Sending member details to client...";
                byte[] responseDataBytes = responseData.getBytes();
                InetAddress clientAddress = requestPacket.getAddress();
                int clientPort = requestPacket.getPort();
                DatagramPacket responsePacket = new DatagramPacket(responseDataBytes, responseDataBytes.length, clientAddress, clientPort);
                socket.send(responsePacket);
            }
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            if (socket != null) {
                socket.close();
            }
        }
    }
}
