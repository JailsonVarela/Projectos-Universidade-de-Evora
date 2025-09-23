/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clientegeral.vista.util;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author jailsonlopes & thawilarickel
 */
public class ESTerminal {
    private final BufferedReader entrada;
    private final int TAMANHO_DA_LINHA = 76;
    
    public ESTerminal() {
        this.entrada = new BufferedReader( new InputStreamReader(System.in) );
    }
    
    public int lerInteiro(String rotulo, int numeroDeEspacosAEsquerda) {
        String valorString = lerString(rotulo, numeroDeEspacosAEsquerda);
        int valor;
        try {
            valor = Integer.parseInt(valorString);
        } catch(NumberFormatException nfe){
            return -1;
        }
        return valor;
    }
    
    public int lerInteiro(String rotulo, int numeroDeEspacos, boolean aceitaPadrao) {
        String valorString = this.lerString(rotulo, numeroDeEspacos, aceitaPadrao);
        valorString = this.valorPadraoSeStringVazia(valorString);
        int valor = Integer.parseInt(valorString);
        return valor;
    }

    public double lerDouble(String rotulo, int numeroDeEspacosAEsquerda) {
        String valorString = lerString(rotulo, numeroDeEspacosAEsquerda);
        double valor = Double.parseDouble(valorString);
        return valor;
    }

    public double lerDouble(String rotulo, int numeroDeEspacos, boolean aceitaPadrao) {
        String valorString = this.lerString(rotulo, numeroDeEspacos, aceitaPadrao);
        valorString = this.valorPadraoSeStringVazia(valorString);
        double valor = Double.parseDouble(valorString);
        return valor;
    }
    
    public Date lerData(String rotulo, int numeroDeEspacos) {
        Date data = null;
        DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        do {
            try {
                String dateString = lerString(rotulo + "(dia-mês-ano)", numeroDeEspacos);
                data = format.parse(dateString);
            } catch (ParseException ex) {
                escreverMensagem("Formato de data inválido. A data deve estar formatada na forma dia-mês-ano.", 0);
            }
        } while(data == null);
        return data;
    }
    
    public String lerString(String rotulo, int numeroDeEspacosAEsquerda) {
        return this.lerString(rotulo, numeroDeEspacosAEsquerda, true);
    }

    public String lerString(String rotulo, int numeroDeEspacos, boolean aceitaStringVazia) {
        String valor = "";
        try {
            do {
                escreverEspacos(numeroDeEspacos); 
                System.out.print(rotulo + ": ");
                valor = entrada.readLine();
                if(aceitaStringVazia && valor.isEmpty())
                    break;
            } while(valor.isEmpty());
        } catch (IOException e) {}
        return valor;
    }
    
    private String valorPadraoSeStringVazia(String valor) {
        if(valor.isEmpty())
            return "0";
        return valor;
    }
    
    public void escreverEspacos(int numeroDeEspacos) {
        for(int i=0; i < numeroDeEspacos; ++i)
            System.out.print(" ");
    }
    
    public void escreverMensagem(String mensagem, int numeroDeEspacosDeDervio) {
        escreverEspacos(numeroDeEspacosDeDervio);
        System.out.println(mensagem);
    }
    
    public void escreverTitulo(String titulo) {
        int numeroTotalDeEspacosPorPreencher = TAMANHO_DA_LINHA - titulo.length();
        int numeroDeEspacosAEsquerda = numeroTotalDeEspacosPorPreencher / 2 - 1;
        int numeroDeEspacosADireita = (numeroTotalDeEspacosPorPreencher / 2 - 1) + (numeroTotalDeEspacosPorPreencher % 2);
        escreverTracos(TAMANHO_DA_LINHA);
        System.out.print("|");
        escreverEspacos(numeroDeEspacosAEsquerda);
        System.out.print(titulo);
        escreverEspacos(numeroDeEspacosADireita);
        System.out.print("|\n");
        escreverTracos(TAMANHO_DA_LINHA);
    }
    
    public void escreverTracos(int numberOfTraits) {
        for(int i=0; i < numberOfTraits; ++i) {
            System.out.print("-");
        }
        System.out.println();
    }
    
    public void escreverLinhas(int numberOfLines) {
        for(int i=0; i < numberOfLines; ++i) {
            System.err.println();
        }
    }
}
