/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ami_pseudoternario;

import javax.swing.JOptionPane;

/**
 *
 * @author rodrigo
 *
 * O AMI funciona assim: SE IGUAL A 0 -> 0 SE IGUAL A 1 -> 1 E PROXIMO 1 SERÁ -1
 * (invertido/bipolar)
 *
 */
public class Ami {

    public String[] coder(String codigo) {
        String[] bin_str = Ami.string2ascii(codigo); //transforma mensagem em binário (ascii)      
        Ami.imprime(bin_str, "Mensagem em binário: "); //imprime mensagem em binário
        String[] codificado = bin_str; //vamos codificar de acordo com AMI
        boolean below = false; //controle do AMI
        for (int i = 0; i < bin_str.length; i++) {
            char[] aux = codificado[i].toCharArray();
            for (int j = 0; j < bin_str[i].length(); j++) {
                if (bin_str[i].charAt(j) == '0') {
                    aux[j] = '0';
                } else {
                    if (below) {
                        aux[j] = '2'; //2 -> seria -1, mas ñ posso colocar em um char
                    } else {
                        aux[j] = '1';
                    }
                    below = !below;
                }
            }
            codificado[i] = String.valueOf(aux); //salva o trecho codificado
        }
        Ami.imprime(codificado, "Mensagem codificada:"); //imprime mensagem codificada em AMI
        return codificado;
    }

    public String decoder(String[] codigo) {
        String[] decodificado = codigo;
        for (int i = 0; i < decodificado.length; i++) { //preciso percorrer cada String e converter para binário
            char[] aux = decodificado[i].toCharArray();
            for (int j = 0; j < decodificado[i].length(); j++) {
                if (codigo[i].charAt(j) == '0') {
                    aux[j] = '0';
                } else {
                    aux[j] = '1';
                }
            }            
            decodificado[i] = String.valueOf(aux); //salvo string decodificada
        }
        Ami.imprime(decodificado, "Mensagem em binário: ");
        return Ami.ascii2string(decodificado); //retorno ela convertida para ascii
    }

    public static String[] string2ascii(String texto) {
        String[] bin_str = new String[texto.length()]; //onde iremos armazenar a mensagem convertidad para binário
        for (int i = 0; i < texto.length(); i++) {
            bin_str[i] = Integer.toBinaryString(texto.charAt(i)); //converte magicamente para binário (ascii)
        }
        return bin_str;
    }
    public static String ascii2string(String[] texto) {
        char[] bin_str = new char[texto.length]; //vamos converter de bin para texto normal
        for (int i = 0; i < texto.length; i++) {
            bin_str[i] = (char) Integer.parseInt(texto[i], 2); //cada char da string será convertido de bin (ascii) para char
        }
        return String.valueOf(bin_str); //retorna em 'forma' de String
    }
    public static void imprime(String[] msg, String label){
        //imprime vetor de string
        String retorno = "";
        for (int i = 0; i < msg.length; i++){
            for (int j = 0; j < msg[i].length(); j++){
                if (msg[i].charAt(j) == '2'){
                    retorno += "-1";
                }
                else{
                    retorno += msg[i].charAt(j);
                }
            }
        }
        JOptionPane.showMessageDialog(null, label + retorno);
    }
}
