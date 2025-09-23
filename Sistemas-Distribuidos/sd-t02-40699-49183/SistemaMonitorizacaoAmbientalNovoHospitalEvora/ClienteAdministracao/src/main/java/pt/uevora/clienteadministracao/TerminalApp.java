package pt.uevora.clienteadministracao;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import pt.uevora.clienteadministracao.models.*;
import pt.uevora.clienteadministracao.repositories.*;
import pt.uevora.clienteadministracao.util.ComponentesTerminal;
import pt.uevora.clienteadministracao.util.ESTerminal;
import pt.uevora.clienteadministracao.util.RetrofitServices;

import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jailsonlopes
 */
public class TerminalApp {

    private final RetrofitServices retrofitServices;
    private final EdificiosRepository edificiosRepository;
    private final SalasRepository salasRepository;
    private final ServicosRepository servicosRepository;
    private final DispositivosIoTRepository dispositivosIoTRepository;
    private final MetricasRepository metricasRepository;
    private final ESTerminal esTerminal;
    private final ComponentesTerminal terminalComponents;

    public TerminalApp() {
        this.retrofitServices = new RetrofitServices();
        this.edificiosRepository = this.retrofitServices.construirServico(EdificiosRepository.class);
        this.salasRepository = this.retrofitServices.construirServico(SalasRepository.class);
        this.servicosRepository = this.retrofitServices.construirServico(ServicosRepository.class);
        this.dispositivosIoTRepository = this.retrofitServices.construirServico(DispositivosIoTRepository.class);
        this.metricasRepository = this.retrofitServices.construirServico(MetricasRepository.class);
        this.esTerminal = new ESTerminal();
        this.terminalComponents = new ComponentesTerminal(this.esTerminal);
    }

    private int menuPrincipal() {
        int escolha = -1;
        do {
            this.esTerminal.escreverTitulo("MENU PRINCIPAL");
            this.esTerminal.escreverLinhas(1);
            this.esTerminal.escreverMensagem("1. Gestão de dispisitivos IoT", 4);
            this.esTerminal.escreverMensagem("2. Consulta de métricas", 4);
            this.esTerminal.escreverMensagem("0. Sair", 4);
            this.esTerminal.escreverLinhas(1);
            escolha = this.esTerminal.lerInteiro("Escolha", escolha);
            this.esTerminal.escreverLinhas(1);
        } while(escolha < 0 || escolha > 2);
        return escolha;
    }

    private int menuGestaoDeDispositivoIoT() {
        int escolha = -1;
        do {
            this.esTerminal.escreverTitulo("MENU GESTÃO DE DISPOSITIVO IOT");
            this.esTerminal.escreverLinhas(1);
            this.esTerminal.escreverMensagem("1. Registar dispositivo IoT", 4);
            this.esTerminal.escreverMensagem("2. Editar dispositivo IoT", 4);
            this.esTerminal.escreverMensagem("3. Listar dispositivos IoT", 4);
            this.esTerminal.escreverMensagem("4. Eliminar dispositivo IoT", 4);
            this.esTerminal.escreverMensagem("0. Sair", 4);
            this.esTerminal.escreverLinhas(1);
            escolha = this.esTerminal.lerInteiro("Escolha", escolha);
            this.esTerminal.escreverLinhas(1);
        } while(escolha < 0 || escolha > 4);
        return escolha;
    }

