/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades.tipos;

/**
 *
 * @author jailsonlopes
 */
public enum TipoUnidadeDeTempo {
    HORA("Hora"), 
    DIA("Dia"), 
    MES("Mes"), 
    ANO("Ano");
    
    private final String label;

    private TipoUnidadeDeTempo(String label) {
        this.label = label;
    }
    
    public final String label() {
        return this.label;
    }
    
    @Override 
    public String toString() { 
        return this.label; 
    }
}
