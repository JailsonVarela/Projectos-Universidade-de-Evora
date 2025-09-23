package sigaa;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

import configuracoes.InicializadorDeDados;
import servicos.ServicosAuxiliares;
import servicos.ServicosPrincipaisDoSistema;
import servicos.interfaces.ServicosAuxiliaresInterface;
import servicos.interfaces.ServicosPrincipaisDoSistemaInterface;

/**
 *
 * @author jailsonlopes & thawilarickel 
 */
public class ServidorMain {
    
    public static Registry registry;

    public static void main(String[] args) {
        int port = 0;
        if(args.length > 0) {
            port = Integer.parseInt(args[0]);
        }
        
        try {
            // Instantiating the implementation class 
           ServicosPrincipaisDoSistema servicosDoSistema = new ServicosPrincipaisDoSistema();
           ServicosAuxiliares servicosAuxiliares = new ServicosAuxiliares();
           InicializadorDeDados.inicializarDados(servicosDoSistema);
           
    
            // Exporting the object of implementation class  
            // (here we are exporting the remote object to the stub) 
            ServicosPrincipaisDoSistemaInterface servicosDoSistemaStub = 
                    (ServicosPrincipaisDoSistemaInterface) UnicastRemoteObject.exportObject(servicosDoSistema, port);  
            
            //ServicosAuxiliaresInterface servicosAuxiliaresStub = (ServicosAuxiliaresInterface) UnicastRemoteObject.exportObject( servicosAuxiliares, 0);  
            //Replaced to:
            ServicosAuxiliaresInterface servicosAuxiliaresStub = 
                    (ServicosAuxiliaresInterface) UnicastRemoteObject.exportObject( servicosAuxiliares, port);  

            // Binding the remote object (stub) in the registry 
            registry = LocateRegistry.getRegistry(); 

            registry.bind("ServicosClienteAdministrador", servicosDoSistemaStub);  
            registry.bind("ServicosClienteGeral", servicosDoSistemaStub); 
            registry.bind("ServicosAuxiliares", servicosAuxiliaresStub); 
            System.out.println("Servidor RMI pronto para receber requisições do cliente iniciado com sucesso."); 
        } catch (AlreadyBoundException | RemoteException e) { 
            System.err.println("Server exception: " + e.toString()); 
        } 
    }
}
