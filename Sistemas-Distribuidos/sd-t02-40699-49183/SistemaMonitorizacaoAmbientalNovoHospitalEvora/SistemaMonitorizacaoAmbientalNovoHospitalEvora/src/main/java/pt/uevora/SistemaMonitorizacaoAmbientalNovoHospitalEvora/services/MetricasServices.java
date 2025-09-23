package pt.uevora.SistemaMonitorizacaoAmbientalNovoHospitalEvora.services;

import org.springframework.stereotype.Service;
import pt.uevora.SistemaMonitorizacaoAmbientalNovoHospitalEvora.exceptions.EntidadeJaExistenteException;
import pt.uevora.SistemaMonitorizacaoAmbientalNovoHospitalEvora.exceptions.EntidadeNaoExistenteException;
import pt.uevora.SistemaMonitorizacaoAmbientalNovoHospitalEvora.models.*;
import pt.uevora.SistemaMonitorizacaoAmbientalNovoHospitalEvora.repositories.*;
import pt.uevora.SistemaMonitorizacaoAmbientalNovoHospitalEvora.services.dto.MetricasDTO;
import pt.uevora.SistemaMonitorizacaoAmbientalNovoHospitalEvora.services.dto.MetricasMediaDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MetricasServices {
    private final MetricasRepository metricasRepository;
    private final DispositivosIoTRepository dispositivosIoTRepository;
    private final DispositivoIoTServices dispositivoIoTServices;
    private final SalaRepository salaRepository;
    private final EdificioRepositrory edificioRepositrory;

    public MetricasServices(MetricasRepository mensagemRepository,
                            DispositivosIoTRepository dispositivosIoTRepository, DispositivoIoTServices dispositivoIoTServices,
                            SalaRepository salaRepository,
                            EdificioRepositrory edificioRepositrory
                            ) {
        this.metricasRepository = mensagemRepository;
        this.dispositivosIoTRepository = dispositivosIoTRepository;
        this.dispositivoIoTServices = dispositivoIoTServices;
        this.salaRepository = salaRepository;
        this.edificioRepositrory = edificioRepositrory;
    }

    public void createMetricas(MetricasDTO mensagemDTO) {
        if(mensagemDTO.getId() != null) {
            Optional<Metricas> mensagem = this.metricasRepository.findById(mensagemDTO.getId());
            ajudanteCreateMetricas(mensagemDTO, mensagem, "ID", mensagemDTO.getId().toString());
        } else {
            ajudanteCreateMetricas(mensagemDTO, Optional.empty(), null, null);
        }
    }

    public void deleteMetrica(MetricasDTO metricasDTO){
        if (metricasDTO.getId() == null) {
            throw new EntidadeNaoExistenteException("metricaDTO", "ID", metricasDTO.getId());
        }

        Optional<Metricas> metrica = this.metricasRepository.findById(metricasDTO.getId());
        if (metrica.isPresent()){
            this.metricasRepository.delete(metrica.get());
        }else
            throw new EntidadeNaoExistenteException("Metrica", "ID",metricasDTO.getId());
    }

    private void ajudanteCreateMetricas(MetricasDTO metricasDTO, Optional<Metricas> metricas, String propriedade, String valor) throws EntidadeJaExistenteException {
        if(metricas.isPresent())
            throw new EntidadeJaExistenteException("Mensagem", propriedade, valor);
        DispositivoIoT dispositivoIoT = this.dispositivosIoTRepository
                .findById(metricasDTO.getDispositivoIoTId())
                .orElseThrow(() -> new EntidadeNaoExistenteException("DispositivoIoT", "ID", metricasDTO.getDispositivoIoTId()));
        this.metricasRepository.save(new Metricas(metricasDTO.getId(), dispositivoIoT,
                metricasDTO.getTemperatura(), metricasDTO.getHumidade(), metricasDTO.getTimestamp()));
    }

    public  MetricasDTO getMetricasDetailsById(Long metricasId){
        Metricas metricas = this.metricasRepository.findById(metricasId)
                .orElseThrow(() -> new EntidadeNaoExistenteException("Metrica", "ID", metricasId));
        Long dispositivoId = metricas.getDispositivoIoT().getId();
        return new MetricasDTO(metricas.getId(), dispositivoIoTServices.getDipositivoIoTDetailsById(dispositivoId).getId(),metricas.getTemperatura(),metricas.getHumidade(),metricas.getTimestamp());
    }

    public List<MetricasDTO> getAllMetricas(LocalDateTime dataInicio, LocalDateTime dataTermino) {

        if (dataTermino == null)
            dataTermino = LocalDateTime.now();
        if (dataInicio == null)
            dataInicio = dataTermino.minusHours(24);

        if (dataTermino.isBefore(dataInicio))
            dataTermino = dataInicio.plusHours(24) ;

        return this.metricasRepository.findAllByTimestampBetween(dataInicio, dataTermino).stream()
                .map(mensagem -> new MetricasDTO(mensagem.getId(),
                        mensagem.getDispositivoIoT().getId(),
                        mensagem.getTemperatura(), mensagem.getHumidade(), mensagem.getTimestamp())
        ).collect(Collectors.toList());
    }

    public List<MetricasDTO> getMetricasByServicoId(Long servicoId, LocalDateTime dataInicio, LocalDateTime dataTermino) {

        if (dataTermino == null)
            dataTermino = LocalDateTime.now();
        if (dataInicio == null)
            dataInicio = dataTermino.minusHours(24);

        if (dataTermino.isBefore(dataInicio))
            dataTermino = LocalDateTime.now();


        return this.metricasRepository.findAllByServicoIdAndTimestampBetween(servicoId, dataInicio, dataTermino).stream()
                .map(metricas -> new MetricasDTO(metricas.getId(),
                                metricas.getTemperatura(),
                                metricas.getHumidade(),
                                metricas.getTimestamp()))
                .collect(Collectors.toList());
    }

    public List<MetricasDTO> getMetricasByEdificioId(Long edificioId, LocalDateTime dataInicio, LocalDateTime dataTermino) {

        if (dataTermino == null)
            dataTermino = LocalDateTime.now();
        if (dataInicio == null)
            dataInicio = dataTermino.minusHours(24);

        if (dataTermino.isBefore(dataInicio))
            dataTermino = LocalDateTime.now();

        Edificio edificio = this.edificioRepositrory
                .findById(edificioId)
                .orElseThrow(() -> new EntidadeNaoExistenteException("Edificio", "ID", edificioId.toString()));

        return this.metricasRepository.findAllByTimestampBetween(dataInicio,dataTermino).stream()
                .filter((Metricas metricas) -> metricas.getDispositivoIoT().getLocalizacao().getEdificio().getId().equals(edificioId))
                .map(metricas -> new MetricasDTO(metricas.getId(),
                        metricas.getDispositivoIoT().getId(),
                        metricas.getTemperatura(), metricas.getHumidade(), metricas.getTimestamp())
                )
                .collect(Collectors.toList());
    }
    public List<MetricasDTO> getMetricasByEdificioIdAndPiso(Long edificioId, int piso, LocalDateTime dataInicio, LocalDateTime dataTermino) {

        if (dataTermino == null)
            dataTermino = LocalDateTime.now();
        if (dataInicio == null)
            dataInicio = dataTermino.minusHours(24);

        if (dataTermino.isBefore(dataInicio))
            dataTermino = LocalDateTime.now();

        return this.metricasRepository
                .findAllByEdificioIdAndPisoAndTimestampBetween(edificioId,piso,dataInicio,dataTermino).stream()
                .filter((Metricas metricas) -> metricas.getDispositivoIoT().getLocalizacao().getEdificio().getId().equals(edificioId))
                .map(metricas -> new MetricasDTO(metricas.getId(),
                        metricas.getDispositivoIoT().getId(),
                        metricas.getTemperatura(), metricas.getHumidade(), metricas.getTimestamp())
                )
                .collect(Collectors.toList());
    }

    public List<MetricasDTO> getMetricasBySalaId(Long salaId, LocalDateTime dataInicio, LocalDateTime dataTermino) {

        if (dataTermino == null)
            dataTermino = LocalDateTime.now();
        if (dataInicio == null)
            dataInicio = dataTermino.minusHours(24);

        if (dataTermino.isBefore(dataInicio))
            dataTermino = LocalDateTime.now();

        Sala sala = this.salaRepository
                .findById(salaId)
                .orElseThrow(() -> new EntidadeNaoExistenteException("Sala", "ID", salaId.toString()));

        return this.metricasRepository.findAllByTimestampBetween(dataInicio,dataTermino).stream()
                .filter((Metricas metricas) -> metricas.getDispositivoIoT().getLocalizacao().getId().equals(salaId))
                .map(metricas -> new MetricasDTO(metricas.getId(),
                        metricas.getDispositivoIoT().getId(),
                        metricas.getTemperatura(), metricas.getHumidade(), metricas.getTimestamp())
                )
                .collect(Collectors.toList());
    }

    public List<MetricasDTO> getMetricasByDispositiovoIoTId(Long dispositivoIoTId, LocalDateTime dataInicio, LocalDateTime dataTermino) {

        if (dataTermino == null)
            dataTermino = LocalDateTime.now();
        if (dataInicio == null)
            dataInicio = dataTermino.minusHours(24);

        if (dataTermino.isBefore(dataInicio))
            dataTermino = LocalDateTime.now();

        DispositivoIoT dispositivoIoT = this.dispositivosIoTRepository
            .findById(dispositivoIoTId)
            .orElseThrow(() -> new EntidadeNaoExistenteException("DispositivoIoT", "ID", dispositivoIoTId.toString()));

        return this.metricasRepository.findAllByDispositivoIoTAndTimestampBetween(dispositivoIoT, dataInicio,dataTermino).stream()
                .map(metricas -> new MetricasDTO(metricas.getId(),
                        metricas.getDispositivoIoT().getId(),
                        metricas.getTemperatura(), metricas.getHumidade(), metricas.getTimestamp())
                ).collect(Collectors.toList());
    }

    MetricasMediaDTO createMedia(List<MetricasDTO> metricas) {
        double totalTemperatura = 0.0;
        double totalHumidade = 0.0;
        for(MetricasDTO metrica : metricas) {
            totalTemperatura += metrica.getTemperatura();
            totalHumidade += metrica.getHumidade();
        }
        return new MetricasMediaDTO(!metricas.isEmpty() ? totalTemperatura/metricas.size() : 0,
                !metricas.isEmpty() ? totalHumidade/metricas.size() : 0);
    }

    public MetricasMediaDTO getMediaAllMetricas(LocalDateTime dataInicio, LocalDateTime dataTermino) {
        if(dataTermino == null)
            dataTermino = LocalDateTime.now();
        if(dataInicio == null)
            dataInicio = dataTermino.minusHours(24);
        return createMedia(this.getAllMetricas(dataInicio, dataTermino));
    }

    public MetricasMediaDTO getMediaMetricasBySalaId(Long salaId, LocalDateTime dataInicio, LocalDateTime dataTermino) {
        if(dataTermino == null)
            dataTermino = LocalDateTime.now();
        if(dataInicio == null)
            dataInicio = dataTermino.minusHours(24);
        return createMedia(this.getMetricasBySalaId(salaId, dataInicio, dataTermino));
    }

    public MetricasMediaDTO getMediaMetricasByServicoId(Long servicoId, LocalDateTime dataInicio, LocalDateTime dataTermino) {
        if(dataTermino == null)
            dataTermino = LocalDateTime.now();
        if(dataInicio == null)
            dataInicio = dataTermino.minusHours(24);
        return createMedia(this.getMetricasByServicoId(servicoId, dataInicio, dataTermino));
    }

    public MetricasMediaDTO getMediaMetricasByPiso(Long edificioId, Integer piso, LocalDateTime dataInicio, LocalDateTime dataTermino) {
        if(dataTermino == null)
            dataTermino = LocalDateTime.now();
        if(dataInicio == null)
            dataInicio = dataTermino.minusHours(24);
        return createMedia(this.getMetricasByEdificioIdAndPiso(edificioId, piso, dataInicio, dataTermino));
    }

    public MetricasMediaDTO getMediaMetricasByEdificioId(Long edificioId, LocalDateTime dataInicio, LocalDateTime dataTermino) {
        if(dataTermino == null)
            dataTermino = LocalDateTime.now();
        if(dataInicio == null)
            dataInicio = dataTermino.minusHours(24);
        return createMedia(this.getMetricasByEdificioId(edificioId, dataInicio, dataTermino));
    }
}
