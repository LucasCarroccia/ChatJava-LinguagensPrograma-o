import javax.swing.*;
import java.io.*;
import java.net.*;

public class ClienteGUI {

    public static void main(String[] args){

        String endereço = "127.0.0.1";
        int port = 5000;

        try {
            Socket socket = new Socket(endereço, port);

            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            PrintWriter output= new PrintWriter(socket.getOutputStream(),true);

            String nomeUser= JOptionPane.showInputDialog(null, "Digite seu nome:");

            output.println(nomeUser);

            Chat chat = new Chat(output, nomeUser);

            Thread receberThread= new Thread(() -> {

                try {

                    String mensagemServer;

                    while((mensagemServer = input.readLine()) != null){

                        boolean minhaMSG = mensagemServer.startsWith(nomeUser + ":");

                        chat.adicionarMensagem(mensagemServer, minhaMSG);

                    };

                }
                catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "COnexão encerarda");
                }
            });
            receberThread.start();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}

