/*import  javax.swing . * ;
import  javax.swing.border.EmptyBorder ;​​​​​​
import  java.awt . * ;
import  java.time.LocalTime ;​​​​
import  java.time.format.DateTimeFormatter ;​​​​​​

// IMPORT NECESSÁRIO PARA ENVIAR MENSAGENS PELA REDE
importar  java . ei . PrintWriter ;

public  class  Chat  extends  JFrame {

    // PAINEL ONDE AS MENSAGENS SERÃO MOSTRADAS
    JPanel  painelMensagens ;

    // CAMPO DE TEXTO PARA DIGITAR
    JTextField  campoMensagem ;

    // BOTÃO DE ENVIAR
    JButton  botaoEnviar ;

    // GUARDA A CONEXÃO DE SAÍDA
    // ISSO PERMITE ENVIAR MENSAGENS PELO SOCKET
     Saída PrintWriter  privada ;

    // GUARDA O NOME DA JANELA
     string  privada nomeUsuario ;

    // CONSTRUTOR
    // RECEBER:
    // saída -> bombe de rede
    // nomeUsuario -> nome da janela
    público  Chat ( PrintWriter  saída , String  nomeUsuario ) {

        // SALVA A CONEXÃO
        isto.saída = saída ;​​

        // SALVA O NOME
        este . nomeUsuario = nomeUsuario ;

        // TÍTULO DA JANELA
        setTitle ( nomeUsuario + " - InstantMessenger" );

        setSize ( 600 , 855 );

        setDefaultCloseOperation ( JFrame . EXIT_ON_CLOSE );

        definirLocalizaçãoRelativaA ( nulo );

        setLayout ( novo  BorderLayout ());

        // PRINCIPAL DO PAINEL
        JPanel  painelPrincipal = new  JPanel ( new  BorderLayout ());

        painelPrincipal.setBackground ( new Color ( 48 , 48 , 48 ) ) ; 

        // TOPO

        JPanel  topo = new  JPanel ( new  FlowLayout ( FlowLayout . LEFT ));

        topo.setBackground ( new Color ( 48 , 48 , 48 ) ) ; 

        topo.setPreferredSize ( new Dimension ( 600 , 70 ) ) ; 

        // NOME NO TOPO
        JLabel  nomeChat = new  JLabel ( nomeUsuario );

        nomeChat.setForeground ( Color.WHITE ) ;​​​

        nomeChat.setFont ( new Font ( " Arial " , Font.BOLD , 24 ) ) ; 

        topo.adicionar ( nomeChat ) ;​

        painelMensagens = new  JPanel ();

        painelMensagens . setLayout (
                novo  BoxLayout (
                        painelMensagens ,
                        BoxLayout . Eixo Y
                )
        );

        painelMensagens . setBackground ( nova  Cor ( 115 , 108 , 108 ));

        JScrollPane  scroll = new  JScrollPane ( painelMensagens );

        scroll.setBorder ( null ) ;​

        // PARTE INFERIOR

        JPanel  painelInferior = novo  JPanel ( novo  BorderLayout ());

        painelInferior . definirBorda (
                novo  EmptyBorder ( 10 , 10 , 10 , 10 )
        );

        painelInferior.setBackground ( Color.GRAY ) ;​​​

        // CAMPO DE TEXTO
        campoMensagem = novo  JTextField ();

        campoMensagem . setPreferredSize (
                nova  Dimensão ( 100 , 35 )
        );

        campoMensagem . setFont (
                nova  fonte ( "Arial" , Fonte . PLAIN , 18 )
        );

        // BOTÃO ENVIAR

        botaoEnviar = new  JButton ( "➤" );

        botaoEnviar.setBackground ( new Color ( 48 , 48 , 48 ) ) ; 

        botaoEnviar.setForeground ( Color.WHITE ) ;​​​

        botaoEnviar.setFocusPainted ( false ) ;​

        // =========================
        // EVENTOS
        // =========================

        // CLICAR NO BOTÃO
        botaoEnviar . addActionListener (
                e -> enviarMensagem ()
        );

        // APERTAR ENTER
        campoMensagem . adicionarOuvinteDeAção (
                e -> enviarMensagem ()
        );

        // ADICIONA ELEMENTOS
        painelInferior . add ( campoMensagem , BorderLayout.CENTER ) ;​

        painelInferior . add ( botaoEnviar , BorderLayout . EAST );

        // MONTA O LAYOUT
        painelPrincipal.add ( topo , BorderLayout.NORTH ) ;​​​

        painelPrincipal.add ( scroll , BorderLayout.CENTER ) ;​​​

        painelPrincipal . add ( painelInferior , BorderLayout . SOUTH );

        adicionar ( painelPrincipal );

        definirVisível ( verdadeiro );
    }

    // MÉTODO RESPONSÁVEL POR ENVIAR MENSAGENS

    private  void  enviarMensagem () {

        // PEGA O TEXTO DIGITAL
        String  texto = campoMensagem . getText ();

        // SE ESTIVER VAZIO NÃO ENVIA
        se ( texto.isEmpty ( ) ) retorne ;

        // ENVIA PELO SOCKET
        saída.println ( texto ) ;​

        // MOSTRA NA TELA
        // true = mensagem enviada por mim
        adicionarMensagem (
                nomeUsuario + ": " + texto ,
                verdadeiro
        );

        // LIMPA O CAMPO
        campoMensagem . setText ( "" );
    }

    // MÉTODO PARA ADICIONAR MENSAGENS
    public  void  adicionarMensagem (
             Texto em string ,
            boolean  minhaMensagem
    ) {

        // PEGA HORÁRIO ATUAL
        String  horario = LocalTime.now ( ) . format (
                FormatadorDataHora.dePadrão ( "HH: mm " )
        );

        // DEFINIR O LADO DA MENSAGEM


        JPanel  mensagemPanel ;

        se ( minhaMensagem ){

            // MENSAGEM ENVIADA
            // FICA NA DIREITA
            mensagemPanel = novo  JPanel (
                    novo  FlowLayout ( FlowLayout . DIREITA )
            );

        } outro {

            // MENSAGEM RECEBIDA
            // FICA NA ESQUERDA
            mensagemPanel = novo  JPanel (
                    novo  FlowLayout ( FlowLayout . LEFT )
            );
        }

        mensagemPanel . definirBackground (
                nova  cor ( 115 , 108 , 108 )
        );

        // TEXTO DA MENSAGEM
        JLabel  mensagem = new  JLabel (
                texto + " " + horário
        );

        mensagem . setOpaque ( verdadeiro );


        se ( minhaMensagem ){

            // SUA MENSAGEM
            mensagem . setBackground (
                    nova  cor ( 153 , 154 , 166 )
            );

        } outro {

            // MENSAGEM RECEBIDA
            mensagem . setBackground (
                    nova  cor ( 230 , 230 , 230 )
            );
        }

        // BORDA INTERNA
        mensagem . definirBorda (
                novo  EmptyBorder ( 10 , 15 , 10 , 15 )
        );

        // FONTE
        mensagem . definirFonte (
                nova  fonte ( "Arial" , Fonte . PLAIN , 16 )
        );

        // ADICIONA MENSAGEM NO PAINEL
        mensagemPanel . adicionar ( mensagem );

        painelMensagens . adicionar ( mensagemPanel );

        // ATUALIZAR TELA
        painelMensagens . revalidar ();

        painelMensagens . repintar ();

        // ROLAGEM AUTOMÁTICA
        painelMensagens . scrollRectToVisible (
                mensagemPanel.getBounds ( )​
        );
    }
}

 */