/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ami_pseudoternario;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javax.swing.JOptionPane;

/**
 *
 * @author rodrigo
 */
public class Client {

    private final String ip_dest;
    private final int port_dest;

    public Client(String ip_dest, int port_dest) {
        this.ip_dest = ip_dest;
        this.port_dest = port_dest;
    }

    public void sent_msg(String msg, boolean codificacao) throws IOException {
        Socket connection = new Socket(ip_dest, port_dest); //abre uma conexão com o ip_dest na porta port_dest
        JOptionPane.showMessageDialog(null, "Mensagem Enviada: " + msg); //imprime msg original
        String[] codificada;
        if (codificacao){
            Ami ami = new Ami();        
            codificada = ami.coder(msg); //codifica para AMI
        }
        else{
            Pseudoternary psd = new Pseudoternary();
            codificada = psd.coder(msg);
        }
        new Grafico().gerarGrafico(codificada); //gera um gráfico com a mensagem codificada
        
        //abre uma stream para enviar msgs para servidor
        ObjectOutputStream out = new ObjectOutputStream(connection.getOutputStream());
        out.flush();       
        out.writeObject(codificada); //envia para o Server

        //fecha as conexões
        out.close();
        connection.close();
    }
}
