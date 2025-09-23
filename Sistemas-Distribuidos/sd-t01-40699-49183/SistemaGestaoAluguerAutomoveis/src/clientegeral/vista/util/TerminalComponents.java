/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clientegeral.vista.util;

import entidades.tipos.PaginacaoDeLista;
import java.util.List;

/**
 *
 * @author jailsonlopes & thawilarickel
 */
public class TerminalComponents {
    
    private final ESTerminal esTerminal;
    
    public TerminalComponents(ESTerminal esTerminal) {
        this.esTerminal = esTerminal;
    }
    
    public <T> T escolhaUm(String mensagem, List<T> opcoes, int espacos) {
        int escolha;
        do {
            this.esTerminal.escreverMensagem(mensagem + ":", espacos);
            for(int i=0; i < opcoes.size(); ++i) {
                this.esTerminal.escreverMensagem(String.format("%d. %s", i+1, opcoes.get(i)), espacos + 4);
            }
            
            if(opcoes.isEmpty()) {
                this.esTerminal.escreverLinhas(1);
                this.esTerminal.escreverMensagem("(Lista vazia)", espacos + 4);
                this.esTerminal.escreverLinhas(1);
                this.esTerminal.escreverMensagem("0. Sair", espacos + 4);
            }
            
            escolha = this.esTerminal.lerInteiro("Escolha", espacos);
            if(escolha < 0 || (escolha == 0 && !opcoes.isEmpty()) || escolha > opcoes.size()) {
                this.esTerminal.escreverMensagem(String.format("Opção inválida. Insira um número inteiro no intervalo de [%d - %d]",
                                opcoes.isEmpty()? 0 : 1, opcoes.size()), 
                espacos);
                System.out.println();
            }
        } while(escolha < 0 || (escolha == 0 && !opcoes.isEmpty()) || escolha > opcoes.size());
        return escolha > 0 ? opcoes.get(escolha - 1) : null;
    }
    
    public <T> T escolhaUm(String mensagem, PaginacaoDeLista<T> opcoes, int espacos) {
        int escolha;
        do {
            this.esTerminal.escreverMensagem(mensagem + ":", espacos);
            for(int i=0; i < opcoes.getListaDeElementos().size(); ++i) {
                this.esTerminal.escreverMensagem(String.format("%d. %s", i+1, opcoes.getListaDeElementos().get(i)), espacos + 4);
            }
            if(opcoes.getListaDeElementos().isEmpty()) {
                this.esTerminal.escreverLinhas(1);
                this.esTerminal.escreverMensagem("(Lista vazia)", espacos+8);
                this.esTerminal.escreverLinhas(1);
            }
            String msg = String.format("%s%s%s",
                            (opcoes.getListaDeElementos().isEmpty() ? "0. Sair" : ""),
                            (opcoes.getPaginaAtual() < opcoes.getProximaPagina() ? "    -1. Mostrar mais" : ""),
                            (opcoes.getPaginaAtual() > opcoes.getPaginaAnterior()? "    -1. Mostrar anterior" : ""));
            if(!msg.isEmpty())
                this.esTerminal.escreverMensagem(msg, espacos + 4);
            escolha = this.esTerminal.lerInteiro("Escolha", espacos);
            if(escolha < -2 || escolha > opcoes.getListaDeElementos().size() || 
                    (escolha == -1 && opcoes.getPaginaAtual() == opcoes.getProximaPagina()) ||
                    (escolha == -2 && opcoes.getPaginaAtual() == opcoes.getPaginaAnterior())) {
                this.esTerminal.escreverMensagem(
                        String.format("Opção inválida. Insira um número inteiro no intervalo de [%d - %d]",
                                -2, opcoes.getListaDeElementos().size()), 
                espacos);
            }
            if(escolha == -1) {
                opcoes.avancarPagina();
            } else if (escolha == -2) {
                opcoes.retrocederPagina();
            }
        } while(escolha < 0 || escolha > opcoes.getListaDeElementos().size());
        return escolha > 0 ? opcoes.getListaDeElementos().get(escolha - 1) : null;
    }
    
