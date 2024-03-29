
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
public class UDPServer {

    public static void main(String[] args) {
        final int serverPort = 2288; // Use the same port as specified in the assignment
        try {
            DatagramSocket socket = new DatagramSocket(serverPort);

            // Receive request from client
            byte[] requestData = new byte[1024]; // Adjust buffer size as needed
            DatagramPacket requestPacket = new DatagramPacket(requestData, requestData.length);
            socket.receive(requestPacket);
            InetAddress clientAddress = requestPacket.getAddress();
            int clientPort = requestPacket.getPort();

            // Read Java objects from file
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("memberlistObject"));
            Object memberObject = objectInputStream.readObject();
            objectInputStream.close();

            // Serialize the object data
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(memberObject);
            objectOutputStream.flush();

            // Send serialized object data back to client
            byte[] responseData = byteArrayOutputStream.toByteArray();
            DatagramPacket responsePacket = new DatagramPacket(responseData, responseData.length, clientAddress, clientPort);
            socket.send(responsePacket);

            // Close the socket
            socket.close();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
