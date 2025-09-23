package sigaa;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

import clientegeral.vista.ClienteGeralTerminalApp;
import servicos.interfaces.ServicosAuxiliaresInterface;
import servicos.interfaces.ServicosClienteGeralInterface;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author jailsonlopes & thawilarickel
 */
public class ClienteGeralMain {

    public static void main(String[] args) {
        String host = "localhost";
        int port = 0;
        if(args.length > 1) {
            host = args[0];
            port = Integer.parseInt(args[1]);
        }
        try {
            Registry registry = LocateRegistry.getRegistry(host, port);
            ServicosClienteGeralInterface servicosClienteGeralStub = (ServicosClienteGeralInterface) registry.lookup("ServicosClienteGeral");
            ServicosAuxiliaresInterface servicosAuxiliaresStub =(ServicosAuxiliaresInterface) registry.lookup("ServicosAuxiliares");
            ClienteGeralTerminalApp app = new ClienteGeralTerminalApp(servicosClienteGeralStub, servicosAuxiliaresStub);
            app.iniciar();
        } catch (NotBoundException | RemoteException e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
