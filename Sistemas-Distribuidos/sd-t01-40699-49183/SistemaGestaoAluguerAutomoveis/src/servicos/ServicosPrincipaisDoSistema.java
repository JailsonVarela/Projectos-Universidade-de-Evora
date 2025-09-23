/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicos;

import entidades.Aluguer;
import entidades.Cliente;
import entidades.Veiculo;
import entidades.tipos.TipoEstadoAdministrativo;
import entidades.tipos.TipoEstadoAluguer;
import entidades.tipos.TipoVeiculo;
import exceptions.EntidadeJaExistenteException;
import exceptions.EntidadeNaoExistenteException;
import exceptions.VeiculoNaoDisponivelException;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import repositorios.RepositorioAlugueres;
import repositorios.RepositorioClientes;
import repositorios.RepositorioVeiculos;
import servicos.interfaces.ServicosPrincipaisDoSistemaInterface;

/**
 *
 * @author jailsonlopes
 */
public class ServicosPrincipaisDoSistema implements ServicosPrincipaisDoSistemaInterface {

    private final RepositorioAlugueres repositorioAlugueres;
    private final RepositorioVeiculos repositorioVeiculos;
    private final RepositorioClientes repositorioClientes;

    public ServicosPrincipaisDoSistema(RepositorioAlugueres repositorioAlugueres, RepositorioVeiculos repositorioVeiculos, RepositorioClientes repositorioClientes) {
        this.repositorioAlugueres = repositorioAlugueres;
        this.repositorioVeiculos = repositorioVeiculos;
        this.repositorioClientes = repositorioClientes;
    }

    public ServicosPrincipaisDoSistema() {
        this.repositorioAlugueres = new RepositorioAlugueres();
        this.repositorioClientes = new RepositorioClientes();
        this.repositorioVeiculos = new RepositorioVeiculos();
    }

    @Override
    public List<Veiculo> listarVeiculosPorEstadoAdministrativo(TipoEstadoAdministrativo estadoAdministrativo) throws RemoteException {
        return this.repositorioVeiculos.listar().stream()
                .filter((Veiculo veiculo) -> veiculo.getEstadoAdministrativo().equals(estadoAdministrativo))
                .collect(Collectors.toList());
        }

    @Override
    public void aprovarVeiculo(int veiculoId) throws EntidadeNaoExistenteException {

        Veiculo veiculo = this.repositorioVeiculos.consultarPorId(veiculoId);
        if(veiculo == null)
            throw new EntidadeNaoExistenteException("O veículo a aprovar não existe");
        if(veiculo.getEstadoAdministrativo().equals(TipoEstadoAdministrativo.NAO_APROVADO)) {
            veiculo.setEstadoAdministrativo(TipoEstadoAdministrativo.APROVADO);
            this.repositorioVeiculos.editar(veiculo);
        }
    }

    @Override
    public void naoAprovarVeiculo(int veiculoId) throws RemoteException {

        Veiculo veiculo = this.repositorioVeiculos.consultarPorId(veiculoId);
        if(veiculo == null)
            throw new EntidadeNaoExistenteException("O veículo a aprovar não existe");
        if(veiculo.getEstadoAdministrativo().equals(TipoEstadoAdministrativo.APROVADO)){
            veiculo.setEstadoAdministrativo(TipoEstadoAdministrativo.NAO_APROVADO);
            this.repositorioVeiculos.editar(veiculo);
        }
    }

    @Override
    public Veiculo registoNovoVeiculo(String matricula, String modelo, TipoVeiculo tipo, String localizacaoAtual) throws EntidadeJaExistenteException {

        Veiculo veiculo = this.repositorioVeiculos.consultaPorMatricula(matricula);
        if(veiculo != null)
            throw new EntidadeJaExistenteException("O veículo com a mesma matricula já existe");
        veiculo = new Veiculo(matricula, modelo, tipo, localizacaoAtual);
        return this.repositorioVeiculos.salvar(veiculo);
    }

    @Override
    public Cliente registoCliente(String nome, String documentoIdentificacao, String contacto) throws EntidadeJaExistenteException {

        Cliente cliente = this.repositorioClientes.consultarPorDocumentoIdentificacao(documentoIdentificacao);
        if (cliente != null)
            throw new EntidadeJaExistenteException("O Cliente com o nome " + nome + " já existe!");
        cliente = new Cliente(nome, documentoIdentificacao, contacto);
        return this.repositorioClientes.salvar(cliente);
    
    }

    @Override
    public Aluguer registoNovoAluguer(int veiculoId, int clienteId, float valorPagamento, Date inicioAluguer, int duracaoPrevista) throws EntidadeNaoExistenteException, VeiculoNaoDisponivelException {

        Cliente cliente = this.repositorioClientes.consultarPorId(clienteId);
        if (cliente == null)
            throw new EntidadeNaoExistenteException("O cliente não existe.");
        
        Veiculo veiculo = this.repositorioVeiculos.consultarPorId(veiculoId);
        if(veiculo == null)
            throw new EntidadeNaoExistenteException("O veiculo não existe.");
        
        if(!veiculo.getEstadoAluguer().equals(TipoEstadoAluguer.DISPONIVEL) || veiculo.getEstadoAdministrativo().equals(TipoEstadoAdministrativo.NAO_APROVADO) )
            throw new VeiculoNaoDisponivelException("O veiculo não está disponivel");
        
        Aluguer aluguer = new Aluguer(veiculo, cliente, valorPagamento, inicioAluguer, duracaoPrevista);
        aluguer = this.repositorioAlugueres.salvar(aluguer);
        veiculo.setEstadoAluguer(TipoEstadoAluguer.ALUGADO);
        this.repositorioVeiculos.editar(veiculo);
        return aluguer;
    
    }

    @Override
    public List<Veiculo> listarVeiculosDisponiveisParaAluguer() {

        return this.repositorioVeiculos.listar().stream()
                .filter((Veiculo veiculo) -> veiculo.getEstadoAluguer().equals(TipoEstadoAluguer.DISPONIVEL) && veiculo.getEstadoAdministrativo().equals(TipoEstadoAdministrativo.APROVADO))
                .collect(Collectors.toList());
    
    }

    @Override
    public List<String> listarLocalizacoesVeiculosAlugados() {

        List<Veiculo> listaVeiculosAlugado = this.repositorioVeiculos.listar().stream().filter((Veiculo veiculo) -> veiculo.getEstadoAluguer().equals(TipoEstadoAluguer.ALUGADO)).collect(Collectors.toList());
        return listaVeiculosAlugado.stream().map(veiculo -> String.format("%s (%s - #%s)", veiculo.getLocalizacaoAtual(), veiculo.getModelo(), veiculo.getMatricula()))
                .collect(Collectors.toList());
    
    }

    @Override
    public List<Aluguer> consultarHistoricoAluguerUmVeiculoEspecifico(int veiculoId) throws EntidadeNaoExistenteException {

        Veiculo veiculo = this.repositorioVeiculos.consultarPorId(veiculoId);
        if(veiculo == null)
            throw new EntidadeNaoExistenteException("O veiculo não existe.");
        return this.repositorioAlugueres.listar().stream()
                .filter((Aluguer aluguer) -> aluguer.getVeiculo().getId() == veiculoId)
                .collect(Collectors.toList());
    
    }
    
}
