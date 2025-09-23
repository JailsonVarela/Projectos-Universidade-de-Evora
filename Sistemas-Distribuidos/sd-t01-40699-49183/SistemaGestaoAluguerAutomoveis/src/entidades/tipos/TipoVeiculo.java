/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades.tipos;

/**
 *
 * @author jailsonlopes
 */
public enum TipoVeiculo {
    COMPACTO ("Compacto"), 
    SUV ("SUV"), 
    CAMIONETA("Camioneta");

    private final String label;

    private TipoVeiculo(String label) {
        this.label = label;
    }
    
    public final String label() {
        return this.label;
    }
}
