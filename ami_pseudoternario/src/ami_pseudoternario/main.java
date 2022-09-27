package ami_pseudoternario;

import java.io.IOException;
import javax.swing.JOptionPane;

/**
 *
 * @author rodrigo
 */
public class main {

    public static int PORT = 6660;
    
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String[] opcoes = {"cliente", "server"}; //para rodar no modo cliente, pelo menos um servidor deve estar rodando na rede
        String resposta = (String) JOptionPane. showInputDialog(null, "Modo de Execução:", 
                "Modo", JOptionPane.QUESTION_MESSAGE, null, opcoes, null); //pede para usuário escolher
        opcoes[0] = "AMI"; //altero as opções
        opcoes[1] = "Pseudoternário";
        String resposta_cod = (String) JOptionPane. showInputDialog(null, "Codificação:", 
                "Código", JOptionPane.QUESTION_MESSAGE, null, opcoes, null); //pede para usuário escolher a codificação
        boolean codificacao = true; //AMI
        if (resposta_cod.equals("Pseudoternário")){
            codificacao = false;
        }
        
        switch (resposta){
                case "cliente": //se for cliente, pede IP e a Mensagem para enviar (porta já está definida)
                    String ip_destino = JOptionPane.showInputDialog(null, "IP Destino");
                    Client client = new Client(ip_destino, PORT);
                    while (true){
                        String msg = JOptionPane.showInputDialog(null, "Qual mensagem deseja enviar?");
                        client.sent_msg(msg, codificacao);
                        int continua = JOptionPane.showConfirmDialog(null, "Deseja continuar?");
                        if (continua != 0)
                            break;
                    }
                    break;
                case "server": //se for server, abre um server na máquina (local) na porta já definida
                    Server server = new Server(PORT);
                    server.run(codificacao);
                    break;
                default:
                    System.out.println("opção inválida!");
                    break;
        }
    
        /******
         * Teste Unitário do AMI
         * 
         */
        /*Ami ami = new Ami();
        String[] retorno;
        retorno = ami.coder("oi");
        //new Grafico().gerarGrafico(retorno);
        //retorno = ami.decoder(retorno);
        //System.out.println(retorno[0]);*/
        //Pseudoternary psd = new Pseudoternary();
        //String[] retorno = psd.coder("oi");
    }   
}
