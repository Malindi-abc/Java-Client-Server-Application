
import java.util.Scanner;
import java.io.*;
import java.net.*;

/**
 *
 * @author malin
 */
public class TCPClient {

    //declareing Variables 
    private static int memberNumber;
    private static String memberFirstName;
    private static String memberLastName;
    private static String memberAddress;
    private static int phoneNumber;

    public static void main(String[] args) {

        try {
            // connecting to the server 
            Socket socket = new Socket("localhost", 1188);
            //creating input and output stream to communicate with the server 
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            //providing membership details to register for club membership
            Scanner input = new Scanner(System.in);
            System.out.println("Enter Detail for member number");
            //getting inputs from client 
            memberNumber = input.nextInt();
            input.nextLine(); // this code will cosume as a new line 
            System.out.println("Enter Your First Name");
            //getting inputs from client 
            memberFirstName = input.nextLine();
            System.out.println("Enter Your Last Name");
            //getting inputs from client 
            memberLastName = input.nextLine();
            System.out.println("Enter Your Address");
            //getting inouts from client 
            memberAddress = input.nextLine();
            System.out.println("Enter Your Phone Number");
            //getting inouts from client 
            phoneNumber = input.nextInt();
            System.out.println("Sending Data To Server");
            String details = memberNumber + ": " + memberFirstName + ": " + memberLastName + ": " + memberAddress + ": " + phoneNumber;
// Print the entered details
            System.out.println("details");
            
            //send member details into the server 
            out.writeInt(memberNumber);
            out.writeUTF(memberFirstName);
            out.writeUTF(memberLastName);
            out.writeUTF(memberAddress);
            out.writeInt(phoneNumber);

            //receieving feedback from the server 
            String feedback = in.readUTF();
            System.out.println("Server Response " + feedback);

// Close the socket
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
