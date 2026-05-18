import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Chat extends JFrame {

    JPanel painelMensagens;
    JTextField campoMensagem;
    JButton botaoEnviar;

    public Chat() {

        setTitle("InstantMessenger");
        setSize(600, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel painelPrincipal = new JPanel(new BorderLayout());
        painelPrincipal.setBackground(new Color(48,48,48));

        //Lista de contatos

        JPanel painelContatos = new JPanel(new BorderLayout());
        painelContatos.setPreferredSize(new Dimension(180,900));
        painelContatos.setBackground(new Color(35,35,35));

        JLabel contatos = new JLabel("Contatos");
        contatos.setForeground(Color.WHITE);
        contatos.setFont(new Font("Arial", Font.BOLD, 20));
        contatos.setBorder(new EmptyBorder(15,15,15,15));

        String[] nomes = {"Caio","Nicolas","Lucas","Samuel"};

        JList<String> lista = new JList<>(nomes);
        lista.setBackground(new Color(50,50,50));
        lista.setForeground(Color.WHITE);
        lista.setFont(new Font("Arial", Font.PLAIN, 18));

        JScrollPane scrollContatos = new JScrollPane(lista);
        scrollContatos.setBorder(null);

        painelContatos.add(contatos, BorderLayout.NORTH);
        painelContatos.add(scrollContatos, BorderLayout.CENTER);

        //Parte de cima

        JPanel topo = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topo.setBackground(new Color(48,48,48));
        topo.setPreferredSize(new Dimension(600,70));

        JLabel nomeChat = new JLabel("InstantMessenger");
        nomeChat.setForeground(Color.WHITE);
        nomeChat.setFont(new Font("Arial", Font.BOLD, 24));

        topo.add(nomeChat);

        //Mensagens

        painelMensagens = new JPanel();
        painelMensagens.setLayout(new BoxLayout(painelMensagens, BoxLayout.Y_AXIS));
        painelMensagens.setBackground(new Color(115,108,108));

        JScrollPane scroll = new JScrollPane(painelMensagens);
        scroll.setBorder(null);

        //Painel de baixo

        JPanel painelInferior = new JPanel(new BorderLayout());
        painelInferior.setBorder(new EmptyBorder(10,10,10,10));
        painelInferior.setBackground(Color.GRAY);

        campoMensagem = new JTextField();
        campoMensagem.setPreferredSize(new Dimension(100,35));
        campoMensagem.setFont(new Font("Arial", Font.PLAIN, 18));

        //Botao

        botaoEnviar = new JButton("➤");
        botaoEnviar.setBackground(new Color(48,48,48));
        botaoEnviar.setForeground(Color.WHITE);
        botaoEnviar.setFocusPainted(false);

        // EVENTOS

        botaoEnviar.addActionListener(e -> enviarMensagem());
        campoMensagem.addActionListener(e -> enviarMensagem());

        painelInferior.add(campoMensagem, BorderLayout.CENTER);
        painelInferior.add(botaoEnviar, BorderLayout.EAST);

        //Layout

        painelPrincipal.add(painelContatos, BorderLayout.WEST);
        painelPrincipal.add(topo, BorderLayout.NORTH);
        painelPrincipal.add(scroll, BorderLayout.CENTER);
        painelPrincipal.add(painelInferior, BorderLayout.SOUTH);

        add(painelPrincipal);

        setVisible(true);
    }

    private void enviarMensagem() {

        String texto = campoMensagem.getText();

        if(texto.isEmpty()) return;

        String horario = LocalTime.now().format(
                DateTimeFormatter.ofPattern("HH:mm")
        );

        JPanel mensagemPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        mensagemPanel.setBackground(new Color(115,108,108));

        JLabel mensagem = new JLabel(texto + "  " + horario);

        mensagem.setOpaque(true);
        mensagem.setBackground(new Color(153,154,166));
        mensagem.setBorder(new EmptyBorder(10,15,10,15));
        mensagem.setFont(new Font("Arial", Font.PLAIN, 16));

        mensagemPanel.add(mensagem);

        painelMensagens.add(mensagemPanel);

        painelMensagens.revalidate();
        painelMensagens.repaint();

        campoMensagem.setText("");
    }
}
