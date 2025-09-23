package pt.uevora.SistemaMonitorizacaoAmbientalNovoHospitalEvora.configs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pt.uevora.SistemaMonitorizacaoAmbientalNovoHospitalEvora.utils.ExceptionHandler;
import pt.uevora.SistemaMonitorizacaoAmbientalNovoHospitalEvora.services.DispositivoIoTServices;
import pt.uevora.SistemaMonitorizacaoAmbientalNovoHospitalEvora.services.EdificioServices;
import pt.uevora.SistemaMonitorizacaoAmbientalNovoHospitalEvora.services.SalaServices;
import pt.uevora.SistemaMonitorizacaoAmbientalNovoHospitalEvora.services.ServicoServices;
import pt.uevora.SistemaMonitorizacaoAmbientalNovoHospitalEvora.services.dto.DispositivoIoTDTO;
import pt.uevora.SistemaMonitorizacaoAmbientalNovoHospitalEvora.services.dto.EdificioDTO;
import pt.uevora.SistemaMonitorizacaoAmbientalNovoHospitalEvora.services.dto.SalaDTO;
import pt.uevora.SistemaMonitorizacaoAmbientalNovoHospitalEvora.services.dto.ServicoDTO;

/**
 * Esta classe permite a inicialização de dados na base de dados usando os serviços
 * criados no pacotes de serviços.
 */
@Configuration
public class DataLoaderUsingServices {

    private static final Logger log = LoggerFactory.getLogger(DataLoaderUsingServices.class);