    public int menuConsultaDeMetricas() {
        int escolha = -1;
        do {
            this.esTerminal.escreverTitulo("MENU CONSULTA DE MÉTRICAS");
            this.esTerminal.escreverLinhas(1);
            this.esTerminal.escreverMensagem("1. Listar todas metricas", 4);
            this.esTerminal.escreverMensagem("2. Listar métricas por sala", 4);
            this.esTerminal.escreverMensagem("3. Listar métricas por edifício", 4);
            this.esTerminal.escreverMensagem("4. Listar métricas por serviço", 4);
            this.esTerminal.escreverMensagem("5. Listar métricas por piso de edifício", 4);
            this.esTerminal.escreverMensagem("6. Mostrar média de todas metricas", 4);
            this.esTerminal.escreverMensagem("7. Mostrar média das métricas por sala", 4);
            this.esTerminal.escreverMensagem("8. Mostrar média das métricas por edifício", 4);
            this.esTerminal.escreverMensagem("9. Mostrar média das métricas por serviço", 4);
            this.esTerminal.escreverMensagem("10. Mostar média das métricas por piso de edifício", 4);
            this.esTerminal.escreverMensagem("0. Sair", 4);
            this.esTerminal.escreverLinhas(1);
            escolha = this.esTerminal.lerInteiro("Escolha", escolha);
            this.esTerminal.escreverLinhas(1);
        } while(escolha < 0 || escolha > 10);
        return escolha;
    }

    private <T> void throwExceptionIfNull(T value, String msg) throws Exception {
        if(value == null)
            throw new Exception(msg + " A operação será cancelada.");
    }

    public DispositivoIoT formularioDispositivoIoT(DispositivoIoT dispositivoIoT) throws Exception {
        Edificio edificio = this.terminalComponents.escolhaUm("Selecione o edifício",
                PaginacaoDeLista.of(this.retrofitServices.extractDatas(this.edificiosRepository.getAllEdificios())), 4);
        this.throwExceptionIfNull(edificio, "Nenhum edifício foi encontrado.");
        Sala sala = this.terminalComponents.escolhaUm("Selecione a sala",
                PaginacaoDeLista.of(this.retrofitServices.extractDatas(this.salasRepository.getAllSalasByEdificio(edificio.getId()))), 4);
        this.throwExceptionIfNull(sala, "Nenhuma sala foi encontrada no edifício \"" + edificio.getNome() + "\".");
        Servico servico = this.terminalComponents.escolhaUm("Selecione o serviço",
                PaginacaoDeLista.of(this.retrofitServices.extractDatas(this.servicosRepository.getAllServicosBySala(sala.getId()))), 4);
        this.throwExceptionIfNull(servico, "Nenhum serviço foi encontrado na sala \"" + sala.getNome() + "\".");
        dispositivoIoT.setLocalizacao(sala);
        dispositivoIoT.setServico(servico);
        return dispositivoIoT;
    }

    public void registarDispositivoIoT() {
        try {
            this.esTerminal.escreverTitulo("REGISTAR DISPOSITIVO IOT");
            this.esTerminal.escreverLinhas(1);
            this.esTerminal.escreverMensagem("Insira os dados do dispositivo IoT (Edifício, Sala, Serviço):", 0);
            DispositivoIoT dispositivoIoT = formularioDispositivoIoT(new DispositivoIoT());
            this.retrofitServices.extractDatas(this.dispositivosIoTRepository.createDispositivoIoT(this.retrofitServices.createRequestBody(dispositivoIoT)));
            this.esTerminal.escreverMensagem("** Dispositivo IoT registado com sucesso **", 0);
        } catch (Exception ex) {
            this.esTerminal.escreverMensagem(String.format("** Não foi possível registar o dispositivo IoT: %s **", ex.getMessage()), 0);
        }
    }

    public void editarDispositivoIoT() {
        try {
            this.esTerminal.escreverTitulo("EDITAR DISPOSITIVO IOT");
            this.esTerminal.escreverLinhas(1);
            DispositivoIoT dispositivoIoT = this.terminalComponents.escolhaUm("Selecione o dispositivo IoT a editar",
                    PaginacaoDeLista.of(this.retrofitServices.extractDatas(this.dispositivosIoTRepository.getAllDispositivoIoT())), 4);
            this.esTerminal.escreverLinhas(1);
            this.esTerminal.escreverMensagem("Insira os novos dados do dispositivo IoT (Edifício, Sala, Serviço):", 0);
            dispositivoIoT = formularioDispositivoIoT(dispositivoIoT);
            this.retrofitServices.extractDatas(this.dispositivosIoTRepository.updateDispositivoIoT(this.retrofitServices.createRequestBody(dispositivoIoT), dispositivoIoT.getId()));
            this.esTerminal.escreverMensagem("** Dispositivo IoT editado com sucesso **", 0);
        } catch (Exception ex) {
            this.esTerminal.escreverMensagem(String.format("** Não foi possível registar o dispositivo IoT: %s **", ex.getMessage()), 0);
        }
    }

