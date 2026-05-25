import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

// IMPORT NECESSÁRIO PARA ENVIAR MENSAGENS PELA REDE
import java.io.PrintWriter;

public class Chat extends JFrame {

    // PAINEL ONDE AS MENSAGENS SERÃO MOSTRADAS
    JPanel painelMensagens;

    // CAMPO DE TEXTO PARA DIGITAR
    JTextField campoMensagem;

    // BOTÃO DE ENVIAR
    JButton botaoEnviar;

    // GUARDA A CONEXÃO DE SAÍDA
    // ISSO PERMITE ENVIAR MENSAGENS PELO SOCKET
    private PrintWriter output;

    // GUARDA O NOME DA JANELA
    private String nomeUsuario;

    // CONSTRUTOR
    // RECEBE:
    // output -> conexão de rede
    // nomeUsuario -> nome da janela
    public Chat(PrintWriter output, String nomeUsuario) {

        // SALVA A CONEXÃO
        this.output = output;

        // SALVA O NOME
        this.nomeUsuario = nomeUsuario;

        // TÍTULO DA JANELA
        setTitle(nomeUsuario + " - InstantMessenger");

        setSize(600, 855);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        // PAINEL PRINCIPAL
        JPanel painelPrincipal = new JPanel(new BorderLayout());

        painelPrincipal.setBackground(new Color(48,48,48));

        // TOPO

        JPanel topo = new JPanel(new FlowLayout(FlowLayout.LEFT));

        topo.setBackground(new Color(48,48,48));

        topo.setPreferredSize(new Dimension(600,70));

        // NOME NO TOPO
        JLabel nomeChat = new JLabel(nomeUsuario);

        nomeChat.setForeground(Color.WHITE);

        nomeChat.setFont(new Font("Arial", Font.BOLD, 24));

        topo.add(nomeChat);

        painelMensagens = new JPanel();

        painelMensagens.setLayout(
                new BoxLayout(
                        painelMensagens,
                        BoxLayout.Y_AXIS
                )
        );

        painelMensagens.setBackground(new Color(115,108,108));

        JScrollPane scroll = new JScrollPane(painelMensagens);

        scroll.setBorder(null);

        // PARTE INFERIOR

        JPanel painelInferior = new JPanel(new BorderLayout());

        painelInferior.setBorder(
                new EmptyBorder(10,10,10,10)
        );

        painelInferior.setBackground(Color.GRAY);

        // CAMPO DE TEXTO
        campoMensagem = new JTextField();

        campoMensagem.setPreferredSize(
                new Dimension(100,35)
        );

        campoMensagem.setFont(
                new Font("Arial", Font.PLAIN, 18)
        );

        // BOTÃO ENVIAR

        botaoEnviar = new JButton("➤");

        botaoEnviar.setBackground(new Color(48,48,48));

        botaoEnviar.setForeground(Color.WHITE);

        botaoEnviar.setFocusPainted(false);

        // =========================
        // EVENTOS
        // =========================

        // CLICAR NO BOTÃO
        botaoEnviar.addActionListener(
                e -> enviarMensagem()
        );

        // APERTAR ENTER
        campoMensagem.addActionListener(
                e -> enviarMensagem()
        );

        // ADICIONA ELEMENTOS
        painelInferior.add(campoMensagem, BorderLayout.CENTER);

        painelInferior.add(botaoEnviar, BorderLayout.EAST);

        // MONTA O LAYOUT
        painelPrincipal.add(topo, BorderLayout.NORTH);

        painelPrincipal.add(scroll, BorderLayout.CENTER);

        painelPrincipal.add(painelInferior, BorderLayout.SOUTH);

        add(painelPrincipal);

        setVisible(true);
    }

    // MÉTODO RESPONSÁVEL POR ENVIAR MENSAGENS

    private void enviarMensagem() {

        // PEGA O TEXTO DIGITADO
        String texto = campoMensagem.getText();

        // SE ESTIVER VAZIO NÃO ENVIA
        if(texto.isEmpty()) return;

        // ENVIA PELO SOCKET
        output.println(texto);


        // LIMPA O CAMPO
        campoMensagem.setText("");
    }

    // MÉTODO PARA ADICIONAR MENSAGENS
    public void adicionarMensagem(
            String texto,
            boolean minhaMensagem
    ) {

        // PEGA HORÁRIO ATUAL
        String horario = LocalTime.now().format(
                DateTimeFormatter.ofPattern("HH:mm")
        );

        // DEFINE O LADO DA MENSAGEM


        JPanel mensagemPanel;

        if(minhaMensagem){

            // MENSAGEM ENVIADA
            // FICA NA DIREITA
            mensagemPanel = new JPanel(
                    new FlowLayout(FlowLayout.RIGHT)
            );

        } else {

            // MENSAGEM RECEBIDA
            // FICA NA ESQUERDA
            mensagemPanel = new JPanel(
                    new FlowLayout(FlowLayout.LEFT)
            );
        }

        mensagemPanel.setBackground(
                new Color(115,108,108)
        );

        // TEXTO DA MENSAGEM
        JLabel mensagem = new JLabel(
                texto + "   " + horario
        );

        mensagem.setOpaque(true);


        if(minhaMensagem){

            // SUA MENSAGEM
            mensagem.setBackground(
                    new Color(153,154,166)
            );

        } else {

            // MENSAGEM RECEBIDA
            mensagem.setBackground(
                    new Color(230,230,230)
            );
        }

        // BORDA INTERNA
        mensagem.setBorder(
                new EmptyBorder(10,15,10,15)
        );

        // FONTE
        mensagem.setFont(
                new Font("Arial", Font.PLAIN, 16)
        );

        // ADICIONA MENSAGEM NO PAINEL
        mensagemPanel.add(mensagem);

        painelMensagens.add(mensagemPanel);

        // ATUALIZA TELA
        painelMensagens.revalidate();

        painelMensagens.repaint();

        // AUTO SCROLL
        painelMensagens.scrollRectToVisible(
                mensagemPanel.getBounds()
        );
    }
}

