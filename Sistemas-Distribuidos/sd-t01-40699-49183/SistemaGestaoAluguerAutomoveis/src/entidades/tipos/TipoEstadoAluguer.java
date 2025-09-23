/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades.tipos;

/**
 *
 * @author jailsonlopes
 */
public enum TipoEstadoAluguer {
    DISPONIVEL("Disponível"), 
    ALUGADO("Alugado"), 
    EM_MANUTENCAO("Em manutenção");
    
    private final String label;

    private TipoEstadoAluguer(String label) {
        this.label = label;
    }
    
    public final String label() {
        return this.label;
    }
}