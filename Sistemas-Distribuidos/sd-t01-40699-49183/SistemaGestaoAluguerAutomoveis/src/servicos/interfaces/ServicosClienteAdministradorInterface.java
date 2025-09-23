/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package servicos.interfaces;

import entidades.Veiculo;
import entidades.tipos.TipoEstadoAdministrativo;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author jailsonlopes
 */
public interface ServicosClienteAdministradorInterface extends Remote{
    public List<Veiculo> listarVeiculosPorEstadoAdministrativo (TipoEstadoAdministrativo estadoAdministrativo) throws RemoteException;
    public void aprovarVeiculo (int veiculoId) throws RemoteException;
    public void naoAprovarVeiculo (int veiculoId) throws RemoteException;
}
