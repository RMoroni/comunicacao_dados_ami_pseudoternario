/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ami_pseudoternario;

/**
 *
 * @author rodrigo
 */
public class Pseudoternary {

    public String[] coder(String codigo) {
        String[] bin_str = Ami.string2ascii(codigo); //transforma mensagem em binário (ascii)
        Ami.imprime(bin_str, "Mensagem em binário:"); //imprime mensagem em binário
        String[] codificado = bin_str; //vamos codificar de acordo com Pseudoternário
        boolean below = false; //controle do pseudo
        for (int i = 0; i < bin_str.length; i++) {
            char[] aux = codificado[i].toCharArray();
            for (int j = 0; j < bin_str[i].length(); j++) {
                if (bin_str[i].charAt(j) == '1') {
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
        System.out.println("Mensagem codificada:");
        Ami.imprime(codificado, "Mensagem codificada:"); //imprime mensagem codificada em Pseudoternario
        return codificado;
    }

    public String decoder(String[] codigo) {
        String[] decodificado = codigo;
        for (int i = 0; i < decodificado.length; i++) { //preciso percorrer cada String e converter para binário
            char[] aux = decodificado[i].toCharArray();
            for (int j = 0; j < decodificado[i].length(); j++) {
                if (codigo[i].charAt(j) == '0') { //contrário do AMI
                    aux[j] = '1';
                } else {
                    aux[j] = '0';
                }
            }            
            decodificado[i] = String.valueOf(aux); //salvo string decodificada
        }
        Ami.imprime(decodificado, "Mensagem em binário: ");
        return Ami.ascii2string(decodificado); //retorno ela convertida para ascii
    }
}
