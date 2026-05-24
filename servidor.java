import java.io.*;
import java.net.*;
import java.util.ArrayList;

//rodar este código primeiro (servidor precisa estar de pé para o cliente conectar)

public class servidor {

    public static ArrayList<ClientHandler> clients = new ArrayList<>();

    public static void main(String[] args){

        int port=5000;

        try(ServerSocket serversocket=new ServerSocket(port)){

            System.out.println("Servidor está rodando na porta: "+port);

            while(true){
                Socket socket= serversocket.accept();

                System.out.println("Nova conexão");

                ClientHandler clienthandler = new ClientHandler(socket);

                clients.add(clienthandler);

                clienthandler.start();
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}