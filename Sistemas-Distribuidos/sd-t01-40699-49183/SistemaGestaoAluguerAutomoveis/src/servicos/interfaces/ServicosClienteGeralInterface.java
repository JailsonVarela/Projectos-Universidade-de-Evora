/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package servicos.interfaces;

import entidades.Aluguer;
import entidades.Veiculo;
import entidades.Cliente;
import entidades.tipos.TipoVeiculo;
import java.rmi.Remote;
import java.util.Date;
import java.util.List;
import java.rmi.RemoteException; 

/**
 *
 * @author jailsonlopes
 */
public interface ServicosClienteGeralInterface extends Remote {
    
    public Veiculo registoNovoVeiculo(String matricula, String modelo, TipoVeiculo tipo, String localizacaoAtual)throws RemoteException;
    public Cliente registoCliente(String nome, String documentoIdentificacao, String contacto)throws RemoteException;
    public Aluguer registoNovoAluguer(int veiculoId, int clienteId, float valorPagamento, Date inicioAluguer, int duracaoPrevista)throws RemoteException;
    public List<Veiculo> listarVeiculosDisponiveisParaAluguer() throws RemoteException;
    public List<String> listarLocalizacoesVeiculosAlugados() throws RemoteException;
    public List<Aluguer> consultarHistoricoAluguerUmVeiculoEspecifico(int veiculoId) throws RemoteException;
    
}
