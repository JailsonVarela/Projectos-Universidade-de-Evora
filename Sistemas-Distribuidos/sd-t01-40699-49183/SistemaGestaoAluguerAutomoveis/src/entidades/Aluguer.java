/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.time.Instant;
import java.util.Date;

/**
 *
 * @author jailsonlopes
 */
public class Aluguer extends EntidadeBase {
    private Veiculo veiculo;
    private Cliente cliente;
    private float valorPagamento;
    private Date inicioAluguer;
    private int duracaoPrevista;
    private Date terminoAluguer;


    public Aluguer() {
    }

    public Aluguer(Veiculo veiculo, Cliente cliente, float valorPagamento, Date inicioAluguer, int duracaoPrevista, int id) {
        super(id);
        this.veiculo = veiculo;
        this.cliente = cliente;
        this.valorPagamento = valorPagamento;
        if(inicioAluguer.before(Date.from(Instant.now())))
            this.inicioAluguer = Date.from(Instant.now());
        else
            this.inicioAluguer = inicioAluguer;
        this.duracaoPrevista = duracaoPrevista;
        this.terminoAluguer = new Date(inicioAluguer.getTime() + (duracaoPrevista * 60 * 60 * 1000)); // Ajuste para horas.

    }

    public Aluguer(Veiculo veiculo, Cliente cliente, float valorPagamento, Date inicioAluguer, int duracaoPrevista) {
        this.veiculo = veiculo;
        this.cliente = cliente;
        this.valorPagamento = valorPagamento;
        if(inicioAluguer.before(Date.from(Instant.now())))
            this.inicioAluguer = Date.from(Instant.now());
        else
            this.inicioAluguer = inicioAluguer;
        this.duracaoPrevista = duracaoPrevista;
        this.terminoAluguer = new Date(inicioAluguer.getTime() + (duracaoPrevista * 60 * 60 * 1000));

    }

    public Aluguer(Veiculo veiculo, Cliente cliente, float valorPagamento, Date inicioAluguer, int duracaoPrevista, Date terminoAluguer, int id) {
        super(id);
        this.veiculo = veiculo;
        this.cliente = cliente;
        this.valorPagamento = valorPagamento;
        if(inicioAluguer.before(Date.from(Instant.now())))
            this.inicioAluguer = Date.from(Instant.now());
        else
            this.inicioAluguer = inicioAluguer;
        this.duracaoPrevista = duracaoPrevista;
        this.terminoAluguer = terminoAluguer;
    }

    public Aluguer(Veiculo veiculo, Cliente cliente, float valorPagamento, Date inicioAluguer, int duracaoPrevista, Date terminoAluguer) {
        this.veiculo = veiculo;
        this.cliente = cliente;
        this.valorPagamento = valorPagamento;
        if(inicioAluguer.before(Date.from(Instant.now())))
            this.inicioAluguer = Date.from(Instant.now());
        else
            this.inicioAluguer = inicioAluguer;
        this.duracaoPrevista = duracaoPrevista;
        this.terminoAluguer = terminoAluguer;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public float getValorPagamento() {
        return valorPagamento;
    }

    public void setValorPagamento(float valorPagamento) {
        this.valorPagamento = valorPagamento;
    }

    public Date getInicioALuguer() {
        return inicioAluguer;
    }

    public void setInicioALuguer(Date inicioALuguer) {
        this.inicioAluguer = inicioALuguer;
    }

    public int getDuracaoPrevista() {
        return duracaoPrevista;
    }

    public void setDuracaoPrevista(int duracaoPrevista) {
        this.duracaoPrevista = duracaoPrevista;
    }
    
    public Date getTerminoALuguer() {
        return terminoAluguer;
    }

    public void setTerminoALuguer(Date terminoAluguer) {
        this.terminoAluguer = terminoAluguer;
    }

    @Override
    public String toString() {
        return String.format("Alugado por %s, no dia %s, por %d horas", this.cliente, this.inicioAluguer.toString(), this.duracaoPrevista);
    }
    
}
