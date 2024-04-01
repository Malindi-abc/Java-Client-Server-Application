
import java.io.*;
import java.net.*;

public class UDPServer {

    private static final int SERVER_PORT = 2288; //my last digits of Student ID 
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

                // Create StringBuilder to build the server response
                StringBuilder responseBuilder = new StringBuilder();

                // Read Java objects from file
                try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(MEMBER_OBJECT_FILE))) {
                    Object obj;
                    while ((obj = objectInputStream.readObject()) != null) {
                        if (obj instanceof Member) {
                            Member member = (Member) obj;
                            // Append member details to the response string with the specified format
                            responseBuilder.append(member.getMemberFirstName()).append("|")
                                    .append(member.getMemberLastName()).append("|")
                                    .append(member.getMemberAddress()).append("|")
                                    .append(member.getPhoneNumber()).append("\n");
                        }
                    }
                } catch (EOFException e) {
                    // End of file reached, do nothing
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                // Convert StringBuilder to byte array for sending as response
                byte[] responseData = responseBuilder.toString().getBytes();

                // Get client address and port from the request packet
                InetAddress clientAddress = requestPacket.getAddress();
                int clientPort = requestPacket.getPort();

                // Create DatagramPacket with the response data
                DatagramPacket responsePacket = new DatagramPacket(responseData, responseData.length, clientAddress, clientPort);

                // Send the response packet back to the client
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
