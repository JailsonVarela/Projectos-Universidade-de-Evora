package pt.uevora.clienteadministracao.models;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author jailsonlopes
 */
public class PaginacaoDeLista <T> {
    private int paginaAtual = 1;
    private int proximaPagina = 1;
    private int paginaAnterior = 1;
    private int tamanhoDaPagina = 10;
    private List<T> listaDeElementos;
    private final List<T> listaDeTodosElementosNaBD;
    private final int numeroTotalDeElementosNaBD;
    private int indiceInicialDaPagina;
    private int indiceFinalDaPagina;
    
    public PaginacaoDeLista(List<T> listaDeElementos, int paginaAtual, 
            int tamanhoPagina) {
        this.listaDeTodosElementosNaBD = listaDeElementos;
        this.numeroTotalDeElementosNaBD = listaDeElementos.size();
        atualizarPaginamento(paginaAtual, tamanhoPagina);
    }
    
    private void atualizarPaginamento(int paginaAtual, int tamanhoPagina) {
        this.indiceInicialDaPagina = (paginaAtual - 1)*tamanhoPagina;
        this.indiceFinalDaPagina = paginaAtual*tamanhoPagina;
        this.listaDeElementos = new ArrayList<>();
        for(int i=this.indiceInicialDaPagina; i < this.indiceFinalDaPagina && i < this.numeroTotalDeElementosNaBD; ++i) {
            this.listaDeElementos.add(this.listaDeTodosElementosNaBD.get(i));
        }
        this.paginaAtual = paginaAtual;
        this.paginaAnterior = paginaAtual > 1 ? paginaAtual - 1 : paginaAtual;
        this.proximaPagina = indiceFinalDaPagina < this.numeroTotalDeElementosNaBD ? paginaAtual + 1 : paginaAtual;
        this.tamanhoDaPagina = tamanhoPagina;
    }

    public int getPaginaAtual() {
        return paginaAtual;
    }

    public int getProximaPagina() {
        return proximaPagina;
    }

    public int getPaginaAnterior() {
        return paginaAnterior;
    }

    public int getTamanhoDaPagina() {
        return tamanhoDaPagina;
    }

    public List<T> getListaDeElementos() {
        return listaDeElementos;
    }

    public int getNumeroTotalDeElementosNaBD() {
        return numeroTotalDeElementosNaBD;
    }

    public int getIndiceInicialDaPagina() {
        return indiceInicialDaPagina;
    }

    public int getIndiceFinalDaPagina() {
        return indiceFinalDaPagina;
    }
    
    public void avancarPagina() {
        atualizarPaginamento(this.proximaPagina, this.tamanhoDaPagina);
    }
    
    public void retrocederPagina() {
        atualizarPaginamento(this.paginaAnterior, this.tamanhoDaPagina);
    }

    public static <T> PaginacaoDeLista<T> of(List<T> dados) {
        return new PaginacaoDeLista<T>(dados, 1, 10);
    }

    public static <T> PaginacaoDeLista<T> of(List<T> dados, int paginaAtual, int tamanhoDaPagina) {
        return new PaginacaoDeLista<T>(dados, paginaAtual, tamanhoDaPagina);
    }
}
