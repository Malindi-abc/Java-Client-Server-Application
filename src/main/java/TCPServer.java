
/**
 *
 * @author malin
 */
import java.net.*;
import java.io.*;

public class TCPServer {

    private static int clientCounter = 1; // Counter for clients

    public static void main(String args[]) {
        final int serverPort = 1188; // Use the same port as specified in the assignment
        try {
            ServerSocket listenSocket = new ServerSocket(serverPort);
            System.out.println("Server started and now waiting for client details...");

            while (true) {
                Socket clientSocket = listenSocket.accept();
                System.out.println("Receiving data from Client " + clientCounter);
                Connection c = new Connection(clientSocket, clientCounter);
                c.start();
                clientCounter++; // Increment client counter for the next client
            }
        } catch (IOException e) {
            System.err.println("Error starting the server: " + e.getMessage());
        }
    }
}

class Connection extends Thread {

    private Socket clientSocket;
    private int clientNumber;

    public Connection(Socket socket, int clientNumber) {
        this.clientSocket = socket;
        this.clientNumber = clientNumber;
    }

    public void run() {
        try (DataInputStream in = new DataInputStream(clientSocket.getInputStream()); DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream()); PrintWriter writer = new PrintWriter(new FileWriter("memberlist.txt", true))) {

            // Reading membership details from the client
            int memberNumber = in.readInt();
            String memberFirstName = in.readUTF();
            String memberLastName = in.readUTF();
            String memberAddress = in.readUTF();
            String phoneNumber = in.readUTF();

            // Writing membership details to the file
            writer.println(memberFirstName + ":" + memberLastName + ":" + memberAddress + ":" + phoneNumber);
            

            // Send feedback to the client
            out.writeUTF("Save data of the member Number " + memberNumber);

            System.out.println("....................................");

        } catch (IOException e) {
            System.err.println("Error handling client connection: " + e.getMessage());
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                System.err.println("Error closing client socket: " + e.getMessage());
            }
        }
    }
}
