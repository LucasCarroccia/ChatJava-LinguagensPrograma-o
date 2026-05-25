import java.io.*;
import java.net.*;

public class ClientHandler extends Thread{

    private Socket socket;
    private PrintWriter output;
    private String nome;

    public ClientHandler(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run(){

        try{

            BufferedReader input= new BufferedReader(new InputStreamReader(socket.getInputStream()));

            output= new PrintWriter(socket.getOutputStream(), true);

            nome = input.readLine();

            System.out.println(nome + " entrou na conversa");

            String message;

            while((message = input.readLine()) != null){

                System.out.println(nome + " enviou a mensagem: " + message);
                megafone(nome+ " disse: "+ message);
            }
        }
        catch (IOException e){
            System.out.println("Cliente" + nome +  "desconectou");
        }
    }

    public void enviarMensagem(String message){
        output.println(message);
    }

    public void megafone(String message){

        for(ClientHandler client: servidor.clients){

            client.enviarMensagem(message);
        }
    }
}


