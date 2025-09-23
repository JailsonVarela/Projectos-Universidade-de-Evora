/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clientegeral.vista;

import entidades.Aluguer;
import entidades.Cliente;
import entidades.Veiculo;
import entidades.tipos.PaginacaoDeLista;
import entidades.tipos.TipoUnidadeDeTempo;
import entidades.tipos.TipoVeiculo;
import exceptions.EntidadeJaExistenteException;
import exceptions.EntidadeNaoExistenteException;
import exceptions.VeiculoNaoDisponivelException;
import servicos.interfaces.ServicosAuxiliaresInterface;
import com.uevora.sistemagestaoaluguer.clientegeral.vista.interfaces.TerminalAppInterface;
import servicos.interfaces.ServicosPrincipaisDoSistemaInterface;
import clientegeral.vista.util.ESTerminal;
import clientegeral.vista.util.TerminalComponents;
import entidades.tipos.TipoEstadoAdministrativo;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jailsonlopes & thawilarickel
 */
public class ClienteAdministradorTerminalApp implements TerminalAppInterface {
    private final ServicosPrincipaisDoSistemaInterface servicosPrincipaisDoSistemaInterface;
    private final ServicosAuxiliaresInterface servicosAuxiliares;
    private final ESTerminal esTerminal;
    private final TerminalComponents terminalComponents;
    
    public ClienteAdministradorTerminalApp(ServicosPrincipaisDoSistemaInterface servicosPrincipaisDoSistemaInterface, ServicosAuxiliaresInterface servicosAuxiliares) {
        this.servicosPrincipaisDoSistemaInterface = servicosPrincipaisDoSistemaInterface;
        this.servicosAuxiliares = servicosAuxiliares;
        this.esTerminal = new ESTerminal();
        this.terminalComponents = new TerminalComponents(this.esTerminal); 
    }
    
    private int menuPrincipal() {
        int escolha = -1;
        do {
            this.esTerminal.escreverTitulo("MENU PRINCIPAL");
            this.esTerminal.escreverLinhas(1);
            this.esTerminal.escreverMensagem("1. Registar novo veículo", 4);
            this.esTerminal.escreverMensagem("2. Registar clientes", 4);
            this.esTerminal.escreverMensagem("3. Registar novo aluguer", 4);
            this.esTerminal.escreverMensagem("4. Listar veículos disponíveis para aluguer", 4);
            this.esTerminal.escreverMensagem("5. Listar localizações de veículos alugados", 4);
            this.esTerminal.escreverMensagem("6. Listar veículos por estado administrativo", 4);            
            this.esTerminal.escreverMensagem("7. Consultar histórico de aluguer de um veículo", 4);
            this.esTerminal.escreverMensagem("8. Aprovar um veículo", 4);
            this.esTerminal.escreverMensagem("9. Desprovar um veículo", 4);
            this.esTerminal.escreverMensagem("0. Sair", 4);
            this.esTerminal.escreverLinhas(1);
            escolha = this.esTerminal.lerInteiro("Escolha", escolha);
            this.esTerminal.escreverLinhas(1);
        } while(escolha < 0 || escolha > 9);
        return escolha;
    }
   
    
    private void listarVeiculoPorEstadoAdministrativo() throws RemoteException {
        TipoEstadoAdministrativo tipoEstadoAdministrativo = this.terminalComponents.escolhaUm("> Escolha o tipo de estado administrativo",
                this.servicosAuxiliares.listarEstadosAdministrativos(), 4);
        
        List<Veiculo> veiculosPorEstadoAdministrativo = this.servicosPrincipaisDoSistemaInterface.listarVeiculosPorEstadoAdministrativo(tipoEstadoAdministrativo);
        PaginacaoDeLista<Veiculo> paginacaoVeiculos = new PaginacaoDeLista<>(veiculosPorEstadoAdministrativo, 1, 10);
        this.terminalComponents.mostrarLista("VEICULOS POR ESTADO ADMINISTRATIVO PARA ALUGUER", paginacaoVeiculos, 0);
        
    }
    
       
    private void aprovarVeiculo() throws RemoteException {
        try {
            this.esTerminal.escreverTitulo("APROVAR UM VEÍCULO");
            this.esTerminal.escreverLinhas(1);
            Veiculo veiculo = this.terminalComponents.escolhaUm("Selecione o veiculo", this.servicosPrincipaisDoSistemaInterface.listarVeiculosPorEstadoAdministrativo(TipoEstadoAdministrativo.NAO_APROVADO), 4);
            if(veiculo == null){
                this.esTerminal.escreverMensagem("Sem veiculos não aprovados.", 4);
                System.out.println();
                return;
            }

            this.servicosPrincipaisDoSistemaInterface.aprovarVeiculo(veiculo.getId());
            this.esTerminal.escreverMensagem("** Veículo aprovado com sucesso **", 0);
        } catch (EntidadeJaExistenteException ex) {
            this.esTerminal.escreverMensagem(String.format("** Ocorreu um erro ao aprovar o veiculo: %s **", ex.getMessage()), 0);
            System.out.println();
        }
    }
    
