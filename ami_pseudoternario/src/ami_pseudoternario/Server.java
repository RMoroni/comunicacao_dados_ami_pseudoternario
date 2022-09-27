/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ami_pseudoternario;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JOptionPane;

/**
 *
 * @author rodrigo
 */
public class Server {   

    private final ServerSocket server;
    
    public Server(int port) throws IOException {
        server = new ServerSocket(port); //inicia o server escutando na porta especificada
    }

    public void run(boolean codificacao) throws IOException, ClassNotFoundException {
        System.out.println("Server start!");
        while (true) {
            //espera por uma conexão TCP...
            Socket connection = server.accept();
            System.out.println("Conexão aberta com: " + connection.getInetAddress().getHostName());

            //abre um 'meio' para receber dados da conexão(cliente)
            ObjectInputStream input = new ObjectInputStream(connection.getInputStream());

            //recebe um objeto (msg) enviado pelo cliente, no caso aqui que o algoritmo deve atuar               
            String[] msg = (String[]) input.readObject();
            String decodificada;
            //System.out.print("Mensagem Recebida: ");
            Ami.imprime(msg, "Mensagem recebida: ");
            new Grafico().gerarGrafico(msg); //gera um gráfico com a mensagem recebida  
            if (codificacao){
                Ami ami = new Ami();            
                decodificada = ami.decoder(msg); //decodifica  
            }
            else{
                Pseudoternary psd = new Pseudoternary();
                decodificada = psd.decoder(msg);
            }           
            JOptionPane.showMessageDialog(null, "Mensagem Decodificada: " + decodificada); //imprime mensagem decodificada
            
            //fecha as conexões
            input.close();
            connection.close();
            System.out.println("Conexão com " + connection.getInetAddress().getHostName() + " finalizada!");
        }
    }
}