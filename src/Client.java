import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args){
        try{
            // Creating Socket
            Socket socket = new Socket("localhost",5500);
            System.out.println("Connection Established Successfully!");
            
            // Creating Scanner to read Server Output
            Scanner serverScanner = new Scanner(socket.getInputStream());
            
            // Creating Scanner to read User Message
            Scanner userScanner = new Scanner(System.in);

            // Creating PrintStream obj to read server response
            PrintStream ps = new PrintStream(socket.getOutputStream());

            // Sending and Receiving Message
            while(true){
                // getting the user input
                System.out.print("Write Message:  ");
                String msg = userScanner.nextLine();

                if(msg.equals("exit")){
                    System.out.println("You Have Ended Session! Thank You");
                    break;
                }

                // sending msg to server
                ps.println(msg);

                // reading the response of server
                String response = serverScanner.nextLine();
                System.out.println("Response Message:   " + response);
                
            }
            // closing the resources
            socket.close();
            serverScanner.close();
            userScanner.close();
            ps.close();
        }
        catch(RuntimeException r){
            System.out.println("Session Ended By The Server! Thank You");
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}