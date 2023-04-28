import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    public static void main(String[] args){
        try{
            ServerSocket serverSocket = new ServerSocket(5500);
            Socket socket = serverSocket.accept();

            System.out.println("Client Connected Successfully!");

            // Creating Scanner obj to read msg
            Scanner sc1 = new Scanner(System.in);

            // Creating Scanner obj to read response
            Scanner sc2 = new Scanner(socket.getInputStream());

            // Creating PrintStream obj 
            PrintStream ps = new PrintStream(socket.getOutputStream());

            //Sending and Receiving Msg
            while(true){
                // getting the response from client
                String response = sc2.nextLine();
                System.out.println("Message from Client:    "+response);

                // getting the msg by the user
                System.out.print("Enter Your Message:     ");
                String msg = sc1.nextLine();

                // break statement
                if(msg.equals("exit")){
                    System.out.println("You Have Ended Session! Thank You");
                    break;
                }

                // sending the msg to client
                ps.println(msg);

            }
            // Closing Resources
            serverSocket.close();
            socket.close();
            sc1.close();
            sc2.close();
            ps.close();
        }
        catch(RuntimeException r){
            System.out.println("Session Ended By The Client! Thank You");
        }
        catch(Exception e){
            System.out.println(e);
        }
    }   
}