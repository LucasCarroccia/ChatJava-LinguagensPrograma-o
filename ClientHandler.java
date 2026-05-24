import java.io.*;
import java.net.*;

public class ClientHandler extends Thread{

    private Socket socket;
    private PrintWriter output;

    public ClientHandler(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run(){

        try{

            BufferedReader input= new BufferedReader(new InputStreamReader(socket.getInputStream()));

            output= new PrintWriter(socket.getOutputStream(), true);

            String message;

            while((message = input.readLine()) != null){

                System.out.println("Cliente enviou a mensagem: " + message);
                megafone(message);
            }
        }
        catch (IOException e){
            System.out.println("Cliente desconectou");
        }
    }

    public void enviarMensagem(String message){
        output.println(message);
    }

    public void megafone(String message){

        for(ClientHandler client: servidor.clients){
            if(client != this){
                client.enviarMensagem(message);
            }
        }
    }
}


