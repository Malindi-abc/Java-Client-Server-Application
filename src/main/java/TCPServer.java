
/**
 *
 * @author malin
 */
import java.net.*;
import java.io.*;

public class TCPServer {

    public static void main(String args[]) {
        try {
            int serverPort = 1188;
            ServerSocket listenSocket = new ServerSocket(serverPort);
            System.out.println("Server started and now waiting for the client details");
            while (true) {
                Socket clientSocket = listenSocket.accept();
                Connection c = new Connection(clientSocket);
                c.start();
            }
        } catch (IOException e) {
            System.out.println("Listen :" + e.getMessage());
        }
    }
}
//This class is to be used by TcpServer class, not public.

class Connection extends Thread {

    private Socket clientSocket;
    
    public Connection (Socket socket){
        this.clientSocket = socket;
    }
    

    public void run() {
        try{
        DataInputStream in = new DataInputStream(clientSocket.getInputStream());
        DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());
        
        // reading memebership Details from clients 
        int memberNumber = in.readInt();
        String memberFirstName = in.readUTF();
        String memberLastName = in.readUTF();
        String memberAddress = in.readUTF();
        int phoneNumber = in.readInt();
        
        //sending membership details into a txt file 
        
         System.out.println("Received Membership Details:");
            System.out.println("Member Number: " + memberNumber);
            System.out.println("First Name: " + memberFirstName);
            System.out.println("Last Name: " + memberLastName);
            System.out.println("Address: " + memberAddress);
            System.out.println("Phone Number: " + phoneNumber);

            // Send feedback to the client
            out.writeUTF("Save Data of the member Number" + memberNumber);
            
            // Close the connection
            clientSocket.close();
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        }
    }
}

