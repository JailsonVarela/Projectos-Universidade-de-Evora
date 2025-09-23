/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;
import entidades.tipos.*;
        
/**
 *
 * @author jailsonlopes
 */
public class Veiculo extends EntidadeBase{
    private String matricula;
    private String modelo;
    private TipoVeiculo tipo;
    private String localizacaoAtual;
    private TipoEstadoAluguer estadoAluguer;
    private TipoEstadoAdministrativo estadoAdministrativo;

    public Veiculo(String matricula, String modelo, TipoVeiculo tipo, String localizacaoAtual) {
        this.matricula = matricula;
        this.modelo = modelo;
        this.tipo = tipo;
        this.localizacaoAtual = localizacaoAtual;
        this.estadoAluguer = TipoEstadoAluguer.DISPONIVEL;
        this.estadoAdministrativo = TipoEstadoAdministrativo.NAO_APROVADO;
    }

    public Veiculo() {
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public TipoVeiculo getTipo() {
        return tipo;
    }

    public void setTipo(TipoVeiculo tipo) {
        this.tipo = tipo;
    }

    public String getLocalizacaoAtual() {
        return localizacaoAtual;
    }

    public void setLocalizacaoAtual(String localizacaoAtual) {
        this.localizacaoAtual = localizacaoAtual;
    }

    public TipoEstadoAluguer getEstadoAluguer() {
        return estadoAluguer;
    }

    public void setEstadoAluguer(TipoEstadoAluguer estadoAluguer) {
        this.estadoAluguer = estadoAluguer;
    }

    public TipoEstadoAdministrativo getEstadoAdministrativo() {
        return estadoAdministrativo;
    }

    public void setEstadoAdministrativo(TipoEstadoAdministrativo estadoAdministrativo) {
        this.estadoAdministrativo = estadoAdministrativo;
    }
    
    @Override
    public String toString() {
        return String.format("%s (#%s)", this.modelo, this.matricula);
    }
}
