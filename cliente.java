import java.io.*;
import java.net.*;

public class cliente {
    public static void main(String[] args){
        String serverAddress ="127.0.0.1";
        //Port local
        int port=5000;

        try(Socket socket=new Socket(serverAddress,port)) {
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Conectado ao servidor. Digite mensagens:");
            String message;

            while ((message = userInput.readLine()) != null) {
                output.println(message);
                System.out.println("Servidor: " + input.readLine());
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }

    }
}