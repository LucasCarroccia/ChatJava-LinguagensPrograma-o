import java.io.*;
import java.net.*;

public class cliente {
    public static void main(String[] args){
        String serverAddress ="127.0.0.1";
        //endereço local
        int port=5000;

        try(Socket socket=new Socket(serverAddress,port)) {

            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Conectado ao servidor. Digite seu nome:");

            String nome = userInput.readLine();

            output.println(nome);

            System.out.println("Olá "+ nome+ ", Comece a conversar\n");

            Thread receiveThread = new Thread(() -> {
                try{
                    String serverMessage;

                    while((serverMessage = input.readLine()) != null){
                        System.out.println(serverMessage);
                    }
                }
                catch(IOException e){
                    System.out.println("Conexão encerrada");
                }
            });

            receiveThread.start();

            String message;

            while ((message = userInput.readLine()) != null) {
                output.println(message);
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}