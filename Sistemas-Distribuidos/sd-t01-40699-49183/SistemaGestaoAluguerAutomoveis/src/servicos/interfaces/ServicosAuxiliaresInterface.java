/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package servicos.interfaces;

import java.rmi.RemoteException;
import java.util.List;

import java.rmi.Remote;
import entidades.Cliente;
import entidades.Veiculo;
import entidades.tipos.TipoEstadoAdministrativo;
import entidades.tipos.TipoEstadoAluguer;
import entidades.tipos.TipoUnidadeDeTempo;
import entidades.tipos.TipoVeiculo;

/**
 *
 * @author jailsonlopes
 */
public interface ServicosAuxiliaresInterface extends Remote {
    
    public List<Veiculo> listarVeiculos() throws RemoteException;
    public List<Cliente> listarClientes() throws RemoteException;
    public List<TipoVeiculo> listarTiposDeVeiculos() throws RemoteException;
    public List<TipoEstadoAluguer> listarEstadosDeAluguer() throws RemoteException;
    public List<TipoEstadoAdministrativo> listarEstadosAdministrativos() throws RemoteException;
    public List<TipoUnidadeDeTempo> listarUnidadesDeTempo() throws RemoteException;

}