    public <T> void mostrarLista(String titulo, PaginacaoDeLista<T> opcoes, int espacos) {
        int escolha;
        do {
            this.esTerminal.escreverTitulo(titulo);
            this.esTerminal.escreverLinhas(1);
            for(int i=0; i < opcoes.getListaDeElementos().size(); ++i) {
                this.esTerminal.escreverMensagem(String.format("%d. %s", i+1, opcoes.getListaDeElementos().get(i)), espacos + 4);
            }
            if(opcoes.getListaDeElementos().isEmpty()) {
                this.esTerminal.escreverMensagem("(Lista vazia)", espacos+8);
                this.esTerminal.escreverLinhas(1);
            }
            this.esTerminal.escreverMensagem(
                    String.format("0. Sair%s%s",
                            (opcoes.getPaginaAtual() < opcoes.getProximaPagina() ? "    -1. Mostrar mais" : ""),
                            (opcoes.getPaginaAtual() > opcoes.getPaginaAnterior()? "    -1. Mostrar anterior" : "")),
                    4);
            this.esTerminal.escreverLinhas(1);
            escolha = this.esTerminal.lerInteiro("Escolha", espacos);
            if(escolha < -2 || escolha > 0 || 
                    (escolha == -1 && opcoes.getPaginaAtual() == opcoes.getProximaPagina()) ||
                    (escolha == -2 && opcoes.getPaginaAtual() == opcoes.getPaginaAnterior())) {
                this.esTerminal.escreverMensagem(
                        String.format("Opção inválida. Insira um número inteiro no intervalo de [%d - %d]",
                                -2, 0), 
                espacos);
            }
            if(escolha == -1) {
                opcoes.avancarPagina();
            } else if (escolha == -2) {
                opcoes.retrocederPagina();
            }
        } while(escolha != 0);
    }
    
    public <T> void mostrarListaSemTitulo(String mensagem, PaginacaoDeLista<T> opcoes, int espacos) {
        int escolha;
        do {
            this.esTerminal.escreverMensagem(mensagem, 0);
            this.esTerminal.escreverLinhas(1);
            for(int i=0; i < opcoes.getListaDeElementos().size(); ++i) {
                this.esTerminal.escreverMensagem(String.format("%d. %s", i+1, opcoes.getListaDeElementos().get(i)), espacos + 4);
            }
            if(opcoes.getListaDeElementos().isEmpty()) {
                this.esTerminal.escreverMensagem("(Lista vazia)", espacos+8);
                this.esTerminal.escreverLinhas(1);
            }
            this.esTerminal.escreverMensagem(
                    String.format("0. Sair%s%s",
                            (opcoes.getPaginaAtual() < opcoes.getProximaPagina() ? "    -1. Mostrar mais" : ""),
                            (opcoes.getPaginaAtual() > opcoes.getPaginaAnterior()? "    -1. Mostrar anterior" : "")),
                    4);
            this.esTerminal.escreverLinhas(1);
            escolha = this.esTerminal.lerInteiro("Escolha", espacos);
            if(escolha < -2 || escolha > 0 || 
                    (escolha == -1 && opcoes.getPaginaAtual() == opcoes.getProximaPagina()) ||
                    (escolha == -2 && opcoes.getPaginaAtual() == opcoes.getPaginaAnterior())) {
                this.esTerminal.escreverMensagem(
                        String.format("Opção inválida. Insira um número inteiro no intervalo de [%d - %d]",
                                -2, 0), 
                espacos);
            }
            if(escolha == -1) {
                opcoes.avancarPagina();
            } else if (escolha == -2) {
                opcoes.retrocederPagina();
            }
        } while(escolha != 0);
        this.esTerminal.escreverLinhas(1);
    }
}
