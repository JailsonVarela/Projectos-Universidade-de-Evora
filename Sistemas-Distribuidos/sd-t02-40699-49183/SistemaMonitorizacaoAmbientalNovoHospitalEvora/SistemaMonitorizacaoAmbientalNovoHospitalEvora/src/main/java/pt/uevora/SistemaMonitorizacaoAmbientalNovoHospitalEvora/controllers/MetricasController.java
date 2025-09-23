package pt.uevora.SistemaMonitorizacaoAmbientalNovoHospitalEvora.controllers;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pt.uevora.SistemaMonitorizacaoAmbientalNovoHospitalEvora.services.MetricasServices;
import pt.uevora.SistemaMonitorizacaoAmbientalNovoHospitalEvora.services.dto.MetricasDTO;
import pt.uevora.SistemaMonitorizacaoAmbientalNovoHospitalEvora.services.dto.MetricasMediaDTO;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("metricas")
public class MetricasController {
    private  final MetricasServices metricasServices;

    public MetricasController(MetricasServices metricasServices){
        this.metricasServices = metricasServices;
    }

    @DeleteMapping("{id}")
    void  delete(@RequestBody MetricasDTO metricasDTO, @PathVariable Long id){
        metricasDTO.setId(id);
        this.metricasServices.deleteMetrica(metricasDTO);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    void register(@RequestBody MetricasDTO metricasDTO){
        this.metricasServices.createMetricas(metricasDTO);
    }

    @GetMapping("sala/{salaId}")
    List<MetricasDTO> getBySalaId(@PathVariable Long id,
                                  @RequestParam( name = "data_inicio", required = false)
                                  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicio,
                                  @RequestParam( name = "data_termino", required = false)
                                  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataTermino){
        return this.metricasServices.getMetricasBySalaId(id, dataInicio, dataTermino);
    }

    @GetMapping("servico/{servicoId}")
    List<MetricasDTO> getByServicoId(@PathVariable Long id,
                                     @RequestParam( name = "data_inicio", required = false)
                                     @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicio,
                                     @RequestParam( name = "data_termino", required = false)
                                     @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataTermino){
        return this.metricasServices.getMetricasByServicoId(id,dataInicio,dataTermino);
    }

    @GetMapping("edificio/{edificioId}")
    List<MetricasDTO> getByEdificioId(@PathVariable Long id,
                                      @RequestParam( name = "data_inicio", required = false)
                                      @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicio,
                                      @RequestParam( name = "data_termino", required = false)
                                      @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataTermino){
        return this.metricasServices.getMetricasByEdificioId(id, dataInicio, dataTermino);
    }

    @GetMapping("dispositivo/{dispositivoId}")
    List<MetricasDTO> getByDispositivoIoTId(@PathVariable Long id,
                                            @RequestParam( name = "data_inicio", required = false)
                                            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicio,
                                            @RequestParam( name = "data_termino", required = false)
                                            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataTermino){
        return this.metricasServices.getMetricasByDispositiovoIoTId(id, dataInicio, dataTermino);
    }

    @GetMapping("{id}")
    MetricasDTO getById(@PathVariable Long id){
        return this.metricasServices.getMetricasDetailsById(id);
    }

    @GetMapping("")
    List<MetricasDTO> all(@RequestParam( name = "data_inicio", required = false)
                          @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicio,
                          @RequestParam( name = "data_termino", required = false)
                          @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataTermino){
        return this.metricasServices.getAllMetricas(dataInicio, dataTermino);
    }

    @GetMapping("media/sala/{salaId}")
    MetricasMediaDTO getMediaOfAllBySala(@PathVariable Long salaId,
                                         @RequestParam(name = "data_inicio", required = false)
                                         @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicio,
                                         @RequestParam(name = "data_termino", required = false)
                                         @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataTermino) {
        return this.metricasServices.getMediaMetricasBySalaId(salaId, dataInicio, dataTermino);
    }

    @GetMapping("media/edificio/{edificioId}")
    MetricasMediaDTO getMediOfAllByEdificio(@PathVariable Long edificioId,
                                            @RequestParam(name = "data_inicio", required = false)
                                            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicio,
                                            @RequestParam(name = "data_termino", required = false)
                                            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataTermino) {
        return this.metricasServices.getMediaMetricasByEdificioId(edificioId, dataInicio, dataTermino);
    }

    @GetMapping("media/edificio/{edificioId}/piso/{pisoId}")
    MetricasMediaDTO getMediaOfAllByPiso(@PathVariable Long edificioId, @PathVariable Integer pisoId,
                                         @RequestParam(name = "data_inicio", required = false)
                                         @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicio,
                                         @RequestParam(name = "data_termino", required = false)
                                         @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataTermino) {
        return this.metricasServices.getMediaMetricasByPiso(edificioId, pisoId, dataInicio, dataTermino);
    }

    @GetMapping("media/servico/{servicoId}")
    MetricasMediaDTO getMediOfAllByServico(@PathVariable Long servicoId,
                                           @RequestParam(name = "data_inicio", required = false)
                                           @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicio,
                                           @RequestParam(name = "data_termino", required = false)
                                           @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataTermino) {
        return this.metricasServices.getMediaMetricasByServicoId(servicoId, dataInicio, dataTermino);
    }

    @GetMapping("media")
    MetricasMediaDTO getMediaOfAll(@RequestParam(name = "data_inicio", required = false)
                                   @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicio,
                                   @RequestParam(name = "data_termino", required = false)
                                   @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataTermino) {
        return this.metricasServices.getMediaAllMetricas(dataInicio, dataTermino);
    }
}
