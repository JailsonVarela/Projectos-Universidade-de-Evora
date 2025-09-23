/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicos;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.List;

import entidades.Cliente;
import entidades.Veiculo;
import entidades.tipos.TipoEstadoAdministrativo;
import entidades.tipos.TipoEstadoAluguer;
import entidades.tipos.TipoUnidadeDeTempo;
import entidades.tipos.TipoVeiculo;
import repositorios.RepositorioClientes;
import repositorios.RepositorioVeiculos;
import servicos.interfaces.ServicosAuxiliaresInterface;

/**
 *
 * @author jailsonlopes
 */
public class ServicosAuxiliares implements ServicosAuxiliaresInterface, Remote{

    private final RepositorioVeiculos repositorioVeiculos;
    private final RepositorioClientes repositorioClientes;

    public ServicosAuxiliares(RepositorioVeiculos repositorioVeiculos, RepositorioClientes repositorioClientes) {
        this.repositorioVeiculos = repositorioVeiculos;
        this.repositorioClientes = repositorioClientes;
    }

    public ServicosAuxiliares() {
        this.repositorioVeiculos = new RepositorioVeiculos();
        this.repositorioClientes = new RepositorioClientes();
    }
    
    
    @Override
    public List<Veiculo> listarVeiculos() throws RemoteException {
        return this.repositorioVeiculos.listar();
    }

    @Override
    public List<Cliente> listarClientes() throws RemoteException {
        return this.repositorioClientes.listar();
    }

    @Override
    public List<TipoVeiculo> listarTiposDeVeiculos() throws RemoteException {
        return Arrays.asList(TipoVeiculo.values());
    }

    @Override
    public List<TipoEstadoAluguer> listarEstadosDeAluguer() throws RemoteException {
        return Arrays.asList(TipoEstadoAluguer.values());
    }

    @Override
    public List<TipoEstadoAdministrativo> listarEstadosAdministrativos() throws RemoteException {
        return Arrays.asList(TipoEstadoAdministrativo.values());
    }

    @Override
    public List<TipoUnidadeDeTempo> listarUnidadesDeTempo() throws RemoteException {
        return Arrays.asList(TipoUnidadeDeTempo.values());
    }
    
}