    private void desaprovarVeiculo() throws RemoteException {
        try {
            this.esTerminal.escreverTitulo("DESAPROVAR UM VEÍCULO");
            this.esTerminal.escreverLinhas(1);
            Veiculo veiculo = this.terminalComponents.escolhaUm("Selecione o veiculo", this.servicosPrincipaisDoSistemaInterface.listarVeiculosPorEstadoAdministrativo(TipoEstadoAdministrativo.APROVADO), 4);
            if(veiculo == null){
                this.esTerminal.escreverMensagem("Sem veiculos aprovados.", 4);
                System.out.println();
                return;
            }

            this.servicosPrincipaisDoSistemaInterface.naoAprovarVeiculo(veiculo.getId());
            this.esTerminal.escreverMensagem("** Veículo desaprovado com sucesso **", 0);
        } catch (EntidadeJaExistenteException ex) {
            this.esTerminal.escreverMensagem(String.format("** Ocorreu um erro ao desaprovar o veiculo: %s **", ex.getMessage()), 0);
            System.out.println();
        }
    }
    
    private void registarNovoVeiculo() throws RemoteException {
        try {
            this.esTerminal.escreverTitulo("REGISTAR NOVO VEÍCULO");
            this.esTerminal.escreverLinhas(1);
            this.esTerminal.escreverMensagem("Insira os dados do veículo (Matrícula, Modelo, tipo e localizacao):", 0);
            String matricula = this.esTerminal.lerString("> Matricula", 4);
            String modelo = this.esTerminal.lerString("> Modelo", 4);
            TipoVeiculo tipoDeVeiculo = this.terminalComponents.escolhaUm("> Escolha o tipo de veículo",
                    this.servicosAuxiliares.listarTiposDeVeiculos(), 4);
            System.out.println();
            String localizacaoAtual = this.esTerminal.lerString("Insira a localização atual do véiculo", 4);
            this.servicosPrincipaisDoSistemaInterface.registoNovoVeiculo(matricula, modelo, tipoDeVeiculo, localizacaoAtual);
            this.esTerminal.escreverMensagem("** Veículo registado com sucesso **", 0);
        } catch (EntidadeJaExistenteException ex) {
            this.esTerminal.escreverMensagem(String.format("** Ocorreu um erro ao registar o veiculo: %s **", ex.getMessage()), 0);
            System.out.println();
        }
    }
    
    public void registarClientes() throws RemoteException {
        try {
            this.esTerminal.escreverTitulo("REGISTAR NOVO CLIENTE");
            this.esTerminal.escreverLinhas(1);
            this.esTerminal.escreverMensagem("Insira os dados do cliente (Nome, Documento de identificação, contacto):", 0);
            String nome = this.esTerminal.lerString("> Nome", 4);
            String documentoDeIdentificacao = this.esTerminal.lerString("> Documento de identificação", 4);
            String contacto = this.esTerminal.lerString("> Contacto", 4);
            this.servicosPrincipaisDoSistemaInterface.registoCliente(nome, documentoDeIdentificacao, contacto);
            this.esTerminal.escreverMensagem("** Cliente registado com sucesso **", 0);
        } catch (EntidadeJaExistenteException ex) {
            this.esTerminal.escreverMensagem(String.format("** Ocorreu um erro ao registar o cliente: %s **", ex.getMessage()), 0);
            System.out.println();
        }
    }
    
