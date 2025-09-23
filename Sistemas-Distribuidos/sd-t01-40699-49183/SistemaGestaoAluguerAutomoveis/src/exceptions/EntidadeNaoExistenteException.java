/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exceptions;
import java.rmi.RemoteException;

/**
 *
 * @author jailsonlopes
 */
public class EntidadeNaoExistenteException extends RemoteException {

    public EntidadeNaoExistenteException(String message) {
        super(message);
    }
    
}
