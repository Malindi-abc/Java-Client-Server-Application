
import java.util.Scanner;
import java.io.*;
import java.net.*;

/**
 *
 * @author malin
 */
public class TCPClient {

    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 1188); DataInputStream in = new DataInputStream(socket.getInputStream()); DataOutputStream out = new DataOutputStream(socket.getOutputStream()); Scanner input = new Scanner(System.in)) {

            // Calling enterMemberDetails method to enter member details
            enterMemberDetails(input, out, in);

        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    //This is my scanner Input used for clients to enter fitness club member details
    private static void enterMemberDetails(Scanner input, DataOutputStream out, DataInputStream in) throws IOException {
        System.out.println("Enter Details for member Number:");
        int memberNumber = input.nextInt();
        input.nextLine(); // Consume newline to get data in next line 
        System.out.println("Enter Your First Name:");
        String memberFirstName = input.nextLine();
        while (memberFirstName.isEmpty()) {
            //print error  
            System.out.println("ERROR Member First name cannot be blank");

            System.out.println("Enter Your First Name");
            memberFirstName = input.nextLine();
        }
        System.out.println("Enter Your Last Name:");
        String memberLastName = input.nextLine();
        while (memberLastName.isEmpty()) {
            //print error  
            System.out.println("ERROR Member Last name cannot be blank");

            System.out.println("Enter Your Last Name");
            memberLastName = input.nextLine();
        }
        System.out.println("Enter Your Address:");
        String memberAddress = input.nextLine();
        System.out.println("Enter Your Phone Number:");
        String phoneNumber = input.nextLine();
        // Validate phone number format
        while (phoneNumber.length() != 10 || !phoneNumber.matches("\\d+")) {
            System.out.println(" Error : Enter Your 10 digit phone Number");
            phoneNumber = input.nextLine();
        }

        // Send data to the server
        out.writeInt(memberNumber);
        out.writeUTF(memberFirstName);
        out.writeUTF(memberLastName);
        out.writeUTF(memberAddress);
        out.writeUTF(phoneNumber);

        System.out.println("Sending Data to Server...");

        // Receive feedback from the server
        String feedback = in.readUTF();
        System.out.println("Server Response: " + feedback);
        System.out.println(".....................................");

        // Call method recursively to enter details for another member(Client)
        enterMemberDetails(input, out, in);
    }
}
