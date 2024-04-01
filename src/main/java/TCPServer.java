
import java.io.*;
import java.net.*;
import java.util.Timer;
import java.util.TimerTask;

public class TCPServer {

    private static int clientCounter = 1;
    private static final int SERVER_PORT = 1188; //my last didgit of student ID

    public static void main(String[] args) {
        try (ServerSocket listenSocket = new ServerSocket(SERVER_PORT)) {
            System.out.println("Server started and now waiting for client details...");

            Timer timer = new Timer();
            timer.schedule(new ConvertMemberDetailsTask(), 0, 2000);

            while (true) {
                Socket clientSocket = listenSocket.accept();
                System.out.println("Receiving data from Client " + clientCounter);
                handleClient(clientSocket, clientCounter);
                clientCounter++;
            }
        } catch (IOException e) {
           
        }
    }

    private static void handleClient(Socket clientSocket, int clientNumber) {
        try (DataInputStream in = new DataInputStream(clientSocket.getInputStream()); DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream())) {

            int memberNumber = in.readInt();
            String memberFirstName = in.readUTF();
            String memberLastName = in.readUTF();
            String memberAddress = in.readUTF();
            String phoneNumber = in.readUTF();

            writeMemberDetailsToFile(memberFirstName, memberLastName, memberAddress, phoneNumber);

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

    private static void writeMemberDetailsToFile(String firstName, String lastName, String address, String phoneNumber) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("memberlist.txt", true))) {
            writer.println(firstName + ":" + lastName + ":" + address + ":" + phoneNumber);
            System.out.println("Member details written to file.");
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    static class ConvertMemberDetailsTask extends TimerTask {

        @Override
        public void run() {
            try (BufferedReader reader = new BufferedReader(new FileReader("memberlist.txt")); ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("memberObjects"))) {

                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(":");
                    if (parts.length != 4) {
                        System.err.println("Invalid line in memberlist.txt: " + line);
                        continue;
                    }
                    try {
                        String memberFirstName = parts[0];
                        String lastName = parts[1];
                        String address = parts[2];
                        String phoneNumber = parts[3];
                        int memberCounter = 0;
                        Member member = new Member(memberCounter++, memberFirstName, lastName, address, phoneNumber);
                        outputStream.writeObject(member);
                    } catch (NumberFormatException e) {
                        System.err.println("Invalid member details: " + line);
                    }
                }

            } catch (IOException e) {
                System.err.println("Error converting member details to Java objects: " + e.getMessage());
            }
        }
    }
}
