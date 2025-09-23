package sigaa;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

import clientegeral.vista.ClienteAdministradorTerminalApp;
import servicos.interfaces.ServicosAuxiliaresInterface;
import servicos.interfaces.ServicosPrincipaisDoSistemaInterface;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author jailsonlopes & thawilarickel
 */
public class ClienteAdministradorMain {

    public static void main(String[] args) {
        String host = "localhost";
        try {
            Registry registry = LocateRegistry.getRegistry(host);
            ServicosPrincipaisDoSistemaInterface servicosPrincipaisDoSistemaStub = (ServicosPrincipaisDoSistemaInterface) registry.lookup("ServicosClienteAdministrador");
            ServicosAuxiliaresInterface servicosAuxiliaresStub =(ServicosAuxiliaresInterface) registry.lookup("ServicosAuxiliares");
            ClienteAdministradorTerminalApp app = new ClienteAdministradorTerminalApp(servicosPrincipaisDoSistemaStub, servicosAuxiliaresStub);
            app.iniciar();
        } catch (NotBoundException | RemoteException e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