    public void listarDispoitivosIoT() {
        try {
            this.terminalComponents.mostrarLista("LISTA DE DISPOSITIVOS IOT",
                    PaginacaoDeLista.of(this.retrofitServices.extractDatas(this.dispositivosIoTRepository.getAllDispositivoIoT())), 0);
        }catch (Exception ex) {
            this.esTerminal.escreverMensagem(String.format("** Não foi possível listar o dispositivos IoT: %s **", ex.getMessage()), 0);
        }
    }

    public void deleteDiposistivoIoT() {
        try {
            this.esTerminal.escreverTitulo("ELIMINAR DISPOSITIVOS IOT");
            DispositivoIoT dispositivoIoT = this.terminalComponents.escolhaUm("Selecione o dispositivo IoT a eliminar",
                    PaginacaoDeLista.of(this.retrofitServices.extractDatas(this.dispositivosIoTRepository.getAllDispositivoIoT())), 4);
            this.retrofitServices.extractDatas(this.dispositivosIoTRepository.deleteDispositivoIoT(dispositivoIoT.getId()));
            this.esTerminal.escreverMensagem(String.format("** Dispositivo IoT com ID %d eliminado com sucesso **", dispositivoIoT.getId()), 0);
        } catch (Exception ex) {
            this.esTerminal.escreverMensagem(String.format("** Não foi possível eliminar o dispositivo IoT: %s **", ex.getMessage()), 0);
        }
    }

    public void listarMetricas(FiltroMetricas filtroMetricas, String nome, Long[] id) {
        try {
            Escolha escolha = this.terminalComponents.escolhaUm("Definir intervalos de data?", List.of(Escolha.values()), 0);
            if(escolha == Escolha.NAO) {
                switch (filtroMetricas) {
                    case EDIFICIO -> this.terminalComponents.mostrarLista(String.format("LISTA DE MÉTRICAS NO EFICIO %s.", nome),
                            PaginacaoDeLista.of(this.retrofitServices.extractDatas(this.metricasRepository.getMetricasPorEdificio(id[0]))), 0);
                    case SALA -> this.terminalComponents.mostrarLista(String.format("LISTA DE MÉTRICAS NO EFICIO %s.", nome),
                            PaginacaoDeLista.of(this.retrofitServices.extractDatas(this.metricasRepository.getMetricasPorSala(id[0]))), 0);
                    case SERVICO -> this.terminalComponents.mostrarLista(String.format("LISTA DE MÉTRICAS NO EFICIO %s.", nome),
                            PaginacaoDeLista.of(this.retrofitServices.extractDatas(this.metricasRepository.getMetricasPorServico(id[0]))), 0);
                    case PISO -> this.terminalComponents.mostrarLista(String.format("LISTA DE MÉTRICAS NO EFICIO %s.", nome),
                            PaginacaoDeLista.of(this.retrofitServices.extractDatas(this.metricasRepository.getMetricasPorPisoEdificio(id[0], id[1]))), 0);
                    case TODOS -> this.terminalComponents.mostrarLista("LISTA DE TODAS MÉTRICAS",
                            PaginacaoDeLista.of(this.retrofitServices.extractDatas(this.metricasRepository.getAllMetricas())), 0);
                }
            } else {
                this.esTerminal.escreverMensagem("Insira as datas de início e termino do intervalo:", 0);
                LocalDateTime dataInicio = this.esTerminal.lerDataHoraLocal("> Data de início", 4);
                LocalDateTime dataTermino = this.esTerminal.lerDataHoraLocal("> Data de término", 4);
                switch (filtroMetricas) {
                    case EDIFICIO -> this.terminalComponents.mostrarLista(String.format("LISTA DE MÉTRICAS NO EFICIO %s.", nome),
                            PaginacaoDeLista.of(this.retrofitServices.extractDatas(this.metricasRepository.getMetricasPorEdificio(id[0], dataInicio, dataTermino))), 0);
                    case SALA -> this.terminalComponents.mostrarLista(String.format("LISTA DE MÉTRICAS NO EFICIO %s.", nome),
                            PaginacaoDeLista.of(this.retrofitServices.extractDatas(this.metricasRepository.getMetricasPorSala(id[0], dataInicio, dataTermino))), 0);
                    case SERVICO -> this.terminalComponents.mostrarLista(String.format("LISTA DE MÉTRICAS NO EFICIO %s.", nome),
                            PaginacaoDeLista.of(this.retrofitServices.extractDatas(this.metricasRepository.getMetricasPorServico(id[0], dataInicio, dataTermino))), 0);
                    case PISO -> this.terminalComponents.mostrarLista(String.format("LISTA DE MÉTRICAS NO EFICIO %s.", nome),
                            PaginacaoDeLista.of(this.retrofitServices.extractDatas(this.metricasRepository.getMetricasPorPisoEdificio(id[0], id[1], dataInicio, dataTermino))), 0);
                    case TODOS -> this.terminalComponents.mostrarLista("LISTA DE TODAS MÉTRICAS",
                            PaginacaoDeLista.of(this.retrofitServices.extractDatas(this.metricasRepository.getAllMetricas())), 0);
                }
            }
        } catch (Exception ex) {
            this.esTerminal.escreverMensagem(String.format("** Não foi possível listar as métricas: %s **", ex.getMessage()), 0);
        }
    }