    public void registarNovoAluguer() throws RemoteException {
        try {
            this.esTerminal.escreverTitulo("REGISTAR NOVO ALUGUER");
            this.esTerminal.escreverLinhas(1);
            this.esTerminal.escreverMensagem("Insira os dados do aluguer (Cliente, Veiculo, valor de pagamento, data de inicio, periodo de aluguer):", 0);
            Cliente cliente = this.terminalComponents.escolhaUm("Selecione o cliente solicitante", this.servicosAuxiliares.listarClientes(), 4);
            System.out.println();
            Veiculo veiculo = this.terminalComponents.escolhaUm("Selecione o veiculo", this.servicosPrincipaisDoSistemaInterface.listarVeiculosDisponiveisParaAluguer(), 4);
            if(veiculo == null) {
                this.esTerminal.escreverMensagem("Não há veículos disponíveis para aluguer...", 0);
                System.out.println();
                return;
            }
            System.out.println();
            double valorDePagamento = this.esTerminal.lerDouble("> Valor de pagamento", 4);
            Date dataDeInicio = this.esTerminal.lerData("> Data de inicio", 4);
            this.esTerminal.escreverMensagem("> Período de aluguer:", 4);
            TipoUnidadeDeTempo unidadeDeTempo = this.terminalComponents.escolhaUm("- Unidade de tempo", this.servicosAuxiliares.listarUnidadesDeTempo(), 8);
            int tempo = this.esTerminal.lerInteiro("- Tempo", 8);
            switch(unidadeDeTempo) {
                case DIA -> tempo = tempo * 24;
                case MES -> tempo = tempo * 24 * 30;
                case ANO -> tempo = tempo * 24 * 30 * 12;
            }
            this.servicosPrincipaisDoSistemaInterface.registoNovoAluguer(veiculo.getId(), cliente.getId(), (float) valorDePagamento, dataDeInicio, tempo);
            this.esTerminal.escreverMensagem("** Alguer registado com sucesso **", 0);
        } catch (EntidadeNaoExistenteException | VeiculoNaoDisponivelException ex) {
            this.esTerminal.escreverMensagem(String.format("** Ocorreu um erro ao registar o aluguer: %s **", ex.getMessage()), 0);
            System.out.println();
        }
    }
    
    public void listarVeiculosDisponiveisParaAluguer() throws RemoteException {
        List<Veiculo> veiculosDisponiveis = this.servicosPrincipaisDoSistemaInterface.listarVeiculosDisponiveisParaAluguer();
        PaginacaoDeLista<Veiculo> paginacaoVeiculos = new PaginacaoDeLista<>(veiculosDisponiveis, 1, 10);
        this.terminalComponents.mostrarLista("VEICULOS DISPONÍVEIS PARA ALUGUER", paginacaoVeiculos, 0);
    }
    
    public void listarLocalizacaoDeVeiculosAlugados() throws RemoteException {
        List<String> localizacaoDeVeiculosAlugados = this.servicosPrincipaisDoSistemaInterface.listarLocalizacoesVeiculosAlugados();
        PaginacaoDeLista<String> paginacaoLocalizacoes = new PaginacaoDeLista<>(localizacaoDeVeiculosAlugados, 1, 10);
        this.terminalComponents.mostrarLista("LOCALIZAÇÃO DE VEICULOS ALUGADOS", paginacaoLocalizacoes, 0);
    }
    
    public void consultarHistoricoDeAluguelDeVeiculo() throws RemoteException {
        try {
            this.esTerminal.escreverTitulo("CONSULTAR HISTÓRICO DE ALUGUER DE VEICULO");
            this.esTerminal.escreverLinhas(1);
            Veiculo veiculo = this.terminalComponents.escolhaUm("Selecione o veiculo", this.servicosAuxiliares.listarVeiculos(), 0);
            if(veiculo == null) {
                this.esTerminal.escreverMensagem("Sem veículos disponíveis", 0);
                return;
            }
            List<Aluguer> listaDeAluguer = this.servicosPrincipaisDoSistemaInterface.consultarHistoricoAluguerUmVeiculoEspecifico(veiculo.getId());
            PaginacaoDeLista<Aluguer> paginacaoAluguer = new PaginacaoDeLista<>(listaDeAluguer, 1, 10);
            this.esTerminal.escreverLinhas(1);
            this.terminalComponents.mostrarListaSemTitulo("Histórico de aluguer do veículo " + veiculo, paginacaoAluguer, 0);
        } catch (EntidadeNaoExistenteException ex) {
            this.esTerminal.escreverMensagem(String.format("** Ocorreu um erro ao consultar histórioco de aluguer: %s **", ex.getMessage()), 0);
            System.out.println();
        }
    }
    
    

    @Override
    public void iniciar() {
        this.esTerminal.escreverLinhas(1);
        int escolha = 0;
        do {
            try {
                escolha = this.menuPrincipal();
                switch(escolha) {
                    case 1 -> registarNovoVeiculo();
                    case 2 -> registarClientes();
                    case 3 -> registarNovoAluguer();
                    case 4 -> listarVeiculosDisponiveisParaAluguer();
                    case 5 -> listarLocalizacaoDeVeiculosAlugados();
                    case 6 -> listarVeiculoPorEstadoAdministrativo();
                    case 7 -> consultarHistoricoDeAluguelDeVeiculo();
                    case 8 -> aprovarVeiculo();
                    case 9 -> desaprovarVeiculo();
                }
            } catch (RemoteException ex) {
                Logger.getLogger(ClienteAdministradorTerminalApp.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while(escolha > 0);
    }
    
}
