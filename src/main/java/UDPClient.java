
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author malin
 */
public class UDPClient {

    public static void main(String[] args) {
        final int serverPort = 2288; // According to the assignment UDP Connection should be in the following format “22xx” where xx is the last 2 numbers on your student ID. MyStudent ID is 12217588 so according to that 2288 is connection
        try {
            InetAddress serverAddress = InetAddress.getLocalHost();
            DatagramSocket socket = new DatagramSocket();

            // Sending request to the server
            String request = "memberlistObject";
            byte[] requestData = request.getBytes();
            DatagramPacket requestPacket = new DatagramPacket(requestData, requestData.length, serverAddress, serverPort);
            socket.send(requestPacket);

            // Receiving response from the server
            byte[] responseData = new byte[1024]; // Adjust the buffer size as needed
            DatagramPacket responsePacket = new DatagramPacket(responseData, responseData.length);
            socket.receive(responsePacket);

            // Extracting and printing member details from the response
            String response = new String(responseData, 0, responsePacket.getLength());
            System.out.println("Member Details:");
            System.out.println(response);

            // Closing the socket
            socket.close();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

}