    public void mediaMetricas(FiltroMetricas filtroMetricas) {
        int i = 1;
        try {
            Escolha escolha = this.terminalComponents.escolhaUm("Definir intervalos de data?", List.of(Escolha.values()), 0);
            if(escolha == Escolha.NAO) {
                switch (filtroMetricas) {
                    case EDIFICIO -> {
                        List<Edificio> edificios = this.retrofitServices.extractDatas(this.edificiosRepository.getAllEdificios());
                        for(Edificio edificio : edificios) {
                            this.esTerminal.escreverMensagem(String.format("%d.Edifício %s: %s.", i++, edificio.getNome(),
                                            this.retrofitServices.extractDatas(this.metricasRepository.getMediaMetricasPorEdificio(edificio.getId())).toString()),
                                    4);
                        }
                    }
                    case SALA -> {
                        List<Sala> salas = this.retrofitServices.extractDatas(this.salasRepository.getAllSalas());
                        for(Sala sala : salas) {
                            this.esTerminal.escreverMensagem(String.format("%d. Sala %s: %s", i++, sala.getNome(),
                                            this.retrofitServices.extractDatas(this.metricasRepository.getMediaMetricasPorSala(sala.getId())).toString()),
                                    4);
                        }
                    }
                    case SERVICO -> {
                        List<Servico> servicos = this.retrofitServices.extractDatas(this.servicosRepository.getAllServicos());
                        for(Servico servico : servicos) {
                            this.esTerminal.escreverMensagem(String.format("%d. Serviço %s: %s.", i++, servico.getNome(),
                                    this.retrofitServices.extractDatas(this.metricasRepository.getMediaMetricasPorServico(servico.getId()))), 4);
                        }
                    }
                    case PISO -> {
                        List<Edificio> edificios = this.retrofitServices.extractDatas(this.edificiosRepository.getAllEdificios());
                        for(Edificio edificio : edificios) {
                            for(long piso=1L; piso <= edificio.getNumeroDePisos(); ++piso) {
                                this.esTerminal.escreverMensagem(String.format("%d. Piso %d do edifício %s: %s.", i++, piso, edificio.getNome(),
                                        this.retrofitServices.extractDatas(this.metricasRepository.getMediaMetricasPorPisoEdificio(edificio.getId(), piso))), 4);
                            }
                        }
                    }
                    case TODOS -> {
                        this.esTerminal.escreverMensagem(String.format("Media de todas métricas: %s",
                                this.retrofitServices.extractDatas(this.metricasRepository.getMediaMetricas())),
                                4);
                    }
                }
            } else {
                this.esTerminal.escreverMensagem("Insira as datas de início e termino do intervalo:", 0);
                LocalDateTime dataInicio = this.esTerminal.lerDataHoraLocal("> Data de início", 4);
                LocalDateTime dataTermino = this.esTerminal.lerDataHoraLocal("> Data de término", 4);
                switch (filtroMetricas) {
                    case EDIFICIO -> {
                        List<Edificio> edificios = this.retrofitServices.extractDatas(this.edificiosRepository.getAllEdificios());
                        for(Edificio edificio : edificios) {
                            this.esTerminal.escreverMensagem(String.format("%d.Edifício %s: %s.", i++, edificio.getNome(),
                                            this.retrofitServices.extractDatas(this.metricasRepository.getMediaMetricasPorEdificio(edificio.getId(), dataInicio, dataTermino)).toString()),
                                    4);
                        }
                    }
                    case SALA -> {
                        List<Sala> salas = this.retrofitServices.extractDatas(this.salasRepository.getAllSalas());
                        for(Sala sala : salas) {
                            this.esTerminal.escreverMensagem(String.format("%d. Sala %s: %s", i++, sala.getNome(),
                                            this.retrofitServices.extractDatas(this.metricasRepository.getMediaMetricasPorSala(sala.getId(), dataInicio, dataTermino)).toString()),
                                    4);
                        }
                    }
                    case SERVICO -> {
                        List<Servico> servicos = this.retrofitServices.extractDatas(this.servicosRepository.getAllServicos());
                        for(Servico servico : servicos) {
                            this.esTerminal.escreverMensagem(String.format("%d. Serviço %s: %s.", i++, servico.getNome(),
                                    this.retrofitServices.extractDatas(this.metricasRepository.getMediaMetricasPorServico(servico.getId(), dataInicio, dataTermino))), 4);
                        }
                    }
                    case PISO -> {
                        List<Edificio> edificios = this.retrofitServices.extractDatas(this.edificiosRepository.getAllEdificios());
                        for(Edificio edificio : edificios) {
                            for(long piso=1L; piso <= edificio.getNumeroDePisos(); ++piso) {
                                this.esTerminal.escreverMensagem(String.format("%d. Piso %d do edifício %s: %s.", i++, piso, edificio.getNome(),
                                        this.retrofitServices.extractDatas(this.metricasRepository.getMediaMetricasPorPisoEdificio(edificio.getId(), piso, dataInicio, dataTermino))), 4);
                            }
                        }
                    }
                    case TODOS -> {
                        this.esTerminal.escreverMensagem(String.format("Media de todas métricas: %s",
                                        this.retrofitServices.extractDatas(this.metricasRepository.getMediaMetricas(dataInicio, dataTermino))),
                                4);
                    }
                }
            }
            this.esTerminal.enterParaContinuar(0);
        } catch (Exception ex) {
            this.esTerminal.escreverMensagem(String.format("** Não foi possível listar as métricas: %s **", ex.getMessage()), 0);
        }
    }

