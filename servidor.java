import java.io.*;
import java.net.*;

//rodar este código primeiro (servidor precisa estar de pé para o cliente conectar)

public class servidor {
    public static void main(String[] args){
        int port=5000;
        try(ServerSocket serversocket=new ServerSocket(port)){
            System.out.println("Servidor está rodando na porta: "+port);
            Socket socket=serversocket.accept();
            System.out.println("Conectados!");

            BufferedReader input=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output=new PrintWriter(socket.getOutputStream(),true);

            String message;

            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

            while((message = input.readLine()) != null){
                System.out.println("Cliente: " + message);


                System.out.print("Servidor: ");
                String reply = userInput.readLine();

                output.println(reply);
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}