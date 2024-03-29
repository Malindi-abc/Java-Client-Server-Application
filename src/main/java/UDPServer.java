// UDPServer.java

import java.io.*;
import java.net.*;

public class UDPServer {

    private static final int SERVER_PORT = 2288;
    private static final String MEMBER_OBJECT_FILE = "C:\\Users\\malin\\Documents\\NetBeansProjects\\fitnessClub\\fitnessClub\\memberObjects";

    public static void main(String[] args) {
        DatagramSocket socket = null;

        try {
            socket = new DatagramSocket(SERVER_PORT);

            while (true) {
                byte[] requestData = new byte[1024];
                DatagramPacket requestPacket = new DatagramPacket(requestData, requestData.length);

                // Receive request from client
                socket.receive(requestPacket);
                System.out.println("Received request from client");

                // Read Java objects from file
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
                try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(MEMBER_OBJECT_FILE))) {
                    Object obj;
                    while ((obj = objectInputStream.readObject()) != null) {
                        objectOutputStream.writeObject(obj);
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                byte[] responseData = byteArrayOutputStream.toByteArray();

                // Send response to client
                InetAddress clientAddress = requestPacket.getAddress();
                int clientPort = requestPacket.getPort();
                DatagramPacket responsePacket = new DatagramPacket(responseData, responseData.length, clientAddress, clientPort);
                socket.send(responsePacket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (socket != null) {
                socket.close();
            }
        }
    }
}