    public void consultarMetricasPor(FiltroMetricas filtroMetricas) {
        try {
            this.esTerminal.escreverTitulo("LISTAR MÉTRICAS");
            this.esTerminal.escreverLinhas(1);
            switch (filtroMetricas) {
                case EDIFICIO -> {
                    Edificio escolha = this.terminalComponents.escolhaUm("Selecione o edificio",
                            PaginacaoDeLista.of(this.retrofitServices.extractDatas(this.edificiosRepository.getAllEdificios())), 4);
                    throwExceptionIfNull(escolha, "Nenhum edifício encontrado.");
                    listarMetricas(filtroMetricas, escolha.getNome(), new Long[] { escolha.getId() });
                }
                case SALA -> {
                    Sala escolha = this.terminalComponents.escolhaUm("Selecione a sala",
                            PaginacaoDeLista.of(this.retrofitServices.extractDatas(this.salasRepository.getAllSalas())), 4);
                    throwExceptionIfNull(escolha, "Nenhuma sala encontrada.");
                    listarMetricas(filtroMetricas, escolha.getNome(), new Long[] { escolha.getId() });
                }
                case SERVICO -> {
                    Servico escolha = this.terminalComponents.escolhaUm("Selecione o serviço",
                            PaginacaoDeLista.of(this.retrofitServices.extractDatas(this.servicosRepository.getAllServicos())), 4);
                    throwExceptionIfNull(escolha, "Nenhum serviço encontrado.");
                    listarMetricas(filtroMetricas, escolha.getNome(), new Long[] { escolha.getId() });
                }
                case PISO -> {
                    Edificio escolha = this.terminalComponents.escolhaUm("Selecione o edificio",
                            PaginacaoDeLista.of(this.retrofitServices.extractDatas(this.edificiosRepository.getAllEdificios())), 4);
                    throwExceptionIfNull(escolha, "Nenhum edifício encontrado.");
                    int piso = this.esTerminal.lerInteiro(String.format("Insira o número do piso [1 - %d]", escolha.getNumeroDePisos()), 4);
                    listarMetricas(filtroMetricas, escolha.getNome(), new Long[] { escolha.getId(), (long) piso });
                }
                case TODOS -> {
                    listarMetricas(filtroMetricas, null, null);
                }
            }
        } catch (Exception ex) {
            esTerminal.escreverMensagem(ex.getMessage(), 0);
        }
    }

