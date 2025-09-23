/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades.tipos;

/**
 *
 * @author jailsonlopes
 */
public enum TipoEstadoAdministrativo {
    APROVADO ("Aprovado"), 
    NAO_APROVADO ("Não aprovado");
    
    private final String label;

    private TipoEstadoAdministrativo(String label) {
        this.label = label;
    }
    
    public final String label() {
        return this.label;
    }
} 