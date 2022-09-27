/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ami_pseudoternario;

import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

/**
 *
 * @author rodrigo
 */
public class Grafico {

    public static int INTERACOES = 100;
    
    //função que irá gerar o gráfico
    public void gerarGrafico(String[] codigo) {
        TimeSeriesCollection ds = new TimeSeriesCollection(); //aqui é onde vou inserir meus dados para gerar o gráfico
        TimeSeries serie = new TimeSeries("Série"); //uma série de dados em função do tempo
        Second segundo = new Second(); //vou utilizar os segundos, por padrão inicializa com tempo atual

        //como é um vetor de Strings, preciso percorrer cada String e então cada char para pegar valor em binário
        for (int i = 0; i < codigo.length; i++) {
            for (int j = 0; j < codigo[i].length(); j++) {
                if (codigo[i].charAt(j) == '0') {
                    for (int y = 0; y < INTERACOES; y++) {
                        serie.add(segundo, 0); //adiciona na série
                        segundo = (Second) segundo.next(); //pula para o próx segundo                 
                    }
                } else if (codigo[i].charAt(j) == '1') {
                    for (int y = 0; y < INTERACOES; y++) {
                        serie.add(segundo, 1);
                        segundo = (Second) segundo.next();
                    }
                } else {
                    for (int y = 0; y < INTERACOES; y++) {
                        serie.add(segundo, -1);
                        segundo = (Second) segundo.next();
                    }
                }
            }
        }
        ds.addSeries(serie); //adiciona a série na base de dados
        JFreeChart grafico = ChartFactory.createTimeSeriesChart("Código de Linha", "Tempo",
                "Valor", ds, true, true, false); //gera o gráfico de tempo
        //aqui de fato que vai para GUI
        JFrame frame = new JFrame("Mensagem Codificada"); //criamos um frame (ver Java.Swing) para mostrar o gráfico
        frame.add(new ChartPanel(grafico)); //adicionamos o gráfico no frame criado
        //frame.setDefaultCloseOperation(EXIT_ON_CLOSE); //setamos a opção de fechar quando clicar no 'x'
        frame.pack(); //organiza os componentes do frame
        frame.setVisible(true); //agora ele irá de fato aparecer
    }
}