    /**
     * Este método cria um comnando para a inicialização de dados na base de dados em todas tabelas
     * @param edificioServices - Serviços para manipulação de Edifícios
     * @param salaServices - Serviços para manipulação de salas
     * @param servicoServices - Serviços para manipulação de serrviços
     * @param dispositivoIoTServices - Serviços para manipulação de dispositivos IOT
     * @return O comando que faz a inicialização dos dados
     */
    @Bean
    CommandLineRunner initDatabase(EdificioServices edificioServices, SalaServices salaServices,
                                   ServicoServices servicoServices, DispositivoIoTServices dispositivoIoTServices) {
        ExceptionHandler handler = new ExceptionHandler(log);

        return args -> {
            //TODO: Sugestão de alterar edificio para "Ala", "Bloco", "Area"
            log.info("Carregando dados de edificios...");
            handler.handleCreate(() -> edificioServices.createEdificio(new EdificioDTO(1L, "Edificio 1")));
            handler.handleCreate(() -> edificioServices.createEdificio(new EdificioDTO(2L, "Edificio 2")));
            handler.handleCreate(() -> edificioServices.createEdificio(new EdificioDTO(3L, "Zona de cirurgia")));
            handler.handleCreate(() -> edificioServices.createEdificio(new EdificioDTO(4L, "Area de espera 1 - Atrio")));
            handler.handleCreate(() -> edificioServices.createEdificio(new EdificioDTO(5L, "Edificio 3")));
            handler.handleCreate(() -> edificioServices.createEdificio(new EdificioDTO(6L, "Zona de medicina interna")));
            handler.handleCreate(() -> edificioServices.createEdificio(new EdificioDTO(7L, "Area de espera 2 - Pátio Hikari")));
            handler.handleCreate(() -> edificioServices.createEdificio(new EdificioDTO(8L, "Edificio 4")));
            handler.handleCreate(() -> edificioServices.createEdificio(new EdificioDTO(9L, "Edificio 5")));
            log.info("Os edificios já estão carregados na BD");
            log.info("--");

            log.info("Carregando dados de salas");
            // Edificio 1
            EdificioDTO detalhesEdificio1 = edificioServices.getEdificioDetails(1L);
            handler.handleCreate(() -> salaServices.createSala(new SalaDTO( "Sala de quimioterapia", 2, detalhesEdificio1)));

            // Edificio 2
            EdificioDTO detalhesEdificio2 = edificioServices.getEdificioDetails(2L);
            handler.handleCreate(() -> salaServices.createSala(new SalaDTO("Dematologia", 2, detalhesEdificio2)));
            handler.handleCreate(() -> salaServices.createSala(new SalaDTO("Cirurgia Oral e Maxilofacial", 2, detalhesEdificio2)));
            handler.handleCreate(() -> salaServices.createSala(new SalaDTO("Otorrinolarigologia e Cirurgia de Cabeça e Pescoço", 2, detalhesEdificio2)));
            handler.handleCreate(() -> salaServices.createSala(new SalaDTO("Oftalmologia", 2, detalhesEdificio2)));

            // Zona de cirurgia
            EdificioDTO edificioZonaDeCirurgia = edificioServices.getEdificioDetails(3L);
            handler.handleCreate(() -> salaServices.createSala(new SalaDTO("Cirurgia A",2, edificioZonaDeCirurgia)));
            handler.handleCreate(() -> salaServices.createSala(new SalaDTO("Cirurgia B",2, edificioZonaDeCirurgia)));
            handler.handleCreate(() -> salaServices.createSala(new SalaDTO("Cirurgia C",2, edificioZonaDeCirurgia)));
            handler.handleCreate(() -> salaServices.createSala(new SalaDTO("Cirurgia D",2, edificioZonaDeCirurgia)));
            handler.handleCreate(() -> salaServices.createSala(new SalaDTO("Centro de Controle Pré-Operatório",2, edificioZonaDeCirurgia)));

            // Edificio 3
            EdificioDTO edificio3 = edificioServices.getEdificioDetails(5L);
            handler.handleCreate(() -> salaServices.createSala(new SalaDTO("Obstetrícia e Ginecologia",2, edificio3)));
            handler.handleCreate(() -> salaServices.createSala(new SalaDTO("Pediatria",2, edificio3)));

            // Zona de medicina interna
            EdificioDTO edificioZonaDeMedicina = edificioServices.getEdificioDetails(6L);
            handler.handleCreate(() -> salaServices.createSala(new SalaDTO("Medicina Interna A",2, edificioZonaDeMedicina)));
            handler.handleCreate(() -> salaServices.createSala(new SalaDTO("Medicina Interna B",2, edificioZonaDeMedicina)));
            handler.handleCreate(() -> salaServices.createSala(new SalaDTO("Medicina Interna C",2, edificioZonaDeMedicina)));

            // Edificio 4
            EdificioDTO edificio4 = edificioServices.getEdificioDetails(8L);
            handler.handleCreate(() -> salaServices.createSala(new SalaDTO("Psiquiatria",2, edificio4)));
            handler.handleCreate(() -> salaServices.createSala(new SalaDTO("Divisão de Reabilitação",2, edificio4)));
            handler.handleCreate(() -> salaServices.createSala(new SalaDTO("Divisão de laboratório clinico",2, edificio4)));
            handler.handleCreate(() -> salaServices.createSala(new SalaDTO("Recepção da Divisão de Reabilitação",2, edificio4)));
            handler.handleCreate(() -> salaServices.createSala(new SalaDTO("Toilet para coleta de urina",2, edificio4)));
            handler.handleCreate(() -> salaServices.createSala(new SalaDTO("Area de coleta de sangue central",2, edificio4)));

            // Edificio 5
            EdificioDTO edificio5 = edificioServices.getEdificioDetails(9L);
            handler.handleCreate(() -> salaServices.createSala(new SalaDTO("Divisão de laboratório clinico",2, edificio5)));
            handler.handleCreate(() -> salaServices.createSala(new SalaDTO("Loja",2, edificio5)));
            handler.handleCreate(() -> salaServices.createSala(new SalaDTO("Restaurante",2, edificio5)));
            log.info("Salas preloaded sucessfully");

            log.info("---");

            log.info("Carregando dados de serviços");
            SalaDTO salaQuimioterapia = salaServices.getSalaDetailsByNome("Sala de quimioterapia");
            handler.handleCreate(() -> servicoServices.createServico(new ServicoDTO("Serviço Recepção", "Recepção Sala de quimioterapia", salaQuimioterapia)));

            SalaDTO salaDematologia = salaServices.getSalaDetailsByNome("Dematologia");
            handler.handleCreate(() -> servicoServices.createServico(new ServicoDTO("Serviço Dermatologia", "Dermatologia", salaDematologia)));

            SalaDTO salaCururgiaOral = salaServices.getSalaDetailsByNome("Cirurgia Oral e Maxilofacial");
            handler.handleCreate(() -> servicoServices.createServico(new ServicoDTO("Serviço Cirurgia Oral e Maxilofacial", "Cirurgia Oral e Maxilofacial", salaCururgiaOral)));

            SalaDTO salaOtorino = salaServices.getSalaDetailsByNome("Otorrinolarigologia e Cirurgia de Cabeça e Pescoço");
            handler.handleCreate(() -> servicoServices.createServico(new ServicoDTO("Serviço Otorrinolarigologia e Cirurgia de Cabeça e Pescoço", "Otorrinolarigologia e Cirurgia de Cabeça e Pescoço", salaOtorino)));

            SalaDTO salaOftamologia = salaServices.getSalaDetailsByNome("Oftalmologia");
            handler.handleCreate(() -> servicoServices.createServico(new ServicoDTO("Serviço Oftalmologia", "Oftalmologia", salaOftamologia)));

            SalaDTO salaCirurgiaA = salaServices.getSalaDetailsByNome("Cirurgia A");
            handler.handleCreate(() -> servicoServices.createServico(new ServicoDTO("Serviço Anestesiologia e Terapia de Dor", "Cirurgia A", salaCirurgiaA)));
            handler.handleCreate(() -> servicoServices.createServico(new ServicoDTO("Serviço Cirurgia Cardiovascular", "Cirurgia A", salaCirurgiaA)));
            log.info("Dados de serviços carregados");

            log.info("---");

            log.info("Carregando dados sobre dispositivo IOT");

            //Salas do Edificio 1
            handler.handleCreate(() -> dispositivoIoTServices.createDispositivoIOT(new DispositivoIoTDTO(salaQuimioterapia)));


            // Salas do Edificio 2
            handler.handleCreate(() -> dispositivoIoTServices.createDispositivoIOT(new DispositivoIoTDTO(salaOtorino)));
            handler.handleCreate(() -> dispositivoIoTServices.createDispositivoIOT(new DispositivoIoTDTO(salaDematologia)));
            handler.handleCreate(() -> dispositivoIoTServices.createDispositivoIOT(new DispositivoIoTDTO(salaCururgiaOral)));
            handler.handleCreate(() -> dispositivoIoTServices.createDispositivoIOT(new DispositivoIoTDTO(salaOftamologia)));
        };
    }

}