    public void consultarMediaMetricas(FiltroMetricas filtroMetricas) {
        this.esTerminal.escreverTitulo("LISTAR MÉDIAS MÉRICAS");
        this.esTerminal.escreverLinhas(1);
        this.mediaMetricas(filtroMetricas);
    }

    public void iniciar() {
        this.esTerminal.escreverLinhas(1);
        int escolha = 0;
        do {
            try {
                escolha = this.menuPrincipal();
                switch(escolha) {
                    case 1 -> {
                        int menuGestaoEscolha = 0;
                        do {
                            menuGestaoEscolha = menuGestaoDeDispositivoIoT();
                            switch (menuGestaoEscolha) {
                                case 1 -> registarDispositivoIoT();
                                case 2 -> editarDispositivoIoT();
                                case 3 -> listarDispoitivosIoT();
                                case 4 -> deleteDiposistivoIoT();
                            }
                        }while (menuGestaoEscolha > 0);
                    }
                    case 2 -> {
                        int menuConsultaEscolha = menuConsultaDeMetricas();
                        switch (menuConsultaEscolha) {
                            case 1 -> consultarMetricasPor(FiltroMetricas.TODOS);
                            case 2 -> consultarMetricasPor(FiltroMetricas.EDIFICIO);
                            case 3 -> consultarMetricasPor(FiltroMetricas.SALA);
                            case 4 -> consultarMetricasPor(FiltroMetricas.SERVICO);
                            case 5 -> consultarMetricasPor(FiltroMetricas.PISO);
                            case 6 -> consultarMediaMetricas(FiltroMetricas.TODOS);
                            case 7 -> consultarMediaMetricas(FiltroMetricas.EDIFICIO);
                            case 8 -> consultarMediaMetricas(FiltroMetricas.SALA);
                            case 9 -> consultarMediaMetricas(FiltroMetricas.SERVICO);
                            case 10 -> consultarMediaMetricas(FiltroMetricas.PISO);
                        }
                    }
                }
            } catch (Exception ex) {
                Logger.getLogger(TerminalApp.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while(escolha > 0);
    }

    public static void main(String[] args) {
        TerminalApp app = new TerminalApp();
        app.iniciar();
    }

}
